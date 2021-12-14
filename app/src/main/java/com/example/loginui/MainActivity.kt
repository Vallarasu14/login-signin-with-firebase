package com.example.loginui

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import com.example.loginui.R.id.new_email
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonlogin: Button = findViewById(R.id.buttonlogin)

        auth = FirebaseAuth.getInstance();

        val tvsignin: TextView = findViewById(R.id.tvsignin)

        tvsignin.setOnClickListener {
            startActivity(Intent(this, signinActivity::class.java))
        }

        buttonlogin.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                doLogin()
            }
        }
    }

    private fun doLogin() {

        val email: EditText = findViewById(new_email)
        val password: EditText = findViewById(R.id.password)

        if (email.text.toString().isEmpty()) {

            email.error = "Please Enter Your email Adress"
            email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {

            email.error = "Please Enter A Valid Email"
            email.requestFocus()
            return

        }
        if (password.text.toString().isEmpty()) {

            password.error = "Please Enter The Password"
            password.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(baseContext, "Succesfully LoggedIn", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Dashboard::class.java))
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = auth.currentUser
        updateUi(currentUser)
    }

    fun updateUi(currentUser: FirebaseUser?) {

    }

}

