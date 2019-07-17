package com.subrata.paginglibraryproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        final ItemAdapter itemAdapter = new ItemAdapter(this);

        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(@Nullable PagedList<Item> items) {
                itemAdapter.submitList(items);
            }
        });

        recyclerView.setAdapter(itemAdapter);

//        //call the stack exchange api
//        Call<StackApiResponse> call =
//                RetrofitClient.getInstance()
//                .getApi()
//                .getAnswer(1, 50, "stackoverflow");
//
//        call.enqueue(new Callback<StackApiResponse>() {
//            @Override
//            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
//
//                StackApiResponse stackApiResponse = response.body();
//
//                //Toast.makeText(MainActivity.this, "Size:"+stackApiResponse.items.size(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "Size:"+stackApiResponse.items.size(), Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "Result"+stackApiResponse);
//            }
//
//            @Override
//            public void onFailure(Call<StackApiResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "Error:"+t.getMessage());
//            }
//        });
    }
}
