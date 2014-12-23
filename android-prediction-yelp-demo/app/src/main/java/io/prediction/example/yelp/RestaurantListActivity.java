package io.prediction.example.yelp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
** Show the list of recommendations received from the server
*/
public class RestaurantListActivity extends Activity {
    HashMap<String, Restaurant> restaurants;
    ArrayList<Restaurant> resultRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Example);
        setContentView(R.layout.activity_user_recommend);
        overridePendingTransition(R.anim.in, R.anim.out);

        restaurants = ((AppDataCollect) this.getApplication()).getRestaurantMaps();
        resultRestaurants = ((AppDataCollect) this.getApplication()).getRestaurants_list();

        ListView restaurant_list = (ListView) findViewById(R.id.restaurant_list);

        generateList(restaurant_list, R.layout.item_restaurant);


    }

    private void generateList(ListView view, int layout) {
        MyListAdapter adapter = new MyListAdapter(getBaseContext(), layout, resultRestaurants);
        SwingBottomInAnimationAdapter swing = new SwingBottomInAnimationAdapter(adapter);
        swing.setAbsListView(view);
        view.setAdapter(swing);
    }


    private class MyListAdapter extends ArrayAdapter<Restaurant> {
        List<Restaurant> list;

        private int resource;

        public MyListAdapter(Context context, int resourceId, List<Restaurant> list) {
            super(context, resourceId, list);
            this.list = list;
            this.resource = resourceId;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(resource, parent, false);
            }
            final Restaurant curItem = list.get(position);
            String name = curItem.getName();
            String address = curItem.getAddress();
            float rating = (float) curItem.getStars();
            ArrayList<String> category = curItem.getCategories();
            String number = (position + 1) + ".";

            ImageView rest_image = (ImageView) rowView.findViewById(R.id.rest_image);
            TextView rest_name_view = (TextView) rowView.findViewById(R.id.rest_name);
            TextView rest_category_view = (TextView) rowView.findViewById(R.id.rest_categories);
            TextView rest_address_view = (TextView) rowView.findViewById(R.id.rest_address);
            RatingBar ratingBar = (RatingBar) rowView.findViewById(R.id.rest_rating);
            TextView number_label = (TextView) rowView.findViewById(R.id.number_label);

            number_label.setText(number);
            rest_name_view.setText(name);
            rest_address_view.setText(address);
            rest_category_view.setText(category.toString());
            ratingBar.setRating(rating);
            rest_image.setImageDrawable(getResources().getDrawable(curItem.getImage()));


            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //When the item is clicked, move on to the detail activity and transfer the selected restaurant object.
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("restaurant", curItem);
                    startActivity(intent);
                }
            });

            return rowView;
        }

    }

    public void onBackPressed() {
        //Alert dialog for exiting the app
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                RestaurantListActivity.this);

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
}