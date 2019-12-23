package com.assessment;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/getData")
public class getData extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		Connection con = null;
		Statement st = null;
		//PreparedStatement ps = null;
		
		try {
			String dburl = "jdbc:mysql://localhost:3306/Data";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dburl,"root","root");
			st = con.createStatement();
			String query = "select * from storeData";
			ResultSet rs = st.executeQuery(query);
			responseWriter(res, rs);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				st.close();
			//	ps.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void responseWriter(HttpServletResponse res, ResultSet rs) throws Exception {
		PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        
        JSONArray jsonArray = new JSONArray();
		while(rs.next()){
			//System.out.println(rs.getString("data")+" "+ rs.getString("quantity")+" "+rs.getString("amount"));
			JSONObject json = new JSONObject();
			json.put("data", rs.getString("data"));
			json.put("quantity", rs.getString("quantity"));
			json.put("amount", rs.getString("amount"));
			json.put("id", rs.getString("id"));
	        jsonArray.put(json);
		 }
		out.print(jsonArray);
        out.flush();
	}
	
}
