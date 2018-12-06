package app;

// Apache Imports

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

// Java Imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

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
        // Initialise object to allow non-static calls
        Main main = new Main();
        main.runAPICall(main);
    }

    private void runAPICall(Main main) {
        // Initialise a closable HTTPClient
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // Variables to build Github query
        // To match 'https://api.github.com/search/repositories\?q\=reactive'
        String scheme = "https";
        String host = "api.github.com";
        String path = "/search/repositories";
        String searchParameter = "q";
        String searchValue = "reactive";
        // Initialise null Buffer and Response
        StringBuffer result = null;
        HttpResponse response = null;

        // Get StringBuffer result from API Call
        result = getAPIResponse(main, httpclient, scheme, host, path, searchParameter, searchValue, result);

        // Print Output
        main.printResponse("Response Payload is " + result);
    }

    private StringBuffer getAPIResponse(Main main, CloseableHttpClient httpclient, String scheme, String host, String path, String searchParameter, String searchValue, StringBuffer result) {
        HttpResponse response;

        try {
            URI uri = main.getUri(scheme, host, path, searchParameter, searchValue);
            HttpGet httpGet = new HttpGet(uri);
            response = httpclient.execute(httpGet);
            result = main.readResponse(response);
        } catch (URISyntaxException ex) {
            main.printResponse("Invalid URI.");
            ex.printStackTrace();
        } catch (IOException ex) {
            main.printResponse("Apache encountered an IO Exception.");
            ex.printStackTrace();
        }
        return result;
    }

    private StringBuffer readResponse(HttpResponse response) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";

        while ((line = rd.readLine()) != null)
            result.append(line);

        return result;
    }

    private URI getUri(String scheme, String host, String path, String searchParameter, String searchValue) throws URISyntaxException {
        return new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .setParameter(searchParameter, searchValue)
                .build();
    }

    private void printResponse(String s) {
        System.out.println(s);
    }
}