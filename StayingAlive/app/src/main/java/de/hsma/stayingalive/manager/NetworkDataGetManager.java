package de.hsma.stayingalive.manager;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import de.hsma.stayingalive.dto.NutzerDTO;

public class NetworkDataGetManager extends AsyncTask<NutzerDTO, Void, Boolean> {


    private static JsonNode parseResponseToJson(InputStream inputStream) {
        BufferedReader in = new BufferedReader((new InputStreamReader(inputStream)));
        StringBuilder sb = new StringBuilder();

        String line;

        try {
            while (((line = in.readLine()) != null)) {
                sb.append(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
        return convertToJson(sb.toString());
    }

    private static JsonNode convertToJson(String toString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(toString, JsonNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Boolean doInBackground(NutzerDTO... nutzerDTO) {
        readNutzerDto(nutzerDTO[0]);

        return true;

    }

    private void readNutzerDto(NutzerDTO nutzerDTO) {
        String requestMethod = "GET";
        String urlString = "https://mso-backend.herokuapp.com/storage/";

        URL url = null;
        try {
            url = new URL("https://mso-backend.herokuapp.com/storage/");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();


            System.out.println(content.toString());
            System.out.println(con.getResponseMessage());
            con.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        HttpsURLConnection connection = null;
//        try {
//            URL url = new URL(urlString);
//            connection = (HttpsURLConnection) url.openConnection();
//            connection.setRequestMethod(requestMethod);
//            connection.setDoOutput(true);
//
//            System.out.println(connection.getResponseCode());
//
//
//            JsonNode response = parseResponseToJson(connection.getInputStream());
//            System.out.println(response.asText());
//
//            // update!
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//        }

    }


}
