package com.example.sibiko.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sibiko.R;
import com.example.sibiko.SubmitPelanggaranSiswaActivity;
import com.example.sibiko.data.Pelanggaran;
import com.example.sibiko.data.Siswa;
import com.example.sibiko.data.User;

import java.util.List;

public class AdapterDataPelanggaran extends RecyclerView.Adapter<AdapterDataPelanggaran.ViewHolder>{

    private List<Pelanggaran> pelanggarans;
    private Context context;

    public AdapterDataPelanggaran(List<Pelanggaran> pelanggarans, Context context) {
        this.pelanggarans = pelanggarans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Pelanggaran pelanggaran = pelanggarans.get(position);
        holder.poin_pelanggaran.setText(String.valueOf(pelanggaran.getPoin_pelanggaran())+" Poin ");
        holder.nama_pelanggaran.setText(pelanggaran.getNama_pelanggaran());

        final Intent intent = ((Activity) context).getIntent();
        final Siswa siswa = (Siswa) intent.getSerializableExtra("siswa");
        siswa.setSiswa_id(siswa.getSiswa_id());
        siswa.setPoin_poin_pelanggaran_siswa(siswa.getPoin_poin_pelanggaran_siswa());
        siswa.setNama_siswa(siswa.getNama_siswa());
        siswa.setnis(siswa.getnis());
        siswa.setJenis_kelamin(siswa.getJenis_kelamin());
        siswa.setTingkat_kelas(siswa.getTingkat_kelas());
        siswa.setKode_jurusan(siswa.getKode_jurusan());
        siswa.setTipe_kelas(siswa.getTipe_kelas());

        final User user = (User) intent.getSerializableExtra("user");
        user.setUser_id(user.getUser_id());
        user.setNama_user(user.getNama_user());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPelanggaran = new Intent(context, SubmitPelanggaranSiswaActivity.class);
                intentPelanggaran.putExtra("pelanggaran",pelanggaran);
                intentPelanggaran.putExtra("siswa",siswa);
                intentPelanggaran.putExtra("user",user);
                context.startActivity(intentPelanggaran);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pelanggarans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama_pelanggaran, poin_pelanggaran;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poin_pelanggaran = itemView.findViewById(R.id.textViewKeterangan);
            nama_pelanggaran = itemView.findViewById(R.id.tv_list_keterangan);
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//
//            Intent i = new Intent(context, DataPelanggaranActivity.class);
//            i.putExtra("pelanggaran_id", pelanggarans.get(getAdapterPosition()).getPelanggaran_id());
//            i.putExtra("poin_pelanggaran", pelanggarans.get(getAdapterPosition()).getPoin_pelanggaran());
//            i.putExtra("nama_pelanggaran", pelanggarans.get(getAdapterPosition()).getNama_pelanggaran());
//            context.startActivity(i);
//        }
    }
}
