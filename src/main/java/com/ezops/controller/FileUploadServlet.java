package com.ezops.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Controller;

import com.ezops.pojo.Student_Info;
import com.ezops.pojo.Titanic_Info;



@Controller
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	//Mysql for local database
	private String dbURL = "jdbc:mysql://localhost:3306/ezops";
    private String dbUser = "root";
    private String dbPass = "12345";   
	
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    System.out.println("Working Directory = " +System.getProperty("user.dir"));
	    
	    //TestHibernate();
	    
	    final Part filePart = request.getPart("file");
	    InputStream filecontent = null;
	    
	    filecontent = filePart.getInputStream();
        try {
			//ReadCSV(filecontent);
        	TitanicHibernateUpload(filecontent);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
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
	
	private void ReadCSV(InputStream filecontent) throws IOException, SQLException{	
		Connection conn=null;
		BufferedReader reader=null; 
		try{
       	 	reader=new BufferedReader(new InputStreamReader(filecontent));
       	 	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());   
       	 	conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
       	 	String insertQuery = "Insert into titanic (passenger_id,survied,class_of_travel,"
       	 			+ "passenger_firstname,passenger_lastname,sex, age, number_of_sibspouse_aboard, number_of_parent_aboard,"
       	 			+ "ticket,Fare, Cabin,Embarked) "
       	 			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
       	 	PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            String row = reader.readLine();
	    	row = reader.readLine();
	    	while(row!=null){
	    	 String newRow=row.replaceAll("\"", "");
	    	 String[] stringArray=newRow.split(",");
	    	 int i=0;
	    	 for (String data : stringArray)
             {       
            	     pstmt.setString((i % 13+1), data);
                     if (++i % 13 == 0)
                             pstmt.addBatch();// add batch
                     
                     if (i % 13 == 0)// insert when the batch size is 10
                    	  pstmt.executeBatch();                    	
             }
	    	 row = reader.readLine();
	}
		}catch (Exception e){
          e.printStackTrace();
    	}finally{
    		if(conn!=null){
    			conn.close();
    		}
    		if(reader!=null){
    			reader.close();
    		}
    	}
	}
	
	private void TestHibernate(){
		Student_Info student =new Student_Info();
		student.setName("testName");
		student.setRollNo(1);
		//SessionFactory sessionFactory =new AnnotationConfiguration.configure().buildSessionFactory();
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	    Session session=sessionFactory.openSession();
	    session.beginTransaction();
	    session.save(student);
	    
	    session.getTransaction().commit();
	    session.close();
	    sessionFactory.close();	    
	}
	
	private void TitanicHibernateUpload(InputStream filecontent) throws IOException, SQLException{		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	    Session session=sessionFactory.openSession();
	    session.beginTransaction();	   
	    BufferedReader reader=null; 
		try{
       	 	reader=new BufferedReader(new InputStreamReader(filecontent));
       	    String row = reader.readLine();
	    	row = reader.readLine();
	    	while(row!=null){
	    	 String newRow=row.replaceAll("\"", "");
	    	 String[] stringArray=newRow.split(",");
	    	 if(stringArray.length==13) {
	    	Titanic_Info titanic =new Titanic_Info(stringArray[0]+"",stringArray[1]+"",
	    			 stringArray[2]+"",stringArray[3]+"",stringArray[4]+"",stringArray[5]+"",
	    			 stringArray[6]+"",stringArray[7]+"",stringArray[8]+"",stringArray[9]+"",
	    			 stringArray[10]+"",stringArray[11]+"",stringArray[12]+"");
	    	session.save(titanic);
	    	 }
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
	}
}

