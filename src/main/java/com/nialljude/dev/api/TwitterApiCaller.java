package com.nialljude.dev.api;

import com.google.gson.Gson;
import com.nialljude.dev.files.FileManager;
import com.nialljude.dev.handlers.TwitterApiHandler;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Handles the calling of the Twitter API.
 * Returns the Handler object for each project.
 *
 * @author Niall Jude Collins
 */
public class TwitterApiCaller {

    /**
     * Number of Tweets per account.
     *
     * @author Niall Jude Collins
     */
    private static final int tweetsPerAccount = 3;

    /**
     * Get Tweets from the Twitter API
     * Three per project.
     *
     * @author Niall Jude Collins
     * @param project - Project Name to query for.
     * @param bearerToken - Bearer Token Credentials.
     * @return twitterApiHandler - A handler object to hold the response objects and projects.
     */
    public TwitterApiHandler getTweetsByProject(String project, String bearerToken) {
        TwitterApiHandler twitterApiHandler;
        String queryUrlByProject = getQueryUrlByProject(project);
        twitterApiHandler = getProjectTweets(queryUrlByProject, bearerToken);
        return twitterApiHandler;
    }

    /**
     * Construct the Query URL
     * Base+project+numberOfTweets
     *
     * @author Niall Jude Collins
     * @param projectName - Project Name to query for.
     * @return queryUrl - URL to query.
     */
    private String getQueryUrlByProject(String projectName) {

        String queryUrl = "https://api.twitter.com/1.1/search/tweets.json?q=";
        queryUrl += projectName + "&count=" + tweetsPerAccount;
        return queryUrl;
    }

    /**
     * Query the Twitter API.
     * Uses javax.net.ssl library and GSON.
     * Processes response data and assigns it to objects.
     *
     * @author Niall Jude Collins
     * @param endPointUrl - URL to Query.
     * @param bearerToken - Credentials.
     * @return twitterApiHandler - Twitter API Handler object.
     */
    private TwitterApiHandler getProjectTweets(String endPointUrl, String bearerToken) {
        HttpsURLConnection connection = null;
        TwitterApiHandler twitterApiHandler = null;

        try {
            URL url = new URL(endPointUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Host", "api.twitter.com");
            connection.setRequestProperty("User-Agent", "NiallJudeDev");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
            connection.setUseCaches(false);

            // Parse the JSON response into a JSON mapped object and read response
            FileManager fileManager = new FileManager();
            JSONObject obj = (JSONObject) JSONValue.parse(fileManager.readResponse(connection));

            // Grab the toString of the object and convert to a TwitterApiHandler object
            Gson gson = new Gson();
            twitterApiHandler = gson.fromJson(obj.toString(), TwitterApiHandler.class);
            // Handle all likely exceptions
        } catch (MalformedURLException ex) {
            // return an empty string if there is no response
            System.out.println("Improper URL formation. Please check your project name.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Encountered an IOException when reading input stream.");
            ex.printStackTrace();
        } finally {
            // Tidy up connection object
            if (connection != null) {
                connection.disconnect();
            }
        }
        // Return the collection of Tweets for the projects
        return twitterApiHandler;
    }
}