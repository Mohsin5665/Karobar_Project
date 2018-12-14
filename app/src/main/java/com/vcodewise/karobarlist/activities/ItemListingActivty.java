package com.vcodewise.karobarlist.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vcodewise.karobarlist.R;
import com.vcodewise.karobarlist.adapters.ItemListing_Adapter;
import com.vcodewise.karobarlist.models.ItemListing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemListingActivty extends AppCompatActivity {

    private static String URL_Web = "http://karobarlist.xtechnos.com/api/businesses";
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private List<ItemListing> itemlist;
    String id;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setContentView(R.layout.listing_recyclerview);

        recyclerView = findViewById(R.id.l_recycler);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        itemlist = new ArrayList<>();


        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Web,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject data = jsonObject.getJSONObject("data");
                            JSONArray array = data.getJSONArray("businesses");

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject o = array.getJSONObject(i);
                              ItemListing list = new ItemListing(

                                        o.getString("category"),
                                        o.getString("address"),
                                        o.getInt("total_reviews"),
                                        o.getInt("review_ratings"),
                                        o.getString("image")

                                );
                                itemlist.add(list);
                            }

                            adapter = new ItemListing_Adapter(itemlist,ItemListingActivty.this);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ItemListingActivty.this, "Nothing is Available For Time Being", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}