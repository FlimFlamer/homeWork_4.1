package com.scypro.recipeapp.Controllers;

import com.scypro.recipeapp.Service.RecipeService;
import com.scypro.recipeapp.model.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> save(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.save(recipe));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable long id){
        return ResponseEntity.of(recipeService.getById(id));
    }
}
