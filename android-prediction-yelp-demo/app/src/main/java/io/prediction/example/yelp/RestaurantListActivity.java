package io.prediction.example.yelp;

import android.app.Activity;
import android.os.Bundle;

public class RestaurantListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recommend);
        populateResultList();
    }

    private void populateResultList() {

        String url = "http://10.0.2.2:8000";
        String str = getIntent().getExtras().getString("user");
        String uid = str.substring(str.indexOf("UserID: ") + "UserID: ".length(), str.indexOf(", UserName: "));
        // Create sub thread
        AsyncHttpRequest task = new AsyncHttpRequest(this);
        task.owner = this;
        task.execute(url, uid);
    }
}