package de.ema.mso_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.ema.mso_test.dto.NutzerDTO;
import de.ema.mso_test.helper.StoredDataHelper;

public class UserDatenAnzeigenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_daten_anzeigen);

        Intent intent = getIntent();

        fillTextViewFields();
    }

    private void fillTextViewFields() {
        NutzerDTO nutzerDTO = StoredDataHelper.readStorageData(getSharedPreferences(getString(R.string.shared_preferences_user_data), Context.MODE_PRIVATE), getString(R.string.shared_preferences_user_data_json));

        if (nutzerDTO != null) {
            TextView vorname = findViewById(R.id.tvVorname);
            TextView nachname = findViewById(R.id.tvNachname);
            TextView geburtsdatum = findViewById(R.id.tvGeburtsdatum);

            vorname.setText(nutzerDTO.getPersoenlicheDaten().getVorname());
            nachname.setText(nutzerDTO.getPersoenlicheDaten().getName());
            geburtsdatum.setText(nutzerDTO.getPersoenlicheDaten().getGeburtsdatum().toString());
        }

    }


}
