package com.ipnx.ipnxmobile;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQActivity extends BaseActivity {

    @BindView(R.id.faq_answer1)
    ExpandableRelativeLayout layout1;

    @BindView(R.id.faq_answer2)
    ExpandableRelativeLayout layout2;

    @BindView(R.id.faq_answer3)
    ExpandableRelativeLayout layout3;

    @BindView(R.id.faq_answer4)
    ExpandableRelativeLayout layout4;

    @BindView(R.id.faq_answer5)
    ExpandableRelativeLayout layout5;

    @BindView(R.id.faq_answer6)
    ExpandableRelativeLayout layout6;

    @BindView(R.id.faq_answer7)
    ExpandableRelativeLayout layout7;

    @BindView(R.id.faq_answer8)
    ExpandableRelativeLayout layout8;

    @BindView(R.id.bottomNav_faq)
    TextView bottomNavFaqIcon;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);

        bottomNavFaqIcon.setTextColor(getResources().getColor(R.color.red_button));
        Drawable img = getResources().getDrawable( R.drawable.ic_faq3);
        bottomNavFaqIcon.setCompoundDrawablesWithIntrinsicBounds(null, img, null, null);

        // Collapse Expandable layouts in case of android versions
        // where it is always open by default
        layout1.collapse();
        layout2.collapse();
        layout3.collapse();
        layout4.collapse();
        layout5.collapse();
        layout6.collapse();
        layout7.collapse();
        layout8.collapse();
    }

    public void onFaqClicked(View view){
        switch (view.getId()){
            case R.id.faq1:
                layout1.toggle();
                break;
            case R.id.faq2:
                layout2.toggle();
                break;
            case R.id.faq3:
                layout3.toggle();
                break;
            case R.id.faq4:
                layout4.toggle();
                break;
            case R.id.faq5:
                layout5.toggle();
                break;
            case R.id.faq6:
                layout6.toggle();
                break;
            case R.id.faq7:
                layout7.toggle();
                break;
            case R.id.faq8:
                layout8.toggle();
                break;
        }

    }

    @Override
    public void onBottomNavItemClicked(View view) {
        // All bottom nav items except the FAQ item should
        // be clickable
        if (view.getId() != R.id.bottomNav_faq){
            super.onBottomNavItemClicked(view);
        }
    }

    public void onBackClicked(View view){
        finish();
    }
}
