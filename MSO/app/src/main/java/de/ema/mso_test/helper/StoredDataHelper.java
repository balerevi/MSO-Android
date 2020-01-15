package de.ema.mso_test.helper;

import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.ema.mso_test.dto.NutzerDTO;

public class StoredDataHelper {


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
}
