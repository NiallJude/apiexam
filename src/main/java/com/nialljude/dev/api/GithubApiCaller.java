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

    public void runAPICall() {
        // Initialise a closable HTTPClient and new StringBuffer
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuffer result = new StringBuffer();

        // Variables to build Github query
        // To match 'https://api.github.com/search/repositories\?q\=reactive'
        String scheme = "https";
        String host = "api.github.com";
        String path = "/search/repositories";
        String searchParameter = "q";
        String searchValue = "reactive";
        String fileName = "Github.json";

        // Get StringBuffer result from API Call
        result = getAPIResponse(httpClient, scheme, host, path, searchParameter, searchValue);

        // Write response to a file in JSON
        writeResponse(result, fileName);

        closeResource(httpClient);
    }

    private void closeResource(CloseableHttpClient httpClient) {
        try {
            httpClient.close();
        } catch (IOException ex) {
            System.out.println("Error closing httpClient");
            ex.printStackTrace();
        }
    }

    private void writeResponse(StringBuffer result, String fileName) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"))) {
            writer.append(result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private StringBuffer getAPIResponse(CloseableHttpClient httpclient, String scheme, String host, String path, String searchParameter, String searchValue) {
        // Initalise response and result
        HttpResponse response;
        StringBuffer result = new StringBuffer();

        try {
            URI uri = getUri(scheme, host, path, searchParameter, searchValue);
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

    private StringBuffer processResponse(HttpResponse response) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";

        while ((line = rd.readLine()) != null)
            result.append(line);

        return result;
    }

    public URI getUri(String scheme, String host, String path, String searchParameter, String searchValue) throws URISyntaxException {
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
