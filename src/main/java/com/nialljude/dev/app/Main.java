package com.nialljude.dev.app;

import com.nialljude.dev.api.CallAPI;
import com.nialljude.dev.api.CredentialManager;
import com.nialljude.dev.api.ReadJSON;
import com.nialljude.dev.github.Project;
import com.nialljude.dev.twitter.TweetHandler;
import com.nialljude.dev.twitter.TwitterBearerToken;
import com.nialljude.dev.twitter.TwitterConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Class - This is a command line based Application.
 * The Application calls the GitHub API to find projects with "Reactive".
 * Processing the response and selecting the name and summary of 10,
 * the github looks for five recent Tweets about the github
 * and displays the Project Name, Summary and five recent Tweets (JSON)
 * to the console and command line.
 * <p>
 * The github uses Apache HTTPClient for the API calls.
 * The github builds an executable JAR with all dependencies
 * using Maven - as defined in the pom.xml.
 * Author - NiallJude
 * Github - https://github.com/NiallJude/apiexam
 */
public class Main {

    private static final String endpointURL = "https://api.twitter.com/oauth2/token";

    public static void main(String[] args) {

        runGithubAPICall();
        List<Project> projectsToSearch = getProjectsFromJSON();
        String encodedCredentials = getEncodedCredentials();
        String bearerToken = getTwitterBearerToken(encodedCredentials);

        // Search for 3 recent Tweets of recent projects return list inside TweetHandler object
        TwitterConnection twitterConnection = new TwitterConnection();
        List<TweetHandler> allTweetsHandlers = new ArrayList<>();

        System.out.println("\nFetching tweets by project...");
        for (Project project : projectsToSearch){
            TweetHandler tweetHandler = twitterConnection.getTweetsByProject(project.getName(),bearerToken);
            tweetHandler.setProjectName(project.getName());
            allTweetsHandlers.add(tweetHandler);
        }
    }

    private static String getTwitterBearerToken(String encodedCredentials) {
        // Get Twitter Bearer Token Based on credentials
        String bearerToken = "";
        TwitterBearerToken twitterBearerToken = new TwitterBearerToken();
        bearerToken = twitterBearerToken.requestBearerToken(endpointURL, encodedCredentials);
        return bearerToken;
    }

    private static void runGithubAPICall() {
        // Call GitHub API
        CallAPI api = new CallAPI();
        String apiToQuery = "com/nialljude/dev/github";
        String projectType = "reactive";
        api.runAPICall();
    }

    private static List<Project> getProjectsFromJSON() {
        // Read JSON and get 10 projects in a list
        ReadJSON readJSON = new ReadJSON();
        return readJSON.readJSONFile();
    }

    private static String getEncodedCredentials() {
        // Securely grab Twitter credentials
        String encodedCredentials="";
        CredentialManager credentialManager = new CredentialManager();
        encodedCredentials = credentialManager.getEncodedCredentials();
        return encodedCredentials;
    }

}
