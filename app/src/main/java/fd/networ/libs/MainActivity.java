package fd.networ.libs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTest;
    private String json = "";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnUrlConn = (Button) findViewById(R.id.btn_urlcon);
        btnUrlConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UrlConnectionActivity.class);
                startActivity(i);
            }
        });

        Button btnVolley = (Button) findViewById(R.id.btn_volley);
        btnVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VolleyActivity.class);
                startActivity(i);
            }
        });

        Button btnOkhttp = (Button) findViewById(R.id.btn_okhttp);
        btnOkhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OkhttpActivity.class);
                startActivity(i);
            }
        });
    }
}
