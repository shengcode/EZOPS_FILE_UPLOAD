package com.ezops;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

@Controller
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	     
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {
	    System.out.println("Able to map the HttpSerletRequest!!!");
	    
	}

}