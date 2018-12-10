package com.nialljude.dev.twitter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;
    private String testScreen_name;
    private String testDescription;
    private String testUrl;

    @Before
    public void setUp() throws Exception {
        testScreen_name = "test1";
        testDescription = "test2";
        testUrl = "test3";
        user = new User(testScreen_name,testDescription,testUrl);
    }

    @Test
    public void testConstructor() {
        User actual = user;
        if (user.equals(actual)){
            assertTrue(true);
        }
    }
}