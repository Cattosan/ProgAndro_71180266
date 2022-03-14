package id.ac.ukdw.pertemuan5_71180266

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import id.ac.ukdw.pertemuan5_latihan.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userHellow = intent.getStringExtra("username")
        val textSay = findViewById<TextView>(R.id.txtHelllowUser)
        textSay.text = "Selamat Datang ${userHellow}!!!"
    }
}