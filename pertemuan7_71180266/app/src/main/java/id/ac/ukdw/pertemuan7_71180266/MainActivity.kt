package id.ac.ukdw.pertemuan7_71180266

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ambil data
        val listKontak = ArrayList<Contact>()
        listKontak.add(Contact("Mom", "088177442229", R.mipmap.mama_bacc, 1))
        listKontak.add(Contact("Daddy", "088237442229", R.mipmap.daddy_bacc, 2))
        listKontak.add(Contact("Adek", "088133342229", R.mipmap.adek_bacc_round, 3))
        listKontak.add(Contact("Kakak", "088156743229", R.mipmap.kakak_bacc, 4))
        listKontak.add(Contact("No Kedua Ku", "088222122291", R.mipmap.me_bacc, 5))


        val rvKontak = findViewById<RecyclerView>(R.id.rvLagu)
        rvKontak.layoutManager = LinearLayoutManager(this)
        val kontakAdapter = ContactAdapter(listKontak)
        rvKontak.adapter = kontakAdapter
    }
}