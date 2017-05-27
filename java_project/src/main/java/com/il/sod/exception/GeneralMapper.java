package com.il.sod.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ext.Provider;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

@Provider
public class GeneralMapper  {

	final static Logger LOGGER = LoggerFactory.getLogger(GeneralMapper.class);
	
	public String buildErrorMessage(HttpServletRequest req, String error) {
        StringBuilder message = new StringBuilder();
        String entity = "(empty)";

        try {
            InputStream is = req.getInputStream();
            Scanner s = new Scanner(is, "UTF-8").useDelimiter("\\A");
            entity = s.hasNext() ? s.next() : entity;
        } catch (Exception ex) {
            // Ignore exceptions around getting the entity
        }

        message.append("*** ERROR *** \n");
        message.append("Uncaught REST API exception:\n");
        message.append("URL: ").append(getOriginalURL(req)).append("\n");
        message.append("Method: ").append(req.getMethod()).append("\n");
        message.append("Entity: ").append(entity).append("\n");
        message.append("Exception: " + error);

        return message.toString();
    }

    public String getOriginalURL(HttpServletRequest req) {
        // Rebuild the original request URL: http://stackoverflow.com/a/5212336/356408
        String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80
        String contextPath = req.getContextPath();   // /mywebapp
        String servletPath = req.getServletPath();   // /servlet/MyServlet
        String pathInfo = req.getPathInfo();         // /a/b;c=123
        String queryString = req.getQueryString();   // d=789

        // Reconstruct original requesting URL
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath).append(servletPath);

        if (pathInfo != null) {
            url.append(pathInfo);
        }

        if (queryString != null) {
            url.append("?").append(queryString);
        }

        return url.toString();
    }

    protected void logException(Throwable ex, HttpServletRequest request, long threadId){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String error = sw.toString();
        String errorMessage = buildErrorMessage(request, error);

        LOGGER.error("[{}] {}", threadId, errorMessage);
    }
}