package io.prediction.example.yelp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
** User can create LIKE or DISLIKE event in this activity
** Each time the button is clicked, it does a POST request to the server to create a event.
*/


public class PrefActivity extends Activity implements View.OnTouchListener {


    RelativeLayout pref1;
    GoogleMap pref_map1;
    TextView pref_name1;
    TextView pref_address1;
    TextView pref_categories1;
    ImageView pref_image1;
    RatingBar pref_rating1;
    ImageView dislikeButton1;
    ImageView likeButton1;
    TextView pref_like1;
    TextView pref_dislike1;

    RelativeLayout pref2;
    GoogleMap pref_map2;
    TextView pref_name2;
    TextView pref_address2;
    TextView pref_categories2;
    ImageView pref_image2;
    RatingBar pref_rating2;
    ImageView dislikeButton2;
    ImageView likeButton2;
    TextView pref_like2;
    TextView pref_dislike2;

    RelativeLayout pref3;
    GoogleMap pref_map3;
    TextView pref_name3;
    TextView pref_address3;
    TextView pref_categories3;
    ImageView pref_image3;
    RatingBar pref_rating3;
    ImageView dislikeButton3;
    ImageView likeButton3;
    TextView pref_like3;
    TextView pref_dislike3;

    RelativeLayout pref4;
    GoogleMap pref_map4;
    TextView pref_name4;
    TextView pref_address4;
    TextView pref_categories4;
    ImageView pref_image4;
    RatingBar pref_rating4;
    ImageView dislikeButton4;
    ImageView likeButton4;
    TextView pref_like4;
    TextView pref_dislike4;

    RelativeLayout pref5;
    GoogleMap pref_map5;
    TextView pref_name5;
    TextView pref_address5;
    TextView pref_categories5;
    ImageView pref_image5;
    RatingBar pref_rating5;
    ImageView dislikeButton5;
    ImageView likeButton5;
    TextView pref_like5;
    TextView pref_dislike5;

    RelativeLayout pref6;
    GoogleMap pref_map6;
    TextView pref_name6;
    TextView pref_address6;
    TextView pref_categories6;
    ImageView pref_image6;
    RatingBar pref_rating6;
    ImageView dislikeButton6;
    ImageView likeButton6;
    TextView pref_like6;
    TextView pref_dislike6;

    RelativeLayout pref7;
    GoogleMap pref_map7;
    TextView pref_name7;
    TextView pref_address7;
    TextView pref_categories7;
    ImageView pref_image7;
    RatingBar pref_rating7;
    ImageView dislikeButton7;
    ImageView likeButton7;
    TextView pref_like7;
    TextView pref_dislike7;

    RelativeLayout pref8;
    GoogleMap pref_map8;
    TextView pref_name8;
    TextView pref_address8;
    TextView pref_categories8;
    ImageView pref_image8;
    RatingBar pref_rating8;
    ImageView dislikeButton8;
    ImageView likeButton8;
    TextView pref_like8;
    TextView pref_dislike8;

    RelativeLayout pref9;
    GoogleMap pref_map9;
    TextView pref_name9;
    TextView pref_address9;
    TextView pref_categories9;
    ImageView pref_image9;
    RatingBar pref_rating9;
    ImageView dislikeButton9;
    ImageView likeButton9;
    TextView pref_like9;
    TextView pref_dislike9;

    RelativeLayout pref10;
    GoogleMap pref_map10;
    TextView pref_name10;
    TextView pref_address10;
    TextView pref_categories10;
    ImageView pref_image10;
    RatingBar pref_rating10;
    ImageView dislikeButton10;
    ImageView likeButton10;
    TextView pref_like10;
    TextView pref_dislike10;


    AppDataCollect app;

    ArrayList<Restaurant> prefRest;
    String userId;

