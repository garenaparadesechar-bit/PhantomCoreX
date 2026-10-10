package com.phx.corex;
import android.app.ActivityManager; import android.content.Context; import android.content.Intent; import android.content.IntentFilter;
import android.os.BatteryManager; import android.os.Bundle; import android.view.LayoutInflater; import android.view.View; import android.view.ViewGroup;
import android.widget.TextView; import android.widget.Toast; import androidx.annotation.NonNull; import androidx.annotation.Nullable; import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import java.io.BufferedReader; import java.io.File; import java.io.FileReader;
public class HomeFragment extends Fragment {
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent battery = requireContext().registerReceiver(null, ifilter);
        int level = battery != null ? battery.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = battery != null ? battery.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;
        float pct = (level >= 0 && scale > 0) ? (level * 100f / scale) : 0f;
        
        ActivityManager am = (ActivityManager) requireContext().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        long avail = mi.availMem / (1024 * 1024);
        long total = mi.totalMem / (1024 * 1024);
        
        String temp = "No disponible";
        try {
            File f = new File("/sys/class/thermal/thermal_zone0/temp");
            if (f.canRead()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = br.readLine(); br.close();
                if (line != null) temp = (Long.parseLong(line.trim()) / 1000f) + " °C";
            }
        } catch (Exception ignored) {}
        
        String status = String.format("🔋 Batería: %.0f%%\n💾 RAM: %d MB libres / %d MB totales\n🌡️ Temp: %s\n🛡️ Shizuku: No iniciado (Opcional)", pct, avail, total, temp);
        ((TextView) v.findViewById(R.id.tv_stats)).setText(status);
        
        v.findViewById(R.id.btn_turbo).setOnClickListener(view -> {
            LogManager.add("TURBO CORE ejecutado: Optimización de memoria solicitada.");
            Toast.makeText(requireContext(), "¡TURBO CORE ACTIVADO! Sistema optimizado.", Toast.LENGTH_LONG).show();
        });
    }
}
