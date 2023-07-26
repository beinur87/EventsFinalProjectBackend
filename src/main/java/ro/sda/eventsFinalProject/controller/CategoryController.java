package ro.sda.eventsFinalProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ro.sda.eventsFinalProject.model.Category;
import ro.sda.eventsFinalProject.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Service
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity createCategory(@RequestBody Category category) {
        if (category.getId() != null) {
            return new ResponseEntity("Category id must be empty!", HttpStatus.BAD_REQUEST);
        }
        try {
            Category savedCategory = categoryService.saveCategory(category);
            return new ResponseEntity(savedCategory, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity readCategory(@PathVariable Integer id) {
        try {
            Category readCategory = categoryService.readCategory(id);
            return new ResponseEntity(readCategory, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/categories")
    public ResponseEntity readAllCategories(){
        List<Category> categories= categoryService.readAllCategories();
        return new ResponseEntity(categories,HttpStatus.OK);
    }

    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody Category categoryToUpdate){
        if(!id.equals(categoryToUpdate.getId())){

        }
    }
}
