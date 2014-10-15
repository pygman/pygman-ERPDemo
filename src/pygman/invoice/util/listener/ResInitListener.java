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
		//连接数据库，读取所有的资源信息
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
			//将所有的资源字符串，转换成一个set集合对象，判定时，使用contains方法
			//将所有的资源字符串，转换成一个String对象，判定时，使用contains方法
			//将所有资源的信息字符串放置在一个公共的可访问的区域
			//将数据放置在ServletContext范围内
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
