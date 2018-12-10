package com.nialljude.dev.suite;

import com.nialljude.dev.github.ProjectTest;
import com.nialljude.dev.handlers.GithubApiHandlerTest;
import com.nialljude.dev.handlers.HandlerTest;
import com.nialljude.dev.handlers.TwitterApiHandlerTest;
import com.nialljude.dev.twitter.TweetTest;
import com.nialljude.dev.twitter.UserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ProjectTest.class,
        TweetTest.class,
        UserTest.class,
        HandlerTest.class,
        GithubApiHandlerTest.class,
        TwitterApiHandlerTest.class
})

public class TestSuite {
}
