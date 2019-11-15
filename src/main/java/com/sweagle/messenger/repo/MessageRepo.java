package com.sweagle.messenger.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sweagle.messenger.beans.Message;

@Repository
public interface MessageRepo extends MongoRepository<Message,String> {

	
	public List<Message> getMessageBySender(String sender);
	public List<Message> getMessageByReceiver(String receiver);
	public Message getMessageById(String id);
	
	public List<Message> getMessageBySentDate(String sentDate);
}
