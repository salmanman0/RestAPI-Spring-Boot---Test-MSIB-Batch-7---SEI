package com.example.springboot_mongodb.controller;

import com.example.springboot_mongodb.model.Proyek;
import com.example.springboot_mongodb.model.Counter;
import com.example.springboot_mongodb.repository.ProyekRepository;
import com.example.springboot_mongodb.repository.CounterRepository;
import com.example.springboot_mongodb.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProyekController {

    @Autowired
    private ProyekRepository proyekRepository;

    @Autowired
    private CounterRepository counterRepository;

    private synchronized int getNextSequence(String seqName) {
        Counter counter = counterRepository.findById(seqName).orElse(new Counter(seqName, 0));
        int nextId = counter.getSeq() + 1;
        counter.setSeq(nextId);
        counterRepository.save(counter);
        return nextId;
    }
    

    @PostMapping("/proyek")
    public ResponseEntity<ApiResponse<Proyek>> createProyek(@RequestBody Proyek proyek) {
        proyek.setId(getNextSequence("proyek_seq"));
        Proyek savedProyek = proyekRepository.save(proyek);
        ApiResponse<Proyek> response = new ApiResponse<>(
                "sukses",
                List.of(savedProyek)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/proyek")
    public ResponseEntity<ApiResponse<Proyek>> getAllProyek() {
        List<Proyek> proyekList = proyekRepository.findAll();
        ApiResponse<Proyek> response = new ApiResponse<>(
                "sukses",
                proyekList
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/proyek/{id}")
    public ResponseEntity<ApiResponse<Proyek>> updateProyek(@PathVariable Integer id, @RequestBody Proyek proyekDetails) {
        Optional<Proyek> proyek = proyekRepository.findById(id);

        if (proyek.isPresent()) {
            Proyek updatedProyek = proyek.get();
            updatedProyek.setNamaProyek(proyekDetails.getNamaProyek());
            updatedProyek.setClient(proyekDetails.getClient());
            updatedProyek.setTglMulai(proyekDetails.getTglMulai());
            updatedProyek.setTglSelesai(proyekDetails.getTglSelesai());
            updatedProyek.setPimpinanProyek(proyekDetails.getPimpinanProyek());
            updatedProyek.setKeterangan(proyekDetails.getKeterangan());
            updatedProyek.setLokasiIds(proyekDetails.getLokasiIds());
            proyekRepository.save(updatedProyek);
            ApiResponse<Proyek> response = new ApiResponse<>(
                    "sukses",
                    List.of(updatedProyek)
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Proyek> response = new ApiResponse<>(
                    "gagal",
                    null
            );
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/proyek/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProyek(@PathVariable Integer id) {
        if (proyekRepository.existsById(id)) {
            proyekRepository.deleteById(id);
            ApiResponse<Void> response = new ApiResponse<>(
                    "sukses",
                    null
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Void> response = new ApiResponse<>(
                    "gagal",
                    null
            );
            return ResponseEntity.status(404).body(response);
        }
    }
}
