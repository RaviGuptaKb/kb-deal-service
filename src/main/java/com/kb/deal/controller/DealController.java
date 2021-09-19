package com.kb.deal.controller;

import com.kb.deal.model.ProductDeal;
import com.kb.deal.request.DealRequest;
import com.kb.deal.service.DealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
/**
 * @author Ravi Gupta
 */
@RestController
@CrossOrigin
@RequestMapping("/deal")
@Api(value="Product Deal API's")
public class DealController {

    @Autowired
    private DealService dealService;

    @ApiOperation(value = "View a list of available product deals",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved banner list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(path = "/deals")
    public ResponseEntity<?> getDeal(@RequestParam(required = false) Long id) {
        if (id != null) {
            ProductDeal deal = dealService.getDeal(id);
            if (deal != null)
                return new ResponseEntity<>(deal, HttpStatus.OK);
            else
                return new ResponseEntity<>("Deal not found", HttpStatus.NOT_FOUND);
        } else {
            List<ProductDeal> deals = dealService.getDeals();
            return new ResponseEntity<>(deals, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Add a product deal")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addDeal(@RequestBody DealRequest dealRequest) {

        ProductDeal productDeal = dealService.addDeal(dealRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productDeal.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update a product banner")
    @PutMapping({"/updateDeal/{id}"})
    public ResponseEntity<?> updateDeal(@PathVariable(name = "id") Long id, @RequestBody DealRequest dealRequest) {
        ProductDeal deal = dealService.updateDeal(id, dealRequest);
        if (deal != null)
            return new ResponseEntity<>(deal, HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid Deal Id", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Delete product banner")
    @DeleteMapping({"/deleteDeal"})
    public ResponseEntity<?> deleteDeal(@RequestParam(required = false) Long id) {
        try {
            dealService.deleteDeal(id);
            return new ResponseEntity<>("Deals Deleted", HttpStatus.OK);
        } catch (EmptyResultDataAccessException exception) {
            return new ResponseEntity<>("Invalid Deal Id", HttpStatus.NOT_FOUND);
        }
    }
}
