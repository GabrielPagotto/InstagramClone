package com.example.instagramclone.ui

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.instagramclone.R
import com.example.instagramclone.models.User
import com.example.instagramclone.utils.FirebaseConfiguration
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var usernameField: EditText
    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var registerButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var firebaseAuth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initComponents()

        progressBar.visibility = View.GONE;

        registerButton.setOnClickListener { view: View -> registerUser(view)}
    }

    private fun initComponents() {
        usernameField = findViewById(R.id.registerUsernameField)
        emailField = findViewById(R.id.registerEmailField)
        passwordField = findViewById(R.id.registerPasswordField)
        registerButton = findViewById(R.id.registerButton)
        progressBar = findViewById(R.id.registerProgressBar)
    }

    private fun  changeProgressBarVisibility(visible: Boolean) {
        if (!visible) {
            registerButton.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        } else {
            registerButton.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun registerUser(view: View) {
        val usernameText: String? = usernameField.text?.toString()
        val emailText: String? = emailField.text?.toString()
        val passwordText: String? = passwordField.text?.toString()

        if (usernameText.isNullOrEmpty()) {
            Toast.makeText(this, "Informe um nome de usuário", Toast.LENGTH_SHORT).show()
        } else {
            if (emailText.isNullOrEmpty()) {
                Toast.makeText(this, "Informe um email", Toast.LENGTH_SHORT).show()
            } else {
                if (passwordText.isNullOrEmpty()) {
                    Toast.makeText(this, "Informe uma senha", Toast.LENGTH_SHORT).show()
                } else {
                    changeProgressBarVisibility(true)

                    val user: User = User();

                    user.username = usernameText
                    user.email = emailText
                    user.password = passwordText

                    firebaseAuth = FirebaseConfiguration.getFirebaseAuth()
                    firebaseAuth.createUserWithEmailAndPassword(
                        user.email as String,
                        user.password as String
                    ).addOnCompleteListener { task: Task<AuthResult> ->  onFirebaseCreateComplete(task)}
                }
            }
        }
    }

    private fun onFirebaseCreateComplete(task: Task<AuthResult>) {
        changeProgressBarVisibility(false)

        if (task.isSuccessful) {
            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()

            val mainIntent: Intent = Intent(this, MainActivity::class.java)

            startActivity(mainIntent)
            finish()
        } else {
            Toast.makeText(this, "Ocorreu um erro, verifique os dados e tente novamente", Toast.LENGTH_SHORT).show()
        }
    }
}
