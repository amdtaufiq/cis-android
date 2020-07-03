package com.example.sibiko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sibiko.adapter.AdapterSiswaPelanggaran;
import com.example.sibiko.api.ApiService;
import com.example.sibiko.api.UtilsApi;
import com.example.sibiko.data.Siswa;
import com.example.sibiko.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPelanggaranActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView back;
    private List<Siswa> siswaList;
    private AdapterSiswaPelanggaran adapter;
    private ApiService apiService;

//    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pelanggaran);

//        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycler_data);
        back = findViewById(R.id.back);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        apiService = UtilsApi.getAPIService();

        final User user = (User) getIntent().getSerializableExtra("user");
        user.setUser_id(user.getUser_id());
        user.setNama_user(user.getNama_user());


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.search_siswa);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchContact("siswa", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchContact("siswa", newText);
                return false;
            }
        });
    }

    private void fetchContact(String type, String key) {
        Call<List<Siswa>> call = apiService.getsiswa(type, key);
        call.enqueue(new Callback<List<Siswa>>() {
            @Override
            public void onResponse(Call<List<Siswa>> call, Response<List<Siswa>> response) {
//                progressBar.setVisibility(View.GONE);
                siswaList = response.body();
                adapter = new AdapterSiswaPelanggaran(siswaList, SearchPelanggaranActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Siswa>> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
                Toast.makeText(SearchPelanggaranActivity.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
