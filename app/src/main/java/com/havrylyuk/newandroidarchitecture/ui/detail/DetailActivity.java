package com.havrylyuk.newandroidarchitecture.ui.detail;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.havrylyuk.newandroidarchitecture.BuildConfig;
import com.havrylyuk.newandroidarchitecture.R;
import com.havrylyuk.newandroidarchitecture.data.entiry.Country;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_COUNTRY = "EXTRA_COUNTRY";

    private SimpleDraweeView imageView;
    private TextView tvCapital;
    private TextView tvContinent;
    private TextView tvCode;
    private TextView tvArea;
    private TextView tvPopulation;
    private TextView tvSouth;
    private TextView tvNorth;
    private TextView tvEast;
    private TextView tvWest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupUI();
        Country country = getIntent().getParcelableExtra(EXTRA_COUNTRY);
        showCountryDetail(country);
    }

    private void setupUI(){
        imageView = (SimpleDraweeView) findViewById(R.id.image);
        tvCapital = (TextView) findViewById(R.id.capital);
        tvContinent = (TextView) findViewById(R.id.continent);
        tvCode = (TextView) findViewById(R.id.code);
        tvArea = (TextView) findViewById(R.id.area);
        tvPopulation = (TextView) findViewById(R.id.population);
        tvNorth = (TextView) findViewById(R.id.north);
        tvSouth = (TextView) findViewById(R.id.south);
        tvWest = (TextView) findViewById(R.id.west);
        tvEast = (TextView) findViewById(R.id.east);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @SuppressWarnings("deprecation")
    private void showCountryDetail(@NonNull Country country) {
        CollapsingToolbarLayout toolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(country.getCountryName());
        toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.white));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(country.getCountryName());
        }
        if (imageView != null ) {
             String flagUri = BuildConfig.GEONAME_ICON_URL +"x/" +
                     country.getCountryCode().toLowerCase() + ".gif";
            imageView.setImageURI(Uri.parse(flagUri));
        }
        tvCapital.setText(getString(R.string.format_country_capital,country.getCapital()));
        tvContinent.setText(getString(R.string.format_country_continent,country.getContinentName()));
        tvCode.setText(getString(R.string.format_country_code,country.getCountryCode()));
        tvArea.setText(Html.fromHtml(getString(R.string.format_country_area,country.getAreaInSqKm())));
        tvPopulation.setText(getString(R.string.format_country_population,country.getPopulation()));
        tvSouth.setText(getString(R.string.format_country_south,country.getSouth()));
        tvEast.setText(getString(R.string.format_country_east,country.getEast()));
        tvNorth.setText(getString(R.string.format_country_north,country.getNorth()));
        tvWest.setText(getString(R.string.format_country_west,country.getWest()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
