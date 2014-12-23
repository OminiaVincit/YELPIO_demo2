package io.prediction.example.yelp;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Save Restaurant maps
 */
public class AppDataCollect extends Application {
    private HashMap<String, Restaurant> restaurant_maps = new HashMap<String, Restaurant>();

    private ArrayList<Restaurant> restaurants_list = new ArrayList<Restaurant>();

    public HashMap<String, Restaurant> getRestaurantMaps() {
        return restaurant_maps;
    }

    public ArrayList<Restaurant> getRestaurants_list() { return restaurants_list; }

    public void setRestaurants_list(ArrayList<Restaurant> list) {
        this.restaurants_list = list;
    }

    public void setRestaurantMaps(HashMap<String, Restaurant> restaurant_maps) {
        this.restaurant_maps = restaurant_maps;
    }
}
