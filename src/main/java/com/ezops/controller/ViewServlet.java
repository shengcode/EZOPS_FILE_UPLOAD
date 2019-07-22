package com.ezops.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ezops.pojo.Titanic_Info;

@WebServlet( "/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)   
	           throws ServletException, IOException {  
	        response.setContentType("text/html");  
	        PrintWriter out=response.getWriter();  
	          
	        String spageid=request.getParameter("page");  
	        int pageid=Integer.parseInt(spageid);  
	        int total=5;  
	        if(pageid==1){}  
	        else{  
	            pageid=pageid-1;  
	            pageid=pageid*total+1;  
	        }  
	        List<Titanic_Info> list=RetrieveDataHibernate(pageid,total);  
	  
	        out.print("<h1>Page No: "+spageid+"</h1>");  
	        out.print("<table border='1' cellpadding='4' width='60%'>");  
	        out.print("<tr><th>Id</th><th>Name</th><th>Salary</th>");  
	        for(Titanic_Info e:list){  
	            out.print("<tr><td>"+e.getPassengerId()+"</td><td>"+e.getName()+"</td><td>"+e.getSex()+"</td></tr>");  
	        }  
	        out.print("</table>");  
	          
	        out.print("<a href='ViewServlet?page=1'>1</a> ");  
	        out.print("<a href='ViewServlet?page=2'>2</a> ");  
	        out.print("<a href='ViewServlet?page=3'>3</a> ");  
	          
	        out.close();  
	    }  
		
		private List<Titanic_Info> RetrieveDataHibernate (int start, int maxRows){
			List<Titanic_Info> res=new ArrayList<Titanic_Info>();
			Configuration cfg=new Configuration();
			cfg.configure("hibernate.loaddata.xml");
			SessionFactory factory=cfg.buildSessionFactory();
			Session session=factory.openSession();
			// Titanic_Info is the same as entity class name NOT table name
			String SQL_QUERY = "FROM Titanic_Info titanic ORDER BY titanic.PassengerId+'0'";
			Query q=session.createQuery(SQL_QUERY);
		    q.setFirstResult(start);
		    q.setMaxResults(maxRows);
		    List li=q.list();
		    Iterator it=li.iterator();
		    while(it.hasNext()){
		    	Object o=(Object)it.next();
		    	Titanic_Info titanic = (Titanic_Info)o;
		    	res.add(titanic);
		    	System.out.println("Id is: " + titanic.getPassengerId());
		    }
		   session.close();
		   factory.close();	
		   return res;
			
		}
}
