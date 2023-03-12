package com.scypro.recipeapp.Exeption;

public class ValidationExeption extends RuntimeException {
    public ValidationExeption(String entity) {
        super("Ошибка валидации сущности: " + entity);
    }
}
