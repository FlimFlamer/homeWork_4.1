package com.scypro.recipeapp.Controllers;

import com.scypro.recipeapp.Service.RecipeService;
import com.scypro.recipeapp.model.Recipe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(summary = "Сохранить рецепт")
    public ResponseEntity<Recipe> save(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.save(recipe));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти рецепт")
    public ResponseEntity<Recipe> getById(@PathVariable long id){
        return ResponseEntity.of(recipeService.getById(id));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Изменение рецепта")
    public ResponseEntity<Recipe> update(@PathVariable long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.update(id, recipe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить рецепт")
    public ResponseEntity<Recipe> delete(@PathVariable long id) {
        return ResponseEntity.ok(recipeService.delete(id));
    }

    @GetMapping
    @Operation(summary = "Все рецепты")
    public ResponseEntity<Map<Long, Recipe>> getAll() {
        return ResponseEntity.ok(recipeService.getAll());
    }
}
