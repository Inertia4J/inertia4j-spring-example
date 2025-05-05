package io.github.inertia4j.spring.example;

import io.github.inertia4j.spring.Inertia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
public class RecordController {

    @Autowired
    private Inertia inertia;

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/")
    public ResponseEntity<String> index() {
        List<Record> records = recordRepository.findAll();

        return inertia.render("records/Index", Map.of("records", records));
    }

    @GetMapping("/records")
    public ResponseEntity<String> records() {
        return inertia.redirect("/");
    }

    @GetMapping("/records/new")
    public ResponseEntity<String> newRecord() {
        return inertia.render("records/New", null);
    }

    @PostMapping("/records")
    public ResponseEntity<String> create(@RequestBody Record record) {
        recordRepository.save(record);

        return inertia.redirect("/records");
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<String> show(@PathVariable int id) {
        Record record = recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found"));;

        return inertia.render("records/Show", Map.of("record", record));
    }

    @DeleteMapping("/records/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable int id) {
        recordRepository.deleteById(id);

        return inertia.redirect("/records");
    }

    // TODO: Add external usage example
    @GetMapping("/externalRedirect")
    public ResponseEntity<String> externalRedirect() {
        return inertia.location("https://inertiajs.com/");
    }

}
