package com.example.instagramclone.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseConfiguration {
    public companion object  {
        private var firebaseAuth: FirebaseAuth? = null
        private var firebaseDatabase: FirebaseDatabase? = null

        fun getFirebaseAuth(): FirebaseAuth {
            if (firebaseAuth == null) {
                firebaseAuth = FirebaseAuth.getInstance()
            }
            return firebaseAuth as FirebaseAuth
        }

        fun getFirebaseDatabase(): FirebaseDatabase {
            if (firebaseDatabase == null) {
                firebaseDatabase = FirebaseDatabase.getInstance().reference as FirebaseDatabase?
            }
            return firebaseDatabase as FirebaseDatabase
        }
    }
}