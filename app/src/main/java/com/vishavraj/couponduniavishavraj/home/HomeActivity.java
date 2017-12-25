package com.vishavraj.couponduniavishavraj.home;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vishavraj.couponduniavishavraj.BaseApp;
import com.vishavraj.couponduniavishavraj.R;
import com.vishavraj.couponduniavishavraj.models.CouponDuniaData;
import com.vishavraj.couponduniavishavraj.models.CouponDuniaList;
import com.vishavraj.couponduniavishavraj.networking.Service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends BaseApp implements HomeView  {
    @Inject
    public Service service;
    private boolean isLoading = true, hasMoreDataToLoad = true;
    HomeAdapter adapter;
    private int pageNumber = 1;
    HomePresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    int totalDataCount = 0;
    int totalPages=0;
    List<CouponDuniaList> couponDuniaLists;
    List<CouponDuniaList> couponDuniaListsToshow;

    @BindView(R.id.list)
    RecyclerView list;

    @BindView(R.id.textview_list_count)
    TextView listCountTextView;
    @BindView(R.id.progress)
    ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDeps().inject(this);
        ButterKnife.bind(this);

        renderView();

        couponDuniaListsToshow = new ArrayList<>();

        adapter = new HomeAdapter(getApplicationContext(), couponDuniaListsToshow,
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CouponDuniaList Item) {

                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });
        list.setAdapter(adapter);

        lazyLoadingSetup();


    }

    public  void renderView(){
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(linearLayoutManager);
        list.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        Log.e("appErrorMessage",appErrorMessage);
    }

    @Override
    public void getityListSuccess(CouponDuniaData couponDuniaData) {
        progressBar.setVisibility(View.GONE);
        totalDataCount = couponDuniaData.getResponse().getTotalItems();
        totalPages = couponDuniaData.getResponse().getTotalPages();
               couponDuniaLists =couponDuniaData.getResponse().getList();
             if(couponDuniaLists.size()>0){
                 isLoading = true;

                 if (null != couponDuniaLists && couponDuniaLists.size() == totalDataCount) {
                     hasMoreDataToLoad = false;
                 } else {
                     hasMoreDataToLoad = true;
                 }
                 list.getRecycledViewPool().clear();
                 couponDuniaListsToshow.addAll( couponDuniaLists);

                 adapter.notifyDataSetChanged();


             }

         else{
             Toast.makeText(getApplicationContext(), "No more data", Toast.LENGTH_LONG).show();
         }


    }
    private void lazyLoadingSetup() {

        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleThreshold = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()+1;
                listCountTextView.setText((lastVisibleItem) + " / "+totalDataCount+"");


                if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (isLoading && pageNumber >= 1 && hasMoreDataToLoad) {
                        pageNumber++;
                        isLoading = false;
                        progressBar.setVisibility(View.VISIBLE);
                        presenter.getCityList(pageNumber);
                    }
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        presenter = new HomePresenter(service, this);
        presenter.getCityList(pageNumber);

    }

}
