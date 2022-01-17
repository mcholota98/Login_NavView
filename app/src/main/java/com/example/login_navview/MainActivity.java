package com.example.login_navview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue rqueue;
    EditText txtuser, txtpassword;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rqueue = Volley.newRequestQueue(this);
        txtuser = (EditText)findViewById(R.id.txtUser);
        txtpassword = (EditText)findViewById(R.id.txtpass);
        btnlogin = (Button)findViewById(R.id.btnLogIn);

        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getData(txtuser.getText().toString().trim(), txtpassword.getText().toString().trim());
            }
        });
    }

    private void getData(String user, String password)
    {
        String url = "https://my-json-server.typicode.com/mcholota98/jsonn/data?user=";
        Intent intent = new Intent(this, NavDrawOptions.class);
       JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
               Request.Method.GET,
               url + user + "&password=" + password,
               null,
               new Response.Listener<JSONArray>() {
                   @Override
                   public void onResponse(JSONArray response) {
                       if(response.length() <= 0)
                       {
                           Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                       }else
                       {
                           int size = response.length();
                           for (int i = 0; i<size; i++)
                           {
                               try {
                                   JSONObject jsObject = new JSONObject(response.get(i).toString());
                                   intent.putExtra("name",jsObject.getString("name"));
                                   intent.putExtra("img", jsObject.getString("img"));
                                   intent.putExtra("rol", jsObject.getString("rol"));
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }
                           }
                           startActivity(intent);
                       }
                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               }
       );
       rqueue.add(jsonArrayRequest);
    }
}