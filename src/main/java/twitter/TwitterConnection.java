package twitter;

import api.CredentialManager;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class TwitterConnection {

    private static final String endpointURL = "https://api.twitter.com/oauth2/token";

    public static void main(String[] args) throws IOException {

        String encodedCredentials="";
        CredentialManager credentialManager = new CredentialManager();
        encodedCredentials = credentialManager.getEncodedCredentials();
        String bearerToken = "";
        bearerToken = requestBearerToken(endpointURL, encodedCredentials);
        System.out.println(bearerToken);
    }

    private static String requestBearerToken(String endPointUrl, String encodedCredentials) throws IOException {

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

            writeRequest(connection, "grant_type=client_credentials");

            // Parse the JSON response into a JSON mapped object to fetch fields from.
            JSONObject obj = (JSONObject) JSONValue.parse(readResponse(connection));

            if (obj != null) {
                String tokenType = (String)obj.get("token_type");
                String token = (String)obj.get("access_token");

                return ((tokenType.equals("bearer")) && (token != null)) ? token : "";
            }
            // return an empty string if there is no response
            return new String();
        }
        catch (MalformedURLException e) {
            // catch bad URL
            throw new IOException("Invalid endpoint URL specified.", e);
        }
        finally {
            // Tidy up connection object
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    // Writes a request to a connection
    private static boolean writeRequest(HttpsURLConnection connection, String textBody) {
        try {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            wr.write(textBody);
            wr.flush();
            wr.close();

            return true;
        }
        catch (IOException e) { return false; }
    }


    // Reads a response for a given connection and returns it as a string.
    private static String readResponse(HttpsURLConnection connection) {
        try {
            StringBuilder str = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = br.readLine()) != null) {
                str.append(line + System.getProperty("line.separator"));
            }
            return str.toString();
        }
        catch (IOException e) { return new String(); }
    }

}
