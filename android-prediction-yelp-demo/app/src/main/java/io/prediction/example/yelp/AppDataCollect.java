package io.prediction.example.yelp;

import android.app.Application;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Read user, restaurant set from local json file
 */
public class AppDataCollect extends Application {
    private HashMap<String, User> user_maps = new HashMap<String, User>();
    private HashMap<String, Restaurant> restaurant_maps = new HashMap<String, Restaurant>();

    public HashMap<String, User> getUserMaps() {
        return user_maps;
    }

    public void setUserMaps(HashMap<String, User> user_maps) {
        this.user_maps = user_maps;
    }

    public HashMap<String, Restaurant> getRestaurantMaps() {
        return restaurant_maps;
    }

    public void setRestaurantMaps(HashMap<String, Restaurant> restaurant_maps) {
        this.restaurant_maps = restaurant_maps;
    }

    public HashMap<String, User> parseUserMaps() {
        HashMap<String, User> users = new HashMap<String, User>();
        JSONParser parser = new JSONParser();
        String current;
        BufferedReader br = null;

        try {
            // Read from local json file (/assets)
            InputStream reader = getAssets().open("yelp_training_set_user.json");
            br = new BufferedReader(new InputStreamReader(reader));
            Set<String> uids = new TreeSet<String>();
            while ((current = br.readLine()) != null) {
                Object obj;
                try {
                    obj = parser.parse(current);
                    JSONObject jObject = (JSONObject) obj;
                    String uid = (String) jObject.get("user_id");
                    if (uids.add(uid)) {
                        String name = (String) jObject.get("name");
                        Double stars = (Double) jObject.get("average_stars");
                        Long review_count = (Long) jObject.get("review_count");
                        User user = new User(uid, name, stars, review_count);
                        users.put(uid, user);
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public HashMap<String, Restaurant> parseRestaurantMaps(){
        HashMap<String, Restaurant> restaurants = new HashMap<String, Restaurant>();
        JSONParser parser = new JSONParser();
        String current;
        BufferedReader br = null;

        try {
            // Read from local json file (/assets)
            InputStream reader = getAssets().open("yelp_training_set_business.json");
            br = new BufferedReader(new InputStreamReader(reader));

            Set<String> bids = new TreeSet<String>();
            // Read the JSON file line by line
            while ((current = br.readLine()) != null) {
                Object obj;
                try {
                    obj = parser.parse(current);
                    JSONObject jObject = (JSONObject) obj;
                    // Only reading restaurants info
                    ArrayList<String> categories = new ArrayList<String>();
                    JSONArray categories_array = (JSONArray) jObject
                            .get("categories");
                    if (categories_array.contains("Restaurants")) {
                        // Categories will only contain the category of the
                        // restaurant
                        categories_array.remove("Restaurants");
                        if (categories_array.size() > 0) {
                            String bid = (String) jObject.get("business_id");
                            if (bids.add(bid)) {
                                String name = (String) jObject.get("name");
                                String address = (String) jObject
                                        .get("full_address");
                                Double longitude = (Double) jObject
                                        .get("longitude");
                                Double latitude = (Double) jObject.get("latitude");
                                Double stars = (Double) jObject.get("stars");
                                for (int i = 0; i < categories_array.size(); i++) {
                                    categories
                                            .add((String) categories_array.get(i));
                                }
                                Restaurant restaurant = new Restaurant(bid, name,
                                        address, categories, longitude, latitude,
                                        stars);
                                // Generating restaurants list
                                restaurants.put(bid, restaurant);

                            }
                        }
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return restaurants;
    }

    public void SetCollectedData() {
        this.user_maps = parseUserMaps();
        this.restaurant_maps = parseRestaurantMaps();
    }
}
