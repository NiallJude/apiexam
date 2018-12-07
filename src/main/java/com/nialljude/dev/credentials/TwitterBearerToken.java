package com.nialljude.dev.credentials;

import com.nialljude.dev.files.FileManager;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TwitterBearerToken {

    public String requestBearerToken(String endPointUrl, String encodedCredentials) {

        HttpsURLConnection connection = null;

        try {
            URL url = new URL(endPointUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Host", "api.twitter.com");
            connection.setRequestProperty("User-Agent", "NiallJudeDev");
            connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            connection.setRequestProperty("Content-Length", "29");
            connection.setUseCaches(false);

            FileManager fileManager = new FileManager();

            fileManager.writeRequest(connection, "grant_type=client_credentials");

            // Parse the JSON response into a JSON mapped object to fetch fields from.
            JSONObject obj = (JSONObject) JSONValue.parse(fileManager.readResponse(connection));

            if (obj != null) {
                String tokenType = (String) obj.get("token_type");
                String token = (String) obj.get("access_token");

                return ((tokenType.equals("bearer")) && (token != null)) ? token : "";
            }

        } catch (MalformedURLException ex) {
            // return an empty string if there is no response
            System.out.println("Improper URL formation. Please check your access credentials.");
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
        // return an empty string if there is no response
        return new String();
    }
}
