package io.prediction.example.yelp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/*
** Show details of the restaurant including the users' reviews
*/
public class DetailActivity extends Activity {

    GoogleMap googleMap;
    TextView detail_name;
    TextView detail_address;
    TextView detail_category;
    ImageView detail_image;
    RatingBar detail_rating;

    RatingBar review_rating1;
    TextView review1_short;
    TextView review1_long;

    RatingBar review_rating2;
    TextView review2_short;
    TextView review2_long;

    RatingBar review_rating3;
    TextView review3_short;
    TextView review3_long;

    RatingBar review_rating4;
    TextView review4_short;
    TextView review4_long;

    RatingBar review_rating5;
    TextView review5_short;
    TextView review5_long;

    ArrayList<Double> ratings = new ArrayList<Double>();
    ArrayList<String> contents = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Example);
        setContentView(R.layout.activity_detail);
        overridePendingTransition(R.anim.in, R.anim.out);

        googleMap = ((MapFragment) getFragmentManager().
                findFragmentById(R.id.detail_map)).getMap();

        detail_name = (TextView) findViewById(R.id.detail_name);
        detail_address = (TextView) findViewById(R.id.detail_address);
        detail_category = (TextView) findViewById(R.id.detail_categories);
        detail_image = (ImageView) findViewById(R.id.detail_image);
        detail_rating = (RatingBar) findViewById(R.id.detail_rating);

        //Setting for review section
        review_rating1 = (RatingBar) findViewById(R.id.review_rating1);
        review1_short = (TextView) findViewById(R.id.review1_short);
        review1_long = (TextView) findViewById(R.id.review1_long);

        //Full review is initially hidden but it can be shown when it's touched
        review1_short.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review1_short.setVisibility(View.GONE);
                review1_long.setVisibility(View.VISIBLE);
            }
        });
        review1_long.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review1_long.setVisibility(View.GONE);
                review1_short.setVisibility(View.VISIBLE);
            }
        });


        review_rating2 = (RatingBar) findViewById(R.id.review_rating2);
        review2_short = (TextView) findViewById(R.id.review2_short);
        review2_long = (TextView) findViewById(R.id.review2_long);

        review2_short.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review2_short.setVisibility(View.GONE);
                review2_long.setVisibility(View.VISIBLE);
            }
        });
        review2_long.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review2_long.setVisibility(View.GONE);
                review2_short.setVisibility(View.VISIBLE);
            }
        });

        review_rating3 = (RatingBar) findViewById(R.id.review_rating3);
        review3_short = (TextView) findViewById(R.id.review3_short);
        review3_long = (TextView) findViewById(R.id.review3_long);

        review3_short.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review3_short.setVisibility(View.GONE);
                review3_long.setVisibility(View.VISIBLE);
            }
        });
        review3_long.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review3_long.setVisibility(View.GONE);
                review3_short.setVisibility(View.VISIBLE);
            }
        });

        review_rating4 = (RatingBar) findViewById(R.id.review_rating4);
        review4_short = (TextView) findViewById(R.id.review4_short);
        review4_long = (TextView) findViewById(R.id.review4_long);

        review4_short.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review4_short.setVisibility(View.GONE);
                review4_long.setVisibility(View.VISIBLE);
            }
        });
        review4_long.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review4_long.setVisibility(View.GONE);
                review4_short.setVisibility(View.VISIBLE);
            }
        });

        review_rating5 = (RatingBar) findViewById(R.id.review_rating5);
        review5_short = (TextView) findViewById(R.id.review5_short);
        review5_long = (TextView) findViewById(R.id.review5_long);

        review5_short.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review5_short.setVisibility(View.GONE);
                review5_long.setVisibility(View.VISIBLE);
            }
        });
        review5_long.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review5_long.setVisibility(View.GONE);
                review5_short.setVisibility(View.VISIBLE);
            }
        });


        //Detail setting
        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");

        //Get the review from the server for the restaurant
        new GetReviews().execute(restaurant.getBid());

        detail_name.setText(restaurant.getName());
        setTitle(restaurant.getName());
        detail_address.setText(restaurant.getAddress());
        String category = restaurant.getCategories().toString();
        detail_category.setText(category.substring(1, category.length() - 1));
        detail_rating.setRating(new Float(restaurant.getStars()));

        detail_image.setImageDrawable(getResources().getDrawable(restaurant.getImage()));

        //Map setting
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(restaurant.getLatitude(), restaurant.getLongitude()))
                .title(restaurant.getName())
                .snippet(restaurant.getCategories().toString()));

        googleMap.setMyLocationEnabled(true);

        LatLng latLng = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);
        googleMap.animateCamera(cameraUpdate);

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps
                String url = "http://maps.google.com/maps?daddr=" + marker.getPosition().latitude + "," + marker.getPosition().longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });


    }

    //Shorten the full review
    private String toShort(String source) {
        boolean tooLong = false;
        String[] words = source.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < words.length; i++) {
            sb.append(words[i]);
            sb.append(" ");
            if(i > 30) {
                tooLong = true;
                break;
            }
        }
        if(tooLong) {
            sb.append("... <a href=\"#\">Read more</a>");
        }
        return sb.toString();

    }


    //Getting reviews from the server
    private class GetReviews extends AsyncTask<String, String, String> {


        ProgressDialog pd;

        public GetReviews() {
            super();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = "http://zorovn.hongo.wide.ad.jp/api/v1/reviews?yelp_business_id="+params[0]+"&page_size=5";
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
            pd = ProgressDialog.show(DetailActivity.this, "", "Waiting for Reviews...", false);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            JSONParser parser = new JSONParser();
            try {
                JSONArray jobj = (JSONArray) parser.parse(result);

                for (int i = 0; i < jobj.size(); i++) {
                    JSONObject jObject = (JSONObject) jobj.get(i);
                    Double stars = (Double) jObject.get("stars");
                    String content = (String) jObject.get("content");
                    ratings.add(stars);
                    contents.add(content);
                }
                pd.dismiss();


            } catch (ParseException e) {
                Log.e("JSONException", e + "");
            }

            //Set the reveiws
            review_rating1.setRating(new Float(ratings.get(0)));
            review1_short.setText(Html.fromHtml(toShort(contents.get(0))));
            review1_long.setText(contents.get(0));

            review_rating2.setRating(new Float(ratings.get(1)));
            review2_short.setText(Html.fromHtml(toShort(contents.get(1))));
            review2_long.setText(contents.get(1));

            review_rating3.setRating(new Float(ratings.get(2)));
            review3_short.setText(Html.fromHtml(toShort(contents.get(2))));
            review3_long.setText(contents.get(2));

            review_rating4.setRating(new Float(ratings.get(3)));
            review4_short.setText(Html.fromHtml(toShort(contents.get(3))));
            review4_long.setText(contents.get(3));

            review_rating5.setRating(new Float(ratings.get(4)));
            review5_short.setText(Html.fromHtml(toShort(contents.get(4))));
            review5_long.setText(contents.get(4));

        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.end_in, R.anim.end_out);
    }

}
