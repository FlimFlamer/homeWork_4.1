package com.scypro.recipeapp.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scypro.recipeapp.Exeption.ValidationExeption;
import com.scypro.recipeapp.model.Ingredient;
import com.scypro.recipeapp.model.Recipe;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeServiceImp implements RecipeService{

    private static long idCounter = 1;
    private Map<Long, Recipe> recipeMap = new HashMap<>();
    private final ValidationService validationService;
    private final FileService fileService;

    @Value("${path.to.recipes.file}")
    private String recipesFilePath;

    @Value("${name.of.recipes.file}")
    private String recipesFileName;

    @Value("${name.of.recipes.txt.file}")
    private String recipesTxtFileName;

    private Path recipePath;

    @Override
    public Recipe save(Recipe recipe) {
        if (!validationService.validate(recipe)){
            throw new ValidationExeption(recipe.toString());
        }
        recipeMap.put(idCounter++,recipe);
        fileService.saveMapToFile(recipeMap, recipePath);

        return recipe;
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
        recipeMap.replace(id, recipe);
        fileService.saveMapToFile(recipeMap, recipePath);
        return recipe;
    }

    @Override
    public Recipe delete(Long id) {
        Recipe recipe = recipeMap.remove(id);
        fileService.saveMapToFile(recipeMap, recipePath);
        return recipe;
    }

    @Override
    public Map<Long, Recipe> getAll() {
        return recipeMap;
    }

    @Override
    public File readFile() {
        return recipePath.toFile();
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        fileService.uploadFile(file, recipePath);
        recipeMap = fileService.readMapFromFile(recipePath, new TypeReference<HashMap<Long, Recipe>>() {});
    }

    @Override
    public File prepareRecipesTxt() throws IOException {
        return fileService
                .saveToText(recipesToString(), Path.of(recipesFilePath, recipesTxtFileName))
                .toFile();
    }


    @PostConstruct
    private void init(){
        recipePath = Path.of(recipesFilePath, recipesFileName);
        recipeMap = fileService.readMapFromFile(recipePath, new TypeReference<HashMap<Long, Recipe>>() {});
    }

    private String recipesToString() {
        StringBuilder sb = new StringBuilder();
        String listEl = " * ";

        for (Recipe recipe :  recipeMap.values()) {
            sb.append("\n").append(recipe.toString()).append("\n");

            sb.append("\nИнгридиенты");
            for (Ingredient ingredient : recipe.getIngredients()) {
                sb.append(listEl).append(ingredient.toString()).append("\n");
            }

            sb.append("\nИнструкция: ");
            for (String step : recipe.getSteps()) {
                sb.append(listEl).append(step).append("\n");
            }
        }

        return sb.append("\n").toString();
    }
}
