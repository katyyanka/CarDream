package org.bsuir.coursework.helpers;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.AllArgsConstructor;
import org.bsuir.coursework.model.Order;
import org.bsuir.coursework.model.Place;
import org.bsuir.coursework.model.Ticket;

import static org.bsuir.coursework.helpers.parserStringToRadian.toRadian;

@AllArgsConstructor
public class OrderDocument {
    private List<Ticket> tickets;
    Order order;

    private void writeTableHeader(PdfPTable table) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);
        final String FONT = "C:\\Users\\kathe\\IdeaProjects\\demo\\src\\main\\resources\\static\\fonts\\timesnrcyrmt.ttf";

        BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font=new Font(bf,12,Font.NORMAL);
        font.setColor(BaseColor.BLACK);
        cell.setPhrase(new Phrase("Заказ №", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Билет №", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Пассажир", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Комментарий", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Оценка", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Место", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Статус", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) throws DocumentException, IOException {
        final String FONT = "C:\\Users\\kathe\\IdeaProjects\\demo\\src\\main\\resources\\static\\fonts\\timesnrcyrmt.ttf";

        BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font=new Font(bf,12,Font.NORMAL);
        for (Ticket ticket : tickets) {
            table.addCell(new Phrase(String.valueOf(ticket.getOrder().getIdOrder()), font));
            table.addCell(new Phrase(String.valueOf(ticket.getId()), font));
            table.addCell(new Phrase(String.valueOf(ticket.getUser().getUsername()), font));
            if (ticket.getComment()!=null){
                table.addCell(new Phrase(String.valueOf(ticket.getComment()), font));
            } else {
                table.addCell(" ");
            }
            table.addCell(new Phrase(String.valueOf(ticket.getMark()), font));
            table.addCell(new Phrase(String.valueOf(ticket.getSet()), font));
            if (ticket.isStatus()){
                table.addCell("+");
            } else {
                table.addCell("-");
            }

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        final String FONT = "C:\\Users\\kathe\\IdeaProjects\\demo\\src\\main\\resources\\static\\fonts\\timesnrcyrmt.ttf";

        BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font=new Font(bf,20,Font.NORMAL);
        document.open();
        Paragraph p = new Paragraph("Все подробности рейса №" + order.getIdOrder(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        font.setSize(12);
        font.setColor(BaseColor.BLACK);

        document.add(new Paragraph("Водитель: " + order.getVehicle().getUser().getUsername()
                , font));

        document.add(new Paragraph("Откуда: " + order.getDeparture().getName(), font));

        document.add(new Paragraph("Куда: " + order.getArrive().getName(), font));

        document.add(new Paragraph("Когда: " + order.getDatetime(), font));

        Paragraph distance = new Paragraph("Расстояние: " + distanceBetweenCities(
                order.getDeparture(),
                order.getArrive()), font);
        document.add(distance);


        Paragraph vehicle = new Paragraph("Автомобиль: " + order.getVehicle().getNumber()
                , font);
        document.add(vehicle);

        Paragraph fuel_consumption = new Paragraph("Расход на 100 километров: " + order.getVehicle().getFuelConsumptionPer100Kilometers()
                , font);
        document.add(fuel_consumption);

        Paragraph fuel = new Paragraph("Топливо: " + order.getVehicle().getFuel().getName()
                , font);
        document.add(fuel);

        Paragraph price = new Paragraph("Стоимость топлива: " + order.getVehicle().getFuel().getPrice()
                , font);
        document.add(price);

        Paragraph expenses = new Paragraph("Расходы: " + new DecimalFormat("#0.00").format(
                order.getVehicle().getFuel().getPrice() /100 *
                order.getVehicle().getFuelConsumptionPer100Kilometers() * distanceBetweenCities(
                order.getDeparture(),
                order.getArrive()))
                , font);
        document.add(expenses);

        document.add(new Paragraph("Доходы: " + tickets.size()*order.getPrice()
                , font));

        document.add(new Paragraph("Финансовый результат: " +
                new DecimalFormat("#0.00").format(tickets.size()*order.getPrice() -
                        order.getVehicle().getFuel().getPrice() /100 *
                                order.getVehicle().getFuelConsumptionPer100Kilometers() * distanceBetweenCities(
                                order.getDeparture(),
                                order.getArrive()))
                , font));

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.3f, 2.2f, 5.5f, 7.0f, 2.5f, 2.2f, 2.2f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }


    public static double distanceBetweenCities(Place first, Place second) {
        Double w1 = toRadian(first.getWidth()),
                l1 = toRadian(first.getLongitude()),
                w2 = toRadian(second.getWidth()),
                l2 = toRadian(second.getLongitude());
        Integer EARTH_RADIUS = 6373;

        double coefficient = 1.25;

        Double result = coefficient * EARTH_RADIUS *
                Math.atan(
                        Math.sqrt(
                                Math.pow(Math.cos(w2) * Math.sin(l2 - l1), 2) +
                                        Math.pow((Math.cos(w1) * Math.sin(w2) -
                                                Math.sin(w1) * Math.cos(w2) * Math.cos(l2 - l1)), 2))
                                / ((Math.sin(w1) * Math.sin(w2) + Math.cos(w1) * Math.cos(w2) * Math.cos(l2 - l1))));

        return Math.ceil(result);
    }

}
