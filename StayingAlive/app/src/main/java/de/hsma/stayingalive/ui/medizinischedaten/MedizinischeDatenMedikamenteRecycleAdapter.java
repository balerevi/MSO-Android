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
import de.hsma.stayingalive.dto.MedikamentDTO;
import de.hsma.stayingalive.dto.NutzerDTO;


class MedizinischeDatenMedikamenteRecycleAdapter extends RecyclerView.Adapter<MedizinischeDatenMedikamenteRecycleAdapter.MedizinischeDatenMedikamenteHolder>  {

    private NutzerDTO nutzerDTO;

    public MedizinischeDatenMedikamenteRecycleAdapter(NutzerDTO nutzerDto) {
        this.nutzerDTO = nutzerDto;
    }

    @NonNull
    @Override
    public MedizinischeDatenMedikamenteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_medizin, parent, false);
        MedizinischeDatenMedikamenteHolder vh = new MedizinischeDatenMedikamenteHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MedizinischeDatenMedikamenteHolder holder, int position) {
        MedikamentDTO currentMedikamentDto = nutzerDTO.getMedizinischeInformationen().getMedikamente().get(position);
        if (currentMedikamentDto != null) {
//            String art = currentKontakt.getKontaktart().getValue();
            String name = currentMedikamentDto.getName();
            String dosierung = currentMedikamentDto.getDosierung();

            StringBuilder sb = new StringBuilder();
            if (name != null) {
                sb.append(name);
            }
//            if (art!=null){
//                sb.append(", ");
//                sb.append(art);
//            }
            if (dosierung != null) {
                sb.append(", ");
                sb.append(dosierung);
            }
            holder.name.setText(sb.toString());
        }
    }


    @Override
    public int getItemCount() {
        return nutzerDTO.getMedizinischeInformationen().getMedikamente().size();
    }

    public class MedizinischeDatenMedikamenteHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public Button removeBtn;

        @SuppressLint("ResourceType")
        public MedizinischeDatenMedikamenteHolder(View v) {
            super(v);
            name = v.findViewById(R.id.MedizinAnzeigename);
            removeBtn = v.findViewById(R.id.buttonRemove);
            //removeBtn.setId(1234);
            removeBtn.setOnClickListener(v1 -> {

            });
        }
    }
}
