package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IReporte;
import com.ericson.colegiojosemaria.model.Pago;
import com.ericson.colegiojosemaria.model.PagoDetalle;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReporteService implements IReporte {

    private final PagoDetalleRepository pagoDetalleRepository;
    private final PagoRepository pagoRepository;

    @Override
    public ResponseEntity<Map<String, Object>> reportePagoId(long id) throws DocumentException {
        Pago pago = new Pago();
        Optional<Pago> optional = pagoRepository.findById(id);
        if (optional.isPresent()) {
            pago = optional.get();
        }
        List<PagoDetalle> pagoDetalle = pagoDetalleRepository.listarPorIdPago(id);

        Document document = new Document();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, stream);
        document.open();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
        Paragraph title = new Paragraph("Reporte de Pago", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph("Número de Pago: " + pago.getId()));
        document.add(new Paragraph("Monto Total: " + pago.getMonto()));
        document.add(new Paragraph("Numero OP: " + pago.getNumero_op()));
        document.add(new Paragraph("Metodo pago: " + pago.getMetodo_pago()));
        document.add(new Chunk("\n"));

        PdfPTable table = new PdfPTable(2); // 2 columnas
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        PdfPCell header = new PdfPCell();
        header.setPadding(10f);
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);

        header.setPhrase(new Phrase("Descripcion"));
        table.addCell(header);
        header.setPhrase(new Phrase("Monto"));
        table.addCell(header);

        header.setBackgroundColor(BaseColor.WHITE);

        for (PagoDetalle detalle : pagoDetalle) {
            header.setPhrase(new Phrase(detalle.getConcepto()));
            table.addCell(header);
            header.setPhrase(new Phrase(String.valueOf(detalle.getMonto())));
            table.addCell(header);
        }

        document.add(table);
        document.close();

        String directoryPath = "src/main/resources/static/pdf"; // Cambia esta ruta según lo que desees
        File directory = new File(directoryPath);

        // Si el directorio no existe, créalo
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Crea el archivo de salida
        File pdfFile = new File(directory, "reporte_pago_" + id + ".pdf");

        // Guardar el PDF en el servidor
        try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
            fos.write(stream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(new HashMap<>());
    }
}
