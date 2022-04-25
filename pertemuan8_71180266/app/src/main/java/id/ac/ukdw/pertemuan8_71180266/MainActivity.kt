package id.ac.ukdw.pertemuan8_71180266

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar_default))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val viewPager = findViewById<ViewPager2>(R.id.pager)
        val listFragment: ArrayList<Fragment> = arrayListOf(frag_profile(), frag_settings(), frag_messages())
        val pagerAdapter = PagerAdapter(this, listFragment)
        viewPager.adapter = pagerAdapter
    }

    class PagerAdapter(val activity: AppCompatActivity, val listFragment: ArrayList<Fragment>) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = listFragment.size
        override fun createFragment(position: Int): Fragment = listFragment.get(position)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_default, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_profile -> {
            //Toast.makeText(this,frag_profile().id.toString(),Toast.LENGTH_LONG).show()
            val viewPage = findViewById<ViewPager2>(R.id.pager)
            viewPage.setCurrentItem(0, true)
            true
        }
        R.id.menu_settings -> {
            //Toast.makeText(this,frag_settings().id.toString(),Toast.LENGTH_LONG).show()
            val viewPage2 = findViewById<ViewPager2>(R.id.pager)
            viewPage2.setCurrentItem(1, true)
            true
        }
        R.id.messages -> {
            //Toast.makeText(this,frag_settings().id.toString(),Toast.LENGTH_LONG).show()
            val viewPage2 = findViewById<ViewPager2>(R.id.pager)
            viewPage2.setCurrentItem(2, true)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
