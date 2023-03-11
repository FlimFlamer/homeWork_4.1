package com.scypro.recipeapp.Service;

import com.scypro.recipeapp.model.Ingredient;
import com.scypro.recipeapp.model.Recipe;

public interface ValidationService {

    public boolean validate(Recipe recipe);
    public boolean validate(Ingredient ingredient);
}
