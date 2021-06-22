package com.atcs.action;

import com.atcs.objects.Plane;
import com.atcs.objects.PlaneList;
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


public class ReadPlaneQueue {
    Gson gson = new Gson();

    public String readPlaneQueue() throws IOException {
        URL url = null;
        String html = null;
        try {
            url = new URL("http://localhost:10000/enqueue/planeQueue");
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
                ObjectMapper mapper = new ObjectMapper();
                Plane[] planes = mapper.readValue(json, Plane[].class);
                List<Plane> planeList = Arrays.asList(planes);
                html = toHtml(planeList);
                con.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return html;
    }

    private String toHtml(List<Plane> planeList) {
        String html = null;
        for(Plane plane: planeList) {
            String row = "<tr><td>";
            row = row + plane.getId() + "</td>";
            row = row + "<td>" + plane.getType() + "</td>";
            row = row + "<td>" + plane.getSize() + "</td>";
            row = row + "</tr>";
            if(html == null) {
                html = row;
            } else {
                html = html + row;
            }
        }
        return html;
    }

    public String getQueue() {
        try {
            return readPlaneQueue();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
