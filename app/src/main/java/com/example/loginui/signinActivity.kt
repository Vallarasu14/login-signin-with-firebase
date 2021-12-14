package com.example.loginui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class signinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val buttonsignup:Button=findViewById(R.id.buttonsignup)
        auth = FirebaseAuth.getInstance();
        buttonsignup.setOnClickListener {
        signupUser()
        }

    }

    private fun signupUser(){

        val username:EditText=findViewById(R.id.usernameEt)
        val email:EditText=findViewById(R.id.emailET)
        val password:EditText=findViewById(R.id.passwordET)
        if(username.text.toString().isEmpty()) {
            username.error="Please Enter The Username"
            username.requestFocus()
            return
        }

        if(email.text.toString().isEmpty()) {

            email.error="Please Enter Your email Adress"
            email.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {

            email.error="Please Enter A Valid Email"
            email.requestFocus()
            return

        }

        if(password.text.toString().isEmpty()) {

            password.error= "Please Enter The Password"
            password.requestFocus()
            return
        }



        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext,"Successfully loggedIn",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,Dashboard::class.java))

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }

    }


}