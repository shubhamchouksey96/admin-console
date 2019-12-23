package com.assessment;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		String data = req.getParameter("data");
		String quantity = req.getParameter("quantity");
		String amount = req.getParameter("amount");
		String id = req.getParameter("id");
		
		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		
		try {
			String dburl = "jdbc:mysql://localhost:3306/Data";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dburl,"root","root");
			st = con.createStatement();
			String query = "update storeData set data = ?, quantity = ?, amount = ? where id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1,data);
			ps.setString(2, quantity);
			ps.setString(3, amount);
			ps.setString(4, id);
			ps.executeUpdate();
			responseWritter(res);
			
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
	
	public static void responseWritter(HttpServletResponse res) throws Exception {
		PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
		json.put("status", "S");
		out.print(json);
        out.flush();
	}
}