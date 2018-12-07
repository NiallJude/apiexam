package twitter;

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

    // Getters and Setters
    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
