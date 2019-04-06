package com.softwareproject2.hi.lilbill;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import android.net.ConnectivityManager;g
import android.net.NetworkInfo;
import android.view.View;

import com.google.android.gms.common.SignInButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;
import com.softwareproject2.hi.lilbill.features.transaction.TransactionConstructionActivity;
import com.softwareproject2.hi.lilbill.features.transaction.TransactionFragment;

import java.io.IOException;
import java.util.UUID;


public class MainActivity extends SingleFragmentActivity {


    public static final String TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_TRANSACTION_ID =
            "com.softwareproject2.hi.lilbill.transaction_id";

    public static Intent newIntent(Context packageContext, UUID transactionID) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_TRANSACTION_ID, transactionID);
        return intent;
    }

    protected Fragment createFragment() {
        return new TransactionFragment();

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FloatingActionButton createNewTransaction = findViewById(R.id.new_transaction_fab);
        createNewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TransactionConstructionActivity.class));
            }
        });


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

                        jsonToObject(jsonData);

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

    public void jsonToObject(String json) {
        /**
         * Þetta fall á að geta verið notað til að breyta json yfir í Transaction object
         */

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Transaction transaction = new Transaction();
        transaction = gson.fromJson(String.valueOf(json), Transaction.class);

        Log.i(TAG, String.valueOf(transaction));

    }

    public  void objectToJson(Transaction transaction) {
        /**
         * Þessi aðferð á að breyta java object, í þessu tilfelli Transaction object
         * yfir í json streng.
         */

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String json = gson.toJson(transaction);

        Log.i(TAG, String.valueOf(json));

    }
}
