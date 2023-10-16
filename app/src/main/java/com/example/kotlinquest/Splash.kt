package com.example.kotlinquest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinquest.databinding.SplashBinding

class Splash : AppCompatActivity() {
    private lateinit var binding: SplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val isLoggedIn = checkLoginStatus()

        if (isLoggedIn) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@Splash, LoginActivity::class.java)
                startActivity(intent)
            }, 3000)
            finish()
        }
    }

    private fun checkLoginStatus(): Boolean{
        // Get the data from SharedPreferences API and check the login status
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}