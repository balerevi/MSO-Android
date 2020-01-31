package de.hsma.stayingalive;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import de.hsma.stayingalive.manager.NetworkDataGetManager;
import de.hsma.stayingalive.manager.NetworkDataPostOrPutManager;
import de.hsma.stayingalive.manager.NutzerDTOManager;
import de.hsma.stayingalive.manager.StoredDataManager;
import de.hsma.stayingalive.ui.medizinischedaten.MedzinischeDatenFragment;
import de.hsma.stayingalive.ui.notfallkontakt.NotfallkontakteFragment;
import de.hsma.stayingalive.ui.persoenlichedaten.PersoenlicheDatenFragment;

public class MainActivity extends AppCompatActivity {

    private NutzerDTO nutzerDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.buttonMedizin))
                .setActivated(true);


        // Daten aus dem Speicher holen
        nutzerDTO = StoredDataManager.readStorageData(getSharedPreferences(getString(R.string.shared_preferences_user_data), Context.MODE_PRIVATE), getString(R.string.shared_preferences_user_data_json));


        if (nutzerDTO == null) {
            nutzerDTO = NutzerDTOFactory.createInstance();
        }
        NutzerDTOManager instance = NutzerDTOManager.getInstance();
        instance.setNutzerDto(nutzerDTO);

        new NetworkDataGetManager().execute(nutzerDTO);

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

    @Override
    protected void onStop() {
        super.onStop();
        new NetworkDataPostOrPutManager().execute(nutzerDTO);
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

    public void navigate(View view) {
        int id = view.getId();

        Button pers = (Button) findViewById(R.id.buttonPersoenlich);
        Button med = (Button) findViewById(R.id.buttonMedizin);
        Button notfall = (Button) findViewById(R.id.buttonNotfallKontakt);

        pers.setActivated(false);
        med.setActivated(false);
        notfall.setActivated(false);

        Drawable[] d = pers.getCompoundDrawables();
        d[0].setColorFilter(getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        med.setBackgroundTintList(getResources().getColorStateList(R.color.pers_btn, getTheme()));

        d = med.getCompoundDrawables();
        d[0].setColorFilter(getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        pers.setBackgroundTintList(getResources().getColorStateList(R.color.pers_btn, getTheme()));

        d = notfall.getCompoundDrawables();
        d[0].setColorFilter(getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        notfall.setBackgroundTintList(getResources().getColorStateList(R.color.pers_btn, getTheme()));

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Button btn = (Button) findViewById(id);
        btn.setActivated(true);
        d = btn.getCompoundDrawables();

        switch (id) {
            case R.id.buttonPersoenlich:
                transaction.replace(R.id.nav_host_fragment, new PersoenlicheDatenFragment());
                d[0].setColorFilter(getColor(R.color.colorOrange), PorterDuff.Mode.SRC_ATOP);
                break;
            case R.id.buttonMedizin:
                transaction.replace(R.id.nav_host_fragment, new MedzinischeDatenFragment());
                d[0].setColorFilter(getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
//                d[0].setTint(Color.GREEN);
                break;
            case R.id.buttonNotfallKontakt:
                transaction.replace(R.id.nav_host_fragment, new NotfallkontakteFragment());
                d[0].setColorFilter(getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
//                d[0].setTint(Color.GREEN);
                break;
        }

        transaction.commit();
    }
}
