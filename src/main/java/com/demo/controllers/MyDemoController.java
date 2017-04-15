package com.demo.controllers;

import java.io.FileOutputStream;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.demo.model.Account;

@Controller
@SessionAttributes("aNewAccount")
@RequestMapping("/")
public class MyDemoController {
	
	
	// Just adding the Account attribute to the model if it does not exist: 
	@ModelAttribute         
    public void addAccountToModel(Model model){
		
		System.out.println("Make sure account object is on model ");
        if(!model.containsAttribute("aNewAccount")){
        	Account a = new Account();
        	model.addAttribute("aNewAccount", a);
        }
        		
	}
	
	
	
	// http://localhost:8080/springMVCDemo/getQuote.html
	// We can Add params restriction: @RequetMapping(value="/getQuote", params="from") for a specific param to be set up in the url. [also params="!from" or params="from=Spring"]	
	// We can add headers restriction: @RequestMapping(value="/getQuote", headers="X-API-KEY=111079") or just headers="X-API-KEY" without a value.
	@RequestMapping(value="/getQuote", method = RequestMethod.GET)   
	public String getRandomQuote(Model model){
		
		model.addAttribute("randomQuote", "To be or not to be - Shakespeare");
		
		return "quote";
		
	}
	
	//[This method is used only is we add the attribute userName witin the url, otherwise browser will complain]
	// The purpose of this method is only to add some info to the model: 
/*	@ModelAttribute
	public void setUserDetails(@RequestParam ("userName") String userName, Model model){
		
		model.addAttribute("userName", userName);
		
		// Simulate going off and retrieving role based on userName:
		String userRole = "undefine";
		
		if(userName.equals("Andy")){
			userRole = "Student";
		}
		else if(userName.equals("John")){
			userRole = "Teacher";
		}
		else if(userName.equals("Allana")){
			userRole = "Dean";
		}
		
		model.addAttribute("userRole", userRole);
		 
		System.out.print("Model updated with user information.");
		
	}
	
	*/
	
		// http://localhost:8080/springMVCDemo/getQuote.html
		@RequestMapping(value="/createAccount")
		public String createAccount(@Valid @ModelAttribute ("aNewAccount") Account account, BindingResult bindingResult){
			
			 if (bindingResult.hasErrors())
			 {
				 System.out.println("Form has errors"); 
				 return "createAccount";
			 }
			 
			 System.out.println("Form validated");
			 System.out.println(account.getFirstName() + " "  + account.getLastName() +  " "  + account.getAge() +  " "  + account.getAddress() + " "  + account.getEmail());   
			     return "createAccount";
		}
	

		// http://localhost:8080/springMVCDemo/doCreate.html
		@RequestMapping(value="/doCreate")
		public String doCreate(@ModelAttribute ("aNewAccount") Account account){
			
			System.out.println("Do Create: New account info: " + account.getFirstName() + " "  + account.getLastName() +  " "  + account.getAge() +  " "  + account.getAddress() + " "  + account.getEmail() );
			System.out.println("Do Create: Going off and creating an actual account with a valid account object");
			
			return "redirect:accConfirm.html";
		}
		
		// http://localhost:8080/springMVCDemo/accConfirm.html
		@RequestMapping(value="/accConfirm")
		public String accountConfirmation(@ModelAttribute ("aNewAccount") Account account){
			
			System.out.println("Account confirmed: Welcome " + account.getFirstName() + " "  + account.getLastName() );
			
			return "accountConfirmed";
		}
		
		
		// Method to submit uploading file:  ------------------------------------------ 
		// http://localhost:8080/springMVCDemo/myForm.html
	    @RequestMapping(value="/myForm")
	    public String myForm(){
	    	// Dispatching to myForm.jsp 
	    	return "myForm";
	    }
	    
	    // Method to confirm the submission of the file:
	    // http://localhost:8080/springMVCDemo/handleForm.html
	    @RequestMapping(value="/handleForm")
	    public String handleForm(@RequestParam("file") MultipartFile file){
	    	
	    	// Handling the uploaded file: 
	    	try{
	    		
	    		if(!file.isEmpty()){
	    			byte[] bytes = file.getBytes();
	    			FileOutputStream fos = new FileOutputStream("C:\\Users\\Abdu Jeff\\Desktop\\myFile.jpg");
	    			fos.write(bytes);
	    			fos.close();
	    			System.out.println("File saved successfully");
	    	    	// Dispatching to operationComplete.jsp 
	    	    	return "operationComplete";
	    		}else{
	    			System.out.println("No file available to save.");
	    		}
	    		
	    	}catch(Exception e){
	    		System.out.println("Error saving file");
	    	}
	    	
              return "operationNotComplete";
	    
	    }

}

