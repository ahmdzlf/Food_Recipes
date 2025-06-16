package com.azarcorp.food_recipes;

public class Resep {
    private String id; // <- penting untuk Firebase document ID
    private String nama;
    private String kategori;
    private String porsi;
    private String durasi;
    private String bahan;
    private String langkah;
    private String imageUrl;

    public Resep() {
    }

    public Resep(String nama, String kategori, String porsi, String durasi, String bahan, String langkah, String imageUrl) {
        this.nama = nama;
        this.kategori = kategori;
        this.porsi = porsi;
        this.durasi = durasi;
        this.bahan = bahan;
        this.langkah = langkah;
        this.imageUrl = imageUrl;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNama() { return nama; }
    public String getKategori() { return kategori; }
    public String getPorsi() { return porsi; }
    public String getDurasi() { return durasi; }
    public String getBahan() { return bahan; }
    public String getLangkah() { return langkah; }
    public String getImageUrl() { return imageUrl; }
}
