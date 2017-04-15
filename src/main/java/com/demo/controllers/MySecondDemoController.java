package com.demo.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MySecondDemoController {

	@RequestMapping(value="/addCookie")
	public String addCookie(HttpServletResponse response){
		
		//Add a random cookie
		response.addCookie(new Cookie("myRandomeCookie","aCookieIAdded"));
		
		System.out.println("Cookie has been added");
		
		return "domoPage";
	}
	
	@RequestMapping(value="/getCookie")
	public String getCookie(@CookieValue("myRandomeCookie") String myCookie){
		
		System.out.println("Cookie retrieved:" + myCookie);
		
		return "domoPage";
	}
	
	
}
