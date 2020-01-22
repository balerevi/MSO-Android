package de.hsma.stayingalive.ui.persoenlichedaten;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.time.LocalDate;

import de.hsma.stayingalive.R;
import de.hsma.stayingalive.dto.NutzerDTO;
import de.hsma.stayingalive.manager.NutzerDTOManager;
import de.hsma.stayingalive.manager.StoredDataManager;

public class PersoenlicheDatenFragment extends Fragment {

    private NutzerDTO nutzerDto;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        nutzerDto = NutzerDTOManager.getInstance().getNutzerDto();

        View root = inflater.inflate(R.layout.fragment_persoenliche_daten, container, false);

        handleFields(root);

        return root;
    }

    private void handleFields(View root) {
        // Nachname
        final TextView nachname = root.findViewById(R.id.editTextNachname);
        nachname.setText(nutzerDto.getPersoenlicheDaten().getName());
        nachname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nutzerDto.getPersoenlicheDaten().setName(nachname.getText().toString());
                writeNutzerToSharedPreferences();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // Vorname
        final TextView vorname = root.findViewById(R.id.editTextVorname);
        vorname.setText(nutzerDto.getPersoenlicheDaten().getVorname());
        vorname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nutzerDto.getPersoenlicheDaten().setVorname(vorname.getText().toString());
                writeNutzerToSharedPreferences();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Geburtsdatum
        final TextView geburtsdatum = root.findViewById(R.id.editTextGeburstag);
        // TODO parsen!!!
//        geburtsdatum.setText(nutzerDto.getPersoenlicheDaten().getGeburtsdatum().toString());
        geburtsdatum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nutzerDto.getPersoenlicheDaten().setGeburtsdatum(LocalDate.now());//geburtsdatum.getText().toString());
                writeNutzerToSharedPreferences();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // Straße
        final TextView strasse = root.findViewById(R.id.editTextStrasse);
        strasse.setText(nutzerDto.getPersoenlicheDaten().getAnschrift().getStrasse());
        strasse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nutzerDto.getPersoenlicheDaten().getAnschrift().setStrasse(strasse.getText().toString());
                writeNutzerToSharedPreferences();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Hausnummer
        final TextView hausnummer = root.findViewById(R.id.editTextHausnummer);
        hausnummer.setText(nutzerDto.getPersoenlicheDaten().getAnschrift().getHausnummer());
        hausnummer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nutzerDto.getPersoenlicheDaten().getAnschrift().setHausnummer(hausnummer.getText().toString());
                writeNutzerToSharedPreferences();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // plz
        final TextView plz = root.findViewById(R.id.editTextPlz);
        plz.setText(nutzerDto.getPersoenlicheDaten().getAnschrift().getPlz());
        plz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nutzerDto.getPersoenlicheDaten().getAnschrift().setPlz(plz.getText().toString());
                writeNutzerToSharedPreferences();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Ort
        final TextView ort = root.findViewById(R.id.editTextOrt);
        ort.setText(nutzerDto.getPersoenlicheDaten().getAnschrift().getOrt());
        ort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nutzerDto.getPersoenlicheDaten().getAnschrift().setOrt(ort.getText().toString());
                writeNutzerToSharedPreferences();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Beruf
        final TextView beruf = root.findViewById(R.id.editTextBeruf);
        beruf.setText(nutzerDto.getPersoenlicheDaten().getBeruf());
        beruf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nutzerDto.getPersoenlicheDaten().setBeruf(beruf.getText().toString());
                writeNutzerToSharedPreferences();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Größe
        final TextView groesse = root.findViewById(R.id.editTextGroesse);
        groesse.setText(nutzerDto.getPersoenlicheDaten().getGroesse());
        groesse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nutzerDto.getPersoenlicheDaten().setGroesse(groesse.getText().toString());
                writeNutzerToSharedPreferences();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Gewicht
        final TextView gewicht = root.findViewById(R.id.editTextGewicht);
        gewicht.setText(nutzerDto.getPersoenlicheDaten().getGewicht());
        gewicht.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nutzerDto.getPersoenlicheDaten().setGewicht(gewicht.getText().toString());
                writeNutzerToSharedPreferences();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void writeNutzerToSharedPreferences() {
        StoredDataManager.writeStorageData(getActivity().getSharedPreferences(getString(R.string.shared_preferences_user_data), Context.MODE_PRIVATE), getString(R.string.shared_preferences_user_data_json), nutzerDto);
    }
}
