package io.gitlab.inertia4j.spring.example;

import io.github.inertia4j.spring.Inertia;
import io.github.inertia4j.spring.Inertia.Options;
import io.github.inertia4j.spring.Inertia;
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

    @GetMapping("/records/first")
    public ResponseEntity<String> first() {
        Record record = recordRepository.getRecordById(1);

        return inertia.render("records/Show", Map.of("record", record));
    }

    @PostMapping("/records")
    public ResponseEntity<String> create(@RequestBody Record record) {
        return inertia.redirect("/records/first");
    }

    @GetMapping("/encryptHistory")
    public ResponseEntity<String> indexEncryptHistory() {
        Set<Record> records = recordRepository.getAllRecords();

        return inertia.render("records/Index", Map.of("records", records), Options.encryptHistory());
    }

    @GetMapping("/clearHistory")
    public ResponseEntity<String> indexClearHistory() {
        Set<Record> records = recordRepository.getAllRecords();

        return inertia.render("records/Index", Map.of("records", records), Options.clearHistory());
    }

    @GetMapping("/allOptions")
    public ResponseEntity<String> allOptions() {
        Set<Record> records = recordRepository.getAllRecords();

        return inertia.render("records/Index", Map.of("records", records), Options.clearHistory().encryptHistory());
    }

    @GetMapping("/externalRedirect")
    public ResponseEntity<String> externalRedirect() {
        return inertia.location("https://inertiajs.com/");
    }

}
