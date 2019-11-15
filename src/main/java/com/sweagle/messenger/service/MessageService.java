package com.sweagle.messenger.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweagle.messenger.beans.Message;
import com.sweagle.messenger.repo.MessageRepo;

@Service
public class MessageService {

	
	@Autowired
	private MessageRepo messageRepo;
	
	
public List<Message> getIncomingMessages(String receiver) throws Exception{
		
		List<Message> listOfMessages=new ArrayList<Message>();
		
	
		listOfMessages=messageRepo.getMessageByReceiver(receiver);
		
		return listOfMessages;
		
		
	}


/**
 * @author ashish agrawal
 * @param sender
 * @return list of messages sent by the sender
 */

public List<Message> getSentMessages(String sender) throws Exception{
	
	
	List<Message> listOfMessages=new ArrayList<Message>();
	
	listOfMessages=messageRepo.getMessageBySender(sender);
	
	
	return listOfMessages;
	
	
}


/**
 * @author ashish agrawal
 * @param id
 * @return message with the message id
 */

public Message getMessageById(String id) throws Exception{
	
	
	Message message = new Message();
	
	
	message=messageRepo.getMessageById(id);
		
	
	
	return message;
}


/**
 * @author ashish agrawal
 * @param message
 * @return message which is saved in mongo
 */

public Message saveMessages(Message message) {
	return messageRepo.save(message);
	

}




/**
 * @author ashish agrawal
 * @return expected number of messages to be sent today
 */

public List<String> setDays(){
	List<String> listOfDays=new ArrayList<String>();
	LocalDateTime date=LocalDateTime.now();
	
	for(int i=1;i<=7;i++) {
		
		LocalDateTime previousDate=date.minusDays(i);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY");
		String formatDateTime = previousDate.format(formatter);
		listOfDays.add(formatDateTime);
		
	}
	
	
	
	return listOfDays;
}

public int  getExpectedMessagesForToday() {
	
	LocalTime currentTime=LocalTime.now();
	List<String> listOfDays=setDays();
	
	int totalCountableDays=1;
	int globalCounter=0;
	int finalCount=0;
	
	for(int i=0;i<listOfDays.size();i++) {
		
		List<Message> messageList1=messageRepo.getMessageBySentDate(listOfDays.get(i));
		
		if(null!=messageList1 && !messageList1.isEmpty()) {
			totalCountableDays++;
		}
		
		int totalMessagesForRestOfTheDay=0;
		
		for(Message message:messageList1) {
			
			if(null!=message.getSentTime()) {
			
				LocalTime sentTime=LocalTime.parse(message.getSentTime());
				
				if(sentTime.isAfter(currentTime)) {
					totalMessagesForRestOfTheDay++;
				}
			}
			
			
		}
		
		globalCounter=globalCounter+totalMessagesForRestOfTheDay;
		
	}

	finalCount=globalCounter/totalCountableDays;

	return finalCount;
}

/**
 * @author ashish agrawal
 * @return expected number of messages to be sent the entire week
 */


public int getNumerOfMessagesSentInCurrentWeek(int daysToMinus) {
	

	List<Message> messageListForTheMessagesSentInCurrentWeek=new ArrayList<Message>();
	LocalDateTime date=LocalDateTime.now();
	int totalMessagesSent=0;
	
	
	for(int i=0;i<=daysToMinus;i++) {
		
		LocalDateTime previousDate=date.minusDays(i);	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY");
		String formatDateTime = previousDate.format(formatter);
		
		messageListForTheMessagesSentInCurrentWeek=messageRepo.getMessageBySentDate(formatDateTime);
	
		totalMessagesSent+=messageListForTheMessagesSentInCurrentWeek.size();
		
	}
	
	
	return totalMessagesSent;
	
	
	
}


public int avgNumOfMsgSentInLastThreeWeeks(int daysToMinus) {
	
	
	List<Message> messageListForTheMessagesSentInCurrentWeek=new ArrayList<Message>();
	LocalDateTime date=LocalDateTime.now();
	int totalMessagesSent=0;
	LocalDateTime minusedDate=date.minusDays(daysToMinus);	
	for(int i=0;i<21;i++) {
		
		LocalDateTime previousDate=minusedDate.minusDays(i);	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY");
		String formatDateTime = previousDate.format(formatter);
		messageListForTheMessagesSentInCurrentWeek=messageRepo.getMessageBySentDate(formatDateTime);
		totalMessagesSent+=messageListForTheMessagesSentInCurrentWeek.size();
	}
		
	return totalMessagesSent/3;
}


public int getExpectedMessagesForWeek() {
	
	
	Calendar cal=Calendar.getInstance();
	int currentDay=cal.get(Calendar.DAY_OF_WEEK);
	
	int numberOfMessagesSentInCurrentWeek=0;
	int numberOfMessageLeftToBeSentInCurrentWeek=0;
	int avgNumOfMsgSentInLastThreeWeeks=0;
	
	numberOfMessagesSentInCurrentWeek=getNumerOfMessagesSentInCurrentWeek(currentDay-1);
	avgNumOfMsgSentInLastThreeWeeks=avgNumOfMsgSentInLastThreeWeeks(currentDay);
	
	
	if(avgNumOfMsgSentInLastThreeWeeks<numberOfMessagesSentInCurrentWeek) {
		
		numberOfMessageLeftToBeSentInCurrentWeek=0;
		
	}else {
		
		numberOfMessageLeftToBeSentInCurrentWeek=avgNumOfMsgSentInLastThreeWeeks-numberOfMessagesSentInCurrentWeek;
		
	}
	
	
	return numberOfMessageLeftToBeSentInCurrentWeek;
}



}
