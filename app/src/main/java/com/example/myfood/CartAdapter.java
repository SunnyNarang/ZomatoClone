package com.example.myfood;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductViewHolder> {

    static HashMap<String,String> hm = new HashMap<>();
    private Context mCtx;
    private List<Cart> productList;
    public static int totalAmount = 0,count=0;
    private Cart product;
    public static String Sproduct;
    private String SproductList;

    public CartAdapter(Context mCtx, List<Cart> productList) {
        this.mCtx = mCtx;
        this.productList = productList;

        totalAmount = 0;
        count=0;
        Sproduct= "";
        SproductList= "";
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cartlayout,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ProductViewHolder(view,mCtx, (ArrayList<Cart>) productList);

    }


    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        final Cart product = productList.get(position);
        holder.pname.setText(product.getName());
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.quantity.setText(String.valueOf(product.getQuantity()));
        holder.total.setText(String.valueOf(Integer.parseInt(product.getQuantity())*Integer.parseInt(product.getPrice())));

    }



    @Override
    public int getItemCount() {
        return productList.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder{
        private TextView pname,price,quantity,total;
        ArrayList<Cart> properties = new ArrayList<Cart>();
        Context ctx;
        public ProductViewHolder(View itemView, Context ctx, ArrayList<Cart> properties) {
            super(itemView);
            this.properties = properties;
            this.ctx=ctx;
            quantity = itemView.findViewById(R.id.quantity);
            pname = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            total = itemView.findViewById(R.id.total);
        }

    }
}
