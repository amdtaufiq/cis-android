package com.example.sibiko.api;

import com.example.sibiko.data.CatatanPelanggaran;
import com.example.sibiko.data.CatatanPrestasi;
import com.example.sibiko.data.HistoryPrestasi;
import com.example.sibiko.data.Pelanggaran;
import com.example.sibiko.data.Siswa;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("Login.php")
    Call<ResponseBody> loginRequest(
            @Field("username") String username,
            @Field("password") String password);

    @GET("getValue.php")
    Call<List<Siswa>> getsiswa(
            @Query("item_type") String item_type,
            @Query("key") String keyword
    );

    @GET("gethistoryprestasi.php")
    Call<List<HistoryPrestasi>> gethistoryprestasi(
            @Query("item_type") String item_type,
            @Query("key") String keyword
    );

    @GET("getpelanggaran.php")
    Call<List<Pelanggaran>> getpelanggaran(
            @Query("item_type") String item_type,
            @Query("key") String keyword
    );

    @FormUrlEncoded
    @POST("submitpelanggaran.php")
    Call <CatatanPelanggaran> submitpelanggaran(
            @Field("siswa_id") int siswa_id,
            @Field("pelanggaran_id") int pelanggaran_id,
            @Field("user_id") int user_id,
            @Field("aksi") String aksi,
            @Field("bukti") String bukti,
            @Field("info") String info
    );

    @FormUrlEncoded
    @POST("submitprestasi.php")
    Call <CatatanPrestasi> submitprestasi(
            @Field("nama_prestasi") String nama_prestasi,
            @Field("siswa_id") int siswa_id,
            @Field("user_id") int user_id,
            @Field("bukti") String bukti
            );
}
