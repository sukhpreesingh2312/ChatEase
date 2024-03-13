package com.example.chatease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edt_Email: EditText
    private lateinit var edt_Password: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edt_Email =findViewById(R.id.edt_Email)
        edt_Password =findViewById(R.id.edt_Password)
        btnLogin =findViewById(R.id.btnLogin)
        btnSignUp =findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val email= edt_Email.text.toString()
            val password = edt_Password.text.toString()

            login(email,password)
        }



    }


    private fun login(email: String, password: String){
        //logic of logging in user

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code for logging in User
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
                }
            }
    }
}