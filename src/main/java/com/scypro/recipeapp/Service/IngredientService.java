package com.scypro.recipeapp.Service;

import com.scypro.recipeapp.model.Ingredient;

import java.util.Optional;

public interface IngredientService {
    Ingredient save(Ingredient ingredient);

    Optional<Ingredient> getById(long id);
}
