package ru.you11.prototypechattestapp.chat

import android.support.v4.app.Fragment
import ru.you11.prototypechattestapp.Message
import ru.you11.prototypechattestapp.User

class ChatPresenter(chatView: ChatFragment): ChatContract.Presenter {

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
}