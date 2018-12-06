package app;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
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

        int index = result.indexOf("name");
        int index2 = result.indexOf("full_");
        System.out.println();

        String sub;
        sub = result.substring(index,index2-2);
        index = sub.indexOf(":");
        index++;
        sub = sub.substring(index,sub.length());
        sub = sub.replaceAll("^\"|\"$", "");
        System.out.println(sub);

        writeResponse(result);

    }

    public void writeResponse(StringBuffer result) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("foo.json"), "utf-8"))) {
            writer.append(result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private StringBuffer getAPIResponse(Main main, CloseableHttpClient httpclient, String scheme, String host, String path, String searchParameter, String searchValue, StringBuffer result) {
        HttpResponse response;

        try {
            URI uri = main.getUri(scheme, host, path, searchParameter, searchValue);
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader("accept","application/json");
            response = httpclient.execute(httpGet);

            //verify the valid error code first
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            result = main.processResponse(response);
        } catch (URISyntaxException ex) {
            main.printResponse("Invalid URI.");
            ex.printStackTrace();
        } catch (IOException ex) {
            main.printResponse("Apache encountered an IO Exception.");
            ex.printStackTrace();
        }
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