package com.phx.corex;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvInfo = findViewById(R.id.tv_info);
        tvInfo.setText("¡PHX compilado exitosamente!");

        Button btnTurbo = findViewById(R.id.btn_turbo);
        btnTurbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "¡TURBO CORE activado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
