package com.kb.deal.controller;

import com.kb.deal.model.Offer;
import com.kb.deal.model.ProductBanner;
import com.kb.deal.request.OfferRequest;
import com.kb.deal.service.OfferService;
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
import java.sql.Timestamp;
import java.util.List;
/**
 * @author Ravi Gupta
 */
@RestController
@CrossOrigin
@RequestMapping("/offer")
@Api(value="Product Offer API's")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @ApiOperation(value = "View a list of available offers",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved offers"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(path = "/offers")
    public ResponseEntity<?> getOffer(@RequestParam(required = false) Long id) {
        if (id != null) {
            Offer offer = offerService.getOffer(id);
            if (offer != null)
                return new ResponseEntity<>(offer, HttpStatus.OK);
            else
                return new ResponseEntity<>("Offer not found", HttpStatus.NOT_FOUND);
        } else {
            List<Offer> offerList = offerService.getOfferList();
            return new ResponseEntity<>(offerList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Add an offer")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addOffer(@RequestBody OfferRequest offerRequest) {

        Offer offer = offerService.addOffer(offerRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(offer.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update an offer")
    @PutMapping({"/updateOffer/{id}"})
    public ResponseEntity<?> updateOffer(@PathVariable(name = "id") Long id, @RequestParam(required = false) Integer bannerLength,
                                         @RequestParam(required = false) String occasion, @RequestParam(required = false) String bankOffer,
                                         @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        Offer offer = offerService.updateOffer(id, bannerLength, occasion, bankOffer, startDate, endDate);
        if (offer != null)
            return new ResponseEntity<>(offer, HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid Offer Id", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Delete offers")
    @DeleteMapping({"/deleteOffer"})
    public ResponseEntity<?> deleteOffer(@RequestParam(required = false) Long id) {
        try {
            offerService.deleteOffer(id);
            return new ResponseEntity<>("Offers Deleted", HttpStatus.OK);
        } catch (EmptyResultDataAccessException exception) {
            return new ResponseEntity<>("Invalid Offer Id", HttpStatus.NOT_FOUND);
        }
    }
}
