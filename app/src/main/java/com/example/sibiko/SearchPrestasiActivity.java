package com.example.sibiko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sibiko.adapter.AdapterSiswaPelanggaran;
import com.example.sibiko.adapter.AdapterSiswaPrestasi;
import com.example.sibiko.api.ApiService;
import com.example.sibiko.api.UtilsApi;
import com.example.sibiko.data.Siswa;
import com.example.sibiko.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPrestasiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Siswa> siswaList;
    private AdapterSiswaPrestasi adapter;
    private ApiService apiService;
//    ProgressBar progressBar;
    private ImageView back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_prestasi);

//        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycler_data);
        back = findViewById(R.id.back);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiService = UtilsApi.getAPIService();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
                adapter = new AdapterSiswaPrestasi(siswaList, SearchPrestasiActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Siswa>> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
                Toast.makeText(SearchPrestasiActivity.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
