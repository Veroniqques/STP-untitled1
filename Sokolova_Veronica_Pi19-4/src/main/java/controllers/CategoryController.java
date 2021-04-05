package controllers;


import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sevice.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    public void createCategory(@RequestBody Category category){
        categoryService.create(category);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAll(){
        final List<Category> categoryList = categoryService.findAll();

        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(categoryList, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Optional<Category>> findById(@PathVariable(name = "id") Long id ) {
        final Optional<Category> category = categoryService.findById(id);

        if (category.isPresent()) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        if (categoryService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

}
