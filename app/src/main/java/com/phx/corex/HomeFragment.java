package com.phx.corex;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;

public class HomeFragment extends Fragment {
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        
        // Batería
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = requireContext().registerReceiver(null, ifilter);
        int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;
        float batteryPct = level / (float) scale * 100;
        ((TextView) v.findViewById(R.id.tv_battery)).setText("Batería: " + (int)batteryPct + "%");

        // RAM
        ActivityManager actManager = (ActivityManager) requireContext().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long totalMem = memInfo.totalMem / (1024 * 1024);
        long availMem = memInfo.availMem / (1024 * 1024);
        ((TextView) v.findViewById(R.id.tv_ram)).setText("RAM: " + availMem + "MB libres / " + totalMem + "MB total");

        // Botón Turbo
        MaterialButton btnTurbo = v.findViewById(R.id.btn_turbo);
        btnTurbo.setOnClickListener(view -> {
            Toast.makeText(requireContext(), "¡TURBO CORE ACTIVADO!", Toast.LENGTH_LONG).show();
            // Aquí podríamos agregar lógica para limpiar caché o matar procesos en el futuro
        });
    }
}