    HashMap<ArrayList<String>, Integer> food_category_map;
    ArrayList<String> categories;
    HashMap<String, Restaurant> restaurant_maps = new HashMap<String, Restaurant>();
    ArrayList<Restaurant> restaurants_list = new ArrayList<Restaurant>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Example);
        setContentView(R.layout.activity_pref);
        overridePendingTransition(R.anim.in, R.anim.out);


        //Get the userID
        DeviceUuidFactory uuid = new DeviceUuidFactory(this);
        userId = uuid.getDeviceUuid().toString();

        //Get restaurant list from the previous activity
        app = ((AppDataCollect) this.getApplication());
        prefRest = app.getRestaurants_list();

        //All 10 of restaurant panels are manually initialized

        ///////////// 1. ////////////////
        Restaurant rest = prefRest.get(0);
        pref1 = (RelativeLayout) findViewById(R.id.pref1);
        pref_map1 = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.pref_map1)).getMap();


        pref_name1 = (TextView) findViewById(R.id.pref_name1);
        pref_address1 = (TextView) findViewById(R.id.pref_address1);
        pref_categories1 = (TextView) findViewById(R.id.pref_categories1);
        pref_image1 = (ImageView) findViewById(R.id.pref_image1);
        pref_rating1 = (RatingBar) findViewById(R.id.pref_rating1);

        dislikeButton1 = (ImageView) findViewById(R.id.dislikeButton1);
        likeButton1 = (ImageView) findViewById(R.id.likeButton1);

        pref_like1 = (TextView) findViewById(R.id.pref_like1);
        pref_dislike1 = (TextView) findViewById(R.id.pref_dislike1);

        pref_name1.setText(rest.getName());
        pref_address1.setText(rest.getAddress());
        pref_categories1.setText(rest.getCategories().toString().substring(1, rest.getCategories().toString().length() - 1));
        pref_rating1.setRating(new Float(rest.getStars()));
        pref_image1.setImageDrawable(getResources().getDrawable(rest.getImage()));


        //Map setting
        pref_map1.addMarker(new MarkerOptions()
                .position(new LatLng(rest.getLatitude(), rest.getLongitude()))
                .title(rest.getName())
                .snippet(rest.getCategories().toString()));

        pref_map1.setMyLocationEnabled(true);

        LatLng latLng = new LatLng(rest.getLatitude(), rest.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        pref_map1.animateCamera(cameraUpdate);

        pref_map1.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps
                String url = "http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        dislikeButton1.setOnTouchListener(this);
        likeButton1.setOnTouchListener(this);


        ///////////// 2. ////////////////
        rest = prefRest.get(1);
        pref2 = (RelativeLayout) findViewById(R.id.pref2);
        pref_map2 = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.pref_map2)).getMap();


        pref_name2 = (TextView) findViewById(R.id.pref_name2);
        pref_address2 = (TextView) findViewById(R.id.pref_address2);
        pref_categories2 = (TextView) findViewById(R.id.pref_categories2);
        pref_image2 = (ImageView) findViewById(R.id.pref_image2);
        pref_rating2 = (RatingBar) findViewById(R.id.pref_rating2);

        dislikeButton2 = (ImageView) findViewById(R.id.dislikeButton2);
        likeButton2 = (ImageView) findViewById(R.id.likeButton2);

        pref_like2 = (TextView) findViewById(R.id.pref_like2);
        pref_dislike2 = (TextView) findViewById(R.id.pref_dislike2);

        pref_name2.setText(rest.getName());
        pref_address2.setText(rest.getAddress());
        pref_categories2.setText(rest.getCategories().toString().substring(1, rest.getCategories().toString().length() - 1));
        pref_rating2.setRating(new Float(rest.getStars()));
        pref_image2.setImageDrawable(getResources().getDrawable(rest.getImage()));


        //Map setting
        pref_map2.addMarker(new MarkerOptions()
                .position(new LatLng(rest.getLatitude(), rest.getLongitude()))
                .title(rest.getName())
                .snippet(rest.getCategories().toString()));

        pref_map2.setMyLocationEnabled(true);

        latLng = new LatLng(rest.getLatitude(), rest.getLongitude());
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        pref_map2.animateCamera(cameraUpdate);

        pref_map2.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps

                String url = "http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        dislikeButton2.setOnTouchListener(this);
        likeButton2.setOnTouchListener(this);


        ///////////// 3. ////////////////
        rest = prefRest.get(2);
        pref3 = (RelativeLayout) findViewById(R.id.pref3);
        pref_map3 = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.pref_map3)).getMap();


        pref_name3 = (TextView) findViewById(R.id.pref_name3);
        pref_address3 = (TextView) findViewById(R.id.pref_address3);
        pref_categories3 = (TextView) findViewById(R.id.pref_categories3);
        pref_image3 = (ImageView) findViewById(R.id.pref_image3);
        pref_rating3 = (RatingBar) findViewById(R.id.pref_rating3);

        dislikeButton3 = (ImageView) findViewById(R.id.dislikeButton3);
        likeButton3 = (ImageView) findViewById(R.id.likeButton3);

        pref_like3 = (TextView) findViewById(R.id.pref_like3);
        pref_dislike3 = (TextView) findViewById(R.id.pref_dislike3);

        pref_name3.setText(rest.getName());
        pref_address3.setText(rest.getAddress());
        pref_categories3.setText(rest.getCategories().toString().substring(1, rest.getCategories().toString().length() - 1));
        pref_rating3.setRating(new Float(rest.getStars()));
        pref_image3.setImageDrawable(getResources().getDrawable(rest.getImage()));


        //Map setting
        pref_map3.addMarker(new MarkerOptions()
                .position(new LatLng(rest.getLatitude(), rest.getLongitude()))
                .title(rest.getName())
                .snippet(rest.getCategories().toString()));

        pref_map3.setMyLocationEnabled(true);

        latLng = new LatLng(rest.getLatitude(), rest.getLongitude());
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        pref_map3.animateCamera(cameraUpdate);

        pref_map3.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps

                String url = "http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        dislikeButton3.setOnTouchListener(this);
        likeButton3.setOnTouchListener(this);


        ///////////// 4. ////////////////
        rest = prefRest.get(3);
        pref4 = (RelativeLayout) findViewById(R.id.pref4);
        pref_map4 = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.pref_map4)).getMap();


        pref_name4 = (TextView) findViewById(R.id.pref_name4);
        pref_address4 = (TextView) findViewById(R.id.pref_address4);
        pref_categories4 = (TextView) findViewById(R.id.pref_categories4);
        pref_image4 = (ImageView) findViewById(R.id.pref_image4);
        pref_rating4 = (RatingBar) findViewById(R.id.pref_rating4);

        dislikeButton4 = (ImageView) findViewById(R.id.dislikeButton4);
        likeButton4 = (ImageView) findViewById(R.id.likeButton4);

        pref_like4 = (TextView) findViewById(R.id.pref_like4);
        pref_dislike4 = (TextView) findViewById(R.id.pref_dislike4);

        pref_name4.setText(rest.getName());
        pref_address4.setText(rest.getAddress());
        pref_categories4.setText(rest.getCategories().toString().substring(1, rest.getCategories().toString().length() - 1));
        pref_rating4.setRating(new Float(rest.getStars()));
        pref_image4.setImageDrawable(getResources().getDrawable(rest.getImage()));


        //Map setting
        pref_map4.addMarker(new MarkerOptions()
                .position(new LatLng(rest.getLatitude(), rest.getLongitude()))
                .title(rest.getName())
                .snippet(rest.getCategories().toString()));

        pref_map4.setMyLocationEnabled(true);

        latLng = new LatLng(rest.getLatitude(), rest.getLongitude());
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        pref_map4.animateCamera(cameraUpdate);

        pref_map4.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps

                String url = "http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        dislikeButton4.setOnTouchListener(this);
        likeButton4.setOnTouchListener(this);


        ///////////// 5. ////////////////
        rest = prefRest.get(4);
        pref5 = (RelativeLayout) findViewById(R.id.pref5);
        pref_map5 = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.pref_map5)).getMap();


        pref_name5 = (TextView) findViewById(R.id.pref_name5);
        pref_address5 = (TextView) findViewById(R.id.pref_address5);
        pref_categories5 = (TextView) findViewById(R.id.pref_categories5);
        pref_image5 = (ImageView) findViewById(R.id.pref_image5);
        pref_rating5 = (RatingBar) findViewById(R.id.pref_rating5);

        dislikeButton5 = (ImageView) findViewById(R.id.dislikeButton5);
        likeButton5 = (ImageView) findViewById(R.id.likeButton5);

        pref_like5 = (TextView) findViewById(R.id.pref_like5);
        pref_dislike5 = (TextView) findViewById(R.id.pref_dislike5);

        pref_name5.setText(rest.getName());
        pref_address5.setText(rest.getAddress());
        pref_categories5.setText(rest.getCategories().toString().substring(1, rest.getCategories().toString().length() - 1));
        pref_rating5.setRating(new Float(rest.getStars()));
        pref_image5.setImageDrawable(getResources().getDrawable(rest.getImage()));


        //Map setting
        pref_map5.addMarker(new MarkerOptions()
                .position(new LatLng(rest.getLatitude(), rest.getLongitude()))
                .title(rest.getName())
                .snippet(rest.getCategories().toString()));

        pref_map5.setMyLocationEnabled(true);

        latLng = new LatLng(rest.getLatitude(), rest.getLongitude());
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        pref_map5.animateCamera(cameraUpdate);

        pref_map5.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps

                String url = "http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        dislikeButton5.setOnTouchListener(this);
        likeButton5.setOnTouchListener(this);


        ///////////// 6. ////////////////
        rest = prefRest.get(5);
        pref6 = (RelativeLayout) findViewById(R.id.pref6);
        pref_map6 = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.pref_map6)).getMap();


        pref_name6 = (TextView) findViewById(R.id.pref_name6);
        pref_address6 = (TextView) findViewById(R.id.pref_address6);
        pref_categories6 = (TextView) findViewById(R.id.pref_categories6);
        pref_image6 = (ImageView) findViewById(R.id.pref_image6);
        pref_rating6 = (RatingBar) findViewById(R.id.pref_rating6);

        dislikeButton6 = (ImageView) findViewById(R.id.dislikeButton6);
        likeButton6 = (ImageView) findViewById(R.id.likeButton6);

        pref_like6 = (TextView) findViewById(R.id.pref_like6);
        pref_dislike6 = (TextView) findViewById(R.id.pref_dislike6);

        pref_name6.setText(rest.getName());
        pref_address6.setText(rest.getAddress());
        pref_categories6.setText(rest.getCategories().toString().substring(1, rest.getCategories().toString().length() - 1));
        pref_rating6.setRating(new Float(rest.getStars()));
        pref_image6.setImageDrawable(getResources().getDrawable(rest.getImage()));

        //Map setting
        pref_map6.addMarker(new MarkerOptions()
                .position(new LatLng(rest.getLatitude(), rest.getLongitude()))
                .title(rest.getName())
                .snippet(rest.getCategories().toString()));

        pref_map6.setMyLocationEnabled(true);

        latLng = new LatLng(rest.getLatitude(), rest.getLongitude());
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        pref_map6.animateCamera(cameraUpdate);

        pref_map6.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps

                String url = "http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        dislikeButton6.setOnTouchListener(this);
        likeButton6.setOnTouchListener(this);


        ///////////// 7. ////////////////
        rest = prefRest.get(6);
        pref7 = (RelativeLayout) findViewById(R.id.pref7);
        pref_map7 = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.pref_map7)).getMap();


        pref_name7 = (TextView) findViewById(R.id.pref_name7);
        pref_address7 = (TextView) findViewById(R.id.pref_address7);
        pref_categories7 = (TextView) findViewById(R.id.pref_categories7);
        pref_image7 = (ImageView) findViewById(R.id.pref_image7);
        pref_rating7 = (RatingBar) findViewById(R.id.pref_rating7);

        dislikeButton7 = (ImageView) findViewById(R.id.dislikeButton7);
        likeButton7 = (ImageView) findViewById(R.id.likeButton7);

        pref_like7 = (TextView) findViewById(R.id.pref_like7);
        pref_dislike7 = (TextView) findViewById(R.id.pref_dislike7);

        pref_name7.setText(rest.getName());
        pref_address7.setText(rest.getAddress());
        pref_categories7.setText(rest.getCategories().toString().substring(1, rest.getCategories().toString().length() - 1));
        pref_rating7.setRating(new Float(rest.getStars()));
        pref_image7.setImageDrawable(getResources().getDrawable(rest.getImage()));


        //Map setting
        pref_map7.addMarker(new MarkerOptions()
                .position(new LatLng(rest.getLatitude(), rest.getLongitude()))
                .title(rest.getName())
                .snippet(rest.getCategories().toString()));

        pref_map7.setMyLocationEnabled(true);

        latLng = new LatLng(rest.getLatitude(), rest.getLongitude());
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        pref_map7.animateCamera(cameraUpdate);

        pref_map7.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps

                String url = "http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        dislikeButton7.setOnTouchListener(this);
        likeButton7.setOnTouchListener(this);


        ///////////// 8. ////////////////
        rest = prefRest.get(7);
        pref8 = (RelativeLayout) findViewById(R.id.pref8);
        pref_map8 = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.pref_map8)).getMap();


        pref_name8 = (TextView) findViewById(R.id.pref_name8);
        pref_address8 = (TextView) findViewById(R.id.pref_address8);
        pref_categories8 = (TextView) findViewById(R.id.pref_categories8);
        pref_image8 = (ImageView) findViewById(R.id.pref_image8);
        pref_rating8 = (RatingBar) findViewById(R.id.pref_rating8);

        dislikeButton8 = (ImageView) findViewById(R.id.dislikeButton8);
        likeButton8 = (ImageView) findViewById(R.id.likeButton8);

        pref_like8 = (TextView) findViewById(R.id.pref_like8);
        pref_dislike8 = (TextView) findViewById(R.id.pref_dislike8);


        pref_name8.setText(rest.getName());
        pref_address8.setText(rest.getAddress());
        pref_categories8.setText(rest.getCategories().toString().substring(1, rest.getCategories().toString().length() - 1));
        pref_rating8.setRating(new Float(rest.getStars()));
        pref_image8.setImageDrawable(getResources().getDrawable(rest.getImage()));


        //Map setting
        pref_map8.addMarker(new MarkerOptions()
                .position(new LatLng(rest.getLatitude(), rest.getLongitude()))
                .title(rest.getName())
                .snippet(rest.getCategories().toString()));

        pref_map8.setMyLocationEnabled(true);

        latLng = new LatLng(rest.getLatitude(), rest.getLongitude());
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        pref_map8.animateCamera(cameraUpdate);

        pref_map8.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps

                String url = "http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        dislikeButton8.setOnTouchListener(this);
        likeButton8.setOnTouchListener(this);


        ///////////// 9. ////////////////
        rest = prefRest.get(8);
        pref9 = (RelativeLayout) findViewById(R.id.pref9);
        pref_map9 = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.pref_map9)).getMap();


        pref_name9 = (TextView) findViewById(R.id.pref_name9);
        pref_address9 = (TextView) findViewById(R.id.pref_address9);
        pref_categories9 = (TextView) findViewById(R.id.pref_categories9);
        pref_image9 = (ImageView) findViewById(R.id.pref_image9);
        pref_rating9 = (RatingBar) findViewById(R.id.pref_rating9);

        dislikeButton9 = (ImageView) findViewById(R.id.dislikeButton9);
        likeButton9 = (ImageView) findViewById(R.id.likeButton9);

        pref_like9 = (TextView) findViewById(R.id.pref_like9);
        pref_dislike9 = (TextView) findViewById(R.id.pref_dislike9);


        pref_name9.setText(rest.getName());
        pref_address9.setText(rest.getAddress());
        pref_categories9.setText(rest.getCategories().toString().substring(1, rest.getCategories().toString().length() - 1));
        pref_rating9.setRating(new Float(rest.getStars()));
        pref_image9.setImageDrawable(getResources().getDrawable(rest.getImage()));


        //Map setting
        pref_map9.addMarker(new MarkerOptions()
                .position(new LatLng(rest.getLatitude(), rest.getLongitude()))
                .title(rest.getName())
                .snippet(rest.getCategories().toString()));

        pref_map9.setMyLocationEnabled(true);

        latLng = new LatLng(rest.getLatitude(), rest.getLongitude());
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        pref_map9.animateCamera(cameraUpdate);

        pref_map9.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps

                String url = "http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        dislikeButton9.setOnTouchListener(this);
        likeButton9.setOnTouchListener(this);


        ///////////// 10. ////////////////
        rest = prefRest.get(9);
        pref10 = (RelativeLayout) findViewById(R.id.pref10);
        pref_map10 = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.pref_map10)).getMap();


        pref_name10 = (TextView) findViewById(R.id.pref_name10);
        pref_address10 = (TextView) findViewById(R.id.pref_address10);
        pref_categories10 = (TextView) findViewById(R.id.pref_categories10);
        pref_image10 = (ImageView) findViewById(R.id.pref_image10);
        pref_rating10 = (RatingBar) findViewById(R.id.pref_rating10);

        dislikeButton10 = (ImageView) findViewById(R.id.dislikeButton10);
        likeButton10 = (ImageView) findViewById(R.id.likeButton10);

        pref_like10 = (TextView) findViewById(R.id.pref_like10);
        pref_dislike10 = (TextView) findViewById(R.id.pref_dislike10);


        pref_name10.setText(rest.getName());
        pref_address10.setText(rest.getAddress());
        pref_categories10.setText(rest.getCategories().toString().substring(1, rest.getCategories().toString().length() - 1));
        pref_rating10.setRating(new Float(rest.getStars()));
        pref_image10.setImageDrawable(getResources().getDrawable(rest.getImage()));


        //Map setting
        pref_map10.addMarker(new MarkerOptions()
                .position(new LatLng(rest.getLatitude(), rest.getLongitude()))
                .title(rest.getName())
                .snippet(rest.getCategories().toString()));

        pref_map10.setMyLocationEnabled(true);

        latLng = new LatLng(rest.getLatitude(), rest.getLongitude());
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        pref_map10.animateCamera(cameraUpdate);

        pref_map10.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps

                String url = "http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        dislikeButton10.setOnTouchListener(this);
        likeButton10.setOnTouchListener(this);



        //Creating category image map again for recommended restaurant list
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
    public boolean onTouch(View v, MotionEvent motionEvent) {

        Animation right_out = AnimationUtils.loadAnimation(this, R.anim.right_out);
        Animation left_out = AnimationUtils.loadAnimation(this, R.anim.out);
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //Button pressed effect
                v.setBackground(v.getResources().getDrawable(R.drawable.circle_pressed));
                break;
            }
            case MotionEvent.ACTION_UP: {
                v.setBackground(v.getResources().getDrawable(R.drawable.circle));

                //Handling button event individually
                if (v.getId() == R.id.dislikeButton1) {
                    //Dislike event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(0).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 0);
                    review.put("content", "I hate this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());

                    left_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_dislike1.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            pref1.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref1.startAnimation(left_out);
                } else if (v.getId() == R.id.likeButton1) {
                    //Like event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(0).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 5);
                    review.put("content", "I Love this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());

                    right_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_like1.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Like event


                            pref1.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref1.startAnimation(right_out);
                } else if (v.getId() == R.id.dislikeButton2) {
                    //Dislike event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(1).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 0);
                    review.put("content", "I hate this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());

                    left_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_dislike2.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Dislike event


                            pref2.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref2.startAnimation(left_out);
                } else if (v.getId() == R.id.likeButton2) {
                    //Like event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(1).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 5);
                    review.put("content", "I Love this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());

                    right_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_like2.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Like event


                            pref2.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref2.startAnimation(right_out);
                } else if (v.getId() == R.id.dislikeButton3) {
                    //Dislike event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(2).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 0);
                    review.put("content", "I hate this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());
                    left_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_dislike3.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Dislike event


                            pref3.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref3.startAnimation(left_out);
                } else if (v.getId() == R.id.likeButton3) {
                    //Like event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(2).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 5);
                    review.put("content", "I Love this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());

                    right_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_like3.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Like event


                            pref3.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref3.startAnimation(right_out);
                } else if (v.getId() == R.id.dislikeButton4) {
                    //Dislike event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(3).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 0);
                    review.put("content", "I hate this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());
                    left_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_dislike4.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Dislike event


                            pref4.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref4.startAnimation(left_out);
                } else if (v.getId() == R.id.likeButton4) {
                    //Like event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(3).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 5);
                    review.put("content", "I Love this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());

                    right_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_like4.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Like event


                            pref4.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref4.startAnimation(right_out);
                } else if (v.getId() == R.id.dislikeButton5) {
                    //Dislike event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(4).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 0);
                    review.put("content", "I hate this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());
                    left_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_dislike5.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Dislike event


                            pref5.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref5.startAnimation(left_out);
                } else if (v.getId() == R.id.likeButton5) {
                    //Like event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(4).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 5);
                    review.put("content", "I Love this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());

                    right_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_like5.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Like event


                            pref5.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref5.startAnimation(right_out);
                } else if (v.getId() == R.id.dislikeButton6) {
                    //Dislike event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(5).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 0);
                    review.put("content", "I hate this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());
                    left_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_dislike6.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Dislike event


                            pref6.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref6.startAnimation(left_out);
                } else if (v.getId() == R.id.likeButton6) {
                    //Like event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(5).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 5);
                    review.put("content", "I Love this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());

                    right_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_like6.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Like event


                            pref6.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref6.startAnimation(right_out);
                } else if (v.getId() == R.id.dislikeButton7) {
                    //Dislike event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(6).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 0);
                    review.put("content", "I hate this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());
                    left_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_dislike7.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Dislike event


                            pref7.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref7.startAnimation(left_out);
                } else if (v.getId() == R.id.likeButton7) {
                    //Like event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(6).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 5);
                    review.put("content", "I Love this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());

                    right_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_like7.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Like event


                            pref7.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref7.startAnimation(right_out);
                } else if (v.getId() == R.id.dislikeButton8) {
                    //Dislike event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(7).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 0);
                    review.put("content", "I hate this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());
                    left_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_dislike8.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Dislike event


                            pref8.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref8.startAnimation(left_out);
                } else if (v.getId() == R.id.likeButton8) {
                    //Like event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(7).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 5);
                    review.put("content", "I Love this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());

                    right_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_like8.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Like event


                            pref8.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref8.startAnimation(right_out);
                } else if (v.getId() == R.id.dislikeButton9) {
                    //Dislike event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(8).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 0);
                    review.put("content", "I hate this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());
                    left_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_dislike9.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Dislike event


                            pref9.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref9.startAnimation(left_out);
                } else if (v.getId() == R.id.likeButton9) {
                    //Like event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(8).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 5);
                    review.put("content", "I Love this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    new PostRating().execute(postData.toString());

                    right_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_like9.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Like event


                            pref9.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref9.startAnimation(right_out);
                } else if (v.getId() == R.id.dislikeButton10) {
                    //Dislike event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(9).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 0);
                    review.put("content", "I hate this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    //This time, set extra parameter to specifiy that this is the last event, so it needs to get the recommendation after the post request
                    new PostRating().execute(postData.toString(), "true");
                    left_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_dislike10.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //Dislike event
                            pref10.setVisibility(View.INVISIBLE);

                        }
                    });
                    pref10.startAnimation(left_out);

                } else if (v.getId() == R.id.likeButton10) {
                    //Like event
                    JSONObject review = new JSONObject();
                    String bid = prefRest.get(9).getBid();
                    review.put("yelp_business_id", bid);
                    review.put("yelp_user_id", userId);
                    review.put("stars", 5);
                    review.put("content", "I Love this place");

                    JSONObject postData = new JSONObject();
                    postData.put("review", review);

                    //This time, set extra parameter to specifiy that this is the last event, so it needs to get the recommendation after the post request
                    new PostRating().execute(postData.toString(), "true");

                    right_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            pref_like10.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                            //Like event
                            pref10.setVisibility(View.INVISIBLE);
                        }
                    });
                    pref10.startAnimation(right_out);
                }
                break;
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pref, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        //Show alert dialog when user tries to exit the app
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                PrefActivity.this);

        // set title
        alertDialogBuilder.setTitle("Exit");

        // set dialog message
        alertDialogBuilder
                .setMessage("Are you sure you want to exit?\n")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    ////////////////////////////////////////////////// ASYNC TASK /////////////////////////////////////////////////////

    /*
    ** Do Post request to the server to create a rating event
    */
    private class PostRating extends AsyncTask<String, String, String> {


        String url = "http://zorovn.hongo.wide.ad.jp/api/v1/reviews?categorize=restaurant";

        public PostRating() {
            super();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {

                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new StringEntity(params[0]));
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
            //When there's an extra parameter, time to get recommendation from the server
            if(params.length > 1)
                return params[1];
            else {
                return "false";
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result.equals("true")) {
                //end of the event
                //Get the restaurants

                new GetRestaurants().execute();
            }


        }

    }

    /*
    ** Getting recommended restaurant list for the current user based on the user's ratings
    ** At this point, the pio server is going to be trained and deployed
    */
    private class GetRestaurants extends AsyncTask<String, String, String> {


        ProgressDialog pd;
        String url = "http://zorovn.hongo.wide.ad.jp/api/v1/users?method=predict&yelp_user_id=" + userId + "&num=10&categorize=restaurant&model=retrain";

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
        protected void onPreExecute() {
            super.onPreExecute();
            //Show progress dialog
            pd = ProgressDialog.show(PrefActivity.this, "", "Waiting for Recommendations...", false);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            JSONParser parser = new JSONParser();
            try {
                JSONArray jobj = (JSONArray) parser.parse(result);

                for (int i = 0; i < jobj.size(); i++) {
                    JSONObject jObject = (JSONObject) jobj.get(i);
                    Log.e("JObect", jObject.toString());
                    JSONObject rest = (JSONObject) jObject.get("business");
                    String bid = (String) rest.get("yelp_business_id");
                    String name = (String) rest.get("name");
                    String address = (String) rest
                            .get("full_address");
                    Double longitude = (Double) rest
                            .get("longitude");
                    Double latitude = (Double) rest.get("latitude");
                    Double stars = (Double) rest.get("stars");

                    ArrayList<String> categories = new ArrayList<String>();
                    JSONArray category = (JSONArray) rest.get("categories");
                    Log.e("name", name);
                    for (int j = 0; j < category.size(); j++) {
                        String entry = (String) category.get(j);
                        if (!entry.equals("Restaurants")) {
                            categories.add(entry);
                        }
                    }

                    Restaurant restaurant = new Restaurant(bid, name,
                            address, categories, longitude, latitude,
                            stars);

                    //Image setting
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
                    restaurants_list.add(restaurant);
                }
                app.setRestaurantMaps(restaurant_maps);
                app.setRestaurants_list(restaurants_list);
                pd.dismiss();

                //Move to restraurant list activity
                Intent i = new Intent(PrefActivity.this, RestaurantListActivity.class);
                startActivity(i);
                finish();


            } catch (ParseException e) {
                Log.e("JSONException", e + "");
            }


        }

    }


}
