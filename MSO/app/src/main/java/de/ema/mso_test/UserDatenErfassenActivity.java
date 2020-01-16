package de.ema.mso_test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

import de.ema.mso_test.dto.KontaktartEnum;
import de.ema.mso_test.dto.NotfallkontaktDTO;
import de.ema.mso_test.dto.NutzerDTO;
import de.ema.mso_test.dto.factory.NotfallkontaktDTOFactory;

public class UserDatenErfassenActivity extends AppCompatActivity {

    private NutzerDTO nutzerDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_user_daten_erfassen_stammdaten);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        boolean message = intent.getBooleanExtra("ShowIntToast", false);
        nutzerDTO = (NutzerDTO) intent.getSerializableExtra("nutzerDTO");

        if (message) {
            Toast toast = Toast.makeText(getApplicationContext(), "Herzlich Willkommen, bitte Init", Toast.LENGTH_LONG);
            toast.show();
        }

        // erste Maske der Datenerfassung sichtbar machen
        findViewById(R.id.userDatenErfassenStammdaten).setVisibility(View.VISIBLE);
        prepareStammdatenView();


    }


    /**
     * Called when the user taps the Send button
     */
    public void userDatenErfassenSpeichern(View view) {

        // Notfallkontakte
        handleErfassteNotfallkontakdaten();
        // Navigation?
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            String jsonNutzer = objectMapper.writeValueAsString(nutzerDTO);
            System.out.println(jsonNutzer);

            // 1. in die Shared Preferences Speichern
            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.shared_preferences_user_data), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.shared_preferences_user_data_json), jsonNutzer);
            editor.commit();

            // 2. an den Webserver schicken
            String url = "https://mso-backend.herokuapp.com/storage/";

            callRest(nutzerDTO, url);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }


    private void callRest(NutzerDTO nutzer, String url) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void navigationForwardAndHandleData(View view) {

        // Stammdaten <--> Kontaktdaten <--> weitereStammdaten <--> Notfallkontakte
        // für die Navigation der Buttons innerhalb der Datenerfassung
        switch (view.getId()) {
            case R.id.buttonStammdatenWeiter:
                boolean navigation = handleErfassteStammdaten();
                if (navigation) {
                    prepareKontaktdatenView();
                }
                break;
            case R.id.buttonWeitereStammdatenZurueck:
                handleErfassteWeitereStammdaten();
                prepareKontaktdatenView();
                break;
            case R.id.buttonKontaktdatenWeiter:
                handleErfassteKontaktdaten();
                prepareWeitereStammdatenView();
                break;
            case R.id.buttonNotfallkontakteZurueck:
                handleErfassteNotfallkontakdaten();
                prepareWeitereStammdatenView();
                break;
            case R.id.buttonKontaktdatenZurueck:
                handleErfassteKontaktdaten();
                prepareStammdatenView();
                break;
            case R.id.buttonWeitereStammdatenWeiter:
                handleErfassteWeitereStammdaten();
                prepareNotfallkontaktView();
                break;
            default:
                prepareStammdatenView();
                break;
        }
    }

    private void handleErfassteNotfallkontakdaten() {
        EditText name = findViewById(R.id.editTextKontaktName);
        EditText email = findViewById(R.id.editTextKontaktEmailadresse);
        EditText handynummer = findViewById(R.id.editTextKontaktHandynummer);
        EditText hausnummer = findViewById(R.id.editTextKontaktHausnummer);
        EditText ort = findViewById(R.id.editTextKontaktOrt);
        EditText plz = findViewById(R.id.editTextKontaktPlz);
        EditText strasse = findViewById(R.id.editTextKontaktPlz);
        Spinner kontaktart = findViewById(R.id.spinnerKontaktKontaktart);

        // TODO Validation && NULL/ Emptry Prüfung!!
        NotfallkontaktDTO notfallkontaktDTO = NotfallkontaktDTOFactory.createInstance();
        notfallkontaktDTO.setKontaktart(KontaktartEnum.findByValue(kontaktart.getSelectedItem().toString()));
        notfallkontaktDTO.setName(name.getText().toString());
        notfallkontaktDTO.getKontaktdaten().setEmail(email.getText().toString());
        notfallkontaktDTO.getKontaktdaten().setHandynummer(handynummer.getText().toString());
        notfallkontaktDTO.getAnschrift().setHausnummer(hausnummer.getText().toString());
        notfallkontaktDTO.getAnschrift().setOrt(ort.getText().toString());
        notfallkontaktDTO.getAnschrift().setStrasse(strasse.getText().toString());
        notfallkontaktDTO.getAnschrift().setPlz(plz.getText().toString());

        nutzerDTO.getPrivateDaten().getNotfallkontakte().add(notfallkontaktDTO);
    }

    private void handleErfassteKontaktdaten() {
        EditText email = findViewById(R.id.editTextEmail);
        EditText handynummer = findViewById(R.id.editTextHandynummer);
        EditText telefonnummer = findViewById(R.id.editTextTelefonnummer);

        // TODO Validation!
        nutzerDTO.getPersoenlicheDaten().getKontaktdaten().setHandynummer(handynummer.getText().toString());
        nutzerDTO.getPersoenlicheDaten().getKontaktdaten().setEmail(email.getText().toString());
        nutzerDTO.getPersoenlicheDaten().getKontaktdaten().setTelefonnummer(telefonnummer.getText().toString());
    }

    private void handleErfassteWeitereStammdaten() {
        EditText beruf = findViewById(R.id.editTextBeruf);
        EditText groesse = findViewById(R.id.editTextGroesse);
        EditText gewicht = findViewById(R.id.editTextGewicht);

        // TODO Validation!
        nutzerDTO.getPersoenlicheDaten().setBeruf(beruf.getText().toString());
        nutzerDTO.getPersoenlicheDaten().setGewicht(gewicht.getText().toString());
        nutzerDTO.getPersoenlicheDaten().setGroesse(groesse.getText().toString());
    }

    private boolean handleErfassteStammdaten() {

        EditText vorname = findViewById(R.id.editTextEmail);
        EditText nachname = findViewById(R.id.editTextName);
        EditText geburtsdatum = findViewById(R.id.editTextGeburstag);
        EditText strasse = findViewById(R.id.editTextStrasse);
        EditText hausnummer = findViewById(R.id.editTextHausnummer);
        EditText plz = findViewById(R.id.editTextPlz);
        EditText ort = findViewById(R.id.editTextOrt);

        if (vorname.getText().toString().trim().isEmpty()) {
            vorname.setError("Vorname ist ein Pflichtfeld");
            vorname.setHint("Bitte Vorname eingeben");
            return false;
        }
        // TODO Validation!
        nutzerDTO.getPersoenlicheDaten().setVorname(vorname.getText().toString());
        nutzerDTO.getPersoenlicheDaten().setName(nachname.getText().toString());
        nutzerDTO.getPersoenlicheDaten().getAnschrift().setHausnummer(hausnummer.getText().toString());
        nutzerDTO.getPersoenlicheDaten().getAnschrift().setStrasse(strasse.getText().toString());
        nutzerDTO.getPersoenlicheDaten().getAnschrift().setOrt(ort.getText().toString());
        nutzerDTO.getPersoenlicheDaten().getAnschrift().setPlz(plz.getText().toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // TODO vom eingegebenen Geburtsdatum parsen!
            String s = geburtsdatum.getText().toString();
            nutzerDTO.getPersoenlicheDaten().setGeburtsdatum(LocalDate.now());
        }
        return true;
    }

    private void prepareStammdatenView() {
        // Stammdaten sichtbar
        setContentView(R.layout.content_user_daten_erfassen_stammdaten);

        EditText vorname = findViewById(R.id.editTextEmail);
        EditText nachname = findViewById(R.id.editTextName);
        EditText geburtsdatum = findViewById(R.id.editTextGeburstag);
        EditText strasse = findViewById(R.id.editTextStrasse);
        EditText hausnummer = findViewById(R.id.editTextHausnummer);
        EditText plz = findViewById(R.id.editTextPlz);
        EditText ort = findViewById(R.id.editTextOrt);

        vorname.setText(nutzerDTO.getPersoenlicheDaten().getVorname());
        nachname.setText(nutzerDTO.getPersoenlicheDaten().getName());
        hausnummer.setText(nutzerDTO.getPersoenlicheDaten().getAnschrift().getHausnummer());
        plz.setText(nutzerDTO.getPersoenlicheDaten().getAnschrift().getPlz());
        strasse.setText(nutzerDTO.getPersoenlicheDaten().getAnschrift().getStrasse());
        ort.setText(nutzerDTO.getPersoenlicheDaten().getAnschrift().getOrt());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // TODO vom eingegebenen Geburtsdatum parsen!
            String s = geburtsdatum.getText().toString();
            nutzerDTO.getPersoenlicheDaten().setGeburtsdatum(LocalDate.now());
        }
    }

    private void prepareWeitereStammdatenView() {
        // weitereStammdaten sichtbar
        setContentView(R.layout.content_user_daten_erfassen_weitere_stammdaten);

        // Werte der Felder setzen
        EditText beruf = findViewById(R.id.editTextBeruf);
        EditText groesse = findViewById(R.id.editTextGroesse);
        EditText gewicht = findViewById(R.id.editTextGewicht);

        beruf.setText(nutzerDTO.getPersoenlicheDaten().getBeruf());
        gewicht.setText(nutzerDTO.getPersoenlicheDaten().getGewicht());
        groesse.setText(nutzerDTO.getPersoenlicheDaten().getGroesse());

    }

    private void prepareKontaktdatenView() {
        // Kontaktdaten sichtbar
        setContentView(R.layout.content_user_daten_erfassen_kontaktdaten);

        EditText email = findViewById(R.id.editTextEmail);
        EditText handynummer = findViewById(R.id.editTextHandynummer);
        EditText telefonnummer = findViewById(R.id.editTextTelefonnummer);

        email.setText(nutzerDTO.getPersoenlicheDaten().getKontaktdaten().getEmail());
        handynummer.setText(nutzerDTO.getPersoenlicheDaten().getKontaktdaten().getHandynummer());
        telefonnummer.setText(nutzerDTO.getPersoenlicheDaten().getKontaktdaten().getTelefonnummer());

    }

    private void prepareNotfallkontaktView() {
        // Notfallkontakte sichtbar
        setContentView(R.layout.content_user_daten_erfassen_notfallkontakte);
        // fill Spinner "Kontaktart"
        KontaktartEnum[] values = KontaktartEnum.values();
        ArrayAdapter<KontaktartEnum> adapter = new ArrayAdapter<KontaktartEnum>(this, R.layout.support_simple_spinner_dropdown_item, KontaktartEnum.values());
        Spinner kontaktartSpinner = findViewById(R.id.spinnerKontaktKontaktart);
        kontaktartSpinner.setAdapter(adapter);
    }


    public void notfallKontaktHinzufuegen(View view) {
        handleErfassteNotfallkontakdaten();
    }
}
