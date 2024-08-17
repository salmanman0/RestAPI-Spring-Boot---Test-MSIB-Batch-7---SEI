package com.example.springboot_mongodb.controller;

import com.example.springboot_mongodb.model.Lokasi;
import com.example.springboot_mongodb.model.Counter;
import com.example.springboot_mongodb.repository.LokasiRepository;
import com.example.springboot_mongodb.repository.CounterRepository;
import com.example.springboot_mongodb.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LokasiController {

    @Autowired
    private LokasiRepository lokasiRepository;

    @Autowired
    private CounterRepository counterRepository;

    private synchronized int getNextSequence(String seqName) {
        Counter counter = counterRepository.findById(seqName).orElse(new Counter(seqName, 0));
        int nextId = counter.getSeq() + 1;
        counter.setSeq(nextId);
        counterRepository.save(counter);
        return nextId;
    }
    

    @PostMapping("/lokasi")
    public ResponseEntity<ApiResponse<Lokasi>> createLokasi(@RequestBody Lokasi lokasi) {
        try {
            lokasi.setId(getNextSequence("lokasi_seq"));
            lokasi.setCreatedAt(System.currentTimeMillis()); // Use timestamp for createdAt
            Lokasi savedLokasi = lokasiRepository.save(lokasi);
            ApiResponse<Lokasi> response = new ApiResponse<>("sukses", List.of(savedLokasi));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Lokasi> response = new ApiResponse<>("gagal", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/lokasi")
    public ResponseEntity<ApiResponse<Lokasi>> getAllLokasi() {
        try {
            List<Lokasi> lokasiList = lokasiRepository.findAll();
            ApiResponse<Lokasi> response = new ApiResponse<>("sukses", lokasiList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Lokasi> response = new ApiResponse<>("gagal", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @PutMapping("/lokasi/{id}")
    public ResponseEntity<ApiResponse<Lokasi>> updateLokasi(@PathVariable Integer id, @RequestBody Lokasi lokasiDetails) {
        try {
            Optional<Lokasi> lokasi = lokasiRepository.findById(id);

            if (lokasi.isPresent()) {
                Lokasi updatedLokasi = lokasi.get();
                updatedLokasi.setNamaLokasi(lokasiDetails.getNamaLokasi());
                updatedLokasi.setNegara(lokasiDetails.getNegara());
                updatedLokasi.setProvinsi(lokasiDetails.getProvinsi());
                updatedLokasi.setKota(lokasiDetails.getKota());
                // Optionally update createdAt or keep it unchanged
                lokasiRepository.save(updatedLokasi);
                ApiResponse<Lokasi> response = new ApiResponse<>("sukses", List.of(updatedLokasi));
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Lokasi> response = new ApiResponse<>("gagal", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Lokasi> response = new ApiResponse<>("gagal", null);
            return ResponseEntity.status(500).body(response);
        }
    }

    @DeleteMapping("/lokasi/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLokasi(@PathVariable Integer id) {
        try {
            if (lokasiRepository.existsById(id)) {
                lokasiRepository.deleteById(id);
                ApiResponse<Void> response = new ApiResponse<>("sukses", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>("gagal", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Void> response = new ApiResponse<>("gagal", null);
            return ResponseEntity.status(500).body(response);
        }
    }
}
