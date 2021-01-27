package com.thinkitive.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyDatabaseOperations {

	MyConnection db = new MyConnection();
	Statement st;
	PreparedStatement ps;

	public void insertEmp(String name, String username, String password) {

		try {
			ps = db.getPreparedStatement("insert into emptable values(?,?,?)");
			ps.setString(1, name);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.execute();
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteEmp(String username) {

		try {
			ps = db.getPreparedStatement("delete from emptable where username=?");
			ps.setString(1, username);
			ps.execute();
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateEmp(String name, String username, String password) {

		try {
			ps = db.getPreparedStatement("update emptable set name=? password=? where username=?");
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, username);
			ps.execute();
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List checkEmp(String username, String password) {
		List l = new ArrayList();
		st = db.getStatement();
		ResultSet rs;
		int flag = 0;

		try {
			ps = db.getPreparedStatement("select username from emptable");
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equals(username)) {
					flag = 1;
					break;
				}
			}

			if (flag == 1) {
				ps = db.getPreparedStatement("select * from emptable where username=? and password=?");
				ps.setString(1, username);
				ps.setString(2, password);
				rs = ps.executeQuery();

				String s_name = null, s_password = null;
				while (rs.next()) {
					s_name = rs.getString(1);
					//s_username = rs.getString(2);
					s_password = rs.getString(3);
				}
				if (password.equals(s_password)) {
					l.add(true);
					l.add(s_name);
					l.add(username);
				} else {
					l.add(false);
					l.add("Wrong Password!!!!!");
				}
			}else {
				l.add(false);
				l.add("Wrong Username!!!!!");
			}

			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;

	}

	public List displayEmp() {
		List l = new ArrayList();
		st = db.getStatement();

		try {
			ResultSet rs = st.executeQuery("select * from emptable");

			while (rs.next()) {
				System.out.println(
						"Name: " + rs.getString(1) + " Username: " + rs.getString(2) + " Password: " + rs.getString(3));
				l.add("Name: " + rs.getString(1) + " Username: " + rs.getString(2) + " Password: " + rs.getString(3));
			}

			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;

	}

}
