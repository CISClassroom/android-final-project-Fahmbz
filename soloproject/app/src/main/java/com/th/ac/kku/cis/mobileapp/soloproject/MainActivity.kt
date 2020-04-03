package com.th.ac.kku.cis.mobileapp.soloproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.math.sign

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var googleClient: GoogleSignInClient
    var newpropro: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_login_google.setOnClickListener({ v -> singIn() })

        var gso = GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN
        )
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()
        addproject()
    }

    private fun addproject() {
        Log.w("wwwwwwwwwwwww", "error")
        if (newpropro) {
            var i = Intent(this, aboutActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }
    }
    private fun singOut() {
        auth.signOut()
        googleClient.signOut().addOnCompleteListener(this) {
            updateUI(null)
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            //show.text = "No User"
        } else {
            //show.text = user.email.toString()
            addproject()
        }
    }

    private fun singIn() {
        singOut()
        if (newpropro) {
            addproject()
        } else {
            var signInInent = googleClient.signInIntent
            startActivityForResult(signInInent, 101)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuth(account!!)
                //FirebaseAuth(account)
            } catch (e: ApiException) {
                Log.i("Error OOP", e.toString())
                newpropro = false
                updateUI(null)
            }
        }
    }

    private fun firebaseAuth(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        newpropro = true
                        updateUI(user)
                    } else {
                        newpropro = false
                        updateUI(null)
                    }
                }

    }
}