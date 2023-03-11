package com.scypro.recipeapp.Service;

import com.scypro.recipeapp.model.Recipe;

import java.util.Optional;

public interface RecipeService {
    Recipe save(Recipe recipe);

    Optional<Recipe> getById(long id);
}
