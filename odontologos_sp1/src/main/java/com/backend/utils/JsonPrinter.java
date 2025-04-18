package com.backend.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.backend.utils.LocalDateTimeAdapter;
import com.backend.utils.LocalDateAdapter;

public class JsonPrinter {

    public static String toString(Object t) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        Gson gson = gsonBuilder.create();
        String jsonString = gson.toJson(t).trim().replace("\n", "").replace("\t", "");

        return jsonString.replaceAll("\\s", "");

    }
}
