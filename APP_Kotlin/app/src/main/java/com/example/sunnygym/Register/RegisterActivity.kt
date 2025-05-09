package com.example.sunnygym.Register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sunnygym.R

class RegisterActivity : AppCompatActivity() {
    private val viewModel: RegistreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerConfirmButton = findViewById<Button>(R.id.registerConfirmButton)
        val backButton = findViewById<Button>(R.id.backButton)

        val usernameInput = findViewById<EditText>(R.id.nameInput)
        val emailInput = findViewById<EditText>(R.id.registerEmailInput)
        val passwordInput = findViewById<EditText>(R.id.registerPasswordInput)
        val repeatPasswordInput = findViewById<EditText>(R.id.RepeatPasswordInput)


        val usernameLayout = findViewById<TextInputLayout>(R.id.usernameInputLayout)
        val emailLayout = findViewById<TextInputLayout>(R.id.emailInputLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordInputLayout)


        registerConfirmButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            viewModel.actualitzanomUsuari(username)
            viewModel.actualitzaemail(email)
            viewModel.actualitzacontrassenya(password)
            viewModel.actualitzarepetircontrasenya(repeatPasswordInput.text.toString())
            viewModel.comprovadadesusuari()
        }

        backButton.setOnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
        }

        viewModel.formularivalid.observe(this) { valid ->
            if (valid) {
                val intent = Intent(this@RegisterActivity, RegistrationSuccessActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel.errorNomUsuari.observe(this) { error ->
            usernameLayout.error = if (error.isEmpty()) null else error
        }

        viewModel.errorEmail.observe(this) { error ->
            emailLayout.error = if (error.isEmpty()) null else error
        }

        viewModel.errorContrassenya.observe(this) { error ->
            passwordLayout.error = if (error.isEmpty()) null else error
        }
    }
}