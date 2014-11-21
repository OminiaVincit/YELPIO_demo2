package io.prediction.example.yelp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class UsersListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        populateUsersList();
    }

    private void populateUsersList() {

        HashMap<String, User> user_maps = ((AppDataCollect)this.getApplication()).getUserMaps();
        ArrayList<User> users_array = new ArrayList<User>(user_maps.values());
        Collections.sort(users_array, new Comparator<User>() {
            @Override
            public int compare(User user, User user2) {
                return (user.getUserName().compareTo(user2.getUserName()));
            }
        });
        CustomUsersAdapter adapter = new CustomUsersAdapter(this, users_array);

        // Attach the adapter to a ListView
        final ListView list_view = (ListView) findViewById(R.id.lvUsers);
        list_view.setAdapter(adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), RestaurantListActivity.class);
                    intent.putExtra("user",list_view.getItemAtPosition(position).toString());
                    startActivity(intent);
            }
        });
    }
}