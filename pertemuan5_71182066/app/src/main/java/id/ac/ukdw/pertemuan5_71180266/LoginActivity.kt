package id.ac.ukdw.pertemuan5_71180266

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.ac.ukdw.pertemuan5_latihan.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

          val txtUsernameVal: EditText = findViewById(R.id.txtEmail) //(way 1)
    //    val txtUsernameVal2= findViewById<EditText>(R.id.txtEmail) //(way 2)
          val txtPasswordVal: EditText = findViewById(R.id.txtPassword)
          val btnLoginVal = findViewById<Button>(R.id.btnSignIn)
            btnLoginVal.setOnClickListener {
                loginengine(txtUsernameVal.text.toString(), txtPasswordVal.text.toString())
            }
    }

    fun loginengine(username: String, passphrase: String){
        if(passphrase.equals("12345")){ //pindah intent: 1. tentukan pengirim dan penerima, 2. kirim paketnya (bentuknya intent)
            val intenz: Intent = Intent(this, MainActivity::class.java) //param1 = pengirim , //param2 = penerima
            intenz.putExtra("username",username)
            startActivity(intenz)
        }
        else{
            toasterroaster("Password Salah")
        }
    }

    fun toasterroaster(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}