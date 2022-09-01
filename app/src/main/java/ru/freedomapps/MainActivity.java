package ru.freedomapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        if (preferences.getString("auth", "null").equals("null")) {
            button.setText("Зарегистрироваться");
            editText.setHint("Задайте кодовое слово");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("auth", editText.getText().toString());
                    editor.apply();
                    startActivity(new Intent(MainActivity.this, ListActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                }
            });
        } else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (editText.getText().toString().equals(preferences.getString("auth", "null"))) {
                        startActivity(new Intent(MainActivity.this, ListActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    } else {
                        Toast.makeText(MainActivity.this, "Неправильный код", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void init() {
        button = findViewById(R.id.auth);
        editText = findViewById(R.id.code);
        preferences = getSharedPreferences("auth", MODE_PRIVATE);
    }
}