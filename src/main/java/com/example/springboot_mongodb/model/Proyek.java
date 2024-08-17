package com.example.springboot_mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Document(collection = "proyek")
public class Proyek {
    @Id
    private Integer id;
    private String namaProyek;
    private String client;
    private Date tglMulai;
    private Date tglSelesai;
    private String pimpinanProyek;
    private String keterangan;
    private Long createdAt; 
    private List<Integer> lokasiIds;

    public Proyek() {
        this.createdAt = System.currentTimeMillis(); // Initialize with current timestamp
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNamaProyek() { return namaProyek; }
    public void setNamaProyek(String namaProyek) { this.namaProyek = namaProyek; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public Date getTglMulai() { return tglMulai; }
    public void setTglMulai(Date tglMulai) { this.tglMulai = tglMulai; }

    public Date getTglSelesai() { return tglSelesai; }
    public void setTglSelesai(Date tglSelesai) { this.tglSelesai = tglSelesai; }

    public String getPimpinanProyek() { return pimpinanProyek; }
    public void setPimpinanProyek(String pimpinanProyek) { this.pimpinanProyek = pimpinanProyek; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    public Long getCreatedAt() { return createdAt; }
    public void setCreatedAt(Long createdAt) { this.createdAt = createdAt; }

    public List<Integer> getLokasiIds() { return lokasiIds; }
    public void setLokasiIds(List<Integer> lokasiIds) { this.lokasiIds = lokasiIds; }
}
