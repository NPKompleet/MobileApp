package com.ipnx.ipnxmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import static android.os.SystemClock.sleep;

public class CarouselActivity extends AppCompatActivity {
    CarouselView customCarouselView;
    int NUMBER_OF_PAGES = 3;
    int[] carouselItems = {R.layout.carousel_page1, R.layout.carousel_page2, R.layout.carousel_page3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);

        customCarouselView = findViewById(R.id.carouselView);
        customCarouselView.setPageCount(NUMBER_OF_PAGES);
        customCarouselView.setViewListener(viewListener);

        sleep(15000);
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    ViewListener viewListener = new ViewListener() {

        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(carouselItems[position], null);

            return customView;
        }
    };
}
