package com.example.relatorios.controller;

import com.example.relatorios.service.RelatorioExportService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relatorio")
public class RelatorioExportController {

    private final RelatorioExportService service;

    public RelatorioExportController(RelatorioExportService service) {
        this.service = service;
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> pdf() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(service.gerarPDF());
    }

    @GetMapping("/html")
    public ResponseEntity<byte[]> html() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio.html")
                .contentType(MediaType.TEXT_HTML)
                .body(service.gerarHTML());
    }

    @GetMapping("/xls")
    public ResponseEntity<byte[]> xls() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio.xls")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(service.gerarXLS());
    }
}
