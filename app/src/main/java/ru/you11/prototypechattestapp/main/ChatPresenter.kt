package ru.you11.prototypechattestapp.main

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import ru.you11.prototypechattestapp.Message
import ru.you11.prototypechattestapp.R
import ru.you11.prototypechattestapp.User
import java.util.*

class ChatPresenter(private val chatView: ChatFragment): Contract.Chat.Presenter {

    init {
        chatView.presenter = this
    }

    override fun start() {
        getMessages()
    }

    override fun sendMessage(content: String) {

        val message = Message(content, getCurrentUser(), Calendar.getInstance().time)

        val db = FirebaseFirestore.getInstance()

        val messageHashMap = HashMap<String, Any>()
        messageHashMap["content"] = message.content
        messageHashMap["sender"] = message.sender.name
        messageHashMap["sentDate"] = message.sendDate.time

        val messagesHashMapRoot = HashMap<String, Any>()
        messagesHashMapRoot["messages"] = FieldValue.arrayUnion(messageHashMap)
        db.collection("chats").document("chat").update(messagesHashMapRoot)
            .addOnSuccessListener {
                getMessages()
            }
            .addOnFailureListener { exception ->
                chatView.showSendMessageError(exception)
            }
    }

    override fun getMessages() {
        val db = FirebaseFirestore.getInstance()
        db.collection("chats").document("chat").addSnapshotListener { document, exception ->

            if (exception != null) {
                chatView.showReceiveMessagesError(exception)
            }

            val results = ArrayList<Message>()

            if (document?.data?.get("messages") == null) return@addSnapshotListener
            val messagesData = document.data?.get("messages") as ArrayList<HashMap<String, Any>>
            messagesData.forEach { it ->
                results.add(Message(
                        content = it["content"] as String,
                        sender = User(it["sender"] as String),
                        sendDate = (Date(it["sentDate"] as Long))))
            }

            chatView.showMessages(results)
        }
    }

    override fun getCurrentUser(): User {
        val pref = chatView.activity?.getPreferences(Context.MODE_PRIVATE)
        return User(pref?.getString(chatView.resources.getString(R.string.shared_pref_username_key), "anonymous")!!)
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