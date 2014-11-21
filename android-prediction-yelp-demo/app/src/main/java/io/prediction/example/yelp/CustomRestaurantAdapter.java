package io.prediction.example.yelp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomRestaurantAdapter extends ArrayAdapter<Restaurant> {
    static class ViewHolder {
        TextView name;
        TextView stars;
    }

    private LayoutInflater inflater;

    public CustomRestaurantAdapter(Context context, ArrayList<Restaurant> restaurants) {
        super(context, 0, restaurants);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Restaurant restaurant = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_restaurant, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.stars = (TextView) convertView.findViewById(R.id.tvStars);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(restaurant.getName());
        viewHolder.stars.setText(String.valueOf(restaurant.getStars()));

        // Load xml animation file
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.item_motion);
        convertView.startAnimation(anim);

        // Return the completed view to render on screen
        return convertView;
    }
}
