package com.dbalota.show.converter;

import com.dbalota.show.models.Ticket;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dmytro_Balota on 4/12/2016.
 */
public class TicketPdfConverter extends AbstractHttpMessageConverter<List<Ticket>> {
    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> types = new LinkedList<>();
        return MediaType.parseMediaTypes("application/pdf");
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        if (aClass.equals(Ticket.class)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected List<Ticket> readInternal(Class<? extends List<Ticket>> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(List<Ticket> tickets, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        Document doc = new Document(PageSize.A4);
        ByteArrayOutputStream os = new ByteArrayOutputStream(4096);
        PdfWriter pw = null;
        try {
            pw = PdfWriter.getInstance(doc, os);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        pw.setViewerPreferences(2053);

        doc.open();
        try {
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph(new Chunk(String.format("    %s    %s    %s    %s", "AUDITORIUM", "DATE", "PRICE", "SEAT"),
                    FontFactory.getFont(FontFactory.HELVETICA, 25))));
            doc.add(new Paragraph("\n"));

            for (Ticket ticket : tickets) {
                doc.add(new Paragraph(new Chunk(String.format("    %s    %s    %s    %s", ticket.getAuditoriumName(), ticket.getDate(), ticket.getPrice(), ticket.getSeat()),
                        FontFactory.getFont(FontFactory.HELVETICA, 20))));
            }

        } catch (DocumentException e) {
            throw new HttpMessageNotWritableException("Error creating pdf", e);
        }
        doc.close();
        httpOutputMessage.getBody().write(os.toByteArray());
    }
}
