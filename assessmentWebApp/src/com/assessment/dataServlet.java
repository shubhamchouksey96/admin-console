package com.assessment;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/data")
public class dataServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		String data = req.getParameter("data");
		String quantity = req.getParameter("quantity");
		String amount = req.getParameter("amount");
		
		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		
		try {
			String dburl = "jdbc:mysql://localhost:3306/Data";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dburl,"root","root");
			st = con.createStatement();
			String query = "insert into storeData(data,quantity,amount) value(?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1,data);
			ps.setString(2, quantity);
			ps.setString(3, amount);
			ps.executeUpdate();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				st.close();
				ps.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
