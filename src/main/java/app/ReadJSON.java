package app;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadJSON {

    public static void main(String... args) throws Exception {

        InputStream is = new FileInputStream("foo.json");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();

        while (line != null){
            sb.append(line).append("\n");
            line = buf.readLine();
        }

        String json = sb.toString();

        // Now do the magic.
        Data data = new Gson().fromJson(json, Data.class);

        ArrayList<Object> foo = new ArrayList<>();
        foo = data.getItems();

        // Show it.
        System.out.println(foo.toString());
    }
}

class Data {

    private ArrayList<Object>items;
    public ArrayList<Object> getItems() { return items; }
    public void setItems(ArrayList<Object> items) { this.items = items; }

    public String toString() {
        return String.format("items:%s,size:%d", items.toString(),items.size());
    }
}
