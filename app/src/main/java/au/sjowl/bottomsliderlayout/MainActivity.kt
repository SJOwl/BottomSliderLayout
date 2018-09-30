package au.sjowl.bottomsliderlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        fragmentsContainer

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentsContainer, HomeFragment.newInstance(), "home")
            addToBackStack(null)
            commit()
        }
    }
}
