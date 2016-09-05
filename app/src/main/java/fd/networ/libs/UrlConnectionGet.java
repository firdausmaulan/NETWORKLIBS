package fd.networ.libs;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import fd.networ.libs.agent.HttpListener;
import fd.networ.libs.agent.HttpRequest;

public class UrlConnectionGet extends AppCompatActivity implements HttpListener {

    private TextView tvTest;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = (TextView) findViewById(R.id.tvTest);
        pDialog = ProgressDialog.show(UrlConnectionGet.this, "",
                "Loading...", true);
        HttpRequest.instance().request(pDialog, UrlConnectionGet.this,
                "http://firdaus91.web.id/test/get.php?username=maulana&password=firdaus", "", 0);
    }

    @Override
    public int onHttpResponse(int code, String msg) {
        pDialog.dismiss();
        Log.i("msg", msg);
        showMessage("Successfully Got Data From Server");
        tvTest.setText(msg);
        return 0;
    }

    @Override
    public void onError(int code, String desc) {
        pDialog.dismiss();
        showMessage(desc);
    }

    @Override
    public int onHttpAuthorized(int code) {
        // TODO Auto-generated method stub
        return 0;
    }

    private void showMessage(String message) {
        new AlertDialog.Builder(this).setMessage(message).setCancelable(true)
                .show();
    }
}