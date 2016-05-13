package blackriders.listviewwithimages;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    ListView list_item;
    ProgressDialog dialog;
    String url = "http://mobile.betfan.com/api/?action=v2list&device=de3b5e2d7f401829ea54fd5aca9d2fa92deb65c1b9b6b7729b3c3c8df11e75f4&key=MEu07MgiuWgXwJOo7Oe1aHL0yM8VvP&userID=2385&userkey=d69b87b2c0ad828bf7c0c30b83ea0c11";
    private List<Events> eventsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventsList = new ArrayList<Events>();
        list_item = (ListView) findViewById(R.id.list_item);
        getData();
    }

    private void getData() {

        String tag_string_req = "req_login";
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait ...");
        dialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(TAG, "Response: " + response.toString());
                Log.i(TAG, "URL " + url);
                dialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    if (status == 200) {
                        JSONArray array = jsonObject.getJSONArray("response");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            String platform, logo, name, oppid, hadtip;
                            platform = object.getString("platform");
                            logo = object.getString("logo");
                            name = object.getString("name");
                            oppid = object.getString("oppid");
                            hadtip = object.getString("hadtip");

                            Events events = new Events(platform, logo, name, oppid, hadtip);
                            eventsList.add(events);
                            list_item.setAdapter(new EventAdapter(MainActivity.this,eventsList));

                        }
                    }
                } catch (Exception e) {

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                dialog.dismiss();

            }
        });
        strReq.setRetryPolicy(new RetryPolicy() {

            @Override
            public void retry(VolleyError arg0) throws VolleyError {
                // TODO Auto-generated method stub
                Log.e("", "RE-TRY -: " + arg0);
            }

            @Override
            public int getCurrentTimeout() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public int getCurrentRetryCount() {
                // TODO Auto-generated method stub
                return 0;
            }
        });
        strReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}
