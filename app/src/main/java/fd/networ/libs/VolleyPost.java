package fd.networ.libs;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class VolleyPost extends AppCompatActivity {

    private TextView tvTest;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        tvTest = (TextView) findViewById(R.id.tvTest);
        VolleyPOST("http://firdaus91.web.id/test/post.php");
    }

    private void VolleyPOST(String url) {

        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Jika Sukses Mendapat Data Dari Server
                        progressDialog.dismiss();
                        Log.i("response", response);
                        tvTest.setText(response);
                        showMessage("Berhasil Mendapat Data Dari Server");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Jika Gagal Mendapat Data Dari Server
                        progressDialog.dismiss();
                        Log.i("error", error.toString());
                        showMessage("Gagal Mendapat Data Dari Server");
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username","maulana");
                params.put("password","firdaus");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showMessage(String message) {
        new AlertDialog.Builder(this).setMessage(message).setCancelable(true)
                .show();
    }
}