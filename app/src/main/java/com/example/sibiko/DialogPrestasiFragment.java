package com.example.sibiko;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sibiko.adapter.AdapterDataHistoryPrestasi;
import com.example.sibiko.api.ApiService;
import com.example.sibiko.data.HistoryPrestasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogPrestasiFragment extends DialogFragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<HistoryPrestasi> historyPrestasis;
    private AdapterDataHistoryPrestasi adapter;
    private ApiService apiService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.dialog_prestasi_fragment, container, false);
        TextView textView = view.findViewById(R.id.textView6);
        int siswa_id = getArguments().getInt("siswa_id");
        String key = String.valueOf(siswa_id);
        String type = "prestasi";
        Call<List<HistoryPrestasi>> call = apiService.gethistoryprestasi(type, key);
        call.enqueue(new Callback<List<HistoryPrestasi>>() {
            @Override
            public void onResponse(Call<List<HistoryPrestasi>> call, Response<List<HistoryPrestasi>> response) {
//                historyPrestasis = response.body();
//                adapter = new AdapterDataHistoryPrestasi(historyPrestasis, getContext());
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HistoryPrestasi>> call, Throwable t) {
//                Toast.makeText(getContext(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }


}
