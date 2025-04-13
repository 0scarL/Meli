package com.oscar.meli

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.oscar.meli.databinding.ActivityMainBinding
import com.oscar.meli.ui.view.search.SearchFragment.Companion.getSearchFragmentInstance
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        getInitFragment()
        binding.toolbar.title = getString(R.string.app_name)

    }

    private fun getInitFragment() {
        fragmentSelector(getSearchFragmentInstance())
    }

    fun fragmentSelector(fragment: Fragment) {
        val fragmentTrasaction = supportFragmentManager.beginTransaction()
        fragmentTrasaction.replace(R.id.fragment_container, fragment)
        fragmentTrasaction.addToBackStack(null)
        fragmentTrasaction.commit()
    }


}