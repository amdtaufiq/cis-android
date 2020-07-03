package com.example.sibiko.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Siswa implements Serializable {
    @SerializedName("siswa_id")
    private int siswa_id;
    @SerializedName("poin_pelanggaran_siswa")
    private int poin_poin_pelanggaran_siswa;
    @SerializedName("nama_siswa")
    private String nama_siswa;
    @SerializedName("nis")
    private String nis;
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;
    @SerializedName("tingkat_kelas")
    private String tingkat_kelas;
    @SerializedName("kode_jurusan")
    private String kode_jurusan;
    @SerializedName("tipe_kelas")
    private String tipe_kelas;
    @SerializedName("tahap")
    private String tahap;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;

    public int getSiswa_id() {
        return siswa_id;
    }

    public int getPoin_poin_pelanggaran_siswa() {
        return poin_poin_pelanggaran_siswa;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public String getnis() {
        return nis;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getTingkat_kelas() {
        return tingkat_kelas;
    }

    public String getKode_jurusan() {
        return kode_jurusan;
    }

    public String getTipe_kelas() {
        return tipe_kelas;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public void setSiswa_id(int siswa_id) {
        this.siswa_id = siswa_id;
    }

    public void setPoin_poin_pelanggaran_siswa(int poin_poin_pelanggaran_siswa) {
        this.poin_poin_pelanggaran_siswa = poin_poin_pelanggaran_siswa;
    }

    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public void setnis(String nis) {
        this.nis = nis;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public void setTingkat_kelas(String tingkat_kelas) {
        this.tingkat_kelas = tingkat_kelas;
    }

    public void setKode_jurusan(String kode_jurusan) {
        this.kode_jurusan = kode_jurusan;
    }

    public void setTipe_kelas(String tipe_kelas) {
        this.tipe_kelas = tipe_kelas;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getTahap() {
        return tahap;
    }

    public void setTahap(String tahap) {
        this.tahap = tahap;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
