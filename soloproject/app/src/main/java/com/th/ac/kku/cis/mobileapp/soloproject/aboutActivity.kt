package com.th.ac.kku.cis.mobileapp.soloproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_about.*

class aboutActivity : AppCompatActivity () {
    lateinit var auth: FirebaseAuth
    var peakaboo: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        auth = FirebaseAuth.getInstance()
        val NameSetting: TextView = findViewById(R.id.textname)
        val Email: TextView = findViewById(R.id.textemail)
        val Profile: ImageView = findViewById(R.id.imagepro)
        val xx: Uri? = auth.currentUser!!.photoUrl
        Picasso.get().load(xx).into(Profile)
        NameSetting.text = auth.currentUser!!.displayName.toString()
        Email.text = auth.currentUser!!.email


        auth.currentUser!!.email

        val btnlogout: Button = findViewById(R.id.buttonsignOut)
        btnlogout.setOnClickListener({ v -> singOut() })
        btcalender.setOnClickListener {
            var i = Intent(this, CalActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }
        btadddate.setOnClickListener {
            var i = Intent(this, AdddateActivity::class.java)
            i.setFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP))
            startActivity(i)
        }
    }

    private fun passproject() {
        if (peakaboo) {
            var i = Intent(this, MainActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }
    }

    private fun singOut() {

        auth.signOut()
        peakaboo = true
        passproject()
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            //show.text = "No User"
        } else {
            //show.text = user.email.toString()
            passproject()
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
                peakaboo = false
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
                    peakaboo = true
                    updateUI(user)
                } else {
                    peakaboo = false
                    updateUI(null)
                }
            }
    }
}