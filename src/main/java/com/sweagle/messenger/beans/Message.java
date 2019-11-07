package com.sweagle.messenger.beans;

import org.springframework.data.annotation.Id;

public class Message {
	
	@Id
	private String id;
	private String sender;
	private String receiver;
	private String subject;
	private String content;
	private String sentDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReciever() {
		return receiver;
	}
	public void setReciever(String receiver) {
		this.receiver = receiver;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSentDate() {
		return sentDate;
	}
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", sender=" + sender + ", reciever=" + receiver + ", subject=" + subject
				+ ", content=" + content + ", sentDate=" + sentDate + "]";
	}
	
	

}
