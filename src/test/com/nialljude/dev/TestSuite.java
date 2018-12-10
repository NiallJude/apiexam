package com.nialljude.dev;

import com.nialljude.dev.api.GithubApiCallerTest;
import com.nialljude.dev.api.TwitterApiCallerTest;
import com.nialljude.dev.credentials.CredentialManagerTest;
import com.nialljude.dev.credentials.TwitterBearerTokenTest;
import com.nialljude.dev.files.FileManagerTest;
import com.nialljude.dev.files.JSONManagerTest;
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
        TwitterApiHandlerTest.class,
        FileManagerTest.class,
        JSONManagerTest.class,
        GithubApiCallerTest.class,
        TwitterApiCallerTest.class,
        CredentialManagerTest.class,
        TwitterBearerTokenTest.class
})

public class TestSuite {
}
