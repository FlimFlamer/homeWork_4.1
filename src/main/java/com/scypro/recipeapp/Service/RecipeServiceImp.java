package com.scypro.recipeapp.Service;

import com.scypro.recipeapp.Exeption.ValidationExeption;
import com.scypro.recipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImp implements RecipeService{

    private static long idCounter = 1;
    private final Map<Long, Recipe> recipeMap = new HashMap<>();
    private final ValidationService validationService;

    public RecipeServiceImp(ValidationService validationService) {
        this.validationService = validationService;
    }
    @Override
    public Recipe save(Recipe recipe) {
        if (!validationService.validate(recipe)){
            throw new ValidationExeption(recipe.toString());
        }
        return recipeMap.put(idCounter++,recipe);
    }

    @Override
    public Optional<Recipe> getById(long id) {
        return Optional.ofNullable(recipeMap.get(id));
    }

    @Override
    public Recipe update(Long id, Recipe recipe) {
        if (!validationService.validate(recipe)){
            throw new ValidationExeption(recipe.toString());
        }
        return recipeMap.replace(id, recipe);
    }

    @Override
    public Recipe delete(Long id) {
        return recipeMap.remove(id);
    }

    @Override
    public Map<Long, Recipe> getAll() {
        return recipeMap;
    }
}
