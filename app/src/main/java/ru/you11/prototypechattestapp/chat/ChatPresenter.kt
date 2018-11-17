package ru.you11.prototypechattestapp.chat

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import ru.you11.prototypechattestapp.Message
import ru.you11.prototypechattestapp.R
import ru.you11.prototypechattestapp.User

class ChatPresenter(private val chatView: ChatFragment): Contract.Chat.Presenter {

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
        removeUsernameFromSharedPref()
        startLoginFragment()
    }

    private fun removeUsernameFromSharedPref() {
        val pref = chatView.activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        pref.edit().remove(chatView.resources.getString(R.string.shared_pref_username_key)).apply()
    }

    private fun startLoginFragment() {
        (chatView.activity as Activity).startLoginFragment()
    }
}