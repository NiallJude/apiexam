package com.nialljude.dev.files;

import com.nialljude.dev.github.Project;
import com.nialljude.dev.handlers.GithubApiHandler;
import com.nialljude.dev.handlers.TwitterApiHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class JSONManagerTest {

    JSONManager testJSONManager = new JSONManager();
    List<Project> expected = new ArrayList<>();
    TwitterApiHandler twitterApiHandler;
    GithubApiHandler githubApiHandler;
    Project testProject;
    private String testName;
    private int testId;
    private String testDescription;
    private String testFull_name;
    private String testUrl;
    private int testWatchers_count;
    private int testForks_count;

    @Before
    public void setUp() throws Exception {
        githubApiHandler = new GithubApiHandler(expected);
        twitterApiHandler = new TwitterApiHandler();
        testName = "test1";
        testId = 1;
        testDescription = "test2";
        testFull_name = "test3";
        testUrl = "test4";
        testWatchers_count = 2;
        testForks_count = 3;
        testProject = new Project(testId, testDescription, testName, testFull_name, testUrl, testWatchers_count, testForks_count);
    }

    @Test
    public void readJSONFileReturnsAListOfTheCorrectLength() {
        JSONManager jsonManager = Mockito.mock(JSONManager.class);
        when (jsonManager.readJSONFile()).thenReturn(expected);
        List<Project> actual = jsonManager.readJSONFile();
        assertEquals(expected,actual);
    }

    @Test
    public void printProjectNamesFunctionsWithOneProject() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Use Reflection To Test Private Method
        Method method = testJSONManager.getClass().getDeclaredMethod("printProjectNames", List.class);
        method.setAccessible(true);
        Object object = method.invoke(testJSONManager, expected);
        assertNull(object);
    }

    @Test
    public void getGithubApiHandlerReturnsTheCorrectObjectClass() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = testJSONManager.getClass().getDeclaredMethod("getGithubApiHandler", String.class);
        method.setAccessible(true);
        String json = "{\"value\": \"test\", \"test\": \"test()\"}";
        Object githubApiHandler1 = method.invoke(testJSONManager, json);
        assertTrue(githubApiHandler1.getClass().equals(GithubApiHandler.class));
    }

    @Test
    public void getRateLimitedListReturnsTheCorrectObjectClass() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = testJSONManager.getClass().getDeclaredMethod("getRateLimitedList", List.class);
        method.setAccessible(true);
        expected.add(testProject);
        Object githubApiHandler1 = method.invoke(testJSONManager, expected);
        assertTrue(githubApiHandler1.getClass().equals(ArrayList.class));
    }

    @Test
    public void getRateLimitedListReturnsTheCorrectSize() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = testJSONManager.getClass().getDeclaredMethod("getRateLimitedList", List.class);
        method.setAccessible(true);
        expected.add(testProject);
        Object githubApiHandler1 = method.invoke(testJSONManager, expected);
        assertFalse(githubApiHandler1.toString().isEmpty());
    }

    @Test
    public void convertToJSONWorksWithTwitterAPIHandler() {
        testJSONManager.convertToJSON(twitterApiHandler);
        assertNotNull(testJSONManager);
    }

    @Test
    public void convertToJSONWorksWithTwitterAPIHandlerAndAssociatedProjectReturnContainsExpectedValues() {
        twitterApiHandler.setProject(testProject);
        String actual = testJSONManager.convertToJSON(twitterApiHandler);
        String expected = testProject.getName();
        assertTrue(actual.contains(expected));
    }

    @Test
    public void convertToJSONWorksWithProject() {
        testJSONManager.convertToJSON(testProject);
        assertNotNull(testJSONManager);
    }

    @Test
    public void convertToJSONWorksWithProjectAndReturnContainsExpectedValues() {
        String actual = testJSONManager.convertToJSON(testProject);
        String expected = testProject.getName();
        assertTrue(actual.contains(expected));
    }
}