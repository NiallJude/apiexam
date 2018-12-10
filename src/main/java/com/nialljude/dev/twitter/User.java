package com.nialljude.dev.twitter;

/**
 * User objects for Twitter API.
 * There are no Getters or Setters
 * because GSON uses the Constructor
 * and the objects are printed directly.
 *
 * @author Niall Jude Collins
 */
public class User {

    // Vars
    private String screen_name;
    private String description;
    private String url;

    // Default Constructor
    public User(String screen_name, String description, String url) {
        this.screen_name = screen_name;
        this.description = description;
        this.url = url;
    }
}
