package com.kb.deal.controller;

import com.kb.deal.model.Product;
import com.kb.deal.model.TrendingDeal;
import com.kb.deal.request.TrendingDealRequest;
import com.kb.deal.service.TrendingDealService;
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
@RequestMapping("/trending")
@Api(value="Trending Deal Products API's")
public class TrendingDealController {
    @Autowired
    private TrendingDealService trendingDealService;

    @ApiOperation(value = "View a list of available trending deals",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved trending deals"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(path = "/deal")
    public ResponseEntity<?> getTrendingDeal(@RequestParam(required = false) Long parentId, @RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long brandModelId) {
        List<TrendingDeal> trendingDeal = trendingDealService.getTrendingDeal(parentId, categoryId, brandModelId);
        return new ResponseEntity<>(trendingDeal, HttpStatus.OK);
    }

    @ApiOperation(value = "View a list of available trending brands",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved trending brands"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(path = "/brands")
    public ResponseEntity<?> getTrendingBrands() {
        List<String> brands = trendingDealService.getTrendingBrands();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @ApiOperation(value = "View a list of available trending products",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved trending products"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(path = "/products/{categoryId}")
    public ResponseEntity<?> getTrendingProducts(@PathVariable(name = "categoryId") Long categoryId) {
        List<Product> products = trendingDealService.getTrendingProducts(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a trending deal")
    @PostMapping(path = "/deal/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addTrendingDeal(@RequestBody TrendingDealRequest trendingRequest) {

        TrendingDeal trendingDeal = trendingDealService.addTrendingDeal(trendingRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(trendingDeal.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update trending deal")
    @PutMapping({"/deal/{id}"})
    public ResponseEntity<?> updateTrendingDeal(@PathVariable(name = "id") Long id, @RequestBody TrendingDealRequest trendingRequest) {
        TrendingDeal deal = trendingDealService.updateTrendingDeal(id, trendingRequest);
        if (deal != null)
            return new ResponseEntity<>(deal, HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid trending deal Id", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Delete trending deal")
    @DeleteMapping({"/deleteDeal/{categoryId}"})
    public ResponseEntity<?> deleteTrendingDeal(@PathVariable(name = "categoryId") Long categoryId, @RequestParam(required = false) Long id) {
        try {
            Integer recordDeleted = trendingDealService.deleteTrendingDeal(categoryId, id);
            if (recordDeleted == 0) {
                return new ResponseEntity<>("No deals to delete", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Trending Deals Deleted", HttpStatus.OK);
        } catch (EmptyResultDataAccessException exception) {
            return new ResponseEntity<>("Invalid Trending Deal Id", HttpStatus.NOT_FOUND);
        }
    }
}
