package com.thinkitive.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
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
		
		String username = request.getParameter("txtusername");
	    String password = request.getParameter("txtpassword");  
	    
	    MyDatabaseOperations ops = new MyDatabaseOperations();
	
		List l = ops.checkEmp(username, password);
		if((boolean)l.get(0)) {
			out.println("LogIn Successful "+l.get(1)+"!");
			out.println("Username: "+l.get(2));
		}
		else {
			out.println(l.get(1));
		}
	}
	
	@Override
	public void destroy() {

		super.destroy();
	}

}
