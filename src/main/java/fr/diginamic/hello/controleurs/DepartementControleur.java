package fr.diginamic.hello.controleurs;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.diginamic.hello.entites.DepartementApi;
import fr.diginamic.hello.startup.ExtractFromApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/departement")
public class DepartementControleur {

	@Autowired
	private ExtractFromApi extractFromApi;

	@GetMapping("/departements")
	public void ficheDepartements(HttpServletResponse response) throws IOException, DocumentException {
		Map<Integer, DepartementApi> departementMap = extractFromApi.getDepartementMap();

		response.setHeader("Content-Disposition", "attachment; filename=\"departements.pdf\"");
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		document.addTitle("Liste de tous les Départements");

		BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		Font font = new Font(baseFont, 16.0f, Font.BOLD, BaseColor.BLACK);

		PdfPTable table = new PdfPTable(3); 
		table.setWidthPercentage(100); 
		table.setSpacingBefore(10f); 
		table.setSpacingAfter(10f); 

		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Nom du Département", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Code", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Code Région", font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		for (DepartementApi departement : departementMap.values()) {
			table.addCell(departement.getNom());
			table.addCell(departement.getCode());
			table.addCell(departement.getCodeRegion());
		}

		document.add(table);

		document.close();
		response.flushBuffer();
	}
}
