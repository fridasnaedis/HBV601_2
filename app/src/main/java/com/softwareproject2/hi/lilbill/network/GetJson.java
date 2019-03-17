//package com.softwareproject2.hi.lilbill.network;
//
//
//import android.app.Application;
//import android.content.res.Resources;
//import android.util.Log;
//import android.widget.TextView;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.softwareproject2.hi.lilbill.MainActivity;
//import com.softwareproject2.hi.lilbill.R;
//
//import org.json.JSONObject;
//
//
//public getJson extends Application {
//
//    final String url = "https://apis.is/concerts";
////    RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
//
//
//    // prepare the Request
//    JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//            new Response.Listener<JSONObject>()
//            {
//                @Override
//                public void onResponse(JSONObject response) {
//                    String json = String.valueOf(response);
//
//                    String textView = getResources().getString(R.string.json_response, json);
//                }
//            },
//            new Response.ErrorListener()
//            {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.d("Error.Response", String.valueOf(error));
//                }
//            }
//    );
//
//// add it to the RequestQueue
//
////queue.add(getRequest);
//}
