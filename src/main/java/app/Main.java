package app;

import api.CallAPI;
import api.CredentialManager;
import api.ReadJSON;
import github.Project;
import twitter.TweetHandler;
import twitter.TwitterBearerToken;
import twitter.TwitterConnection;

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
        // Call GitHub API
        CallAPI api = new CallAPI();
        String apiToQuery = "github";
        String projectType = "reactive";
        api.runAPICall();

        // Read JSON and get 10 projects in a list
        ReadJSON readJSON = new ReadJSON();
        List<Project> projectsToSearch = readJSON.readJSONFile();

        // Securely grab Twitter credentials
        String encodedCredentials="";
        CredentialManager credentialManager = new CredentialManager();
        encodedCredentials = credentialManager.getEncodedCredentials();

        // Get Twitter Bearer Token Based on credentials
        String bearerToken = "";
        TwitterBearerToken twitterBearerToken = new TwitterBearerToken();
        bearerToken = twitterBearerToken.requestBearerToken(endpointURL, encodedCredentials);

        // Search for 3 recent Tweets of recent projects return list inside TweetHandler object
        TwitterConnection twitterConnection = new TwitterConnection();

        List<TweetHandler> allTweetsHandlers = new ArrayList<>();

        System.out.println("\nFetching tweets by project...");
        for (Project project : projectsToSearch){
            TweetHandler tweetHandler = twitterConnection.getTweetsByProject(project.getName(),bearerToken);
            tweetHandler.setProjectName(project.getName());
            allTweetsHandlers.add(tweetHandler);
        }

        System.out.println("\nNumber of tweets by project:");
        for (TweetHandler tweetHandler : allTweetsHandlers) {
            System.out.println(tweetHandler.getProjectName()+": "+tweetHandler.getStatuses().size());
        }
    }


}