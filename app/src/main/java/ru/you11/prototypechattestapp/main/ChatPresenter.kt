package ru.you11.prototypechattestapp.main

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import ru.you11.prototypechattestapp.Message
import ru.you11.prototypechattestapp.R
import ru.you11.prototypechattestapp.User

class ChatPresenter(private val chatView: ChatFragment): Contract.Chat.Presenter {

    init {
        chatView.presenter = this
    }

    override fun start() {
        getMessages()
    }

    override fun sendMessage(message: Message) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMessages() {
        val db = FirebaseFirestore.getInstance()
        db.collection("chats").get()
            .addOnCompleteListener { task ->
                //TODO: refactoring
                if (task.isComplete && task.result != null) {
                    val document = task.result!!.documents[0]
                    val results = ArrayList<Message>()
                    val messagesData = document.data?.get("messages") as ArrayList<HashMap<String, String>>
                    messagesData.forEachIndexed { index, hashMap ->
                        results.add(Message(
                            id = index,
                            content = hashMap["text"]!!,
                            from = User(hashMap["username"]!!)))
                    }

                    chatView.showMessages(results)
                } else {
                    chatView.showReceiveMessagesError(task.exception)
                }
            }
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