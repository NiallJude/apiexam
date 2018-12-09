package com.nialljude.dev.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class GithubApiCaller {

    /**
     * Call the Github API - construct the URI in stages.
     * Searches for Reactive projects and sorts by Stars.
     *
     * @author - Niall Jude Collins
     */
    public void runAPICall() {
        // Initialise a closable HTTPClient and new StringBuffer
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuffer result;

        // Variables to build Github query
        // To match 'https://api.github.com/search/repositories\?q\=reactive'
        String scheme = "https";
        String host = "api.github.com";
        String path = "/search/repositories";
        String searchParameter = "q";
        String searchValue = "reactive";
        String sortParameter = "&";
        String sortValue = "sort=stars";

        String fileName = "Github.json";

        // Get StringBuffer result from API Call
        result = getAPIResponse(httpClient, scheme, host, path, searchParameter, searchValue, sortParameter, sortValue);

        // Write response to a file in JSON
        writeResponse(result, fileName);

        closeResource(httpClient);
    }

    /**
     * Close the HttpClient resource.
     * It will not be needed again.
     *
     * @author - Niall Jude Collins
     * @exception IOException - Potential if the httpClient cannot be found (rare).
     */
    private void closeResource(CloseableHttpClient httpClient) {
        try {
            httpClient.close();
        } catch (IOException ex) {
            System.out.println("Error closing httpClient");
            ex.printStackTrace();
        }
    }

    /**
     * Write the String Buffer content to a file.
     * This will be analysed and converted into
     * Project objects.
     *
     * @author - Niall Jude Collins
     * @param result - Results of the API Call (to write).
     * @param fileName - The name of the file this will create.
     * @exception  IOException - Read/Writes may fail.
     */
    private void writeResponse(StringBuffer result, String fileName) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"))) {
            writer.append(result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Call the Github API.
     * Sorts by stars.
     * Orders descending (default).
     * Captures the response and returns it.
     *
     * @author - Niall Jude Collins
     * @param httpclient - The HTTPClient from Apache that will handle the call.
     * @param scheme - HTTP or HTTPS?
     * @param host - The website (api.github.com)
     * @param path - /search/repositories - The sub directories of api.github.com to query
     * @param searchParameter - q - The query notation.
     * @param searchValue- The type of repository to search for: reactive in this case.
     * @param sortParameter - &sort Add sorting command to output
     * @param sortValue- What to sort on (stars in this case).
     * @return result - The StringBuffer output of the response.
     * @exception URISyntaxException - URI May not have been correctly set up.
     * @exception IOException - The Read/Write of the data may fail (unlikely).
     * @exception RuntimeException - Thrown in the result of a non-200 HTTP code.
     */
    private StringBuffer getAPIResponse(CloseableHttpClient httpclient, String scheme, String host, String path, String searchParameter, String searchValue, String sortParameter, String sortValue) {
        // Initalise response and result
        HttpResponse response;
        StringBuffer result = new StringBuffer();

        try {
            URI uri = getUri(scheme, host, path, searchParameter, searchValue, sortParameter, sortValue);
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader("accept", "application/json");
            response = httpclient.execute(httpGet);

            // Verify the healthy error code
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            // Convert the inputStream object to a StringBuffer
            result = processResponse(response);
        } catch (URISyntaxException ex) {
            printResponse("Invalid URI.");
            ex.printStackTrace();
        } catch (IOException ex) {
            printResponse("Apache encountered an IO Exception.");
            ex.printStackTrace();
        }
        // return the response body
        return result;
    }

    /**
     * Process the response and return.
     * Throws IOException.
     *
     * @author - Niall Jude Collins
     * @param response - The HTTP Response from the API.
     */
    private StringBuffer processResponse(HttpResponse response) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line;

        while ((line = rd.readLine()) != null)
            result.append(line);

        return result;
    }

    /**
     * Process the response and return.
     * Throws IOException.
     *
     * @author - Niall Jude Collins
     * @param scheme - HTTP or HTTPS?
     * @param host - The website (api.github.com)
     * @param path - /search/repositories - The sub directories of api.github.com to query
     * @param searchParameter - q - The query notation.
     * @param searchValue- The type of repository to search for: reactive in this case.
     * @param sortParameter - &sort Add sorting command to output
     * @param sortValue- What to sort on (stars in this case).
     * @return URI - The object with which to query the API.
     */
    public URI getUri(String scheme, String host, String path, String searchParameter, String searchValue, String sortParameter, String sortValue) throws URISyntaxException {
        return new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .setParameter(searchParameter, searchValue)
                .setParameter(sortParameter, sortValue)
                .build();
    }

    /**
     * Simply print a given String.
     *
     * @author - Niall Jude Collins
     * @param string - String to print.
     */
    private void printResponse(String string) {
        System.out.println(string);
    }
}
