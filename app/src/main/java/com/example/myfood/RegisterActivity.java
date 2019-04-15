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

import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    TextView signup,login;

    EditText name,email,pass;

    // Creating Volley RequestQueue.
    com.android.volley.RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String User, Email, Pass;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "http://192.168.1.4/foodapp/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signup=(TextView)findViewById(R.id.register);
        login=(TextView) findViewById(R.id.login);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        name=(EditText)findViewById(R.id.name);

        // Creating Volley newRequestQueue .
        requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(RegisterActivity.this);

        progressDialog = new ProgressDialog(RegisterActivity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });

        // Adding click listener to button.
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Showing progress dialog at user registration time.
                progressDialog.setMessage("Please Wait");
                progressDialog.show();

                // Calling method to get value from EditText.
                GetValueFromEditText();

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, HttpUrl,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing response message coming from server.
                                //Toast.makeText(RegisterActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                                try {
                                    JSONObject jsonObject = new JSONObject(ServerResponse);
                                    String msg = jsonObject.getString("message");
                                    String success = jsonObject.getString("success");
                                    String uid = jsonObject.getString("userid");
                                    Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_LONG).show();
                                    // startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    if (success.equalsIgnoreCase("true")){
                                        SharedPreferences preferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);

                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.clear();
                                        editor.putString("uid",uid);
                                        editor.commit();
                                        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                                    }else{

                                        //Toast.makeText(LoginActivity.this, "Please Try Again", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                               // startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                            }
                        },
                        new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(com.android.volley.VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                android.widget.Toast.makeText(RegisterActivity.this, volleyError.toString(), android.widget.Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected java.util.Map<String, String> getParams() {

                        // Creating Map String Params.
                        java.util.Map<String, String> params = new java.util.HashMap<String, String>();

                        // Adding All values to Params.
                        params.put("Content-Type", "application/json");
                        params.put("username", User);
                        params.put("password", Pass);
                        params.put("email", Email);;

                        return params;
                    }

                };

                // Creating RequestQueue.
                com.android.volley.RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(RegisterActivity.this);

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);

            }
        });}


    public void GetValueFromEditText(){

        User = name.getText().toString().trim();
        Email = email.getText().toString().trim();
        Pass = pass.getText().toString().trim();


    }
    }

