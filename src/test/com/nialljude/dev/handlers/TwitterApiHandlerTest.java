package com.nialljude.dev.handlers;

import com.nialljude.dev.github.Project;
import com.nialljude.dev.twitter.Tweet;
import com.nialljude.dev.twitter.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterApiHandlerTest {

    // Tweet Handler Vars
    private TwitterApiHandler twitterApiHandler;
    private List<Tweet> testList;

    // Project Vars
    private Project testProject;
    private String testName;
    private int testId;
    private String testDescription;
    private String testFull_name;
    private String testUrl;
    private int testWatchers_count;
    private int testForks_count;

    // Tweet Vars
    private Tweet testTweet;
    private User testUser;
    private String testCreated_at;
    private String testText;
    private String testScreen_name;

    @Before
    public void setUp() throws Exception {
        // Initialise Tweet
        testCreated_at = "test1";
        testText = "test2";
        testScreen_name = "test3";
        testDescription = "test4";
        testUrl = "test5";
        testUser = new User(testScreen_name, testDescription, testUrl);
        testTweet = new Tweet(testUser, testCreated_at, testText);

        // Initialise Project
        testName = "test1";
        testId = 1;
        testDescription = "test2";
        testFull_name = "test3";
        testUrl = "test4";
        testWatchers_count = 2;
        testForks_count = 3;
        testProject = new Project(testId, testDescription, testName, testFull_name, testUrl, testWatchers_count, testForks_count);

        // Initialise List
        testList = new ArrayList<>();
        testList.add(testTweet);

        // Initialise Handler
        twitterApiHandler = new TwitterApiHandler();
        twitterApiHandler.setProject(testProject);
        twitterApiHandler.setStatuses(testList);
    }

    @Test
    public void getProject() {
        Project actual = twitterApiHandler.getProject();
        Project expected = testProject;
        assertEquals(expected, actual);
    }

    @Test
    public void setProject() {
        twitterApiHandler.setProject(testProject);
        Project actual = twitterApiHandler.getProject();
        Project expected = testProject;
        assertEquals(expected,actual);
    }

    @Test
    public void setStatusesLengthIsCorrectWithOne() {
        int actual = twitterApiHandler.getStatuses().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void setStatusesLengthIsCorrectWithZero() {
        twitterApiHandler.getStatuses().clear();
        int actual = twitterApiHandler.getStatuses().size();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void associatedProjectIsCorrect() {
        Project actual = twitterApiHandler.getProject();
        Project expected = testProject;
        assertEquals(expected, actual);
    }

    @Test
    public void associatedListIsCorrect() {
        List<Tweet> actual = twitterApiHandler.getStatuses();
        List<Tweet> expected = testList;
        assertEquals(expected, actual);
    }
}