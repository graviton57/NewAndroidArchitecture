package com.havrylyuk.newandroidarchitecture.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.havrylyuk.newandroidarchitecture.R;
import com.havrylyuk.newandroidarchitecture.data.local.CountriesDatabase;
import com.havrylyuk.newandroidarchitecture.data.local.dao.CountryDao;
import com.havrylyuk.newandroidarchitecture.data.remote.ApiClient;
import com.havrylyuk.newandroidarchitecture.data.remote.CountriesService;
import com.havrylyuk.newandroidarchitecture.ui.base.BaseLifecycleActivity;
import com.havrylyuk.newandroidarchitecture.ui.detail.DetailActivity;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

public class MainActivity  extends BaseLifecycleActivity {

    private ProgressBar progressBar;
    private TextView tvError;
    private CountriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        setupRecyclerView();

        CountriesViewModel viewModel = getViewModel();
        viewModel.getMoviesList().observe(this, moviesResponse -> {
            if (moviesResponse != null && moviesResponse.getData() != null) {
                adapter.setData(moviesResponse.getData());
                tvError.setVisibility(View.GONE);
            } else if (moviesResponse != null && moviesResponse.getError() != null) {
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(moviesResponse.getError().getMessage());
            }
        });

        viewModel.isLoading().observe(this, isLoading -> {
            if (isLoading != null && isLoading) {
                setProgressBarVisible(true);
            } else {
                setProgressBarVisible(false);
            }
        });
    }

    private void setupUI(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvError = (TextView) findViewById(R.id.txt_error);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        adapter = new CountriesAdapter(country -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_COUNTRY, country);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    private CountriesViewModel getViewModel() {
        CountriesService service = ApiClient.getCountriesService();
        CountryDao moviesDao = CountriesDatabase.getInstance(this).countryDao();
        ViewModelProvider.Factory factory = new CountriesViewModelProviderFactory(service, moviesDao);
        return ViewModelProviders.of(this, factory).get(CountriesViewModel.class);
    }

    private void setProgressBarVisible(boolean visible) {
        if (progressBar == null) {
            return;
        }
        if (visible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
