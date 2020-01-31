package de.hsma.stayingalive.manager;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import de.hsma.stayingalive.dto.NutzerDTO;

public class NetworkDataGetManager extends AsyncTask<NutzerDTO, Void, Boolean> {


    @Override
    protected Boolean doInBackground(NutzerDTO... nutzerDTO) {
        readNutzerDto(nutzerDTO[0]);

        return true;

    }

    private void readNutzerDto(NutzerDTO nutzerDTO) {

        try {
            URL url = new URL("https://mso-backend.herokuapp.com/storage/");
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

    }


}
