package com.example.zumda

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        findViewById<View>(R.id.btnLogin).setOnClickListener { loginUser() }
        findViewById<View>(R.id.tvRegisterLink).setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    RegisterActivity::class.java
                )
            )
        }
    }

    private fun loginUser() {

        val emaill = etEmail!!.text.toString().trim { it <= ' ' }
        val passwordd = etPassword!!.text.toString().trim { it <= ' ' }
        if (emaill.isEmpty()) {
            etEmail!!.error = "email is required"
            etEmail!!.requestFocus()
            return
        } else if (passwordd.isEmpty()) {
            etPassword!!.error = "Password is required"
            etPassword!!.requestFocus()
            return
        }
        val call = RetrofitClient.instance?.aPI?.checkUser(User(email = String(), password = String()))
        call!!.enqueue(object : Callback<ResponseBody?> {override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                var s = ""
                try {
                    if (response.body() != null) {
                        Log.d("TAG", "onResponse: ${response.body().toString()}")
                        s = response.body()!!.string()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (s.) {
                    startActivity(
                        Intent(
                            this@LoginActivity,
                            DashboardActivity::class.java
                        ).putExtra("email", emaill)
                    )


                    Toast.makeText(this@LoginActivity, "User logged in!", Toast.LENGTH_LONG).show()
                } else {

                    Toast.makeText(
                        this@LoginActivity,
                        "Incorrect Credentials! Try again!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}