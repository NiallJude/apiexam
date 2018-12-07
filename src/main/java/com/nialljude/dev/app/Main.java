package com.nialljude.dev.app;

import com.nialljude.dev.api.GithubApiCaller;
import com.nialljude.dev.credentials.CredentialManager;
import com.nialljude.dev.files.JSONReader;
import com.nialljude.dev.github.Project;
import com.nialljude.dev.handlers.TwitterApiHandler;
import com.nialljude.dev.credentials.TwitterBearerToken;
import com.nialljude.dev.api.TwitterApiCaller;

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
        // Starts the application.
        Main main = new Main();
        main.start();
    }

    private void start() {
        runGithubAPICall();
        List<Project> projectsToSearch = getProjectsFromJSON();
        String encodedCredentials = getEncodedCredentials();
        String bearerToken = getTwitterBearerToken(encodedCredentials);

        // Search for 3 recent Tweets of recent projects return list inside TwitterApiHandler object
        TwitterApiCaller twitterApiCaller = new TwitterApiCaller();
        List<TwitterApiHandler> allTweetsHandlers = new ArrayList<>();
        allTweetsHandlers = getAllTweetHandlers(projectsToSearch, bearerToken, twitterApiCaller, allTweetsHandlers);
    }

    private List getAllTweetHandlers(List<Project> projectsToSearch, String bearerToken, TwitterApiCaller twitterApiCaller, List<TwitterApiHandler> allTweetsHandlers) {
        System.out.println("\nFetching tweets by project...");
        for (Project project : projectsToSearch){
            TwitterApiHandler twitterApiHandler = twitterApiCaller.getTweetsByProject(project.getName(),bearerToken);
            twitterApiHandler.setProjectName(project.getName());
            allTweetsHandlers.add(twitterApiHandler);
        }
        return allTweetsHandlers;
    }

    private String getTwitterBearerToken(String encodedCredentials) {
        // Get Twitter Bearer Token Based on credentials
        String bearerToken = "";
        TwitterBearerToken twitterBearerToken = new TwitterBearerToken();
        bearerToken = twitterBearerToken.requestBearerToken(endpointURL, encodedCredentials);
        return bearerToken;
    }

    private void runGithubAPICall() {
        // Call GitHub API
        GithubApiCaller api = new GithubApiCaller();
        String apiToQuery = "com/nialljude/dev/github";
        String projectType = "reactive";
        api.runAPICall();
    }

    private List<Project> getProjectsFromJSON() {
        // Read JSON and get 10 projects in a list
        JSONReader JSONReader = new JSONReader();
        return JSONReader.readJSONFile();
    }

    private String getEncodedCredentials() {
        // Securely grab Twitter credentials
        String encodedCredentials="";
        CredentialManager credentialManager = new CredentialManager();
        encodedCredentials = credentialManager.getEncodedCredentials();
        return encodedCredentials;
    }
}