package de.hsma.stayingalive.ui.medizinischedaten;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hsma.stayingalive.R;
import de.hsma.stayingalive.dto.BlutgruppenEnum;
import de.hsma.stayingalive.dto.NutzerDTO;
import de.hsma.stayingalive.manager.NutzerDTOManager;

public class MedzinischeDatenFragment extends Fragment {

    private NutzerDTO nutzerDto;
    private RecyclerView recyclerViewMedikamente;
    private RecyclerView recyclerViewAllergien;
    private RecyclerView recyclerViewErkankungen;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_medizinische_daten, container, false);
        nutzerDto = NutzerDTOManager.getInstance().getNutzerDto();

        recyclerViewMedikamente = (RecyclerView) root.findViewById(R.id.medikamentenliste);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewMedikamente.setLayoutManager(layoutManager);

        mAdapter = new MedizinischeDatenRecycleAdapter(nutzerDto);
        recyclerViewMedikamente.setAdapter(mAdapter);
        fillSpinnerBlutgruppe(root);
        handleFields(root);

        return root;
    }

    private void fillSpinnerBlutgruppe(View root) {
        // fill Spinner "Kontaktart"
        BlutgruppenEnum[] values = BlutgruppenEnum.values();
        ArrayAdapter<BlutgruppenEnum> adapter = new ArrayAdapter<BlutgruppenEnum>(getContext(), R.layout.support_simple_spinner_dropdown_item, BlutgruppenEnum.values());
        Spinner blutgruppeSpinner = root.findViewById(R.id.spinnerBlutgruppe);
        blutgruppeSpinner.setAdapter(adapter);
    }

    private void handleFields(View root) {

    }
}