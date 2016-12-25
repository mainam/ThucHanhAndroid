package ngocnam.info.demosharepreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = (TextView) findViewById(R.id.tvName);
        SharedPreferences sharedPreferences = getSharedPreferences("myapplication",MODE_PRIVATE);


//        sharedPreferences.edit().putString("NAME","Mai Ngoc Nam").commit();


        Toast.makeText(this,sharedPreferences.getString("NAME",""),Toast.LENGTH_LONG).show();
        tvName.setText(sharedPreferences.getString("NAME","nam2"));

    }
}
