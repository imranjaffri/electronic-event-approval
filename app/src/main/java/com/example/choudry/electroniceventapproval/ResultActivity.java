package com.example.choudry.electroniceventapproval;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.choudry.electroniceventapproval.fragments.ResultsLoadListener;
import com.example.choudry.electroniceventapproval.fragments.ViewPagerAdapter;

public class ResultActivity extends AppCompatActivity implements ResultsLoadListener {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ProgressDialog pDialog;

    private int dataLoadSensor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_result);
        setTitleColor(getResources().getColor(R.color.colorWhite));

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Results");
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        pDialog = new ProgressDialog(ResultActivity.this, R.style.AppCompatAlertDialogStyle);
        pDialog.setMessage("Fetching...");
        pDialog.show();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }


    @Override
    public void onLoadSuccessful(int sender) {

        if (dataLoadSensor == 0)
            dataLoadSensor += 1;
        else pDialog.dismiss();
    }
}
