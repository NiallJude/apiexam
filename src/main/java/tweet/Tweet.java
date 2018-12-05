package tweet;

public class Tweet {

    // Vars
    private String user;
    private String date;
    private String messageContent;

    // Default Constructor
    public Tweet(String user, String date, String messageContent) {
        this.user = user;
        this.date = date;
        this.messageContent = messageContent;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "user='" + user + '\'' +
                ", date='" + date + '\'' +
                ", messageContent='" + messageContent + '\'' +
                '}';
    }
}
