package com.example.sweater;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


import java.util.Map;

/**
 * Контроллер страниц greeting и main
 */
@Controller
public class GreetingController {

    /**
     * @return одно случайно целое число
     */
    public static int genRandomNumber()
    {
        Random random = new Random();
        int min = 0;
        int max = 500;
        return min + random.nextInt(max - min) + 1;
    }

    /**
     * @return массив 15 случайных целых чисел
     */
    public static ArrayList<Integer> getRandomArray()
    {
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < 15; ++i)
        {
            array.add(GreetingController.genRandomNumber());
        }

        return array;
    }


    /** Парсинг параметров строки greeting http://localhost:8080/greeting?name=w
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        String result = "";
        try {
             result = Application.manager.select();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.put("name", result);

        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("some", "hello, letsCode!");
        return "main";
    }
}
