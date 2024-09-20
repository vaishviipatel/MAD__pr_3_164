package com.example.mad_3

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginButton = findViewById<Button>(R.id.loginbtn)
        loginButton.setOnClickListener {
            Intent(this, LoginActivity::class.java)
                .putExtra("username", "vaishvi")
                .putExtra("password", "123")
                .also {
                    startActivity(it)
                }

        }
        implicitIntent()
    }
    fun implicitIntent(){
        findViewById<Button>(R.id.browse).setOnClickListener {
            Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com")).also { startActivity(it) }
        }

        /* findViewById<Button>(R.id.phonebtn).setOnClickListener{
             val num = findViewById<EditText>(R.id.phoneInput).text.toString()
             Intent(Intent.ACTION_DIAL).setData(Uri.parse(num)).also {
                 startActivity(it)
             }
         }*/
        findViewById<Button>(R.id.phonebtn).setOnClickListener {
            val num = findViewById<EditText>(R.id.phoneInput).text.toString().trim()
            if (num.isNotEmpty()) {
                Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:$num")).also {
                    startActivity(it)
                }
            } else {
                Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.callbtn).setOnClickListener {
            Intent(Intent.ACTION_VIEW).setType(CallLog.Calls.CONTENT_TYPE).also {
                startActivity(it)
            }
        }
        findViewById<Button>(R.id.gallerybtn).setOnClickListener {
            Intent(Intent.ACTION_VIEW).setType("image/*").also{
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.camerabtn).setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{
                startActivity(it)
            }
        }
        findViewById<Button>(R.id.alarmbtn).setOnClickListener {
            Intent(AlarmClock.ACTION_SHOW_ALARMS).also{
                startActivity(it)
            }
        }




    }
}

