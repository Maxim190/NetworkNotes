package com.example.networknotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.networknotes.ui.main.MainFragment
import com.example.networknotes.db.sql.SqlDB


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
        SqlDB.getInstance(this)
    }
}

