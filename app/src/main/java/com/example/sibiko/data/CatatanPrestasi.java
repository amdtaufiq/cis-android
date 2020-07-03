package com.example.sibiko.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CatatanPrestasi implements Serializable {
    @SerializedName("catatan_prestasi_id")
    private int catatan_prestasi_id;
    @SerializedName("nama_prestasi")
    private String nama_prestasi;
    @SerializedName("siswa_id")
    private int siswa_id;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public int getCatatan_prestasi_id() {
        return catatan_prestasi_id;
    }

    public int getSiswa_id() {
        return siswa_id;
    }

    public String getnama_prestasi() {
        return nama_prestasi;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getValue() {
        return value;
    }

    public String getMassage() {
        return massage;
    }
}
