package com.isariev.keycloak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    public static void postService(String data) {
        try {
            URL url = new URL("http://localhost:8181");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");

            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes());
            os.flush();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                LOGGER.info("Data sent successfully.");
            } else {
                LOGGER.error("Failed to send data. HTTP error code: " + responseCode);
            }

            conn.disconnect();
        } catch (IOException e) {
            LOGGER.error("An error occurred while sending data: " + e.getMessage());
            e.printStackTrace();
        }
    }

}