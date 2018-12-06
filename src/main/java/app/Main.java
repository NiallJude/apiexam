package app;

import api.CallAPI;
import api.ReadJSON;

/**
 * Main Class - This is a command line based Application.
 * The Application calls the GitHub API to find projects with "Reactive".
 * Processing the response and selecting the name and summary of 10,
 * the project looks for five recent Tweets about the project
 * and displays the Project Name, Summary and five recent Tweets (JSON)
 * to the console and command line.
 * <p>
 * The project uses Apache HTTPClient for the API calls.
 * The project builds an executable JAR with all dependencies
 * using Maven - as defined in the pom.xml.
 * Author - NiallJude
 * Github - https://github.com/NiallJude/apiexam
 */
public class Main {

    public static void main(String[] args) {
        // Call GitHub API
        CallAPI api = new CallAPI();
        api.runAPICall();

        // Read JSON and get 10 projects
        ReadJSON readJSON = new ReadJSON();
        readJSON.readJSONFile();

        // Securely grab Twitter credentials

        // Search for 3 recent Tweets of recent projects
    }


}