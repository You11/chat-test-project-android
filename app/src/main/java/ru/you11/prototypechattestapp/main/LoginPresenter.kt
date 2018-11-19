package ru.you11.prototypechattestapp.main

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import ru.you11.prototypechattestapp.R
import java.util.*

class LoginPresenter(private val loginView: LoginFragment): Contract.Login.Presenter {

    private lateinit var auth: FirebaseAuth

    init {
        loginView.presenter = this
    }

    override fun start() {
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            startChatFragment()
        }
    }

    override fun loginAsGuest(username: String) {
        auth.signInAnonymously()
            .addOnCompleteListener {
                val color = generateColor()
                saveUser(username, color)
                startChatFragment()
            }
            .addOnFailureListener { exception ->
                loginView.showLoginError(exception)
            }
    }

    private fun saveUser(username: String, color: String) {
        val sharedPref = loginView.activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        sharedPref.edit()
            .putString(loginView.resources.getString(R.string.shared_pref_username_key), username)
            .putString(loginView.resources.getString(R.string.shared_pref_color_key), color)
            .apply()
    }

    private fun generateColor(): String {
        val rand = Random()
        val red = String.format("%03d", rand.nextInt(200))
        val green = String.format("%03d", rand.nextInt(200))
        val blue = String.format("%03d", rand.nextInt(200))
        return red + green + blue
    }


    private fun startChatFragment() {
        (loginView.activity as Activity).startChatFragment()
    }
}