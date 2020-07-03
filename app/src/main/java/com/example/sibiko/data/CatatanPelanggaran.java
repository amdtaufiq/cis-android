package com.example.sibiko.data;

import com.google.gson.annotations.SerializedName;

public class CatatanPelanggaran {
    @SerializedName("catatan_poin_pelanggaran_id")
    private int catatan_poin_pelanggaran_id;
    @SerializedName("siswa_id")
    private int siswa_id;
    @SerializedName("pelanggaran_id")
    private int pelanggaran_id;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("aksi")
    private String aksi;
    @SerializedName("bukti")
    private String bukti;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public int getCatatan_poin_pelanggaran_id() {
        return catatan_poin_pelanggaran_id;
    }

    public int getSiswa_id() {
        return siswa_id;
    }

    public int getPelanggaran_id() {
        return pelanggaran_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAksi() { return aksi; }

    public String getBukti() {
        return bukti;
    }

    public String getValue() {
        return value;
    }

    public String getMassage() {
        return massage;
    }


}
