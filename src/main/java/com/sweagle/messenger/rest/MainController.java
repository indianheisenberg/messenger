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
import com.sweagle.messenger.repo.MessageRepo;

/**
 * @author ashish agrawal
 *
 */
@RestController
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private MessageRepo messageRepo;
	
	
	/**
	 * @author ashish agrawal
	 * @param receiver
	 * @return list of messages received by a user
	 */
	@GetMapping("/getIncomingMessages/{reciever}")
	public ResponseEntity<List<Message>> getIncomingMessages(@PathVariable(value = "reciever") String receiver){
		
		List<Message> listOfMessages=new ArrayList<Message>();
		
		try {
			listOfMessages=messageRepo.getMessageByReceiver(receiver);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(listOfMessages);
		
		
	}
	
	
	/**
	 * @author ashish agrawal
	 * @param sender
	 * @return list of messages sent by the sender
	 */
	@GetMapping("/getSentMessages/{sender}")
	public ResponseEntity<List<Message>> getSentMessages(@PathVariable(value = "sender") String sender){
		
		
		List<Message> listOfMessages=new ArrayList<Message>();
		try {
			listOfMessages=messageRepo.getMessageBySender(sender);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(listOfMessages);
		
		
	}
	
	
	/**
	 * @author ashish agrawal
	 * @param id
	 * @return message with the message id
	 */
	@GetMapping("/getMessageById/{id}")
	public ResponseEntity<Message> getMessageById(@PathVariable(value="id") String id){
		
		
		Message message = new Message();
		
		try {
			message=messageRepo.getMessageById(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return ResponseEntity.ok().body(message);
	}
	
	
	/**
	 * @author ashish agrawal
	 * @param message
	 * @return message which is saved in mongo
	 */
	@PostMapping("/saveMessage")
	public Message saveMessages(@Valid @RequestBody Message message) {
		return messageRepo.save(message);
		
	
	}
	
	@GetMapping("/home")
	public String welcomeMessage() {
		
		return "welcome page";
	}
	
	
	
	/**
	 * @author ashish agrawal
	 * @return expected number of messages to be sent today
	 */
	@GetMapping("/getExpectedMessagesForToday")
	public String getExpectedMessagesForToday() {
		
		int count=0;
		
		//algo for expected messages for today
		return "Expected Messages for today : "+count;
	}
	
	/**
	 * @author ashish agrawal
	 * @return expected number of messages to be sent the entire week
	 */
	@GetMapping("/getExpectedMessagesForWeek")
	public String getExpectedMessagesForWeek() {
		
		int count=0;
		
		//algo for expected messages for today
		return "Expected Messages for the week : "+count;
	}
	
	
}
