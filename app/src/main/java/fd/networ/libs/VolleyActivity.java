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
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class VolleyActivity extends AppCompatActivity {

    private TextView tvTest;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        tvTest = (TextView) findViewById(R.id.tvTest);
        JSONObject jObject = new JSONObject();
        try {
            jObject.put("success", "true");
            jObject.put("message", "Behasil Mendapat Data Dari Server");
        } catch (JSONException e) {
            showMessage("JSON Error");
        }
        Log.i("json", jObject.toString());
        VolleyRawPOST("http://firdaus91.web.id/test/raw.php", jObject);
    }

    private void VolleyRawPOST(String url, JSONObject json) {

        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Jika Sukses Mendapat Data Dari Server
                        progressDialog.dismiss();
                        Log.i("json", response.toString());
                        tvTest.setText(response.toString());
                        showMessage("Berhasil Mendapat Data Dari Server");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Jika Gagal Mendapat Data Dari Server
                progressDialog.dismiss();
                Log.i("json", error.toString());
                showMessage("Gagal Mendapat Data Dari Server");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

    private void showMessage(String message) {
        new AlertDialog.Builder(this).setMessage(message).setCancelable(true)
                .show();
    }
}