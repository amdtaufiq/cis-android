package com.example.sibiko.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sibiko.R;
import com.example.sibiko.data.HistoryPrestasi;

import java.util.List;

public class AdapterDataHistoryPrestasi extends RecyclerView.Adapter<AdapterDataHistoryPrestasi.ViewHolder>{
    private List<HistoryPrestasi> history;
    Context context;

    public AdapterDataHistoryPrestasi(List<HistoryPrestasi> history, Context context) {
        this.history = history;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HistoryPrestasi historyPrestasi = history.get(position);
        holder.user.setText(historyPrestasi.getNama_user());
        holder.tanggal.setText(historyPrestasi.getTanggal_prestasi());
        holder.catatan.setText(historyPrestasi.getNama_prestasi());
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user, tanggal, catatan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.textViewUser);
            tanggal = itemView.findViewById(R.id.textViewDate);
            catatan = itemView.findViewById(R.id.textViewList);

        }
    }
}
