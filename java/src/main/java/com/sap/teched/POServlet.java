package com.sap.teched;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.stream.JsonWriter;
import com.sap.teched.cds.PurchaseOrder.Header;

@WebServlet("/rest/po/tree")
public class POServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private POService POService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Header> headers = POService.findAllHeaders();
		
		// write json result
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));

		writer.beginObject();
		writer.name("POs");

		writer.beginObject();
		writer.name("name");
		writer.value("root");

		/* <id>: name, cat, amount, curr, qty */
		int currHeader = -1;
		int child = 0;
		for (Object object : headers) {
			
			Header header = (Header) object;
			
			String id = header.getItem().getId();
			if (Integer.parseInt(id) != currHeader) {
				// close prev header
				if (currHeader != -1)
					writer.endObject();

				// new header
				writer.name(String.valueOf(id));
				writer.beginObject();
				writer.name("id");
				writer.value(header.getItem().getId());

				currHeader = Integer.parseInt(id);
				child = 0;
			}

			// items
			++child;
			writer.name("" + child);
			writer.beginObject();
			writer.name("parnter");
			writer.value(header.getPartner());			
			writer.endObject();
		}

		if (currHeader != -1) {
			writer.endObject();
		}

		writer.endObject();
		writer.endObject();

		writer.close();
		response.getOutputStream().flush();
	}
}