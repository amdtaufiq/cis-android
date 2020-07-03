package com.example.sibiko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sibiko.adapter.AdapterDataPelanggaran;
import com.example.sibiko.api.ApiService;
import com.example.sibiko.api.UtilsApi;
import com.example.sibiko.data.Pelanggaran;
import com.example.sibiko.data.Siswa;
import com.example.sibiko.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPelanggaranActivity extends AppCompatActivity {

    private TextView nis, nama, kelas, poinTotal, tahap, keterangan;
    private ImageView back;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Pelanggaran> pelanggaranList;
    private AdapterDataPelanggaran adapter;
    private ApiService apiService;
//    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pelanggaran);

//        progressBar = findViewById(R.id.progressBar2);
        recyclerView = findViewById(R.id.recycler_data);
        back = findViewById(R.id.back);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        apiService = UtilsApi.getAPIService();

        final User user = (User) getIntent().getSerializableExtra("user");
        user.setUser_id(user.getUser_id());
        user.setNama_user(user.getNama_user());

        Siswa siswa = (Siswa)getIntent().getSerializableExtra("siswa");

        nis = findViewById(R.id.nis);
        nama = findViewById(R.id.nama);
        kelas = findViewById(R.id.kelas);
        tahap = findViewById(R.id.textViewTahap);
        keterangan = findViewById(R.id.textViewKeterangan);

        nis.setText(siswa.getnis());
        nama.setText(siswa.getNama_siswa());
        kelas.setText(siswa.getTingkat_kelas()+" "+siswa.getKode_jurusan()+" "+siswa.getTipe_kelas());
        tahap.setText(String.valueOf(siswa.getTahap()));
        keterangan.setText(String.valueOf(siswa.getKeterangan()));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //search
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.search_pelanggaran);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchContact("pelanggaran", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchContact("pelanggaran", newText);
                return false;
            }
        });
    }

    private void fetchContact(String type, String key) {
        Call<List<Pelanggaran>> call = apiService.getpelanggaran(type, key);
        call.enqueue(new Callback<List<Pelanggaran>>() {
            @Override
            public void onResponse(Call<List<Pelanggaran>> call, Response<List<Pelanggaran>> response) {
//                    progressBar.setVisibility(View.GONE);
                    pelanggaranList = response.body();
                    adapter = new AdapterDataPelanggaran(pelanggaranList, DataPelanggaranActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Pelanggaran>> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
                Toast.makeText(DataPelanggaranActivity.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
