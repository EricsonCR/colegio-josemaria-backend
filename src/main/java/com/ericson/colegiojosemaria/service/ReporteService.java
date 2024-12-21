package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.dto.EmailDto;
import com.ericson.colegiojosemaria.interfaces.IReporte;
import com.ericson.colegiojosemaria.model.Estudiante;
import com.ericson.colegiojosemaria.model.Matricula;
import com.ericson.colegiojosemaria.model.Pago;
import com.ericson.colegiojosemaria.model.PagoDetalle;
import com.ericson.colegiojosemaria.repository.EstudianteRepository;
import com.ericson.colegiojosemaria.repository.MatriculaRepository;
import com.ericson.colegiojosemaria.repository.PagoDetalleRepository;
import com.ericson.colegiojosemaria.repository.PagoRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ReporteService implements IReporte {

    @Override
    public File reportePago(Pago pago) {
        try {
            ByteArrayOutputStream baos = designPdf(pago);
            String filePath = savePdf(baos, pago.getId());
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                return path.toFile();
            }
        } catch (DocumentException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    private String savePdf(ByteArrayOutputStream baos, long id) {
        String directoryPath = "src/main/resources/static/pdf";
        String fileName = "reporte_pago_" + id + ".pdf";
        File directory = new File(directoryPath);

        if (!directory.exists()) directory.mkdirs();

        File pdfFile = new File(directory, fileName);

        try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
            fos.write(baos.toByteArray());
            return directoryPath + File.separator + fileName;
        } catch (IOException e) {
            return "";
        }
    }

    private ByteArrayOutputStream designPdf(Pago pago) throws DocumentException {

        Document document = new Document();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, stream);
        document.open();

        PdfPTable headerTable = new PdfPTable(new float[]{2, 6});
        PdfPTable detailTable = new PdfPTable(new float[]{2, 8, 8});
        Paragraph p = new Paragraph();
        PdfPCell cell = new PdfPCell();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.BLACK);
        Paragraph title = new Paragraph("Reporte de Pago", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        p.setSpacingBefore(20);
        document.add(p);

        titleFont.setSize(14);
        headerTable.setWidthPercentage(100);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPhrase(new Phrase("Numero de Pago", titleFont));
        headerTable.addCell(cell);
        cell.setPhrase(new Phrase(": " + pago.getId()));
        headerTable.addCell(cell);
        cell.setPhrase(new Phrase("Monto Total", titleFont));
        headerTable.addCell(cell);
        cell.setPhrase(new Phrase(": " + pago.getMonto()));
        headerTable.addCell(cell);
        cell.setPhrase(new Phrase("Numero de OP", titleFont));
        headerTable.addCell(cell);
        cell.setPhrase(new Phrase(": " + pago.getNumero_op()));
        headerTable.addCell(cell);
        cell.setPhrase(new Phrase("Metodo de Pago", titleFont));
        headerTable.addCell(cell);
        cell.setPhrase(new Phrase(": " + pago.getMetodo_pago()));
        headerTable.addCell(cell);
        document.add(headerTable);

        p.setSpacingBefore(20);
        document.add(p);

        detailTable.setWidthPercentage(100);
        cell.setBorder(Rectangle.BOX);
        cell.setPadding(10f);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPhrase(new Phrase("ID", titleFont));
        detailTable.addCell(cell);
        cell.setPhrase(new Phrase("Descripcion", titleFont));
        detailTable.addCell(cell);
        cell.setPhrase(new Phrase("Monto", titleFont));
        detailTable.addCell(cell);
        cell.setBackgroundColor(BaseColor.WHITE);
        for (PagoDetalle detalle : pago.getPagoDetalle()) {
            cell.setPhrase(new Phrase(String.valueOf(detalle.getId_matricula_detalle())));
            detailTable.addCell(cell);
            cell.setPhrase(new Phrase(detalle.getConcepto()));
            detailTable.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(detalle.getMonto())));
            detailTable.addCell(cell);
        }
        document.add(detailTable);
        document.close();
        return stream;
    }
}
