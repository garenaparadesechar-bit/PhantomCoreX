package com.phx.corex;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
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

        // Leer Batería
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(null, ifilter);
        int level = -1;
        int scale = -1;
        if (batteryStatus != null) {
            level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        }
        float batteryPct = level / (float) scale * 100;
        
        // Leer RAM
        ActivityManager actManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long totalMem = memInfo.totalMem / (1024 * 1024);
        long availMem = memInfo.availMem / (1024 * 1024);

        // Actualizar textos
        TextView tvInfo = findViewById(R.id.tv_info);
        tvInfo.setText(String.format("Batería: %.0f%%\nRAM: %dMB / %dMB", batteryPct, availMem, totalMem));

        // Botón Turbo
        Button btnTurbo = findViewById(R.id.btn_turbo);
        btnTurbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "¡TURBO CORE ACTIVADO!\nSistema optimizado", Toast.LENGTH_LONG).show();
            }
        });
    }
}
