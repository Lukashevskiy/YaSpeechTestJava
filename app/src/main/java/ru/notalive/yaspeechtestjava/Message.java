package ru.notalive.yaspeechtestjava;

public class Message {
    private final int type;
    private String text;

    public Message(int type, String text){
        this.type = type;
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) { this.text = text;}
}
