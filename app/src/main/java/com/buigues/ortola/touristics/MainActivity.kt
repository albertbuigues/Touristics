package com.buigues.ortola.touristics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.buigues.ortola.touristics.repository.FirebaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            val firebase = FirebaseRepository(application)
            firebase.dumpDataFromFirebase()
        }
    }
}