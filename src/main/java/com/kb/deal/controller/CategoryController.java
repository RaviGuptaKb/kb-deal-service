package com.kb.deal.controller;

import com.kb.deal.model.Category;
import com.kb.deal.request.CategoryRequest;
import com.kb.deal.service.CategoryService;
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
@RequestMapping("/category")
@Api(value="Product Category API's")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "View a list of available product categories",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved category list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping()
    public ResponseEntity<?> getCategories(@RequestParam(required = false) Long id) {
        if (id != null) {
            Category category = categoryService.getcategory(id);
            if (category != null)
                return new ResponseEntity<>(category, HttpStatus.OK);
            else
                return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        } else {
            List<Category> categories = categoryService.getCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Add a product category")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest categoryRequest) {

        Category category = categoryService.addCategory(categoryRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Update a product banner")
    @PutMapping({"/updateCategory/{id}"})
    public ResponseEntity<?> updateCategory(@PathVariable(name = "id") Long id, @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.updateCategory(id, categoryRequest);
        if (category != null)
            return new ResponseEntity<>(category, HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid Category Id", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Delete product banner")
    @DeleteMapping({"/deleteCategory"})
    public ResponseEntity<?> deleteCategory(@RequestParam(required = false) Long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>("Category Deleted", HttpStatus.OK);
        } catch (EmptyResultDataAccessException exception) {
            return new ResponseEntity<>("Invalid Category Id", HttpStatus.NOT_FOUND);
        }
    }
}
