package in.ctrlplussubmit.util;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
	private SessionUtil() {
		
	}
	public static void setLoggedInUser(HttpServletRequest request, User user) {
		HttpSession oldSession = request.getSession();
		if(oldSession != null) {
			oldSession.invalidate();
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedInUser", user);
		session.setMaxInactiveInterval(-1);
	}
	public static User getLoggedInUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			return null;
		}
		return (User) session.getAttribute("loggedInUser");
	}
	public static boolean isLoggedIn(HttpServletRequest request) {
		return getLoggedInUser(request) != null;
	}
	public static String getRole(HttpServletRequest request) {
		User user = getLoggedInUser(request);
		if(user==null) {
			return null;
		}
		return user.getRole();
	}
	public static boolean isAdmin(HttpServletRequest request) {
		return "ADMIN".equals(getRole(request));
	}
	public static boolean isFaculty(HttpServletRequest request) {
		return "FACULTY".equals(getRole(request));
	}
	public static boolean isStudent(HttpServletRequest request) {
		return "STUDENT".equals(getRole(request));
	}
	public static boolean requireLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		if(!isLoggedIn(req)) {
			resp.sendRedirect(req.getContextPath()+"/view/auth/login.html");
			return false;
		}
		return true;
	}
	public static void logout(HttpServletRequest req) {
		HttpSession Session = req.getSession();
		if(Session != null) {
			Session.invalidate(); 
		}
	}
}
