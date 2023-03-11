package com.scypro.recipeapp.Controllers;

import com.scypro.recipeapp.Service.RecipeService;
import com.scypro.recipeapp.model.Ingredient;
import com.scypro.recipeapp.model.Recipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@PathVariable long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.update(id, recipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> delete(@PathVariable long id) {
        return ResponseEntity.ok(recipeService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Map<Long, Recipe>> getAll() {
        return ResponseEntity.ok(recipeService.getAll());
    }
}
