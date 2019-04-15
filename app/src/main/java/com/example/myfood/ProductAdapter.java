package com.example.myfood;

/**
 * Created by Tanmay Ranjan on 19-Apr-18.
 */

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
import java.util.Map;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    static HashMap<String,String> hm = new HashMap<>();
    private Context mCtx;
    Map<String, Integer> count_2 = new HashMap<String, Integer>();
    private List<Product> productList;
    public static int totalAmount = 0,count=0;
    private Product product;
    public static String Sproduct;
    private String SproductList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
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
        View view = inflater.inflate(R.layout.menu_layout,null);
         view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ProductViewHolder(view,mCtx, (ArrayList<Product>) productList);

        }


    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        final Product product = productList.get(position);



         holder.pname.setText(product.getProduct());

        holder.price.setText(String.valueOf(product.getPrice()));

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!count_2.containsKey(productList.get(position).getId())){
                count_2.put(productList.get(position).getId(),1);
                }
                else{
                    count_2.put(productList.get(position).getId(),count_2.get(productList.get(position).getId())+1);
                }

            count =count+1;

                totalAmount=totalAmount +Integer.parseInt( productList.get(position).getPrice());
                holder.display.setText(String.valueOf(count_2.get(productList.get(position).getId())));
/*
                if(Sproduct!=null){
                    SproductList = SproductList+","+Sproduct;
                }else{
                    SproductList = Sproduct;
                }

                Log.d("setStored","saving item: "+SproductList); */


                hm.put(productList.get(position).getId(),count_2.get(productList.get(position).getId())+","+productList.get(position).getPrice()+","+productList.get(position).getProduct());


                SproductList=SproductList+","+productList.get(position).getProduct();
                Sproduct= Sproduct +","+productList.get(position).getPrice();
                Log.d("setStored","saving item: "+Sproduct);
                Log.d("setStored","saving item: "+SproductList);

            }
        });
        holder.btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_2.containsKey(productList.get(position).getId())&&count_2.get(productList.get(position).getId())>0) {

                        count_2.put(productList.get(position).getId(),count_2.get(productList.get(position).getId())-1);


                    count = count - 1;
                    if(count_2.get(productList.get(position).getId())==0){
                        hm.remove(productList.get(position).getId());
                    }
                    else{
                        hm.put(productList.get(position).getId(),count_2.get(productList.get(position).getId())+","+productList.get(position).getPrice()+","+productList.get(position).getProduct());
                        totalAmount = totalAmount - Integer.parseInt(productList.get(position).getPrice());
                    }
                    holder.display.setText(String.valueOf(count_2.get(productList.get(position).getId())));


                }

            }
        });


     /*   holder.stepperTouch.stepper.setMin(0);
        holder.stepperTouch.stepper.setMax(3);
        holder.stepperTouch.stepper.addStepCallback(new OnStepCallback() {
            @Override
            public void onStep(int value, boolean positive) {
               // Toast.makeText(mCtx, value + "", Toast.LENGTH_SHORT).show();
                count =value;



                BottomSheetDialog dialog = new BottomSheetDialog(mCtx);
                dialog.setContentView(R.layout.bottom_sheet);

                TextView price=(TextView)dialog.findViewById(R.id.price);
                TextView cart=(TextView)dialog.findViewById(R.id.cart);
                price.setText(totalAmount);

                dialog.show();

                cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mCtx,CartActivity.class);
                       // intent.putExtra("id",product.getChapter_id());
                        mCtx.startActivity(intent);
                    }
                });

            }
        });*/


       /* holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               totalAmount=totalAmount +Integer.parseInt( productList.get(position).getPrice());

               Log.d("total pay : ", String.valueOf(totalAmount));

                Snackbar.make(v, "Amount "+totalAmount, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Cart", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mCtx.startActivity(new Intent(mCtx,CartActivity.class));

                            }
                        }).show();




            }
        });   */





    }



    @Override
    public int getItemCount() {
        return productList.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       // TextView textViewLocation, textViewPrice,textViewArea,textViewBath,textViewBed;
       private TextView pname,price,display;
      // ImageView add;
       Button btn_add,btn_sub;
       // StepperTouch stepperTouch;

        ArrayList<Product> properties = new ArrayList<Product>();
        Context ctx;
        public ProductViewHolder(View itemView, Context ctx, ArrayList<Product> properties) {
            super(itemView);
            this.properties = properties;
            this.ctx=ctx;
            itemView.setOnClickListener(this);

            btn_add = itemView.findViewById(R.id.bAdd);
            btn_sub = itemView.findViewById(R.id.bSub);
            pname = itemView.findViewById(R.id.pname);
            price = itemView.findViewById(R.id.price);
            display=itemView.findViewById(R.id.tvDisplay);
           // stepperTouch = itemView.findViewById(R.id.stepperTouch);
        }


        @Override
        public void onClick(View v) {
            int postion = getAdapterPosition();
             product = this.properties.get(postion);
           /* Intent intent = new Intent(this.ctx,Main1Activity.class);
            intent.putExtra("id",product.getId());
            this.ctx.startActivity(intent);*/


        }
    }
}
