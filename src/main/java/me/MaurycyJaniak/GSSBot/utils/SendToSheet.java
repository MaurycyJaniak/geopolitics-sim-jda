package me.MaurycyJaniak.GSSBot.utils;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendToSheet {

    private String scriptUrl = "https://script.google.com/macros/s/AKfycbyNJfLC95mI6jL5TdAdv69KGc5jgz3POBO2BBcc1r06umivx2PXr8bPKwqO929Y2mpm/exec";

    public int send(JSONObject payload) throws IOException {
        //Google Apps Script URL
        URL url = new URL(scriptUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Send the data
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = payload.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return conn.getResponseCode();

    }


}
