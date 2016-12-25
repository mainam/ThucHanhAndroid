package ngocnam.info.demobroadcastreceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManHinh2 extends AppCompatActivity {

    private Button btnSendBroadcast;
    public final String ACTION_BROADCAST = "ngocnam.info.demobroadcastreceiver.ACTION_SEND_PROFILE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh2);
        btnSendBroadcast = (Button) findViewById(R.id.btnSendBroadcast);
        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(ACTION_BROADCAST);
                intent.putExtra("NAME", "Mai Ngoc Nam");
                sendBroadcast(intent);
            }
        });

    }
}
