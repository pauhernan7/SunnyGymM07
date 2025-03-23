package com.example.sunnygym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerConfirmButton = findViewById<Button>(R.id.registerConfirmButton)
        val backButton = findViewById<Button>(R.id.backButton)

        registerConfirmButton.setOnClickListener {
            val intent = Intent(this@RegisterActivity, RegistrationSuccessActivity::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
