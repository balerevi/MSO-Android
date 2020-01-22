package de.hsma.stayingalive;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hsma.stayingalive.dto.AllergieDTO;
import de.hsma.stayingalive.dto.ErkankungUndBefundeDTO;
import de.hsma.stayingalive.dto.KontaktartEnum;
import de.hsma.stayingalive.dto.MedikamentDTO;
import de.hsma.stayingalive.dto.NotfallkontaktDTO;
import de.hsma.stayingalive.dto.NutzerDTO;
import de.hsma.stayingalive.dto.factory.AllergieDTOFactory;
import de.hsma.stayingalive.dto.factory.ErkrankungUndBefundeDTOFactory;
import de.hsma.stayingalive.dto.factory.MedikamentDTOFactory;
import de.hsma.stayingalive.dto.factory.NotfallkontaktDTOFactory;
import de.hsma.stayingalive.dto.factory.NutzerDTOFactory;
import de.hsma.stayingalive.manager.NutzerDTOManager;
import de.hsma.stayingalive.manager.StoredDataManager;

public class MainActivity extends AppCompatActivity {

    private NutzerDTO nutzerDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_persoenliche_daten, R.id.navigation_medizinische_daten, R.id.navigation_notfallkontakte)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Daten aus dem Speicher holen
        nutzerDTO = StoredDataManager.readStorageData(getSharedPreferences(getString(R.string.shared_preferences_user_data), Context.MODE_PRIVATE), getString(R.string.shared_preferences_user_data_json));

        // TODO Update mit Daten vom Server

        if (nutzerDTO == null) {
            nutzerDTO = NutzerDTOFactory.createInstance();
        }
        NutzerDTOManager instance = NutzerDTOManager.getInstance();
        instance.setNutzerDto(nutzerDTO);

    }

    public void addKontakt(View view) {

        EditText name = findViewById(R.id.editTextKontaktName);
        EditText handynummer = findViewById(R.id.editTextKontaktTelefonnummer);
        Spinner kontaktart = findViewById(R.id.spinnerKontaktArt);

        NotfallkontaktDTO notfallkontaktDTO = NotfallkontaktDTOFactory.createInstance();
        notfallkontaktDTO.setKontaktart(KontaktartEnum.findByValue(kontaktart.getSelectedItem().toString()));
        notfallkontaktDTO.setName(name.getText().toString());
        notfallkontaktDTO.getKontaktdaten().setHandynummer(handynummer.getText().toString());
        nutzerDTO.getPrivateDaten().getNotfallkontakte().add(notfallkontaktDTO);

        name.setText("");
        handynummer.setText("");
        kontaktart.setSelected(false);
        writeNutzerToSharedPreferences();
    }

    private void writeNutzerToSharedPreferences() {
        StoredDataManager.writeStorageData(getSharedPreferences(getString(R.string.shared_preferences_user_data), Context.MODE_PRIVATE), getString(R.string.shared_preferences_user_data_json), nutzerDTO);
    }


    public void addMedikament(View view) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Neues Medikament erfassen");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.add_medikamente, null);
        EditText medikamentName = viewInflated.findViewById(R.id.editTextMedikamentName);
        EditText medkamentDosierung = viewInflated.findViewById(R.id.editTextMedikamentDosierung);

        alertDialog.setView(viewInflated);

        alertDialog.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MedikamentDTO medikamentDTO = MedikamentDTOFactory.createInstance();
                medikamentDTO.setName(medikamentName.getText().toString());
                medikamentDTO.setDosierung(medkamentDosierung.getText().toString());
                nutzerDTO.getMedizinischeInformationen().getMedikamente().add(medikamentDTO);
                writeNutzerToSharedPreferences();
            }
        });

        alertDialog.show();

    }


    public void addAllergie(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Neue Allergie/ Unverträglichkeit erfassen");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.add_allergien, null);
        EditText allergieName = viewInflated.findViewById(R.id.editTextAllergie);

        alertDialog.setView(viewInflated);

        alertDialog.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AllergieDTO allergieDTO = AllergieDTOFactory.createInstance();
                allergieDTO.setAllergie(allergieName.getText().toString());
                nutzerDTO.getMedizinischeInformationen().getAllergien().add(allergieDTO);
                writeNutzerToSharedPreferences();
            }
        });

        alertDialog.show();
    }

    public void addErkrankung(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Vorerkrankungen erfassen");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.add_erkrankungen, null);
        EditText erkrankungName = viewInflated.findViewById(R.id.editTextErkrankungen);

        alertDialog.setView(viewInflated);

        alertDialog.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ErkankungUndBefundeDTO erkankungUndBefundeDTO = ErkrankungUndBefundeDTOFactory.createInstance();
                erkankungUndBefundeDTO.setName(erkrankungName.getText().toString());
                nutzerDTO.getMedizinischeInformationen().getErkankungUndBefunde().add(erkankungUndBefundeDTO);
                writeNutzerToSharedPreferences();
            }
        });

        alertDialog.show();
    }

}
