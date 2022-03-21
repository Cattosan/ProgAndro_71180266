package id.ac.ukdw.pertemuan6_71180266

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragIchi: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_a, container, false)
        val btnNexCarlos = v.findViewById<Button>(R.id.btnNex)
        btnNexCarlos.setOnClickListener {
            activity?.let{
                val intent = Intent (it, PageNi::class.java)
                startActivity(intent)
            }
        }
        return v
    }
}