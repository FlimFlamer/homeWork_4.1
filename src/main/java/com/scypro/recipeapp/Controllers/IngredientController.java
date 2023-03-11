package com.scypro.recipeapp.Controllers;

import com.scypro.recipeapp.Service.IngredientService;
import com.scypro.recipeapp.model.Ingredient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingridient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> save(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.save(ingredient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable long id) {
        return ResponseEntity.of(ingredientService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable long id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.update(id, ingredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete(@PathVariable long id) {
        return ResponseEntity.ok(ingredientService.delete(id));
    }

    @GetMapping
    public ResponseEntity<Map<Long, Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientService.getAll());
    }
}
