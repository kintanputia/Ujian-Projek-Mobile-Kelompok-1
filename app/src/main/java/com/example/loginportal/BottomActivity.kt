package com.example.loginportal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_bottom.*
import androidx.fragment.app.Fragment

class BottomActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom)
        bottomNavigationView.setOnNavigationItemSelectedListener(onBottomNavigationListener)
        var fragment = supportFragmentManager.beginTransaction()
        fragment.add(R.id.fl, HomeFragment())
        fragment.commit()
    }

    private val onBottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment : Fragment = HomeFragment()

        when(item.itemId) {
            R.id.navigation_home -> {
                selectedFragment = HomeFragment()
            }
            R.id.navigation_matkul -> {
                selectedFragment = MatkulFragment()
            }
            R.id.navigation_krs -> {
                selectedFragment = krsFragment()
            }
            R.id.navigation_khs -> {
                selectedFragment = khsFragment()
            }
        }

        var fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fl, selectedFragment)
        fragment.commit()
        true

    }
}