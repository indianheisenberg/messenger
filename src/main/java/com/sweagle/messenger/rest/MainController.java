package com.sweagle.messenger.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweagle.messenger.beans.Message;
import com.sweagle.messenger.service.MessageService;

/**
 * @author ashish agrawal
 *
 */
@RestController
@RequestMapping("/")
public class MainController {
	
	
	@Autowired
	private MessageService messageService;
	

	
	@GetMapping("/getIncomingMessages/{reciever}")
	public ResponseEntity<List<Message>> getIncomingMessages(@PathVariable(value = "reciever") String receiver){
		
		List<Message> listOfMessages=new ArrayList<Message>();
		
		try {
			listOfMessages=messageService.getIncomingMessages(receiver);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(listOfMessages);
		
		
		
	}
	
	
	
	@GetMapping("/getSentMessages/{sender}")
	public ResponseEntity<List<Message>> getSentMessages(@PathVariable(value = "sender") String sender){
		
		
		List<Message> listOfMessages=new ArrayList<Message>();
		try {
			listOfMessages=messageService.getSentMessages(sender);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(listOfMessages);
		
		
	}
	
	
	
	@GetMapping("/getMessageById/{id}")
	public ResponseEntity<Message> getMessageById(@PathVariable(value="id") String id){
		
		
		Message message = new Message();
		
		try {
			message=messageService.getMessageById(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return ResponseEntity.ok().body(message);
	}
	
	
	
	@PostMapping("/saveMessage")
	public Message saveMessages(@Valid @RequestBody Message message) {
		return messageService.saveMessages(message);
		
	
	}
	
	@GetMapping("/home")
	public String welcomeMessage() {
		
		System.out.println("HOME");
		return "welcome page";
	}
	
	
	
	
	@GetMapping("/getExpectedMessagesForToday")
	public String getExpectedMessagesForToday() {
		
	
		//algo for expected messages for today
		return "Expected number of messages for the rest of the day"+messageService.getExpectedMessagesForToday();
	}
	
	
	
	@GetMapping("/getExpectedMessagesForWeek")
	public String getExpectedMessagesForWeek() {
		
	
		
		//algo for expected messages for today
		return "Expected number of messages for the rest of the week"+messageService.getExpectedMessagesForWeek();
	}
	
	
}
