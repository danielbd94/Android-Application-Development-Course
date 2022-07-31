package com.example.Model;

//chat POJO includes the data of a chat (sender,receiver,message etc).
public class ChatModel {

    String sender, receiver, message, date, type;

    public ChatModel() {
    }

    public ChatModel(String sender, String receiver, String message, String date, String type) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.date = date;
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}