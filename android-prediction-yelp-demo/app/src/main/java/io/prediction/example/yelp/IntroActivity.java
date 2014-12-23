package io.prediction.example.yelp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;



/*
* Splash Screen Activity
* Create User, Get nearby Restaurants in this activity.
*/

public class IntroActivity extends Activity {

    private SharedPreferences mPrefs;
    private AppDataCollect app;


    private HashMap<String, Restaurant> restaurant_maps = new HashMap<String, Restaurant>();
    private ArrayList<Restaurant> restaurant_list = new ArrayList<Restaurant>();


    private HashMap<ArrayList<String>, Integer> food_category_map;
    private ArrayList<String> categories;

    private JSONObject postData = new JSONObject();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Full page logo screen - remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_intro);

        //Getting a device ID in order to generate an Unique User ID
        DeviceUuidFactory uuid = new DeviceUuidFactory(this);
        String userId = uuid.getDeviceUuid().toString();

        mPrefs = getSharedPreferences("device_id.xml", 0);
        if (mPrefs.getBoolean("userExist", false)) {
            Log.d("USER", "Exist.");
        } else {
            Log.d("User", "Does not exist.");

            //Post request to create a user.
            JSONObject user = new JSONObject();
            user.put("name", "pio");
            user.put("yelp_user_id", userId);

            postData.put("user", user);
            new PostUser().execute();

        }


        //Getting nearby restaurants from Server
        app = ((AppDataCollect) this.getApplication());
        new GetRestaurants().execute();


        //Generating category images
        food_category_map = new HashMap<ArrayList<String>, Integer>();

        //Setting icons
        categories = new ArrayList<String>();
        categories.add("burger");
        categories.add("burgers");
        categories.add("fastfood");
        categories.add("hamburger");
        categories.add("hotdog");
        food_category_map.put(categories, R.drawable.burger);

        categories = new ArrayList<String>();
        categories.add("coffee");
        categories.add("tea");
        categories.add("cafe");
        food_category_map.put(categories, R.drawable.coffee);

        categories = new ArrayList<String>();
        categories.add("deli");
        categories.add("bread");
        categories.add("bakeries");
        categories.add("bakery");
        categories.add("sandwich");
        food_category_map.put(categories, R.drawable.deli);

        categories = new ArrayList<String>();
        categories.add("chinese");
        categories.add("dimsum");
        categories.add("ramen");
        categories.add("korean");
        categories.add("asian");
        categories.add("thai");
        food_category_map.put(categories, R.drawable.chinese);

        categories = new ArrayList<String>();
        categories.add("japanese");
        categories.add("japan");
        categories.add("sushi");
        food_category_map.put(categories, R.drawable.sushi);

        categories = new ArrayList<String>();
        categories.add("drink");
        categories.add("beer");
        categories.add("alcohol");
        categories.add("bars");
        categories.add("bar");
        categories.add("nightlife");
        categories.add("pubs");
        categories.add("pub");
        food_category_map.put(categories, R.drawable.drink);

        categories = new ArrayList<String>();
        categories.add("taco");
        categories.add("mexican");
        categories.add("burrito");
        food_category_map.put(categories, R.drawable.mexican);

        categories = new ArrayList<String>();
        categories.add("italian");
        categories.add("pizza");
        categories.add("pizzas");
        food_category_map.put(categories, R.drawable.italian);

        categories = new ArrayList<String>();
        categories.add("icecream");
        food_category_map.put(categories, R.drawable.icecream);

        categories = new ArrayList<String>();
        categories.add("wine");
        food_category_map.put(categories, R.drawable.wine);

        categories = new ArrayList<String>();
        categories.add("steak");
        categories.add("steakhouse");
        categories.add("steakhouses");
        food_category_map.put(categories, R.drawable.steak);

        categories = new ArrayList<String>();
        categories.add("american");
        categories.add("breakfast");
        food_category_map.put(categories, R.drawable.american);

        categories = new ArrayList<String>();
        categories.add("chicken");
        categories.add("wings");
        categories.add("wing");
        food_category_map.put(categories, R.drawable.chicken);

        categories = new ArrayList<String>();
        categories.add("seafood");
        categories.add("fish");
        food_category_map.put(categories, R.drawable.seafood);

        categories = new ArrayList<String>();
        categories.add("barbeque");
        categories.add("bbq");
        food_category_map.put(categories, R.drawable.bbq);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro, menu);
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    ///////////////////////////////////////ASYNC TASK////////////////////////////////////////////


    /*
    ** Getting nearby Restaurants from server
    ** For now, the default location is hard coded because we only have the dataset for Arizona state.
    ** If we can get the datasource for the whole country, we can use this application to actually get the nearby restaurants
    ** by specifying coordinates on the url parameter.
    */
    private class GetRestaurants extends AsyncTask<String, String, String> {


        //URL for getting the restaurants
        private String url = "http://zorovn.hongo.wide.ad.jp/api/v1/businesses?categorize=restaurant&radius=5&longitude=-111.92605&latitude=33.494&page_size=10&categorize=Restaurants";

        public GetRestaurants() {
            super();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(url));
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    responseString = out.toString();
                } else {
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
                Log.e("Client Protocol Exception", e + "");
            } catch (IOException e) {
                Log.e("IO Exception", e + "");
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Once get the result from the server, generate an ArrayList of them.

            JSONParser parser = new JSONParser();
            try {
                JSONArray jobj = (JSONArray) parser.parse(result);

                for (int i = 0; i < jobj.size(); i++) {
                    JSONObject jObject = (JSONObject) jobj.get(i);
                    String bid = (String) jObject.get("yelp_business_id");
                    String name = (String) jObject.get("name");
                    String address = (String) jObject
                            .get("full_address");
                    Double longitude = (Double) jObject
                            .get("longitude");
                    Double latitude = (Double) jObject.get("latitude");
                    Double stars = (Double) jObject.get("stars");
                    ArrayList<String> categories = new ArrayList<String>();
                    JSONArray category = (JSONArray) jObject.get("categories");
                    for (int j = 0; j < category.size(); j++) {
                        String entry = (String) category.get(j);
                        if (!entry.equals("Restaurants")) {
                            categories.add(entry);
                        }
                    }

                    Restaurant restaurant = new Restaurant(bid, name,
                            address, categories, longitude, latitude,
                            stars);

                    //Category Image setting
                    //Find category first, and if there's no match, find the name of the restaurant.
                    Boolean isChanged = false;
                    for (String item : restaurant.getCategories()) {
                        item = item.toLowerCase().replace(" ", "");
                        name = name.toLowerCase().replace(" ", "");
                        for (ArrayList<String> key : food_category_map.keySet()) {
                            for (String element : key) {
                                if (item.contains(element) && !isChanged) {
                                    restaurant.setImage(food_category_map.get(key));
                                    isChanged = true;
                                    break;
                                } else if (name.toLowerCase().contains(element) && !isChanged) {
                                    restaurant.setImage(food_category_map.get(key));
                                    isChanged = true;
                                    break;
                                } else if (!isChanged) {
                                    restaurant.setImage(R.drawable.default_food);
                                }
                            }
                        }
                    }
                    restaurant_maps.put(bid, restaurant);
                    restaurant_list.add(restaurant);

                }
                app.setRestaurantMaps(restaurant_maps);
                app.setRestaurants_list(restaurant_list);


                //When getting data is done, move on to Preference Activity
                Intent i = new Intent(IntroActivity.this, PrefActivity.class);
                startActivity(i);
                finish();

                // Screen fade in & out effect
                overridePendingTransition(android.R.anim.fade_in, R.anim.out);


            } catch (ParseException e) {
                Log.e("JSONException", e + "");
            }


        }

    }


    //Create a new user.
    private class PostUser extends AsyncTask<String, String, String> {


        String url = "http://zorovn.hongo.wide.ad.jp/api/v1/users";

        public PostUser() {
            super();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {

                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new StringEntity(postData.toString()));
                httpPost.setHeader("Content-type", "application/json");
                response = httpclient.execute(httpPost);
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    responseString = out.toString();
                } else {
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (UnsupportedEncodingException e) {
                Log.e("Unsupported Encoding", e + "");
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
                Log.e("Client Protocol Exception", e + "");
            } catch (IOException e) {
                Log.e("IO Exception", e + "");
            }
            return responseString;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //After the new user is created, update the sharedPreference.
            mPrefs.edit().putBoolean("userExist", true).commit();


        }

    }


}