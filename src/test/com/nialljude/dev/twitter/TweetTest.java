package com.nialljude.dev.twitter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TweetTest {

    private Tweet testTweet;
    private User testUser;
    private String testCreated_at;
    private String testText;
    private String testScreen_name;
    private String testDescription;
    private String testUrl;


    @Before
    public void setUp() {
        testCreated_at = "test1";
        testText = "test2";
        testScreen_name = "test3";
        testDescription = "test4";
        testUrl = "test5";
        testUser = new User(testScreen_name, testDescription, testUrl);

        testTweet = new Tweet(testUser, testCreated_at, testText);
    }

    @Test
    public void getUser() {
        User actual = testTweet.getUser();
        User expected = testUser;
        assertEquals(expected, actual);
    }

    @Test
    public void setUser() {
        testTweet.setUser(testUser);
        User actual = testTweet.getUser();
        User expected = testUser;
        assertEquals(expected, actual);
    }

    @Test
    public void getCreated_at() {
        String expected = testCreated_at;
        String actual = testTweet.getCreated_at();
        assertEquals(expected, actual);
    }

    @Test
    public void setCreated_at() {
        testTweet.setCreated_at(testCreated_at);
        String expected = testCreated_at;
        String actual = testTweet.getCreated_at();
        assertEquals(expected, actual);
    }

    @Test
    public void getText() {
        String expected = testText;
        String actual = testTweet.getText();
        assertEquals(expected, actual);
    }

    @Test
    public void setText() {
        testTweet.setText(testText);
        String expected = testCreated_at;
        String actual = testTweet.getCreated_at();
        assertEquals(expected, actual);
    }

}