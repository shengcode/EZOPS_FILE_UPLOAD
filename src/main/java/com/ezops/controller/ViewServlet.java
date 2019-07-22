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
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import com.ezops.pojo.Titanic_Info;

@WebServlet( "/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)   
	           throws ServletException, IOException { 
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.loaddata.xml");
		SessionFactory factory=cfg.buildSessionFactory();
			
		int pageIndex = 0;
		int totalNumberOfRecords = 0;
		int numberOfRecordsPerPage = 50;
		String sPageIndex = request.getParameter("pageIndex");
		if(sPageIndex ==null){
			pageIndex = 1;
		}else{
		pageIndex = Integer.parseInt(sPageIndex);
		}
		Session session=factory.openSession();	
		int s = (pageIndex*numberOfRecordsPerPage) -numberOfRecordsPerPage;
		Criteria crit = session.createCriteria(Titanic_Info.class);
		crit.setFirstResult(s);
		crit.setMaxResults(numberOfRecordsPerPage);

		List l = crit.list();
		Iterator it = l.iterator();

		PrintWriter pw = response.getWriter();
		pw.println("<table border=1>");
		pw.println("<tr>");
		pw.println("<th>PassengerId</th><th>Survied</th><th>Pclass</th>");
		pw.println("<th>Name</th><th>Sex</th><th>Age</th>");
		pw.println("<th>SibSp</th><th>Parch</th><th>Ticket</th>");
		pw.println("<th>Fare</th><th>Cabin</th><th>Embarked</th>");		
		pw.println("</tr>");

		while(it.hasNext())
		{
		Titanic_Info titanic = (Titanic_Info)it.next();
		pw.println("<tr>");
		pw.println("<td>"+titanic.getPassengerId()+"</td>");
		pw.println("<td>"+titanic.getSurvied()+"</td>");
		pw.println("<td>"+titanic.getPclass()+"</td>");
		pw.println("<td>"+titanic.getName()+"</td>");
		pw.println("<td>"+titanic.getSex()+"</td>");
		pw.println("<td>"+titanic.getAge()+"</td>");
		pw.println("<td>"+titanic.getSibSp()+"</td>");
		pw.println("<td>"+titanic.getParch()+"</td>");
		pw.println("<td>"+titanic.getTicket()+"</td>");
		pw.println("<td>"+titanic.getFare()+"</td>");
		pw.println("<td>"+titanic.getCabin()+"</td>");
		pw.println("<td>"+titanic.getEmbarked()+"</td>");
		pw.println("</tr>");
		}
		pw.println("<table>");
		Criteria crit1 = session.createCriteria(Titanic_Info.class).addOrder(Order.desc("PassengerId"));
		crit1.setProjection(Projections.rowCount());
		List l1=crit1.list();
		// pw.println(l1.size());
		//returns 1, as list() is used to execute the query if true will returns 1

		Iterator it1 = l1.iterator();

		if(it1.hasNext())
		{
		Object o=it1.next();
		totalNumberOfRecords = Integer.parseInt(o.toString());
		}

		int noOfPages = totalNumberOfRecords/numberOfRecordsPerPage;
		if(totalNumberOfRecords > (noOfPages * numberOfRecordsPerPage))
		{
		noOfPages = noOfPages + 1;
		}

		for(int i=1;i<=noOfPages;i++)
		{
		String myurl = "ViewServlet?pageIndex="+i;
		pw.println("<a href="+myurl+">"+i+"</a>");
		}

		session.close();
		pw.close();
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
