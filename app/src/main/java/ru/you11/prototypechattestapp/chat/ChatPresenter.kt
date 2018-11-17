package ru.you11.prototypechattestapp.chat

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import ru.you11.prototypechattestapp.Message
import ru.you11.prototypechattestapp.User
import ru.you11.prototypechattestapp.login.LoginActivity

class ChatPresenter(private val chatView: ChatFragment): ChatContract.Presenter {

    init {
        chatView.presenter = this
    }

    override fun start() {
        
    }

    override fun sendMessage(message: Message) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMessages(): ArrayList<Message> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentUser(): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signOutUser() {
        FirebaseAuth.getInstance().signOut()
        startLoginActivity()
    }

    private fun startLoginActivity() {
        chatView.startActivity(Intent(chatView.activity, LoginActivity::class.java))
        chatView.activity?.finish()
    }
}