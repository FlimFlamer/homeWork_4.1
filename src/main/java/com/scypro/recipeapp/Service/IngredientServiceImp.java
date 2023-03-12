package com.scypro.recipeapp.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.scypro.recipeapp.Exeption.ValidationExeption;
import com.scypro.recipeapp.model.Ingredient;
import com.scypro.recipeapp.model.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
public class IngredientServiceImp implements IngredientService {

    private static long idCounter = 1;
    private Map<Long, Ingredient> ingredientMap = new HashMap<>();
    private final ValidationService validationService;
    private final FileService fileService;

    @Value("${path.to.ingredients.file}")
    private String ingredientsFilePath;

    @Value("${name.of.ingredients.file}")
    private String ingredientsFileName;

    private Path ingredientPath;


    @Override
    public Ingredient save(Ingredient ingredient) {
        if (!validationService.validate(ingredient)){
            throw new ValidationExeption(ingredient.toString());
        }
        ingredientMap.put(idCounter++,ingredient);
        fileService.saveMapToFile(ingredientMap, ingredientPath);

        return ingredient;
    }

    @Override
    public Optional<Ingredient> getById(long id) {
        return Optional.ofNullable(ingredientMap.get(id));
    }

    @Override
    public Ingredient update(Long id, Ingredient ingredient) {
        if (!validationService.validate(ingredient)){
            throw new ValidationExeption(ingredient.toString());
        }
        ingredientMap.replace(id, ingredient);
        fileService.saveMapToFile(ingredientMap, ingredientPath);

        return ingredient;
    }

    @Override
    public Ingredient delete(Long id) {
        Ingredient ingredient = ingredientMap.remove(id);
        fileService.saveMapToFile(ingredientMap, ingredientPath);
        return ingredient;
    }

    public void uploadFile(MultipartFile file, Path filePath) throws IOException {
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
            InputStream is = file.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
    }

    @Override
    public Map<Long, Ingredient> getAll() {
        return ingredientMap;
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        fileService.uploadFile(file, ingredientPath);
        ingredientMap = fileService.readMapFromFile(ingredientPath, new TypeReference<HashMap<Long, Ingredient>>() {});
    }

    @PostConstruct
    private void init(){
        ingredientPath = Path.of(ingredientsFilePath, ingredientsFileName);
        ingredientMap = fileService.readMapFromFile(ingredientPath, new TypeReference<HashMap<Long, Ingredient>>() {});
        }
}
