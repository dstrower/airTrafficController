package com.atcs.action;

import com.atcs.objects.Plane;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class RemovePlaneFromQueue {




    Plane plane = new Plane();

    public String getPlane()  {
        URL url = null;

        try {
            url = new URL("http://localhost:10000/dequeue/deplaneQueue");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            if(status == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                String json = content.toString();
                System.out.println("json = " + json);
                Gson gson = new Gson();
                plane = gson.fromJson(json,Plane.class);
                con.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    public int getId() {
        return plane.getId();
    }

    public String getType() {
        return plane.getType();
    }

    public String getSize() {
        return plane.getSize();
    }
}
