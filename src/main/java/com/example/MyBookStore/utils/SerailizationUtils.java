package com.example.MyBookStore.utils;

import com.google.gson.Gson;

public class SerailizationUtils {

    private static Gson GSON = new Gson();

    public static Gson getGSON() {
        return GSON;
    }
}
