package com.test.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;*/

import org.springframework.ui.Model;
//import com.pharmerz.Appcations.UploadObjectSingleOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.test.entity.Users;
import com.test.repository.UsersRepository;
import com.test.util.JsonObjectFormat;




@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class UsersController {
	
	private final Logger log = LoggerFactory.getLogger(UsersController.class);
	
	
	
	@Autowired
	UsersRepository  usersRepository;

	@PostMapping("/user/logIn/emailId")
	public ResponseEntity<String> LoginByEmailid(@RequestParam("emailid")String emailid,@RequestParam("password")String password,@PageableDefault(page=0,size = Integer.MAX_VALUE) Pageable pageable) throws JsonProcessingException{
		
		Users user=usersRepository.findByEmailidAndPassword(emailid, password);
		try{
			Users newuser=user; 
			if(newuser.getId()!=0){
				//newuser.setDevice_id(deviceId); 
				Users details=usersRepository.save(newuser);
				 JsonObjectFormat jsonobjectFormat=new JsonObjectFormat();
				 jsonobjectFormat.setMessage("Authenticated successfully");
				 jsonobjectFormat.setSuccess(true); 
				 jsonobjectFormat.setData(details); 
		         ObjectMapper Obj = new ObjectMapper(); 
		         String customExceptionStr = Obj.writerWithDefaultPrettyPrinter().writeValueAsString(jsonobjectFormat);
			 	 return ResponseEntity.ok().body(customExceptionStr);
			}else{
				 JsonObjectFormat jsonobjectFormat=new JsonObjectFormat();
				 jsonobjectFormat.setMessage("Please Enter Correct Email-Id and Password");
				 jsonobjectFormat.setSuccess(false); 
		         ObjectMapper Obj = new ObjectMapper(); 
		         String customExceptionStr = Obj.writerWithDefaultPrettyPrinter().writeValueAsString(jsonobjectFormat);
			 	 return ResponseEntity.ok().body(customExceptionStr);
			}
		}catch(Exception e){
			 JsonObjectFormat jsonobjectFormat=new JsonObjectFormat();
			 jsonobjectFormat.setMessage("Please Enter Correct Email-Id and Password");
			 jsonobjectFormat.setSuccess(false);
	         ObjectMapper Obj = new ObjectMapper();  
	         String customExceptionStr = Obj.writerWithDefaultPrettyPrinter().writeValueAsString(jsonobjectFormat);
		 	 return ResponseEntity.ok().body(customExceptionStr);
		}
		
		
}
	
	
	@PostMapping("/saveuser")
	public ResponseEntity<String> saveJobPost(@RequestBody Users user) throws JsonProcessingException {
		
		try {
			Users user1	= usersRepository.save(user);

			 JsonObjectFormat jsonobjectFormat=new JsonObjectFormat();
			 
			 jsonobjectFormat.setMessage("Data fetch successfully");
			 jsonobjectFormat.setSuccess(true);
			 jsonobjectFormat.setData(user1);
	         
			 ObjectMapper Obj = new ObjectMapper(); 
	         String customExceptionStr = Obj.writerWithDefaultPrettyPrinter().writeValueAsString(jsonobjectFormat);
		 	 return ResponseEntity.ok().body(customExceptionStr);

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 JsonObjectFormat jsonobjectFormat=new JsonObjectFormat();
			 
			 jsonobjectFormat.setMessage("No Data Found");
			 jsonobjectFormat.setSuccess(false);
			 jsonobjectFormat.setData("");
	         
			 ObjectMapper Obj = new ObjectMapper(); 
	         String customExceptionStr = Obj.writerWithDefaultPrettyPrinter().writeValueAsString(jsonobjectFormat);
		 	 return ResponseEntity.ok().body(customExceptionStr);
			
		}
		
		
		
	}
	
	
}