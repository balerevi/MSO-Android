package de.hsma.stayingalive.ui.medizinischedaten;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hsma.stayingalive.dto.NutzerDTO;

class MedizinischeDatenRecycleAdapter extends RecyclerView.Adapter {
    public MedizinischeDatenRecycleAdapter(NutzerDTO nutzerDto) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
