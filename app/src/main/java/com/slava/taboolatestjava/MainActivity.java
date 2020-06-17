package com.slava.taboolatestjava;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.slava.taboolatestjava.articles.fragments.MainArticlesScreenFragment;

import java.util.Objects;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, MainArticlesScreenFragment.newInstance(), null)
                .commit();
        if (getIntent() != null && getIntent().getAction() != null) {
            if (Intent.ACTION_SEND.equals(getIntent().getAction())) {
                if (Objects.equals(getIntent().getType(), "text/plain")) {
                    fragmentContainer = findViewById(R.id.fragment_container);
                    handleSendText();
                }
            }
        }
    }

    private void handleSendText() {
        String colorHex = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        if (colorHex != null) {
            Timber.d("Received color - %s", colorHex);
            fragmentContainer.setBackgroundColor(Color.parseColor(colorHex));
        }
    }
}
