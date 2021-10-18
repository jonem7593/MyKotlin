package org.kyw.mykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth/*
import com.google.android.gms.auth.api.signin.GoogleSignInClient*/

class Gogle_Login_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var auth: FirebaseAuth?= null/*
        var googleSignInClient : GoogleSignInClient? = null*/
    }
}