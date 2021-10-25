package com.buigues.ortola.touristics

import android.app.Application
import com.buigues.ortola.touristics.repository.FirebaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TouristicsApp: Application()
{
    @Inject val firebaseRepository = FirebaseRepository(this)

    init {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseRepository.dumpDataFromFirebase()
        }
    }
}