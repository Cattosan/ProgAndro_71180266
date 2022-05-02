package id.ac.ukdw.pertemuan9_71180266

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.FieldPosition

class LoginActivity : AppCompatActivity() {
    var sp: SharedPreferences? = null
    var spEdit: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        supportActionBar?.hide()

        sp = getSharedPreferences("custsp", MODE_PRIVATE)
        spEdit = sp?.edit()

        if(sp?.getBoolean("isLogin", false) == true){
            setContentView(R.layout.activity_main)
            val btnlgout = findViewById<Button>(R.id.btnLogOut)

            val spinlangnewpos = sp!!.getInt("langspin", -1)
            val spinfontnewpos = sp!!.getInt("fontspin", -1)


            btnlgout.setOnClickListener{
                logout()
            }

            //LANGUAGE SPINNER
            val langs = resources.getStringArray(R.array.lang)
            val spinnerlangs = findViewById<Spinner>(R.id.spinnerlang)
            if(spinnerlangs != null){
                val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, langs)
                spinnerlangs.adapter = adapter
            }

            spinnerlangs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                    Toast.makeText(this@LoginActivity,getString(R.string.langchoice) + " " + "" + langs[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

            spinnerlangs.setSelection(spinlangnewpos)

            val langusrchoice = findViewById<Spinner>(R.id.spinnerfont)

            val spinlangpos = spinnerlangs.selectedItemPosition
            saveSpinnerPos("langspin", spinlangpos)



            //FONT SPINNER
            val fonts = resources.getStringArray(R.array.font)
            val spinnerfonts = findViewById<Spinner>(R.id.spinnerfont)
            if(spinnerfonts != null){
                val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, langs)
                spinnerfonts.adapter = adapter
            }

            spinnerfonts.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                    Toast.makeText(this@LoginActivity,getString(R.string.fontchoice) + " " + "" + langs[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

            spinnerfonts.setSelection(spinfontnewpos)

            val fontusrchoice = findViewById<Spinner>(R.id.spinnerfont)

            val spinfontpos = spinnerlangs.selectedItemPosition
            saveSpinnerPos("fontspin", spinfontpos)
        }
        else{
            setContentView(R.layout.login_page)
            val edusr = findViewById<EditText>(R.id.edusrmn)
            val passw = findViewById<EditText>(R.id.edpsswd)
            val btnlg = findViewById<Button>(R.id.btnLogIn)

            btnlg.setOnClickListener{
                login(edusr.text.toString(),passw.text.toString())
            }
        }
    }

    fun saveSpinnerPos(spntype: String, position: Int){
        spEdit?.putInt(spntype, position)
        spEdit?.commit()
    }

    fun login(usrnm: String, passwd: String){
        if(usrnm.equals("admin") && passwd.equals("kaito1412")){
            spEdit?.putBoolean("isLogin", true)?.commit()
            val intnt = Intent(this, LoginActivity::class.java)
            startActivity(intnt)
            finish()
        }
        else{
            Toast.makeText(this, "Username / Password yang anda masukkan, tidak dapat kami temukan dalam database kami", Toast.LENGTH_LONG).show()
        }
    }

    fun logout(){
        spEdit?.putBoolean("isLogin", false)?.commit()
        val intnt = Intent(this, LoginActivity::class.java)
        startActivity(intnt)
        finish()
    }
}
