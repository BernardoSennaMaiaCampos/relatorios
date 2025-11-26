package com.example.relatorios.service;

import com.example.relatorios.model.Produto;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.PageType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class RelatorioService {

    public byte[] gerarRelatorioProdutos(List<Produto> produtos) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            StyleBuilder bold = DynamicReports.stl.style().bold();

            DynamicReports.report()
                    .setPageFormat(PageType.A4)
                    .columns(
                            Columns.column("ID", "id", DynamicReports.type.longType()),
                            Columns.column("Nome do Produto", "nome", DynamicReports.type.stringType()),
                            Columns.column("Preço (R$)", "preco", DynamicReports.type.doubleType())
                    )
                    .title(
                            DynamicReports.cmp.text("Relatório de Produtos").setStyle(bold)
                    )
                    .setDataSource(produtos)
                    .toPdf(baos);

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }
}
