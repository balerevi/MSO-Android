package de.hsma.stayingalive.ui.medizinischedaten;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hsma.stayingalive.R;
import de.hsma.stayingalive.dto.BlutgruppenEnum;
import de.hsma.stayingalive.dto.NutzerDTO;
import de.hsma.stayingalive.manager.NutzerDTOManager;
import de.hsma.stayingalive.manager.StoredDataManager;

public class MedzinischeDatenFragment extends Fragment {

    private NutzerDTO nutzerDto;
    private RecyclerView recyclerViewMedikamente;
    private RecyclerView recyclerViewAllergien;
    private RecyclerView recyclerViewErkankungen;
    private RecyclerView.Adapter adapterMedikamente;
    private RecyclerView.Adapter adapterAllergien;
    private RecyclerView.Adapter adapterErkrankungen;
    private RecyclerView.LayoutManager layoutManagerMedikamente;
    private RecyclerView.LayoutManager layoutManagerAllergien;
    private RecyclerView.LayoutManager layoutManagerErkrankungen;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_medizinische_daten, container, false);
        nutzerDto = NutzerDTOManager.getInstance().getNutzerDto();

        // Medikamente
        layoutManagerMedikamente = new LinearLayoutManager(getContext());
        recyclerViewMedikamente = root.findViewById(R.id.medikamentenliste);
        recyclerViewMedikamente.setLayoutManager(layoutManagerMedikamente);
        adapterMedikamente = new MedizinischeDatenMedikamenteRecycleAdapter(nutzerDto);
        recyclerViewMedikamente.setAdapter(adapterMedikamente);

        // Allergien
        layoutManagerAllergien = new LinearLayoutManager(getContext());
        recyclerViewAllergien = root.findViewById(R.id.allergienliste);
        recyclerViewAllergien.setLayoutManager(layoutManagerAllergien);
        adapterAllergien = new MedizinischeDatenAllergienRecycleAdapter(nutzerDto);
        recyclerViewAllergien.setAdapter(adapterAllergien);

        // Erkrankungen
        layoutManagerErkrankungen = new LinearLayoutManager(getContext());
        recyclerViewErkankungen = root.findViewById(R.id.erkrankungenliste);
        recyclerViewErkankungen.setLayoutManager(layoutManagerErkrankungen);
        adapterErkrankungen= new MedizinischeDatenErkrankungenRecycleAdapter(nutzerDto);
        recyclerViewErkankungen.setAdapter(adapterErkrankungen);

        fillSpinnerBlutgruppe(root);
        handleFields(root);

        return root;
    }

    private void fillSpinnerBlutgruppe(View root) {
        // fill Spinner "Blutgruppe"
        BlutgruppenEnum[] values = BlutgruppenEnum.values();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, BlutgruppenEnum.valuesAsString());
        Spinner blutgruppeSpinner = root.findViewById(R.id.spinnerBlutgruppe);
        blutgruppeSpinner.setAdapter(adapter);
    }

    private void handleFields(View root) {
        // Blutgruppe
        final Spinner blutgruppe = root.findViewById(R.id.spinnerBlutgruppe);
        blutgruppe.setSelection(BlutgruppenEnum.findPositionByElement(nutzerDto.getMedizinischeInformationen().getBlutgruppe()));


        blutgruppe.setOnItemSelectedListener(new OnItemSelectedListener () {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nutzerDto.getMedizinischeInformationen().setBlutgruppe(BlutgruppenEnum.setValueByPosition(position));
                writeNutzerToSharedPreferences();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nichts zu tun
            }
        });

        Switch switchOrganspender = root.findViewById(R.id.switchOrganspender);
        switchOrganspender.setChecked(nutzerDto.getMedizinischeInformationen().getOrganspender().isIstOrganspender());
        switchOrganspender.setOnCheckedChangeListener((buttonView, isChecked) -> {
            nutzerDto.getMedizinischeInformationen().getOrganspender().setIstOrganspender(isChecked);
            writeNutzerToSharedPreferences();

        });

    }

    @Override
    public void onResume() {
        super.onResume();
        adapterMedikamente.notifyDataSetChanged();
        adapterErkrankungen.notifyDataSetChanged();
        adapterAllergien.notifyDataSetChanged();
    }
    private void writeNutzerToSharedPreferences() {
        StoredDataManager.writeStorageData(getActivity().getSharedPreferences(getString(R.string.shared_preferences_user_data), Context.MODE_PRIVATE), getString(R.string.shared_preferences_user_data_json), nutzerDto);
    }
}