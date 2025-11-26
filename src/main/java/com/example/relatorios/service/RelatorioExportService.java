package com.example.relatorios.service;

import com.example.relatorios.model.Produto;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsExporterBuilder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class RelatorioExportService {

    private List<Produto> criarMock() {
        return Arrays.asList(
                new Produto(1L, "Curso completo de Java - Prof. Johnny Soares", 8500.00),
                new Produto(2L, "Curso de construção de motores para aeronaves comerciais - Prof. Victor Samuel", 10000.00),
                new Produto(3L, "Ganhe dinheiro dormindo e fique bilionário antes dos 30 - Prof. Gabriel Zeni", 4200.00)
        );
    }

    // ---------------- PDF ----------------
    public byte[] gerarPDF() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            DynamicReports.report()
                    .setPageFormat(PageType.A4)
                    .columns(
                            Columns.column("ID", "id", DynamicReports.type.longType()),
                            Columns.column("Nome", "nome", DynamicReports.type.stringType()),
                            Columns.column("Preço", "preco", DynamicReports.type.doubleType())
                    )
                    .title(DynamicReports.cmp.text("Relatório de Produtos (PDF)"))
                    .setDataSource(criarMock())
                    .toPdf(baos);

            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    // ---------------- HTML ----------------
    public byte[] gerarHTML() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            JasperHtmlExporterBuilder htmlExporter = DynamicReports.export.htmlExporter(baos);

            DynamicReports.report()
                    .setPageFormat(PageType.A4)
                    .columns(
                            Columns.column("ID", "id", DynamicReports.type.longType()),
                            Columns.column("Nome", "nome", DynamicReports.type.stringType()),
                            Columns.column("Preço", "preco", DynamicReports.type.doubleType())
                    )
                    .title(DynamicReports.cmp.text("Relatório de Produtos (HTML)"))
                    .setDataSource(criarMock())
                    .toHtml(htmlExporter);

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar HTML", e);
        }
    }

    // ---------------- XLS ----------------
    public byte[] gerarXLS() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            JasperXlsExporterBuilder xlsExporter = DynamicReports.export.xlsExporter(baos)
                    .setDetectCellType(true)
                    .setIgnorePageMargins(true)
                    .setWhitePageBackground(false)
                    .setRemoveEmptySpaceBetweenColumns(true);

            DynamicReports.report()
                    .setPageFormat(PageType.A4)
                    .columns(
                            Columns.column("ID", "id", DynamicReports.type.longType()),
                            Columns.column("Nome", "nome", DynamicReports.type.stringType()),
                            Columns.column("Preço", "preco", DynamicReports.type.doubleType())
                    )
                    .title(DynamicReports.cmp.text("Relatório de Produtos (XLS)"))
                    .setDataSource(criarMock())
                    .toXls(xlsExporter);

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar XLS", e);
        }
    }
}
