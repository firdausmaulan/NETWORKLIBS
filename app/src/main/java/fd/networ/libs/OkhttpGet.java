package fd.networ.libs;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpGet extends AppCompatActivity {

    private TextView tvTest;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = (TextView) findViewById(R.id.tvTest);
        progressDialog = new ProgressDialog(this);
        new BackgroundTask().execute("http://firdaus91.web.id/test/get.php?username=maulana&password=firdaus");
    }

    private class BackgroundTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            Response response = null;
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(url[0]).build();
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
                showMessage("Successfully Got Data From Server");
                tvTest.setText(result);
            } else {
                showMessage("Not Connect To Server");
            }
        }
    }

    private void showMessage(String message) {
        new AlertDialog.Builder(this).setMessage(message).setCancelable(true)
                .show();
    }
}