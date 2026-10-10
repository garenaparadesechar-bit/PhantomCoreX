package com.phx.corex;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bottom_nav);
        nav.setOnItemSelectedListener(item -> {
            Fragment f = null;
            if (item.getItemId() == R.id.nav_home) f = new HomeFragment();
            else if (item.getItemId() == R.id.nav_games) f = new GamesFragment();
            else if (item.getItemId() == R.id.nav_console) f = new ConsoleFragment();
            else if (item.getItemId() == R.id.nav_settings) f = new SettingsFragment();
            
            if (f != null) getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).commit();
            return true;
        });
        
        if (savedInstanceState == null) nav.setSelectedItemId(R.id.nav_home);
    }
}
