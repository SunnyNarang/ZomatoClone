package com.example.myfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS = "http://192.168.1.4/foodapp/restraunts.php";

    //a list to store all the properties
    List<Restra> restraList;

    //the recyclerview
    RecyclerView recyclerView;

    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.rv);
         progressBar=findViewById(R.id.progress);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        //initializing the product_list
        restraList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
    }

    private void loadProducts() {

        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.VISIBLE);
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                restraList.add(new Restra(
                                        product.getInt("id"),
                                        product.getString("name"),
                                         product.getString("add"),
                                        product.getString("tme"),
                                        product.getString("cat1"),
                                        product.getString("cat2"),
                                        product.getString("cat3"),
                                        product.getString("rate"),
                                        product.getString("image")
                                ));
                            }

                            progressBar.setVisibility(View.GONE);

                            //creating adapter object and setting it to recyclerview
                            RestraAdapter adapter = new RestraAdapter(MainActivity.this, restraList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}