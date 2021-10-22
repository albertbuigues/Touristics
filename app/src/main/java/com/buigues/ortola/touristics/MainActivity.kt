package com.buigues.ortola.touristics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buigues.ortola.touristics.model.database.AppDatabase
import com.buigues.ortola.touristics.repository.FirebaseRepository
import com.buigues.ortola.touristics.repository.LocalRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase = FirebaseRepository(application)
        firebase.dumpDataFromFirebase()
    }
}