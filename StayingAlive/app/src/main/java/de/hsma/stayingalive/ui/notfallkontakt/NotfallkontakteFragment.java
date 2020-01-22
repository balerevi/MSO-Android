package de.hsma.stayingalive.ui.notfallkontakt;

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
import de.hsma.stayingalive.dto.KontaktartEnum;
import de.hsma.stayingalive.dto.NutzerDTO;
import de.hsma.stayingalive.manager.NutzerDTOManager;

public class NotfallkontakteFragment extends Fragment {

    private NutzerDTO nutzerDto;
    private RecyclerView recyclerViewKontaktliste;
    private RecyclerView.Adapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notfallkontakte, container, false);

        NutzerDTOManager instance = NutzerDTOManager.getInstance();
        nutzerDto = instance.getNutzerDto();

        recyclerViewKontaktliste = root.findViewById(R.id.kontaktListe);
        recyclerViewKontaktliste.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new NotfallkontaktRecycleAdapter(nutzerDto);
        recyclerViewKontaktliste.setAdapter(mAdapter);
        fillSpinnerKontaktArt(root);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    private void fillSpinnerKontaktArt(View root) {
        // fill Spinner "Kontaktart"
        KontaktartEnum[] values = KontaktartEnum.values();
        ArrayAdapter<KontaktartEnum> adapter = new ArrayAdapter<KontaktartEnum>(getContext(), R.layout.support_simple_spinner_dropdown_item, KontaktartEnum.values());
        Spinner kontaktartSpinner = root.findViewById(R.id.spinnerKontaktArt);
        kontaktartSpinner.setAdapter(adapter);
    }
}

