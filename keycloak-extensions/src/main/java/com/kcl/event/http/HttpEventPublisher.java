package com.kcl.event.http;

import com.google.gson.Gson;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpEventPublisher {
    private static final String webhookUrl = System.getenv().getOrDefault("WEBHOOK_URL", "http://localhost:8080/webhook");
    private static final Gson gson = new Gson();

    public static void publish(Object eventObject) {
        try {
            String jsonPayload = gson.toJson(eventObject);
            URL url = new URL(webhookUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 200 && responseCode != 202) {
                System.err.println("HTTP Webhook failed. Response Code: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            System.err.println("Failed to send event to webhook: " + e.getMessage());
        }
    }
}
