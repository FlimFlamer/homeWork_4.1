package com.scypro.recipeapp.Controllers;

import com.scypro.recipeapp.Service.IngredientService;
import com.scypro.recipeapp.model.Ingredient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingridient")
@Tag(name = "Ингридиенты")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(summary = "Сохранить ингридиент")
    public ResponseEntity<Ingredient> save(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.save(ingredient));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск ингридиента")
    public ResponseEntity<Ingredient> getById(@PathVariable long id) {
        return ResponseEntity.of(ingredientService.getById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение ингридиента")
    public ResponseEntity<Ingredient> update(@PathVariable long id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.update(id, ingredient));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингридиента")
    public ResponseEntity<Ingredient> delete(@PathVariable long id) {
        return ResponseEntity.ok(ingredientService.delete(id));
    }

    @GetMapping
    @Operation(summary = "Все ингридиенты")
    public ResponseEntity<Map<Long, Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientService.getAll());
    }
}
