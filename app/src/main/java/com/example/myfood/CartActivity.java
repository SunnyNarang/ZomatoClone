package com.example.myfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    ArrayList<Cart> cartlist = new ArrayList<>();
    Button btn_email;
    String payload="";
    TextView total,tax,grand;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        total = findViewById(R.id.add);
        tax = findViewById(R.id.tax);
        grand = findViewById(R.id.grand);

        tax.setText(""+(ProductAdapter.totalAmount*.05));
        total.setText(""+ProductAdapter.totalAmount);
        grand.setText(""+(ProductAdapter.totalAmount+(ProductAdapter.totalAmount*.05)));

        recyclerView = findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        for (Map.Entry<String, String> val : ProductAdapter.hm.entrySet()) {
            String[] data = val.getValue().split(",");
            cartlist.add(new Cart(data[2],data[0],data[1]));
            if(payload.equalsIgnoreCase("")){
            payload += val.getKey()+"*"+data[0];
            }
            else {
                payload += ","+val.getKey()+"*"+data[0];
            }

        }






        CartAdapter adapter = new CartAdapter(CartActivity.this,cartlist);
        recyclerView.setAdapter(adapter);


        btn_email=(Button)findViewById(R.id.button2);
        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartActivity.this,EmailActivity.class);
                i.putExtra("payload",payload);
                startActivity(i);



            }
        });
    }
}
