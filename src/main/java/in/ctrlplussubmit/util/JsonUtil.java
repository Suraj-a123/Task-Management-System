package in.ctrlplussubmit.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpServletResponse;

public class JsonUtil {

	private static final Gson GSON = 
			new GsonBuilder()
			.serializeNulls()
			.setPrettyPrinting()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			.create();
	
	public static void sendJson(HttpServletResponse response, int statusCode, String jsonString) throws IOException {
		response.setStatus(statusCode);
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		
		PrintWriter pw = response.getWriter();
		pw.print(jsonString);
		pw.flush();
		
	}
	
	public static void sendSuccess(HttpServletResponse response, String message, Object data) throws IOException {
		Map<String, Object> body = new HashMap<>();
		body.put("sucess", true);
		body.put("message",message);
		body.put("data", data);
		sendJson(response, HttpServletResponse.SC_OK, GSON.toJson(body));
	}
	
	public static void sendError(HttpServletResponse response, int statusCode, String message) throws IOException {
		Map<String, Object> body = new HashMap<>();
		body.put("sucess", false);
		body.put("message",message);
		sendJson(response, statusCode, GSON.toJson(body));
	}
	public static void sendBadRequest(HttpServletResponse response, String message) throws IOException{
		sendError(response, HttpServletResponse.SC_BAD_REQUEST, message);
	}
	
	public static void sendUnauthorized(HttpServletResponse response, String message) throws IOException{
		sendError(response, HttpServletResponse.SC_UNAUTHORIZED, message);
	}
	
	public static void sendForbidden(HttpServletResponse response, String message) throws IOException{
		sendError(response, HttpServletResponse.SC_FORBIDDEN, message);
	}
	
	public static void sendNotFound(HttpServletResponse response, String message) throws IOException{
		sendError(response, HttpServletResponse.SC_NOT_FOUND, message);
	}
	
	public static void sendServerError(HttpServletResponse response, String message) throws IOException{
		sendError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
	}
	
}
