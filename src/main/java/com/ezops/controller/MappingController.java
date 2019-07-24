package com.ezops.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/gotothat", method=RequestMethod.POST)
public class MappingController {
	public String saveEmployee(){	
		System.out.println("calledme?");
		return "nelala";
	}
}
