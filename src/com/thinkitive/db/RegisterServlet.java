package com.thinkitive.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegisterServlet() {
        super();
        
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	System.out.println("Init method......");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out  = response.getWriter();
		out.println("POST: ");
		
		String name = request.getParameter("txtname");
		String username = request.getParameter("txtusername");
	    String password = request.getParameter("txtpassword");  
	    
	    MyDatabaseOperations ops = new MyDatabaseOperations();
		ops.insertEmp(name, username, password);
		List l = ops.checkEmp(username, password);
		if((boolean)l.get(0)) {
			out.println("Registration Successful "+l.get(1)+"!");
			out.println("Username: "+l.get(2));
		}
		else {
			out.println("Registration Not Succesful :( \n Username "+username +" is not available");
		}
//		out.println(ops.displayEmp());
//		ops.deleteEmp(id);
//		out.println(ops.displayEmp());
//		ops.updateEmp(empid, empname);
//		out.println(ops.displayEmp());
	}
	
	@Override
	public void destroy() {

		super.destroy();
	}

}
