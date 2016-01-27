package sidespell.tech.webservices.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import sidespell.tech.webservices.R;
import sidespell.tech.webservices.fragments.SimpleWeatherFragment;

public class DetailActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG = "fragmentContained";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            throw new RuntimeException("DetailActivity must accept intent extras.");
        }

        int topicId = intent.getIntExtra(MainActivity.Topic.KEY, MainActivity.Topic.SIMPLE_WEATHER);
        String topic = MainActivity.Topic.getName(topicId);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(topic);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fragment fragment;

        if (savedInstanceState == null) {

            if (topicId == MainActivity.Topic.SIMPLE_WEATHER) {
                fragment = SimpleWeatherFragment.newInstance();
            } else {
                throw new RuntimeException("Invalid topic passed");
            }
        } else {
            fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        }

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, FRAGMENT_TAG)
                    .commit();
        }
    }
}
