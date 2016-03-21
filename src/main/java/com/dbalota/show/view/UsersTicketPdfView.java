package com.dbalota.show.view;

import com.dbalota.show.models.Ticket;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Dmytro_Balota on 3/21/2016.
 */
public class UsersTicketPdfView extends AbstractPdfView {

    protected void buildPdfDocument(Map model, Document doc, PdfWriter writer,
                                    HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Ticket> tickets = (List) model.get("usersBookedTickets");
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph(new Chunk(String.format("    %s    %s    %s    %s", "AUDITORIUM", "DATE", "PRICE", "SEAT"),
                FontFactory.getFont(FontFactory.HELVETICA, 25))));
        doc.add(new Paragraph("\n"));
        for (Ticket ticket : tickets) {
            doc.add(new Paragraph(new Chunk(String.format("    %s    %s    %s    %s", ticket.getAuditoriumName(), ticket.getDate(), ticket.getPrice(), ticket.getSeat()),
                    FontFactory.getFont(FontFactory.HELVETICA, 20))));
        }
    }
}
