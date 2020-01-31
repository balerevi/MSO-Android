package de.hsma.stayingalive.manager;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import de.hsma.stayingalive.dto.NutzerDTO;

public class NetworkDataPostOrPutManager extends AsyncTask<NutzerDTO, Void, Integer> {


    @Override
    protected Integer doInBackground(NutzerDTO... nutzerDTO) {
        NutzerDTO nutzerDTO1 = nutzerDTO[0];
        if (nutzerDTO1.getId() == null) {
            Integer nutzerId = postNutzerDto(nutzerDTO1);
            if (nutzerId != null) {
                return nutzerId;
            }
        } else {
            Integer integer = putNutzerDto(nutzerDTO1);
            if (integer != null) {
                return integer;
            }
        }
        return null;
    }


    private Integer postNutzerDto(NutzerDTO nutzerDTO) {

        try {
            URL url = new URL("https://mso-backend.herokuapp.com/storage/");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            String s = StoredDataManager.convertNutzerDTOToJSONString(nutzerDTO);
            out.write(s.getBytes());
            out.flush();
            out.close();


            System.out.println("ResponseCode: " + con.getResponseCode());

            if (con.getResponseCode() < 400) {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();

                String nutzerDtoAsJsonString = content.toString();
                System.out.println(nutzerDtoAsJsonString);
                System.out.println(con.getResponseMessage());
                con.disconnect();

                ObjectMapper objectMapper = new ObjectMapper();

                NutzerDTO result = objectMapper.readValue(nutzerDtoAsJsonString, NutzerDTO.class);
                return result.getId();

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer putNutzerDto(NutzerDTO nutzerDTO) {
        try {
            URL url = new URL("https://mso-backend.herokuapp.com/storage/" + nutzerDTO.getId());
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            String s = StoredDataManager.convertNutzerDTOToJSONString(nutzerDTO);
            out.write(s.getBytes());
            out.flush();
            out.close();


            System.out.println("ResponseCode: " + con.getResponseCode());

            if (con.getResponseCode() < 400) {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();

                String nutzerDtoAsJsonString = content.toString();
                System.out.println(nutzerDtoAsJsonString);
                System.out.println(con.getResponseMessage());
                con.disconnect();

                ObjectMapper objectMapper = new ObjectMapper();

                NutzerDTO result = objectMapper.readValue(nutzerDtoAsJsonString, NutzerDTO.class);
                return result.getId();

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


}
