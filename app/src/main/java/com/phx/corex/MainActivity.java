package com.phx.corex;
import android.os.Bundle; import android.view.View; import android.widget.Button; import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(v -> Toast.makeText(this, "¡Funciona!", Toast.LENGTH_SHORT).show());
    }
}
