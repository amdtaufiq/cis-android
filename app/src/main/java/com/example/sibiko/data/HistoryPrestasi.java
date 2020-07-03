package com.example.sibiko.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HistoryPrestasi implements Serializable {

    @SerializedName("nama_prestasi")
    private String nama_prestasi;
    @SerializedName("tanggal_prestasi")
    private String tanggal_prestasi;
    @SerializedName("nama_user")
    private String nama_user;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;

    public String getNama_prestasi() {
        return nama_prestasi;
    }

    public void setNama_prestasi(String nama_prestasi) {
        this.nama_prestasi = nama_prestasi;
    }

    public String getTanggal_prestasi() {
        return tanggal_prestasi;
    }

    public void setTanggal_prestasi(String tanggal_prestasi) {
        this.tanggal_prestasi = tanggal_prestasi;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
