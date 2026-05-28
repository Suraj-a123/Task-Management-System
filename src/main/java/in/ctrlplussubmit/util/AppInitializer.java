package in.ctrlplussubmit.util;

import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ct = sce.getServletContext();
        String url = ct.getInitParameter("url");
        String username = ct.getInitParameter("username");
        String password = ct.getInitParameter("password");

        try {
            DBConnection.openConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println("[AppInitializer] error " + ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DBConnection.closeConnection();
        } catch (SQLException ex) {
            System.out.println("[AppInitializer] error " + ex.getMessage());
        }
    }
}