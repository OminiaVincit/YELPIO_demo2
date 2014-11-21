package io.prediction.example.yelp;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class IntroActivity extends Activity {

    Handler handler;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Full page logo screen - remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_intro);

        mPrefs = getSharedPreferences("data",0); // initialize SharedPreferences

        // Load user & restaurant list
        ((AppDataCollect)this.getApplication()).SetCollectedData();

        // Delay for ...
        handler = new Handler();
        handler.postDelayed(irun, 3000);

    }

    Runnable irun = new Runnable() {
        public void run() {

            Intent i = new Intent(IntroActivity.this, UsersListActivity.class);
            startActivity(i);
            finish();

            // Screen fade in & out effect
            overridePendingTransition(android.R.anim.fade_in, R.anim.out);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro, menu);
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(irun);
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
}