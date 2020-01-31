package de.hsma.stayingalive.manager;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import de.hsma.stayingalive.dto.NutzerDTO;

public class NetworkDataPutDataManager extends AsyncTask<NutzerDTO, Void, Boolean> {

    private String urlString = "https://mso-backend.herokuapp.com/storage/";

    private static JsonNode convertToJson(String toString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(toString, JsonNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void readNutzerDto(NutzerDTO nutzerDTO) {
        String requestMethod = "GET";
        String urlString = "https://mso-backend.herokuapp.com/storage/";

        urlString = urlString + nutzerDTO.getId();

        HttpsURLConnection connection = null;
        try {
            String jsonNutzer = StoredDataManager.convertNutzerDTOToJSONString(nutzerDTO);
            URL url = new URL(urlString);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
//            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

//            DataOutputStream stream = new DataOutputStream(connection.getOutputStream());
//            stream.write(jsonNutzer.getBytes());
//            stream.flush();
//            stream.close();
            System.out.println(connection.getResponseCode());


            JsonNode response = parseResponseToJson(connection.getInputStream());

            // update!
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }

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
        return convertToJson(sb.toString());
    }

    @Override
    protected Boolean doInBackground(NutzerDTO... nutzerDTO) {
        createOrUpdateNutzerDto(nutzerDTO[0]);
        readNutzerDto(nutzerDTO[0]);


        return true;

    }

    private void createOrUpdateNutzerDto(NutzerDTO nutzerDTO) {

        String requestMethod = "POST";
        String urlString = "https://mso-backend.herokuapp.com/storage/";

        // Hat das NutzerDTO bereits eine ID, haben wir es schon auf dem Server
        if (nutzerDTO.getId() != null && !nutzerDTO.getId().isEmpty()) {
            requestMethod = "PUT";
            urlString = urlString + "/" + nutzerDTO.getId();
        }

        HttpsURLConnection connection = null;
        try {
            String jsonNutzer = URLEncoder.encode(StoredDataManager.convertNutzerDTOToJSONString(nutzerDTO), "UTF-8");
            URL url = new URL(urlString);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            DataOutputStream stream = new DataOutputStream(connection.getOutputStream());
            stream.write(jsonNutzer.getBytes());
            stream.flush();
            stream.close();


            JsonNode response = parseResponseToJson(connection.getInputStream());

            if (nutzerDTO.getId() == null || nutzerDTO.getId().isEmpty()) {
                nutzerDTO.setId(response.get("id").asText());
                createOrUpdateNutzerDto(nutzerDTO);
            }
            // update!
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
