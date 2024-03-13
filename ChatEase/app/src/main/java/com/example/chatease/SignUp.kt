package com.example.chatease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var edt_name: EditText
    private lateinit var edt_Email: EditText
    private lateinit var edt_Password: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edt_name = findViewById(R.id.edt_name)
        edt_Email =findViewById(R.id.edt_Email)
        edt_Password =findViewById(R.id.edt_Password)
        btnSignUp =findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val name = edt_name.text.toString()
            val email = edt_Email.text.toString()
            val password = edt_Password.text.toString()

            signup(name,email,password)
        }

    }

    private fun signup(name:String, email: String, password: String){
        //logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jumping to home
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)

                    val intent = Intent(this@SignUp, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@SignUp, "some error occurred", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun addUserToDatabase(name:String, email:String, uid:String){
       mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email,uid))

    }





}