package com.nialljude.dev.twitter;

public class Tweet {

    // Vars
    private User user;
    private String created_at;
    private String text;

    // Default Constructor
    public Tweet(User user, String created_at, String text) {
        this.user = user;
        this.created_at = created_at;
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "user='" + user + '\'' +
                ", created_at='" + created_at + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
