package com.nialljude.dev.handlers;

import com.nialljude.dev.github.Project;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HandlerTest {

    private Handler testHandler;
    private List<Project> testItems;
    private Project testProject;

    // Project Vars
    private String testName;
    private int testId;
    private String testDescription;
    private String testFull_name;
    private String testUrl;
    private int testWatchers_count;
    private int testForks_count;

    @Before
    public void setUp() throws Exception {
        testName = "test1";
        testId = 1;
        testDescription = "test2";
        testFull_name = "test3";
        testUrl = "test4";
        testWatchers_count = 2;
        testForks_count = 3;
        testProject = new Project(testId, testDescription, testName, testFull_name, testUrl, testWatchers_count, testForks_count);
        testItems = new ArrayList<>();
        testItems.add(testProject);
        testHandler = new GithubApiHandler(testItems);
        testHandler.setItems(testItems);
    }

    @Test
    public void getItemsSizeIsCorrectWithOneItem() {
        int actual = testHandler.getItems().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void getItemsSizeIsCorrectWithZeroItems() {
        testHandler.getItems().clear();
        Boolean actual = testHandler.getItems().isEmpty();
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void getProjectFromListAndEnsureItIsTheTestProject() {
        Project actual = testHandler.getItems().get(0);
        Project expected = testProject;
        assertEquals(expected, actual);
    }

    @Test
    public void getProjectFromListAndEnsureNameVariableValuesIsCorrect() {
        Boolean actual = testProject.getName().equals(testName);
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void checkGetItemsContainsTestProject() {
        Project actual = testItems.get(0);
        Project expected = testProject;
        assertEquals(expected, actual);
    }
}