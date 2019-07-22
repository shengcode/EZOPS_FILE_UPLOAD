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

import org.hibernate.HibernateException;
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
		   	long a= DataCountHibernate();
			System.out.println("the a is "+ a);
	        response.setContentType("text/html");  
	        PrintWriter out=response.getWriter();  
	        String row=request.getParameter("row");  
	        System.out.println("the row per page  is "+row);
	        String spageid=request.getParameter("page");  
	        System.out.println("the number of pages you want to show is "+spageid);
	        int pageid=Integer.parseInt(spageid); 
	        int total=0;
	        if(row==null) total=10;  
	        else total=Integer.parseInt(row);
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
	        out.println("<form action=\"\" method=\"GET\">");
	        //out.println("How many page: <input type=\"text\" name=\"page\"><br>");
	        out.println("Rows per page: <input type=\"text\" name=\"row\"><br>");
	        out.println("<input type=\"submit\" value=\"Submit\">");
	        
	        out.println("</form>");
	        for(int i=1; i<=4;i++){
	        out.print("<a href='ViewServlet?page="+ i+"&row="+row+"\'"+ ">1</a> ");  
	        //out.print("<a href='ViewServlet?page=2'>2</a> ");  
	        //out.print("<a href='ViewServlet?page=3'>3</a> ");  
	        //out.print("<a href='ViewServlet?page=4'>4</a> ");  
	        }
	        out.print("<a href='ViewServlet?page=2&lname=3'>2</a> ");  
	        out.close();  
	    }  
		private long DataCountHibernate (){
			long count=0;			
			final String HQL2 = "select count(titanic) from Titanic_Info titanic ";
			SessionFactory factory=null;
			Session session=null;
			try {
				 	Configuration cfg=new Configuration();
					cfg.configure("hibernate.loaddata.xml");
					factory=cfg.buildSessionFactory();
					session=factory.openSession();
		            Query query = session.createQuery(HQL2);
		            query.setCacheable(true);
		            
		            final Object obj = query.uniqueResult();
		            if (obj != null) {
		                count = (Long) obj;
		            }
		        } catch (HibernateException e) {
		            e.printStackTrace();
		        } finally {
		            if (session != null) {
		                session.close();
		            }if(factory!=null){
		            	factory.close();
		            }
		        }
			
			return count;
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
