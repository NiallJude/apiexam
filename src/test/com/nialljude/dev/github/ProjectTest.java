package com.nialljude.dev.github;

import com.nialljude.dev.twitter.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectTest {

    private Project testProject;
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
    }

    @Test
    public void getName() {
        String actual = testProject.getName();
        String expected = testName;
        assertEquals(expected, actual);
    }

    @Test
    public void testConstructor() {
        Project actual = testProject;
        if (testProject.equals(actual)){
            assertTrue(true);
        }
    }
}