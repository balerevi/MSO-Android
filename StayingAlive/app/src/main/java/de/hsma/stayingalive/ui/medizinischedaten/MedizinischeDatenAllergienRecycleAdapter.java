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
import de.hsma.stayingalive.dto.AllergieDTO;
import de.hsma.stayingalive.dto.NutzerDTO;


class MedizinischeDatenAllergienRecycleAdapter extends RecyclerView.Adapter<MedizinischeDatenAllergienRecycleAdapter.MedizinischeDatenAllergienHolder> {

    private NutzerDTO nutzerDTO;

    public MedizinischeDatenAllergienRecycleAdapter(NutzerDTO nutzerDto) {
        this.nutzerDTO = nutzerDto;
    }

    @NonNull
    @Override
    public MedizinischeDatenAllergienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_medizin, parent, false);
        MedizinischeDatenAllergienHolder vh = new MedizinischeDatenAllergienHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MedizinischeDatenAllergienHolder holder, int position) {
        if (!nutzerDTO.getMedizinischeInformationen().getAllergien().isEmpty()) {
            AllergieDTO allergieDTO = nutzerDTO.getMedizinischeInformationen().getAllergien().get(position);
            if (allergieDTO != null) {
                String name = allergieDTO.getAllergie();

                if (name != null) {
                    holder.name.setText(name);
                    holder.removeBtn.setId(position);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return nutzerDTO.getMedizinischeInformationen().getMedikamente().size();
    }

    public class MedizinischeDatenAllergienHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public Button removeBtn;

        @SuppressLint("ResourceType")
        public MedizinischeDatenAllergienHolder(View v) {
            super(v);
            name = v.findViewById(R.id.MedizinAnzeigename);
            removeBtn = v.findViewById(R.id.buttonRemove);
            removeBtn.setOnClickListener(v1 -> {
                nutzerDTO.getMedizinischeInformationen().getAllergien().remove(v1.getId());
                notifyDataSetChanged();
            });
        }
    }
}
