package com.softwareproject2.hi.lilbill;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
      //Create and commit a fragment transaction
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new TransactionFragment();
            //creates and returns an instance of FragmentTransaction
            //Then we can chain fragments together
            fm.beginTransaction()
                    //(container view id, fragment)
                    //container view id tells where the fragments view should appear - unique identifyer
                    //this is the resource ID of the container view ID
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        getData();
    }

    private void getData() {

        /* Strengur sem er url sem samsvarar hvert á að sækja gögn
           urlið væri root/user/json eða eh álíka, þar sem user er sá sem er logged in
           og json er þá json viewið.
        */
        String url = "https://apis.is/concerts";

        if (isNetworkAvailable()) {

            // Establish connection
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    /**
                     * Ef request failar.
                     */
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "failed!");
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    /**
                     * Ef request gefur response.
                     */

                    try {
                        // Ná í Streng úr json
                        final String jsonData = response.body().string();

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                /**
                                 * Hér mun það sem gerist í UI þræði keyra. Eins og að uppfæra view.
                                 */

//                                Resources res = getResources();
//                                TextView textView = findViewById(R.id.json_view);
//                                String text = String.format(res.getString(R.string.json_response), jsonData);
//                                textView.setText(text);
                            }
                        });

                        Log.v(TAG, jsonData);

                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
    }

    private boolean isNetworkAvailable() {
        /**
         * Fall sem athugar að allt sé í lagi með network manager.
         */
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo!= null && networkInfo.isConnected()) isAvailable = true;
        return isAvailable;
    }
}
