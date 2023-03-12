package com.scypro.recipeapp.Service;

import com.scypro.recipeapp.model.Ingredient;
import com.scypro.recipeapp.model.Recipe;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImp implements ValidationService{
    @Override
    public boolean validate(Recipe recipe) {
        return ObjectUtils.isNotEmpty(recipe)
                && ObjectUtils.isNotEmpty(recipe.getName())
                && ObjectUtils.isNotEmpty(recipe.getSteps())
                && ObjectUtils.isNotEmpty(recipe.getIngredients())
                && ObjectUtils.isNotEmpty(recipe.getIngredients())
                && ObjectUtils.isNotEmpty(recipe.getSteps());
    }

    @Override
    public boolean validate(Ingredient ingredient) {
        return ObjectUtils.isNotEmpty(ingredient)
                && ObjectUtils.isNotEmpty(ingredient.getName());
    }
}
