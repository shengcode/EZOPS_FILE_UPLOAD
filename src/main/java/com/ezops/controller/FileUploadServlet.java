package com.ezops.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import com.ezops.pojo.Titanic_Info;





@Controller
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    final Part filePart = request.getPart("file");
	    InputStream filecontent = null;	    
	    filecontent = filePart.getInputStream();    
	    List<String> tableHeader=null;
    	
        try {
        	tableHeader=TitanicHibernateUpload(filecontent);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			filecontent.close();
		}
        RetrieveDataHibernate (0,10);
       // request.setAttribute("TableHeader", tableHeader);             
        //getServletContext().getRequestDispatcher("/jsps/display.jsp").forward(request, response);
        
	}
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	

	
	private List<String> TitanicHibernateUpload(InputStream filecontent) throws IOException, SQLException{		
		List<String> tableHeader=null;
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	    Session session=sessionFactory.openSession();
	    session.beginTransaction();	   
	    BufferedReader reader=null; 
		try{
       	 	reader=new BufferedReader(new InputStreamReader(filecontent));
       	    String row = reader.readLine(); // read the header here
       	    String[]databaseHeader=row.split(",");
       	    tableHeader = new ArrayList<String>();
       	    	for(String s: databaseHeader){
       	    		tableHeader.add(s);
       	    }	    
	    	row = reader.readLine();
	    	while(row!=null){
	    		String newRow=row.replaceAll("\"", "");
	    		String[] stringArray=newRow.split(",");
	    		Titanic_Info titanic =new Titanic_Info();
    		 titanic.setPassengerId(stringArray[0]+"");
    		 titanic.setSurvied(stringArray[1]+"");
    		 titanic.setPclass(stringArray[2]+"");
    		 titanic.setName(stringArray[3]+" "+stringArray[4]);
    		 titanic.setSex(stringArray[5]+"");
    		 titanic.setAge(stringArray[6]+"");
    		 titanic.setSibSp(stringArray[7]+"");
    		 titanic.setParch(stringArray[8]+"");
    		 titanic.setTicket(stringArray[9]+"");
    		 titanic.setFare(stringArray[10]+"");
    		 titanic.setCabin(stringArray[11]+"");
	    	 if(stringArray.length==13) {	    		 
	    		 titanic.setEmbarked(stringArray[12]+"");
	    	 }else {
	    		 System.out.println(stringArray.length);
	    		 titanic.setEmbarked("");
	    	 }
	    	 session.save(titanic);
	    	 row = reader.readLine();
	    	}
		}catch (Exception e){
          e.printStackTrace();
    	}finally{    		
    		if(reader!=null){
    			reader.close();
    		}
    		session.getTransaction().commit();
    	    session.close();
    	    sessionFactory.close();	  
    	}
		return tableHeader;
	}
	
	private void RetrieveDataHibernate (int start, int maxRows){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.loaddata.xml");
		SessionFactory factory=cfg.buildSessionFactory();
		Session session=factory.openSession();
		// Titanic_Info is the same as entity class name NOT table name
	 	
		Query q=session.createQuery("from Titanic_Info");
	    q.setFirstResult(start);
	    q.setMaxResults(maxRows);
	    List li=q.list();
	    Iterator it=li.iterator();
	    while(it.hasNext()){
	    	Object o=(Object)it.next();
	    	Titanic_Info titanic = (Titanic_Info)o;
	    	System.out.println("Id is: " + titanic.getName());
	    }
	    session.close();
	   factory.close();	
		
	}
}

