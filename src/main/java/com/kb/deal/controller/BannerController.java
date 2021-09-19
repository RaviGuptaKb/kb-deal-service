package com.kb.deal.controller;

import com.kb.deal.model.ProductBanner;
import com.kb.deal.request.ProductBannerRequest;
import com.kb.deal.service.BannerService;
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
@RequestMapping("/productBanner")
@Api(value="Product Banner API's")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "View a list of available product banners",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved banner list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(path = "/banners")
    public ResponseEntity<?> getProductBanner(@RequestParam(required = false) Long id) {
        if (id != null) {
            ProductBanner banner = bannerService.getProductBanner(id);
            if (banner != null)
                return new ResponseEntity<>(banner, HttpStatus.OK);
            else
                return new ResponseEntity<>("Product Banner not found", HttpStatus.NOT_FOUND);
        } else {
            List<ProductBanner> bannerList = bannerService.getProductBannerList();
            return new ResponseEntity<>(bannerList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Add a product banner")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addProductBanner(@RequestBody ProductBannerRequest productBannerRequest) {

        ProductBanner banner = bannerService.addProductBanner(productBannerRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(banner.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update product banner")
    @PutMapping({"/updateBanner/{id}"})
    public ResponseEntity<?> updateProductBanner(@PathVariable(name = "id") Long id, @RequestParam(required = false) String productCategory,
                                                 @RequestParam(required = false) String discountDetail, @RequestParam(required = false) Long imageGalleryId,
                                                 @RequestParam(required = false) String message) {
        ProductBanner productBanner = bannerService.updateProductBanner(id, productCategory, discountDetail, imageGalleryId, message);
        if (productBanner != null)
            return new ResponseEntity<>(productBanner, HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid Banner Id", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Delete product banner")
    @DeleteMapping({"/deleteBanner"})
    public ResponseEntity<?> deleteProductBanner(@RequestParam(required = false) Long id) {
        try {
            bannerService.deleteProductBanner(id);
            return new ResponseEntity<>("Banners deleted", HttpStatus.OK);
        } catch (EmptyResultDataAccessException exception) {
            return new ResponseEntity<>("Invalid Banner Id", HttpStatus.NOT_FOUND);
        }
    }
}
