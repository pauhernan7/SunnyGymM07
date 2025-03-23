package com.example.sunnygym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class QRCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)

        val backToProfileButton = findViewById<Button>(R.id.backToProfileButton)

        // Botón para regresar al perfil
        backToProfileButton.setOnClickListener {
            val intent = Intent(this@QRCodeActivity, UserProfileActivity::class.java)
            startActivity(intent)
        }
    }
}