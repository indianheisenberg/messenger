package com.sweagle.messenger.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.sweagle.messenger.beans.Message;
import com.sweagle.messenger.repo.MessageRepo;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class MessengerServiceTest {
	
	
	 @InjectMocks
	private MessageService messengerService;
	

	@Mock
	private MessageRepo messageRepo;
	
	private Message testMessage1;
	
	
	private final String id1 = "mockid1";
	private final String sender1 = "Ashish";
	private final String receiver1 = "Avinash";
	private final String subject1="Message Service Unit Test 1";
	private final String content1 = "Mock content for unit test 1";
	private final String sentDate1 = "10-Nov-2019";
	
	
	
	private final List<Message> messages=new ArrayList<Message>();
	
	@BeforeEach
	public void setup() {
		
		System.out.println("inside setup");
		testMessage1=new Message();
		testMessage1.setId(id1);
		testMessage1.setSender(sender1);
		testMessage1.setReciever(receiver1);
		testMessage1.setSubject(subject1);
		testMessage1.setContent(content1);
		testMessage1.setSentDate(sentDate1);
		
		
		
		messages.add(testMessage1);
		//messages.add(testMessage2);
		
	}
	
	@Test
	public void getIncomingMessagesTest() throws Exception {
		
		
		when(messengerService.getIncomingMessages(receiver1)).thenReturn(messages);
		//given(messengerService.getIncomingMessages(receiver1)).willReturn(messages);
		
		List<Message> testList1=messengerService.getIncomingMessages(receiver1);
		
	
		
		assertThat(testList1.get(0).getReciever()).isEqualTo(testMessage1.getReciever());
		

    }
	
	
	@Test
	public void getSentMessagesTest() throws Exception {
		
		
		when(messengerService.getSentMessages(sender1)).thenReturn(messages);
		//given(messengerService.getIncomingMessages(receiver1)).willReturn(messages);
		
		List<Message> testList1=messengerService.getSentMessages(sender1);
		
	
		
		assertThat(testList1.get(0).getSender()).isEqualTo(testMessage1.getSender());
		

    }
	
	@Test
	public void getSentMessageByIdTest() throws Exception {
		
	
		when(messengerService.getMessageById(id1)).thenReturn(messages.get(0));
		//given(messengerService.getIncomingMessages(receiver1)).willReturn(messages);
		
		
		Message message=messengerService.getMessageById(id1);
		
		assertThat(message.getId()).isEqualTo(id1);
		

    }
	
	@Test
	public void saveMessage() throws Exception {
		
	
		when(messengerService.saveMessages(testMessage1)).thenReturn(testMessage1);
		//given(messengerService.getIncomingMessages(receiver1)).willReturn(messages);
		
		Message message=messengerService.saveMessages(testMessage1);
		
		
		assertThat(message).isEqualTo(testMessage1);
		

    }
	
	/*@Test
	public void getExpectedMessagesForToday() throws Exception {
		
	
		when(messengerService.getExpectedMessagesForToday()).thenReturn(2);
		//given(messengerService.getIncomingMessages(receiver1)).willReturn(messages);
		
		int i=messengerService.getExpectedMessagesForToday();
		
		
		assertThat(i).isEqualTo(2);
		

    }
	
	@Test
	public void getExpectedMessagesForWeek() throws Exception {
		
	
		when(messengerService.getExpectedMessagesForWeek()).thenReturn(10);
		//given(messengerService.getIncomingMessages(receiver1)).willReturn(messages);
		
		int i=messengerService.getExpectedMessagesForWeek();
		
		
		assertThat(i).isEqualTo(10);
		

    }*/
}
