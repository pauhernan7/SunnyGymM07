package com.example.sunnygym.Register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sunnygym.R

class RegistrationSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_succes)

        val backToLoginButton = findViewById<Button>(R.id.backToLoginButton)

        backToLoginButton.setOnClickListener {
            val intent = Intent(this@RegistrationSuccessActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
