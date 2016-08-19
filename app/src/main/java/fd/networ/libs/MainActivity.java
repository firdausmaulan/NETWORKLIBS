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

        Button btnUrlConnGet = (Button) findViewById(R.id.btn_urlcon_get);
        btnUrlConnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UrlConnectionGet.class);
                startActivity(i);
            }
        });

        Button btnVolleyGet = (Button) findViewById(R.id.btn_volley_get);
        btnVolleyGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VolleyGet.class);
                startActivity(i);
            }
        });

        Button btnOkhttpGet = (Button) findViewById(R.id.btn_okhttp_get);
        btnOkhttpGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OkhttpGet.class);
                startActivity(i);
            }
        });

        Button btnUrlConnPost = (Button) findViewById(R.id.btn_urlcon_post);
        btnUrlConnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UrlConnectionPost.class);
                startActivity(i);
            }
        });

        Button btnVolleyPost = (Button) findViewById(R.id.btn_volley_post);
        btnVolleyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VolleyPost.class);
                startActivity(i);
            }
        });

        Button btnOkhttpPost = (Button) findViewById(R.id.btn_okhttp_post);
        btnOkhttpPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OkhttpPost.class);
                startActivity(i);
            }
        });

        Button btnUrlConnJson = (Button) findViewById(R.id.btn_urlcon_json);
        btnUrlConnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UrlConnectionPostJson.class);
                startActivity(i);
            }
        });

        Button btnVolleyJson = (Button) findViewById(R.id.btn_volley_json);
        btnVolleyJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VolleyPostJson.class);
                startActivity(i);
            }
        });

        Button btnOkhttpJson = (Button) findViewById(R.id.btn_okhttp_json);
        btnOkhttpJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OkhttpPostJson.class);
                startActivity(i);
            }
        });
    }
}
