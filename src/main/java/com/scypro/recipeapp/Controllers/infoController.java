package com.scypro.recipeapp.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class infoController {

    @GetMapping
    public String index(){
        return "Приложение запущенно.";
    }

    @GetMapping("/info")
    public String info(){
        return "Имя ученика: Молотов Андрей <br/>" +
                "Проект: recipe-app <br/>" +
                "Дата создания проекта: 11.03.2023 <br/>" +
                "Описание проекта: web-приложение для рецептов";
    }
}
