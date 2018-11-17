package ru.you11.prototypechattestapp.login

import android.content.Intent
import ru.you11.prototypechattestapp.chat.ChatActivity

class LoginPresenter(private val chatView: LoginFragment): LoginContract.Presenter {

    init {
        chatView.presenter = this
    }

    override fun start() {
        if (chatView.auth.currentUser != null) {
            startChatActivity()
        }
    }

    override fun startChatActivity() {
        chatView.startActivity(Intent(chatView.activity, ChatActivity::class.java))
        chatView.activity?.finish()
    }

    override fun createUserWithEmail(email: String, password: String) {
        chatView.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startChatActivity()
                } else {
                    chatView.showCreateNewUserErrorMessage(task.exception)
                }
            }
            .addOnFailureListener { exception ->
                chatView.showCreateNewUserErrorMessage(exception)
            }
    }
}