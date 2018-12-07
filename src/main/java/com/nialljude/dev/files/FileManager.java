package com.nialljude.dev.files;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;

public class FileManager {

    // Writes a request to a connection
    public boolean writeRequest(HttpsURLConnection connection, String textBody) {
        try {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            wr.write(textBody);
            wr.flush();
            wr.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Reads a response for a given connection and returns it as a string.
    public String readResponse(HttpsURLConnection connection) {
        try {
            StringBuilder str = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                str.append(line + System.getProperty("line.separator"));
            }
            return str.toString();
        } catch (IOException e) {
            return new String();
        }
    }
}
