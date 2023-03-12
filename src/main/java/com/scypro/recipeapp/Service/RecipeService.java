package com.scypro.recipeapp.Service;

import com.scypro.recipeapp.model.Recipe;

import java.util.Map;
import java.util.Optional;

public interface RecipeService {
    Recipe save(Recipe recipe);

    Optional<Recipe> getById(long id);

    Recipe update(Long id, Recipe recipe);

    Recipe delete(Long id);

    Map<Long, Recipe> getAll();
}
