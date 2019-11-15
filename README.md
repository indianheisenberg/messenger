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
