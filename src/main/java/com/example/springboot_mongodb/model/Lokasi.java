package com.example.springboot_mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lokasi")
public class Lokasi {
    @Id
    private Integer id;
    private String namaLokasi;
    private String negara;
    private String provinsi;
    private String kota;
    private long createdAt;

    public Lokasi() {
        this.createdAt = System.currentTimeMillis();
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNamaLokasi() { return namaLokasi; }
    public void setNamaLokasi(String namaLokasi) { this.namaLokasi = namaLokasi; }

    public String getNegara() { return negara; }
    public void setNegara(String negara) { this.negara = negara; }

    public String getProvinsi() { return provinsi; }
    public void setProvinsi(String provinsi) { this.provinsi = provinsi; }

    public String getKota() { return kota; }
    public void setKota(String kota) { this.kota = kota; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
