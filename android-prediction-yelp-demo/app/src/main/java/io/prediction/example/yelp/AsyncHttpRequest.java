package io.prediction.example.yelp;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import io.prediction.EngineClient;

public class AsyncHttpRequest extends AsyncTask<String, Void, String> {
    public Activity owner;
    private ArrayList<Restaurant> restaurant_array;

    public AsyncHttpRequest(Activity activity){
        owner = activity;
    }

    @Override
    protected String doInBackground(String... urls) {
        restaurant_array = new ArrayList<Restaurant>();
        restaurant_array.add(new Restaurant("",urls[1],"",new ArrayList<String>(), 0, 0, 0));
        HashMap<String, Restaurant> restaurants = ((AppDataCollect) owner.getApplication()).getRestaurantMaps();


        EngineClient client = new EngineClient(urls[0], 1, 0, 60);

        Map<String, Object> query = new HashMap<String, Object>();
        query.put("uid", urls[1]);
        query.put("n", 10);

        JsonObject result = null;
        try {
            result = client.sendQuery(query);
            JsonArray array = (JsonArray) result.get("items");

            if (array.size() > 0) {
                int upper = (array.size() > 10)?10:(array.size());
                for (int i = 0; i < upper; i++) {
                    String element = array.get(i).toString();
                    String rkey = element.substring(2, element.indexOf(":") - 1);
                    String score = element.substring(element.indexOf(":") + 1, element.length() - 2);
                    Restaurant rs = restaurants.get(rkey);
                    restaurant_array.add(rs);
                }
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        // Create restaurant adapter for owner
        CustomRestaurantAdapter adapter = new CustomRestaurantAdapter(owner, restaurant_array);
        // Attach the adapter to a ListView
        ListView list_view = (ListView) owner.findViewById(R.id.lvRestaurants);
        list_view.setAdapter(adapter);
    }
}
