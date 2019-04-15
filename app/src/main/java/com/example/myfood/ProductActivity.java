package com.example.myfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductActivity extends AppCompatActivity {

    private static final String URL_PRODUCTS = "http://192.168.1.4/foodapp/menu.php";

    //a list to store all the properties
    List<Product> productList;

    //the recyclerview
    RecyclerView recyclerView;

    ImageView img, cart1;
    TextView rname,loc;
    Button con;

    //ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ProductAdapter.hm.clear();
        ProductAdapter.totalAmount=0;

        con=(Button)findViewById(R.id.button1);
        cart1=(ImageView)findViewById(R.id.cart);
        img=(ImageView)findViewById(R.id.imagev);
        rname=(TextView) findViewById(R.id.rname);
        loc=(TextView) findViewById(R.id.loc);
        String image =  getIntent().getStringExtra("img");
        Glide.with(getApplicationContext())
                .load(image)
                .into(img);

        rname.setText(getIntent().getStringExtra("name"));
        loc.setText(getIntent().getStringExtra("loc "));

        recyclerView = findViewById(R.id.rv1);

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this,CartActivity.class));
            }
        });
        cart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this,CartActivity.class));
            }
        });

      //  progressBar=findViewById(R.id.prog);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initializing the product_list
        productList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview

        loadProducts();
    }


    private void loadProducts() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                       // progressBar.setVisibility(View.VISIBLE);
                        try {

                            Log.i("tagconvertstr", "["+response+"]");
                            //converting the string to json array object
                           // JSONArray array = new JSONArray(response);

                            JSONObject jsnobject = new JSONObject(response);
                            JSONArray array = jsnobject.getJSONArray("a");

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Product(
                                        product.getString("id"),
                                          product.getString("product"),
                                        product.getString("price")

                                ));
                            }



                         //   progressBar.setVisibility(View.GONE);

                            //creating adapter object and setting it to recyclerview
                            ProductAdapter adapter = new ProductAdapter(ProductActivity.this,productList );
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
                }){


            @Override
            protected Map<String, String> getParams() {
                Map<String,String> stringMap = new HashMap<String, String>();
                stringMap.put("id",getIntent().getStringExtra("id"));
                return stringMap;
            }};

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
