package pygman.invoice.util.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ResInitListener implements ServletContextListener{
	private Connection conn = null;
	public void contextInitialized(ServletContextEvent event) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erpdb","root","******");
			Statement stmt = conn.createStatement();
			String sql = "select url from tbl_res";
			ResultSet rs = stmt.executeQuery(sql);
			StringBuilder sbf = new StringBuilder();
			while(rs.next()){
				sbf.append(rs.getString("url"));
				sbf.append(";");
			}
			event.getServletContext().setAttribute("allRes", sbf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
