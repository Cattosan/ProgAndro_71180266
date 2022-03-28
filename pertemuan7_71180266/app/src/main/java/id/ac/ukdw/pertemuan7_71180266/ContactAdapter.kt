package id.ac.ukdw.pertemuan7_71180266

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val listContact: ArrayList<Contact>): RecyclerView.Adapter<ContactAdapter.ContactHolder>() {
    class ContactHolder(val see: View): RecyclerView.ViewHolder(see){
        var contact: Contact? = null

        fun bindView(contact: Contact) {
            this.contact = contact
            see.findViewById<TextView>(R.id.namakontak).text = "#${contact.noContact} ${contact.nama}"
            see.findViewById<TextView>(R.id.nomorkontak).text = "${contact.nomor}"
            see.findViewById<ImageView>(R.id.gambarpojok).setImageResource(contact.gambarPojok)
            see.setOnClickListener{
                Toast.makeText(see.context, "${contact.nama} - ${contact.nomor}", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val see = LayoutInflater.from(parent.context).inflate(R.layout.contact_items, parent, false)
        return ContactHolder(see)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bindView(listContact[position])
    }

    override fun getItemCount(): Int = listContact.size
}