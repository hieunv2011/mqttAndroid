package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String BROKER_URL = "tcp://103.1.238.175:1883";
    private static final String CLIENT_ID = "android";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "testadmin";
    private MqttHandler mqttHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL, CLIENT_ID, USERNAME, PASSWORD);

        // Tìm các nút và gán sự kiện bấm nút
        Button buttonPublish = findViewById(R.id.buttonPublish);
        buttonPublish.setOnClickListener(view -> publishMessage("button", "Android"));

        Button buttonG1 = findViewById(R.id.buttonG1);
        buttonG1.setOnClickListener(view -> publishMessage("button", "G1"));

        Button buttonG2 = findViewById(R.id.buttonG2);
        buttonG2.setOnClickListener(view -> publishMessage("button", "G2"));

        Button buttonG3 = findViewById(R.id.buttonG3);
        buttonG3.setOnClickListener(view -> publishMessage("button", "G3"));
    }

    @Override
    protected void onDestroy() {
        mqttHandler.disconnect();
        super.onDestroy();
    }

    // Hàm xuất bản thông điệp lên một chủ đề
    private void publishMessage(String topic, String message){
//        Toast.makeText(this, "Publishing message: " + message, Toast.LENGTH_SHORT).show();
        mqttHandler.publish(topic, message);  // Gửi thông điệp lên topic tương ứng
    }

    // Hàm đăng ký (subscribe) để nhận thông điệp từ một chủ đề
    private void subscribeToTopic(String topic){
        Toast.makeText(this, "Subscribing to topic "+ topic, Toast.LENGTH_SHORT).show();
        mqttHandler.subscribe(topic);  // Đăng ký nhận thông điệp từ topic
    }
}
