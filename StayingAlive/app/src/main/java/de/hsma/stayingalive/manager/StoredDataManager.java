package de.hsma.stayingalive.manager;

import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import de.hsma.stayingalive.dto.NutzerDTO;

public class StoredDataManager {


    public static NutzerDTO readStorageData(SharedPreferences sharedPreferences, String key) {
        String userJson = sharedPreferences.getString(key, null);

        if (userJson != null) {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                NutzerDTO nutzerDTO = objectMapper.readValue(userJson, NutzerDTO.class);
                return nutzerDTO;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void writeStorageData(SharedPreferences sharedPreferences, String key, NutzerDTO value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            String jsonNutzer = objectMapper.writeValueAsString(value);
            editor.putString(key, jsonNutzer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        editor.commit();


    }

    static String convertNutzerDTOToJSONString(NutzerDTO nutzerDTO) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            try {
                return objectMapper.writeValueAsString(nutzerDTO);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
            return null;
    }
}
