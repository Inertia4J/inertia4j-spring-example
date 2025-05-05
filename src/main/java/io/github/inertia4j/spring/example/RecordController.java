package io.github.inertia4j.spring.example;

import io.github.inertia4j.spring.Inertia;
import io.github.inertia4j.spring.Inertia.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
public class RecordController {

    @Autowired
    private Inertia inertia;

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/")
    public ResponseEntity<String> index() {
        Set<Record> records = recordRepository.getAllRecords();

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
        return inertia.redirect("/records");
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<String> show(@PathVariable int id) {
        Record record = recordRepository.getRecordById(id);

        return inertia.render("records/Show", Map.of("record", record));
    }

    // TODO: Add external usage example
    @GetMapping("/externalRedirect")
    public ResponseEntity<String> externalRedirect() {
        return inertia.location("https://inertiajs.com/");
    }

}
