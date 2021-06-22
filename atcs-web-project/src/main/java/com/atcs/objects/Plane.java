package com.atcs.objects;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author davidtrower
 * This class holds values for the Plane
 */
public class Plane {

    private int id;
    private String type;
    private String size;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStatus() throws IOException {
        //Make REST call
        URL url = new URL ("http://localhost:10000/enqueue/addToQueue");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString = createJSON();
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return con.getResponseCode();
    }

    private String createJSON() {
        String dq = "\"";
        String json = "{";
        json = json + dq + "id" + dq + ":";
        json = json + dq + String.valueOf(id) + dq + ",";
        json = json + dq + "type" + dq + ":";
        json = json + dq + type.toUpperCase() + dq + ",";
        json = json + dq + "size" + dq + ":";
        json = json + dq +  size.toUpperCase() + dq;
        json = json + "}";
        return json;
    }
}
