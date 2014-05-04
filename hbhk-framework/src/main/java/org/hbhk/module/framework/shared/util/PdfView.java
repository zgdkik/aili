package org.hbhk.module.framework.shared.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.module.framework.shared.domain.Person;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class PdfView extends AbstractPdfView {

	protected void buildPdfDocument(Map model, Document doc, PdfWriter writer,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Person person = (Person) model.get("person");
		doc.add(new Paragraph(person.toString()));

	}

}