package com.ezops.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class Authentication {
	
	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	protected ModelAndView onSubmit(HttpServletRequest request,
                                HttpServletResponse response)          
                         throws Exception {
		
		System.out.println("you called me right");
		ModelAndView userNameAndStatus = new ModelAndView(new RedirectView("streams.htm"));
           return userNameAndStatus; 
        }
    }
      


