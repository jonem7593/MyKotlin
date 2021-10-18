package org.kyw.mykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.kyw.navi.DetailViewFrament
import org.kyw.navi.GridFragment
import org.kyw.navi.AlarmFrament
import org.kyw.navi.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class Main_Activity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomnavigation_meun_xml.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.custom_menu_xml_home -> {
                var detailViewFrament = DetailViewFrament()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, detailViewFrament).commit()
                return true
            }
            R.id.custom_menu_xml_sach -> {
                var gridFragment = GridFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, gridFragment).commit()
                return true
            }
            R.id.custom_menu_xml_poto -> {

                return true
            }
            R.id.custom_menu_xml_hato -> {
                var alarmFrament = AlarmFrament()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, alarmFrament).commit()
                return true
            }
            R.id.custom_menu_xml_profile -> {
                var userFragment = UserFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, userFragment).commit()
                return true
            }
        }
        return false
    }
}