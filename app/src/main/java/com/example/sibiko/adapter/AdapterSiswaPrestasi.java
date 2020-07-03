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
import com.example.sibiko.SubmitPrestasiSiswaActivity;
import com.example.sibiko.data.Siswa;
import com.example.sibiko.data.User;

import java.util.List;

public class AdapterSiswaPrestasi extends RecyclerView.Adapter<AdapterSiswaPrestasi.ViewHoler>{

    private List<Siswa> siswas;
    Context context;

    public AdapterSiswaPrestasi(List<Siswa> siswas, Context context) {
        this.siswas = siswas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        final Siswa siswa = siswas.get(position);
        holder.nis.setText(siswa.getnis());
        holder.nama_siswa.setText(siswa.getNama_siswa());
        holder.tingkat.setText(siswa.getTingkat_kelas());
        holder.kode.setText(siswa.getKode_jurusan());
        holder.tipe.setText(siswa.getTipe_kelas());

        final Intent intent = ((Activity) context).getIntent();
        final User user = (User) intent.getSerializableExtra("user");
        user.setUser_id(user.getUser_id());
        user.setNama_user(user.getNama_user());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSiswa = new Intent(context, SubmitPrestasiSiswaActivity.class);
                intentSiswa.putExtra("siswa", siswa);
                intentSiswa.putExtra("user",user);
                context.startActivity(intentSiswa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return siswas.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView nis, nama_siswa, tingkat, kode, tipe;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            nis = itemView.findViewById(R.id.nis);
            nama_siswa = itemView.findViewById(R.id.nama);
            tingkat = itemView.findViewById(R.id.tv_tingkat);
            kode = itemView.findViewById(R.id.tv_kode);
            tipe = itemView.findViewById(R.id.tv_tipe);
        }
    }
}
