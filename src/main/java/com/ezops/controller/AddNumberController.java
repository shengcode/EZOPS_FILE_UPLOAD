package com.ezops.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

@WebServlet("/add")
public class AddNumberController extends HttpServlet {
   public void service(HttpServletRequest request,
           HttpServletResponse response) throws IOException{
	   int i=Integer.parseInt(request.getParameter("num1"));
	   int j=Integer.parseInt(request.getParameter("num2"));
	   int k =i+j;
	   System.out.println("called me right"+ k);
	   PrintWriter out = response.getWriter();
	   out.println("this is my result "+ k);
   }
}
