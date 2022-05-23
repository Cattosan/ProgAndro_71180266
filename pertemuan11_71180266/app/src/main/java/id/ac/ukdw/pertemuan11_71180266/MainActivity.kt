package id.ac.ukdw.pertemuan11_71180266

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etNim: EditText
    private lateinit var etIPK: EditText
    private lateinit var btnSimpan: Button
    private lateinit var listMhs: ListView
    private lateinit var ref: DatabaseReference
    private lateinit var mhsList : MutableList<Mahasiswa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("mahasiswa")

        etNama = findViewById(com.google.firebase.database.R.id.txtNama)
        etNim = findViewById(com.google.firebase.database.R.id.et_asal)
        etIPK = findViewById(com.google.firebase.database.R.id.et_asal)
        btnSimpan = findViewById(com.google.firebase.database.R.id.btn_simpan)
        listMhs = findViewById(com.google.firebase.database.R.id.lvDataWarga)
        btnSimpan.setOnClickListener(this)

        mhsList = mutableListOf()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    mhsList.clear()
                    for (h in snapshot.children){
                        val warga = h.getValue(Mahasiswa::class.java)
                        if (warga != null) {
                            mhsList.add(warga)
                        }
                    }

                    val adapter = MahasiswaAdapter(this@MainActivity, com.google.firebase.database.R.layout.mahasiswa_item, mhsList)
                    listMhs.adapter = adapter
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun onClick(v: View?) {
        saveData()
    }

    private fun saveData() {
        val name = etNama.text.toString().trim()
        val nim = etNim.text.toString().trim()
        val IPK = etIPK.text.toString().trim()

        if (name.isEmpty()) {
            etNama.error = "Tolong ketik nama anda"
            return
        }

        if (nim.isEmpty()) {
            etNim.error = "Tolong ketik nim anda"
            return
        }

        if (IPK.isEmpty()) {
            etIPK.error = "Tolong ketik IPK anda"
            return
        }

        val mhsnim = ref.push().key
        val wrg = Mahasiswa(mhsnim!!,name,IPK)

        ref.child(mhsnim).setValue(wrg).addOnCompleteListener {
            Toast.makeText(applicationContext, "Data berhasil di tambahkan", Toast.LENGTH_SHORT).show()
        }
    }
}