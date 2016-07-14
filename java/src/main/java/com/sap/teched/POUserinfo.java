package com.sap.teched;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.stream.JsonWriter;
import com.sap.xs2.security.container.SecurityContext;
import com.sap.xs2.security.container.UserInfo;
import com.sap.xs2.security.container.UserInfoException;

@Stateless
@WebServlet("/rest/po/userinfo")
public class POUserinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(name = "dev703-java")
	private EntityManager em;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String applicUser  = "null"; //not yet implemented: set application user from Spring based applications
		String logonName   = "null";
		String givenName   = "null";
		String familyName  = "null";
		String currentUser = "null";

		//get user info from security context
		try {
			UserInfo userInfo = SecurityContext.getUserInfo();
			logonName = userInfo.getLogonName();
			givenName = userInfo.getJsonValue("givenName");
			familyName = userInfo.getJsonValue("familyName");
		} catch (UserInfoException e) {
			givenName = e.getMessage();
		}

		//get XS_APPLICATIONUSER from DB
		Query currUserQuery = em.createNativeQuery("SELECT TOP 1 CURRENT_USER FROM DUMMY");
		Query appUserQuery = em.createNativeQuery("SELECT TOP 1 SESSION_CONTEXT('XS_APPLICATIONUSER') FROM DUMMY");
		applicUser = appUserQuery.getSingleResult().toString();
		currentUser = currUserQuery.getSingleResult().toString();

		// write json result
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));

		writer.beginObject(); //root

		writer.name("user");
		writer.beginObject();

		writer.name("id").value(logonName);
		writer.name("name");
		writer.beginObject();
		writer.name("givenName").value(givenName);
		writer.name("familyName").value(familyName);
		writer.endObject(); //name

		writer.endObject(); //user

		writer.name("hdbCurrentUser");
		writer.beginObject();

		writer.name("0");
		writer.beginObject();
		writer.name("APPLICATION_USER").value(applicUser);
		writer.name("CURRENT_USER").value(currentUser);
		writer.endObject(); //0

		writer.endObject();	//hdbCurrentUser
		writer.endObject();	//root

		writer.close();
		response.getOutputStream().flush();
	}
}