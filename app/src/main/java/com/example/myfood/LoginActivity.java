package com.example.myfood;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    TextView txtSinup,txtSinin;

    EditText email,pass;

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String Email, Pass;

    // Creating Progress dialog.
   ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "http://192.168.1.4/foodapp/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtSinup=(TextView)findViewById(R.id.signup1);
        txtSinin=(TextView)findViewById(R.id.login1);
        email=(EditText)findViewById(R.id.emal1);
        pass=(EditText)findViewById(R.id.pass1);

        // Creating Volley newRequestQueue .
        requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(LoginActivity.this);

        progressDialog = new android.app.ProgressDialog(LoginActivity.this);

        txtSinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        txtSinin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(LoginActivity.this,MainActivity.class));

                // Showing progress dialog at user registration time.
                progressDialog.setMessage("Please Wait");
                progressDialog.show();

                // Calling method to get value from EditText.
                GetValueFromEditText();

                // Creating string request with post method.
                com.android.volley.toolbox.StringRequest stringRequest = new com.android.volley.toolbox.StringRequest(com.android.volley.Request.Method.POST, HttpUrl,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                try {
                                    JSONObject jsonObject = new JSONObject(ServerResponse);
                                    String msg = jsonObject.getString("message");
                                    String success = jsonObject.getString("success");
                                    String uid=jsonObject.getString("uid");
                                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                                   // startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    if (success.equalsIgnoreCase("true")){

                                        SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = shared.edit();
                                        editor.putString("uid",uid);

                                        editor.commit();

                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    }else{

                                        //Toast.makeText(LoginActivity.this, "Please Try Again", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                // Showing response message coming from server.
                                // Toast.makeText(LoginActivity.this, ServerResponse, Toast.LENGTH_LONG).show();

                                //startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                        },
                        new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(com.android.volley.VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                android.widget.Toast.makeText(LoginActivity.this, volleyError.toString(), android.widget.Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected java.util.Map<String, String> getParams() {

                        // Creating Map String Params.
                        java.util.Map<String, String> params = new java.util.HashMap<String, String>();

                        // Adding All values to Params.

                        params.put("password", Pass);
                        params.put("email", Email);


                        return params;
                    }

                };

                // Creating RequestQueue.
                com.android.volley.RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(LoginActivity.this);

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);

            }
        });
    }

    public void GetValueFromEditText(){


        Email = email.getText().toString().trim();
        Pass = pass.getText().toString().trim();


    }
    }

