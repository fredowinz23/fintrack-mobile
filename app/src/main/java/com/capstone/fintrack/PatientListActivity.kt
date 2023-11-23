//package com.capstone.fintrack
//
//import android.os.Bundle
//import com.google.android.material.tabs.TabLayout
//import androidx.viewpager.widget.ViewPager
//import androidx.appcompat.app.AppCompatActivity
//import com.capstone.fintrack.todelete.main.SectionsPagerAdapter
//import com.capstone.fintrack.databinding.ActivityPatientListBinding
//
//class PatientListActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityPatientListBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityPatientListBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
//        val viewPager: ViewPager = binding.viewPager
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = binding.tabs
//        tabs.setupWithViewPager(viewPager)
////        val fab: FloatingActionButton = binding.fab
//
//        binding.ivBack.setOnClickListener {
//            finish()
//        }
//
////        fab.setOnClickListener { _ ->
////            startActivity(Intent(this@PatientListActivity, NewPatientFormActivity::class.java))
////        }
//    }
//}