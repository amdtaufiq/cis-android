package com.example.sibiko.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pelanggaran implements Serializable {

    @SerializedName("pelanggaran_id")
    private int pelanggaran_id;
    @SerializedName("nama_pelanggaran")
    private String nama_pelanggaran;
    @SerializedName("poin_pelanggaran")
    private int poin_pelanggaran;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;

    public int getPelanggaran_id() {
        return pelanggaran_id;
    }

    public String getNama_pelanggaran() {
        return nama_pelanggaran;
    }

    public int getPoin_pelanggaran() {
        return poin_pelanggaran;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
