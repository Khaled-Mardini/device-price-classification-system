package com.maids.device_price_classification_backend.http;

import java.io.IOException;

import okhttp3.*;

public class HttpClient {
    // Create OkHttpClient instance
    OkHttpClient client = new OkHttpClient();

    public String post(String url, String json) throws IOException {

        // Build the request body
        RequestBody body = RequestBody.create(json, MediaType.get("application/json"));

        // Create a POST request
        Request request = new Request.Builder()
                .url(url) // Replace with your Flask API URL
                .post(body)
                .build();

        // Send the request and handle the response
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return (response.body().string());
            } else {
                return ("{\n\t\"message\": Request failed " + response.code() + "\n}");
            }
        }
    }
}
