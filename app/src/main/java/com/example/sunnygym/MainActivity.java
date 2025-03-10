package com.example.sunnygym;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Llamar al endpoint /ping al iniciar la aplicación
        testApiConnection();
    }

    private void testApiConnection() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<PingResponse> call = apiService.ping();

        call.enqueue(new Callback<PingResponse>() {
            @Override
            public void onResponse(Call<PingResponse> call, Response<PingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String message = response.body().getMessage();
                    Log.d(TAG, "Respuesta de la API: " + message);
                    Toast.makeText(MainActivity.this, "API dice: " + message, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "Error en la respuesta: " + response.code());
                    Toast.makeText(MainActivity.this, "Error al conectar con API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PingResponse> call, Throwable t) {
                Log.e(TAG, "Error en la conexión: " + t.getMessage());
                Toast.makeText(MainActivity.this, "No se pudo conectar a la API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
