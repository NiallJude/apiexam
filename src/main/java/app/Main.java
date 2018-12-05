package app;

import tweet.Tweet;

public class Main {

    public static void main(String[] args) {

        String user="tweetKing";
        String messageContent="Hello Everyone";
        String date="21/03/2016";

        Tweet tweet = new Tweet (user, date, messageContent);

        System.out.println("Name: "+tweet.getUser());
        System.out.println("Date: " + tweet.getDate());
        System.out.println("Message: " + tweet.getMessageContent());

        RunCurl runCurl;
        runCurl = new RunCurl();
        runCurl.execute();
    }
}
