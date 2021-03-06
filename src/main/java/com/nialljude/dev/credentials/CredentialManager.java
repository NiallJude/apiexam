package com.nialljude.dev.credentials;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the secure fetching, processing and returning
 * of credentials for contacting the Twitter API.
 *
 * @author - Niall Jude Collins
 */
public class CredentialManager {

    /**
     * Public method to be called when encoded credentials
     * are required after calling Github and before calling
     * Twitter.
     *
     * @author Niall Jude Collins
     * @return credentialsEncoded - The crednetials.
     */
    public String getEncodedCredentials () {

        // Get String ArrayList of Credentials
        ArrayList<String> credentials = getCredentialsArray();

        // Encode Credentials
        String credentialsEncoded = "";
        credentialsEncoded = encodeKeys(credentials.get(0),credentials.get(1));

        // Try again in case of the rare UnsupportedEncodingException
        if (credentialsEncoded.isEmpty()){
            credentialsEncoded = encodeKeys(credentials.get(0),credentials.get(1));
        }

        return credentialsEncoded;
    }

    /** Encodes the consumer key and secret to create the basic authorization key
     *
     * @author Niall Jude Collins
     * @param consumerKey
     * @param consumerSecret
     * @return The encoded bytes in String format.
     * @exception UnsupportedEncodingException - RARE. Will return empty.
     */
    private String encodeKeys(String consumerKey, String consumerSecret) {
        try {
            String encodedConsumerKey = URLEncoder.encode(consumerKey, "UTF-8");
            String encodedConsumerSecret = URLEncoder.encode(consumerSecret, "UTF-8");

            String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;
            byte[] encodedBytes = Base64.encodeBase64(fullKey.getBytes());
            return new String(encodedBytes);
        }
        catch (UnsupportedEncodingException ex) {
            return new String();
        }
    }

    /**
     * Secrets are located at secrets/credentials.csv.
     * They are part of .gitignore and will not be committed to SCM.
     *
     * @author Niall Jude Collins
     * @return credentials - The credentials for Twitter securely handled from file.
     * @exception FileNotFoundException - If you get this, please ensure credentials are located correctly.
     */
    private ArrayList getCredentialsArray() {
        ArrayList<String> credentials = new ArrayList<>();

        try {
        Scanner scanner = new Scanner(new File("secrets/credentials.csv"));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            credentials.add(scanner.next());
        }
        scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Potential error in filename / pathing. " +
                    "Ensure credentials file is in the format listed in ReadMe.md.");
            ex.printStackTrace();
        }

        return credentials;
    }


}
