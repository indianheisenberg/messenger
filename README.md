Messenger Application.

APIs

1. /getIncomingMessages/{reciever}
    API returns the list of incoming messages for the reciever.
    Example : /getIncomingMessages/ashish
   
2. /getSentMessages/{sender}
    API returns the list of sent messages for the sender.
    Example : /getSentMessages/ashish

3. /getMessageById/{id}
    API returns the messages with the message id.
    Example : /getSentMessages/5dc462fb78fc92043edf4f11
    
4. /saveMessage
    API is used to save message in Database
    Example : /saveMessage
    body:   {
            "sender" : "avinash",
            "receiver" : "ashish",
            "subject" : "Test",
            "content" : "test content",
            "sentDate" : "13-Nov-2019",
            "sentTime" : "13:15:30",
            }
    
5. /getExpectedMessagesForToday
     API returns the expected number of messages to be sent for the rest of the day.
     
6. /getExpectedMessagesForWeek
     API returns the expected number of messages to be sent for the rest of the week.
     
     

NOTES:

1. Mandatory Fields in the Message body
    sender,receiver,subject,content,sentDate,sentTime
    
2. Basic algorithm behind API /getExpectedMessagesForToday
    1. Calculate the current time
    2. loop over the last 7 days
    3. get the messages sent on the past day
    4. check how many messages were sent after the current time on that day.
    5. Sum all such message counts
    6. Get average of the messages sent on these days.
    7. The average number sent on the past 7 days after the current time should reflect on how many messages could be sent for the rest        of the day.
    
 3. Basic algorithm behind API /getExpectedMessagesForWeek
    1. calucalte the current day of the week.
    2. Get number of messages sent in current week.
    3. Get average number of messages sent in last 3 weeks.
    4. Number of messages left to be sent this week should be equal to the average of the last three weeks minus the messages sent in          the current week.
    
  4. Mongo DB Uri used:
     spring.data.mongodb.uri=mongodb://127.0.0.1:27017/messenger

  
