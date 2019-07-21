package com.ezops.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        try {
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

