package com.example.relatorios.controller;

import com.example.relatorios.model.Produto;
import com.example.relatorios.service.RelatorioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/relatorio/produtos")
    public ResponseEntity<byte[]> gerarPdfProdutos() {

        List<Produto> produtos = Arrays.asList(
                new Produto(1L, "Notebook Lenovo", 3500.00),
                new Produto(2L, "Mouse Logitech", 199.90),
                new Produto(3L, "Monitor 27\"", 1299.00)
        );

        byte[] pdf = relatorioService.gerarRelatorioProdutos(produtos);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=produtos.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
