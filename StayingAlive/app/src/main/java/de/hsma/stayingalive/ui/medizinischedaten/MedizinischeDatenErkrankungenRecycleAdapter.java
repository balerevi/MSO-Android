package de.hsma.stayingalive.ui.medizinischedaten;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hsma.stayingalive.R;
import de.hsma.stayingalive.dto.ErkankungUndBefundeDTO;
import de.hsma.stayingalive.dto.NutzerDTO;


class MedizinischeDatenErkrankungenRecycleAdapter extends RecyclerView.Adapter<MedizinischeDatenErkrankungenRecycleAdapter.MedizinischeDatenErkrankungenHolder> {

    private NutzerDTO nutzerDTO;

    public MedizinischeDatenErkrankungenRecycleAdapter(NutzerDTO nutzerDto) {
        this.nutzerDTO = nutzerDto;
    }

    @NonNull
    @Override
    public MedizinischeDatenErkrankungenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_medizin, parent, false);
        MedizinischeDatenErkrankungenHolder vh = new MedizinischeDatenErkrankungenHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MedizinischeDatenErkrankungenHolder holder, int position) {
        if (!nutzerDTO.getMedizinischeInformationen().getErkankungUndBefunde().isEmpty()) {
            ErkankungUndBefundeDTO erkankungUndBefundeDTO = nutzerDTO.getMedizinischeInformationen().getErkankungUndBefunde().get(position);
            if (erkankungUndBefundeDTO != null) {
                String name = erkankungUndBefundeDTO.getName();

                if (name != null) {
                    holder.name.setText(name);
                    holder.removeBtn.setId(position);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return nutzerDTO.getMedizinischeInformationen().getErkankungUndBefunde().size();
    }

    public class MedizinischeDatenErkrankungenHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public Button removeBtn;

        @SuppressLint("ResourceType")
        public MedizinischeDatenErkrankungenHolder(View v) {
            super(v);
            name = v.findViewById(R.id.MedizinAnzeigename);
            name.setTextColor(R.color.colorBlue);
            removeBtn = v.findViewById(R.id.buttonRemove);
            removeBtn.setOnClickListener(v1 -> {
                nutzerDTO.getMedizinischeInformationen().getErkankungUndBefunde().remove(v1.getId());
                notifyDataSetChanged();
            });
        }
    }
}
