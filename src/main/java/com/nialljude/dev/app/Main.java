package com.nialljude.dev.app;

import com.nialljude.dev.api.GithubApiCaller;
import com.nialljude.dev.credentials.CredentialManager;
import com.nialljude.dev.files.JSONManager;
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
 * @author - Niall Jude Collins
 * @link - https://github.com/NiallJude/apiexam
 */
public class Main {

    private static final String endpointURL = "https://api.twitter.com/oauth2/token";

    /**
     * Runs the application.
     *
     * @author - Niall Jude Collins
     */
    public static void main(String[] args) {
        // Starts the application.
        Main main = new Main();
        main.start();
    }

    /**
     * Follows entire thread of execution for the application.
     * Inline comments summarise each stage.
     *
     * @author - Niall Jude Collins
     */
    private void start() {
        // Call GitHub API
        runGithubAPICall();
        // Select ten projects from results
        List<Project> projectsToSearch = getProjectsFromJSON();
        // Get encoded credentials and bearer token for Twitter call.
        String encodedCredentials = getEncodedCredentials();
        String bearerToken = getTwitterBearerToken(encodedCredentials);
        // Search for 3 recent Tweets of recent projects return list inside TwitterApiHandler object
        TwitterApiCaller twitterApiCaller = new TwitterApiCaller();
        // Display information on each project and the Tweets about it.
        List<TwitterApiHandler> allTweetsHandlers;
        allTweetsHandlers = getAllTweetHandlers(projectsToSearch, bearerToken, twitterApiCaller);
        displayResultsInJSON(allTweetsHandlers);
    }

    /**
     * Using a GSON method, this converts get Twitter API Handler
     * object into a JSON format and displays it. Before each JSON
     * object, the name of the project is printed for clarity.
     * Each TwitterAPIHandler contains Tweet, User and Projects objects.
     *
     * @author - Niall Jude Collins
     * @param  allTweetsHandlers  A List of all TwitterAPIHandlers
     */
    private void displayResultsInJSON(List<TwitterApiHandler> allTweetsHandlers) {
        JSONManager jsonManager = new JSONManager();

        String json;

        for (TwitterApiHandler twitterApiHandler : allTweetsHandlers){
            System.out.println("\n"+twitterApiHandler.getProject().getName()+" Summary:\n");
            json = jsonManager.convertToJSON(twitterApiHandler, twitterApiHandler.getProject().getName());
            System.out.println(json);
        }
    }

    /**
     * Call the Twitter API - and dynamically create TweetHandlerObjects to
     * hold the responses in Java objects. This is translated using GSON.
     *
     * @author - Niall Jude Collins
     * @param projectsToSearch - A List of Project names to query Twitter for
     * @param bearerToken - The base 64 string credential which authenticates the app to Twitter.
     * @param twitterApiCaller - The instance of the class that handles the Twitter API call.
     * @return allTweetsHandlers - An ArrayList of all the Tweet Handler objects.
     */
    private List getAllTweetHandlers(List<Project> projectsToSearch, String bearerToken, TwitterApiCaller twitterApiCaller) {
        System.out.println("\nFetching tweets by project...");
        List<TwitterApiHandler> allTweetsHandlers = new ArrayList<>();
        for (Project project : projectsToSearch){
            TwitterApiHandler twitterApiHandler = twitterApiCaller.getTweetsByProject(project.getName(),bearerToken);
            twitterApiHandler.setProject(project);
            allTweetsHandlers.add(twitterApiHandler);
        }
        return allTweetsHandlers;
    }

    /**
     * Create a bearer token by querying Twitter with the encoded credentials.
     * Credentials to be stored in 'secrets/credentials.csv'.
     *
     * @author - Niall Jude Collins
     * @param encodedCredentials - A String constructed by combining the Consumer Key and Secret.
     * @return bearerToken - The object for authentication.
     */
    private String getTwitterBearerToken(String encodedCredentials) {
        // Get Twitter Bearer Token Based on credentials
        String bearerToken;
        TwitterBearerToken twitterBearerToken = new TwitterBearerToken();
        bearerToken = twitterBearerToken.requestBearerToken(endpointURL, encodedCredentials);
        return bearerToken;
    }

    /**
     * Run the Github API call to search for projects.
     * No return - but this will place Github.json raw output
     * on the root of the project for ingestion and conversion
     * to Java Objects.
     *
     * @author - Niall Jude Collins
     */
    private void runGithubAPICall() {
        // Call GitHub API
        GithubApiCaller api = new GithubApiCaller();
        api.runAPICall();
    }

    /**
     * Get Projects from JSON.
     * Take the output of the Github query
     * and process it.
     *
     * @author - Niall Jude Collins
     * @return A list of Project objects from the Github API response.
     */
    private List<Project> getProjectsFromJSON() {
        // Read JSON and get 10 projects in a list
        JSONManager JSONManager = new JSONManager();
        return JSONManager.readJSONFile();
    }

    /**
     * Get Encoded Credentials from file.
     * File must be located at 'secrets/credentials.csv'
     *
     * @author - Niall Jude Collins
     * @return encodedCredentials - The credentials for Twitter (encoded)
     */
    private String getEncodedCredentials() {
        // Securely grab Twitter credentials
        String encodedCredentials;
        CredentialManager credentialManager = new CredentialManager();
        encodedCredentials = credentialManager.getEncodedCredentials();
        return encodedCredentials;
    }
}