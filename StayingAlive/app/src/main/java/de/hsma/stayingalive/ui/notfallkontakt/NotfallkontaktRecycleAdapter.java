package de.hsma.stayingalive.ui.notfallkontakt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hsma.stayingalive.R;
import de.hsma.stayingalive.dto.NotfallkontaktDTO;
import de.hsma.stayingalive.dto.NutzerDTO;

class NotfallkontaktRecycleAdapter extends RecyclerView.Adapter<NotfallkontaktRecycleAdapter.MyHolder> {

    private NutzerDTO nutzerDTO;
    private int btnCt = 0;

    public NotfallkontaktRecycleAdapter(NutzerDTO nutzerDTO) {
        this.nutzerDTO = nutzerDTO;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_notfallkontakte, parent, false);
        MyHolder vh = new MyHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        NotfallkontaktDTO currentKontakt = nutzerDTO.getPrivateDaten().getNotfallkontakte().get(position);
        if (currentKontakt != null) {
//            String art = currentKontakt.getKontaktart().getValue();
            String name = currentKontakt.getName();
            String nr = currentKontakt.getKontaktdaten().getHandynummer();

            StringBuilder sb = new StringBuilder();
            if (name!=null){
                sb.append(name);
            }
//            if (art!=null){
//                sb.append(", ");
//                sb.append(art);
//            }
            if (nr!=null){
                sb.append(", ");
                sb.append(nr);
            }
            holder.name.setText(sb.toString());
        }
    }

    @Override
    public int getItemCount() {
        return nutzerDTO.getPrivateDaten().getNotfallkontakte().size();
    }

    public int getBtnCt() {
        btnCt++;
        return btnCt;
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public Button removeBtn;

        public MyHolder(View v) {
            super(v);
            name = v.findViewById(R.id.NotfallKontaktAnzeigename);
            removeBtn = v.findViewById(R.id.buttonRemove);
            removeBtn.setId(getBtnCt());
            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
