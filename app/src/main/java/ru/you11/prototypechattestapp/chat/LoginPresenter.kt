package ru.you11.prototypechattestapp.chat

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import ru.you11.prototypechattestapp.R

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
                saveUsername(username)
                startChatFragment()
            }
            .addOnFailureListener { exception ->
                loginView.showLoginError(exception)
            }
    }

    private fun saveUsername(username: String) {
        val sharedPref = loginView.activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        sharedPref.edit().putString(loginView.resources.getString(R.string.shared_pref_username_key), username).apply()
    }

    private fun startChatFragment() {
        (loginView.activity as Activity).startChatFragment()
    }
}