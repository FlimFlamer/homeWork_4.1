package com.scypro.recipeapp.Service;

import com.scypro.recipeapp.Exeption.ValidationExeption;
import com.scypro.recipeapp.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientServiceImp implements IngredientService {

    private static long idCounter = 1;
    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private final ValidationService validationService;

    public IngredientServiceImp(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        if (!validationService.validate(ingredient)){
            throw new ValidationExeption(ingredient.toString());
        }
        return ingredientMap.put(idCounter++,ingredient);
    }

    @Override
    public Optional<Ingredient> getById(long id) {
        return Optional.ofNullable(ingredientMap.get(id));
    }
}
