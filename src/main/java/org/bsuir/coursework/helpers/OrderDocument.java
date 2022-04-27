package org.bsuir.coursework.helpers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.AllArgsConstructor;
import org.bsuir.coursework.entity.Ticket;

@AllArgsConstructor
public class OrderDocument {
    private List<Ticket> tickets;


    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.BLACK);
        cell.setPhrase(new Phrase("Рейс №", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Билет №", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Ticket ticket : tickets) {
            table.addCell(String.valueOf(ticket.getOrder()));
            table.addCell(String.valueOf(ticket.getId()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(BaseColor.BLACK);

        Paragraph p = new Paragraph("Рейсовый отчёт", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
