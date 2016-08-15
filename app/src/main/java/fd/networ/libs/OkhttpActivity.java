package fd.networ.libs;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {

    private TextView tvTest;
    private ProgressDialog progressDialog;
    public static final MediaType JSON  = MediaType.parse("application/json; charset=utf-8");
    public String json = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = (TextView) findViewById(R.id.tvTest);
        progressDialog = new ProgressDialog(this);
        JSONObject jObject = new JSONObject();
        try {
            jObject.put("success", "true");
            jObject.put("message", "Behasil Mendapat Data Dari Server");
        } catch (JSONException e) {
            showMessage("JSON Error");
        }
        Log.i("json", jObject.toString());
        json = jObject.toString();
        new BackgroundTask().execute("http://firdaus91.web.id/test/raw.php");
    }

    private class BackgroundTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            Response response = null;
            OkHttpClient client = new OkHttpClient();
            //RequestBody formBody = new FormBody.Builder().addEncoded(JSON, url[1]).build();
            RequestBody formBody = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(url[0]).post(formBody)
                   .build();
            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("OkHttp", result);
            progressDialog.dismiss();
            if (!result.equals("")) {
                showMessage("Berhasil Mendapat Data Dari Server");
                tvTest.setText(result);
            } else {
                showMessage("Jaringan Bermasalah");
            }
        }
    }

    private void showMessage(String message) {
        new AlertDialog.Builder(this).setMessage(message).setCancelable(true)
                .show();
    }
}