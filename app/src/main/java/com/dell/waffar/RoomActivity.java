package com.dell.waffar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RoomActivity extends AppCompatActivity {

    TextView bot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        bot = findViewById(R.id.bot);

        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
    }
}
