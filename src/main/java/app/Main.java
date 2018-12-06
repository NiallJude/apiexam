package app;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {

        Main main = new Main ();

        main.runAPICall(main);

    }

    private void runAPICall(Main main) {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String scheme = "https";
        String host = "api.github.com";
        String path = "/search/repositories";
        String searchParameter = "q";
        String searchValue = "reactive";
        StringBuffer result;

        try {
            URI uri = main.getUri(scheme, host, path, searchParameter, searchValue);
            HttpGet httpGet = new HttpGet(uri);
            HttpResponse response = httpclient.execute(httpGet);

            result = main.readResponse(response);
            main.printResponse("Response Payload is " + result);

        } catch (Exception ex) {
            main.printResponse("Didn't work.");
        }
    }

    private StringBuffer readResponse(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();

        String line = "";

        while ((line=rd.readLine())!=null) {
            result.append(line);
        }

        return result;
    }

    private void printResponse(String s) {
        System.out.println(s);
    }

    private URI getUri(String scheme, String host, String path, String searchParameter, String searchValue) throws URISyntaxException {
        return new URIBuilder()
                    .setScheme(scheme)
                    .setHost(host)
                    .setPath(path)
                    .setParameter(searchParameter, searchValue)
                    .build();
    }
}
