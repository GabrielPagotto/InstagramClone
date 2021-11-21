package com.example.instagramclone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.instagramclone.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    public fun openRegistration(view: View) {
        val intent: Intent = Intent(this, RegisterActivity::class.java)
         startActivity(intent)
    }
}
