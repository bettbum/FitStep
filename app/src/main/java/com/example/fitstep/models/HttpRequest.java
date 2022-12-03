package com.example.fitstep.models;

import android.os.StrictMode;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequest {
    public static ArrayList<Recipe> getReceipe (String foodName){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ArrayList<Recipe> listOfRecipes = new ArrayList<Recipe>();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://edamam-recipe-search.p.rapidapi.com/search?q="+foodName+"")
                .get()
                .addHeader("X-RapidAPI-Key", "53bc3c2972mshc3dc513bc1d7931p16001ejsnbd3ac305aee3")
                .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            // Log.d("dd",response.body().string());
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> obj = mapper.readValue( response.body().string(), new TypeReference<Map<String, Object>>() {
            });
            ArrayList<Map<String, Object>> listOfReceipt = (ArrayList<Map<String, Object>>) obj.get("hits");
            for(int i = 0; i < listOfReceipt.size(); i++) {
                Map<String, Object> getReceipt = (Map<String, Object>) listOfReceipt.get(i);
                Map<String, Object> listOfElementsInsideReceipt = (Map<String, Object>) getReceipt.get("recipe");
                ArrayList<String> dietLabels = (ArrayList<String>) listOfElementsInsideReceipt.get("dietLabels");
                ArrayList<String> healthLabels = (ArrayList<String>) listOfElementsInsideReceipt.get("healthLabels");
                String description = "";
                for (String item : dietLabels) {
                    description = description + item + ", ";
                }
                for (String item : healthLabels) {
                    description = description + item + ", ";
                }
                String label = String.valueOf(listOfElementsInsideReceipt.get("label"));
                String image = String.valueOf(listOfElementsInsideReceipt.get("image"));
                ArrayList<String> ingredients = (ArrayList<String>) listOfElementsInsideReceipt.get("ingredientLines");
                String cuisineTypes = String.valueOf(listOfElementsInsideReceipt.get("cuisineType"));
                String calories = String.valueOf(listOfElementsInsideReceipt.get("calories"));
                //String source = String.valueOf(listOfElementsInsideReceipt.get("source"));
                Log.d("object", String.valueOf(getReceipt.containsKey("recipe")));
                Recipe recipe = new Recipe(label,description,image,ingredients,cuisineTypes,calories);
                listOfRecipes.add(recipe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfRecipes;
    }
    public static Quote getRandomQuote(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Quote quotes = new Quote();
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String value = "{\"topic\": \"health\"}";
        RequestBody body = RequestBody.create(mediaType, value);
        Request request = new Request.Builder()
                .url("https://pquotes.p.rapidapi.com/api/quote")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("X-RapidAPI-Key", "53bc3c2972mshc3dc513bc1d7931p16001ejsnbd3ac305aee3")
                .addHeader("X-RapidAPI-Host", "pquotes.p.rapidapi.com").build();
        Response response = null;
        try{
            response = client.newCall(request).execute();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> obj = mapper.readValue( response.body().string(), new TypeReference<Map<String, String>>() {
            });
            String quote = obj.get("quote").toString();
            String author = obj.get("by").toString();
            quotes = new Quote(quote,author);
        }catch(Exception e){

        }
        return quotes;
    }
}
