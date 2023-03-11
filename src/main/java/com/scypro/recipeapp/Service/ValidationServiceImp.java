package com.scypro.recipeapp.Service;

import com.scypro.recipeapp.model.Ingredient;
import com.scypro.recipeapp.model.Recipe;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImp implements ValidationService{
    @Override
    public boolean validate(Recipe recipe) {
        return recipe != null
                && recipe.getName() != null
                && recipe.getSteps() != null
                && recipe.getIngredients() != null
                && !recipe.getIngredients().isEmpty()
                && ! recipe.getSteps().isEmpty();
    }

    @Override
    public boolean validate(Ingredient ingredient) {
        return ingredient != null
                && ingredient.getName() != null;
    }
}
