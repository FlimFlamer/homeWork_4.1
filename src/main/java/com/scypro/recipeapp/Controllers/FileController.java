package com.scypro.recipeapp.Controllers;

import com.scypro.recipeapp.Service.IngredientService;
import com.scypro.recipeapp.Service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/files")
@Tag(name = "Api для файлов")
@RequiredArgsConstructor
public class FileController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @GetMapping("/recipes/export")
    @Operation(summary = "Выгрузка файлов рецептов")
    public ResponseEntity<InputStreamResource> downloadRecipesFile() {

        try {
            File recipeFile = recipeService.readFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(recipeFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(recipeFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename = " + recipeFile.getName())
                    .body(resource);


        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/recipe/import", consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка файлов рецептов")
    public ResponseEntity<String> uploadRecipesFile(@RequestParam MultipartFile file) {
        try {
            recipeService.uploadFile(file);
            return ResponseEntity.ok("Файл успешно импортирован");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ошибка загрузки файла");
        }
    }

    @PostMapping(value = "/ingredient/import", consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка файлов ингиридиентов")
    public ResponseEntity<String> uploadIngredientFile(@RequestParam MultipartFile file) {
        try {
            ingredientService.uploadFile(file);
            return ResponseEntity.ok("Файл успешно импортирован");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ошибка загрузки файла");
        }
    }
    @GetMapping("/recipes/export/txt")
    @Operation(summary = "Выгрузка файлов рецептов в текстовом формате")
    public ResponseEntity<InputStreamResource> downloadRecipesTxtFile() {

        try {
            File recipeFile = recipeService.prepareRecipesTxt();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(recipeFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(recipeFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename = " + recipeFile.getName())
                    .body(resource);


        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
