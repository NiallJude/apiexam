package com.nialljude.dev.files;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;

/**
 * A class to hold all functions concerned with the reading and writing of data.
 * Largely IO related.
 *
 * @author Niall Jude Collins.
 */
public class FileManager {


    /**
     * Writes a request to a connection.
     *
     * @author Niall Jude Collins.
     * @param connection - A HttpsURLConnection object.
     * @param textBody - The text to write.
     * @exception IOException - Read Write failed.
     */
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

    /**
     * Reads a response for a given connection and returns it as a string.
     *
     * @author Niall Jude Collins.
     * @param connection
     * @return The response expressed as a String.
     * @exception IOException - The Read/Write failed.
     */
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
