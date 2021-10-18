package org.kyw.mykotlin

import android.accounts.Account
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class login_Activity : AppCompatActivity() {//mykotlin-c2ea7 프로젝트 ID
    var auth : FirebaseAuth? = null
    var gogleSignInClient :GoogleSignInClient? = null
    var GOOGLE_LOGIN_CODE = 9001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        button_login_xml.setOnClickListener {
            singUp_AND_SingIn()
        }//button_login_xml
        imagebutton_google_xml.setOnClickListener {
            //First step
            gogleLogin()
        }
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken("802667019433-bq6q4fsnbbdtp58t92ga6aesd8l2ajhm.apps.googleusercontent.com")
            .requestEmail()
            .build()
        gogleSignInClient = GoogleSignIn.getClient(this,gso)

    }//onCreate
    fun gogleLogin() {
        var signInIntent = gogleSignInClient?.signInIntent
        startActivityForResult(signInIntent,GOOGLE_LOGIN_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_LOGIN_CODE) {
            var  result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result!!.isSuccess) {
                var account = result.signInAccount
                //second step
                firebaseAuthWithGoogle(account!!)
            }
        }
    }
    fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        var credential = GoogleAuthProvider.getCredential(account?.idToken,null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener {
                    task ->
                if (task.isSuccessful){
                    //성공하면 로그인 하기.
                    login_Complete_move_page(task.result?.user)
                } else {
                    Toast.makeText(this, task.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun singUp_AND_SingIn() {
        auth?.createUserWithEmailAndPassword(editText_email_xml.text.toString(),editText_password_xml.text.toString())
            ?.addOnCompleteListener {//통신 완료가 된 후 무슨일을 할지
                task ->
                if (task.isSuccessful){//만약 성공하면
                    //유저의 어카운트를 만들기.
                    //페이지 이동도 하기.
                    login_Complete_move_page(task.result?.user)
                } else if (task.exception?.message.isNullOrEmpty()){
                    //exception이 발생하면 에러메세지 발생시키기.
                    Toast.makeText(this,task.exception!!.message,Toast.LENGTH_SHORT).show()
                } else{
                    //만약 어카운트를 가지고있으면 로그인시키기
                    singIn_Email()
                }
            }
    }//singUp_AND_SingIn

    fun singIn_Email() {//함수만들기//메서드만들기
        auth?.createUserWithEmailAndPassword(editText_email_xml.text.toString(),editText_password_xml.text.toString())
            ?.addOnCompleteListener {
                task ->
                if (task.isSuccessful){
                    //성공하면 로그인 하기.
                    login_Complete_move_page(task.result?.user)
                } else {
                    Toast.makeText(this, task.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }

    }
    fun login_Complete_move_page(user: FirebaseUser?){
        if (user!=null){
            startActivity(Intent(this,Main_Activity::class.java))
        }
    }

}//MainActivity