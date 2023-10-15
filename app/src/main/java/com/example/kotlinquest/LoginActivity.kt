package com.example.kotlinquest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinquest.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // When there's event click button, the checkCredentials() method will compare the acc
        // and proceed if verified, and vice versa
        binding.btnLogin.setOnClickListener{
            val username = binding.user.text.toString()
            val password = binding.password.text.toString()

            if (checkCredentials(username, password)) {
                val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }

        }
    }

    // Check the acc credentials based on the SharedPreference API
    private fun checkCredentials(username: String, password: String): Boolean {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")

        return username == savedUsername && password == savedPassword
    }
}