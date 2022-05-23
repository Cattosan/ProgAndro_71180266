package id.ac.ukdw.pertemuan11_71180266

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MahasiswaAdapter (val mCtx: Context, val layoutResId: Int, val mhsList: List<Mahasiswa>): ArrayAdapter<Mahasiswa>(mCtx, layoutResId, mhsList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(layoutResId, null)

        val  tvNama : TextView = view.findViewById(R.id.txtNama)
        val  tvNim  : TextView = view.findViewById(R.id.txtNim)
        val  tvIPK  : TextView = view.findViewById(R.id.txtIPK)
        val  tvAEdit : TextView = view.findViewById(R.id.tv_edit)

        val  mahasiswo : Mahasiswa = mhsList[position]

        tvAEdit.setOnClickListener {
            showUpdateDialog(mahasiswo)
        }

        tvNama.text = mahasiswo.nama
        tvNim.text = mahasiswo.nim.toString()
        tvIPK.text = mahasiswo.IPK.toString()

        return view
    }

    private fun showUpdateDialog(mahasiswa: Mahasiswa) {
        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Edit Data Warga")

        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.update_dialogue, null)

        val  etNama : EditText = view.findViewById<EditText>(R.id.textNama)
        val  etNim : EditText = view.findViewById<EditText>(R.id.textNim)
        val  etIPK  : EditText = view.findViewById<EditText>(R.id.textIPK)

        //take data from database
        etNama.setText(mahasiswa.nama)
        etNim.setText(mahasiswa.nim)
        etIPK.setText(mahasiswa.IPK.toString())

        builder.setView(view)

        builder.setPositiveButton("Update"){ p0, p1 ->
            //firebase database for update context
            val dbMhs = FirebaseDatabase.getInstance().getReference("mahasiswa")

            //catch changes content for input into database
            val nama = etNama.text.toString().trim()
            val nim = etNim.text.toString().trim()
            val IPK = etIPK.text.toString().trim()

            if(nama.isEmpty()){
                etNama.error = "Tolong isi nama Anda !!"
                etNama.requestFocus()
                return@setPositiveButton
            }

            if(nim.isEmpty()){
                etNim.error = "Tolong isi nim Anda !!"
                etNim.requestFocus()
                return@setPositiveButton
            }

            if(IPK.isEmpty()){
                etIPK.error = "Tolong isi IPK Anda !!"
                etIPK.requestFocus()
                return@setPositiveButton
            }

            val mahasiswah = Mahasiswa(mahasiswa.nim, nama, IPK)
            dbMhs.child(mahasiswah.nim.toString()).setValue(mahasiswah)

            Toast.makeText(mCtx, "Data berhasil di update", Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("No"){p0, p1 ->
        }

        builder.setNegativeButton("Delete"){p0,p1 ->
            val dbMhswa = FirebaseDatabase.getInstance().getReference("mahasiswa").child(mahasiswa.nim.toString())
            dbMhswa.removeValue()
            Toast.makeText(mCtx, "Data berhasil di hapus", Toast.LENGTH_SHORT).show()
        }

        val alert = builder.create()
        alert.show()
    }
}