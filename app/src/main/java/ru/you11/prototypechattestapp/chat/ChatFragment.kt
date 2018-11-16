package ru.you11.prototypechattestapp.chat

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.you11.prototypechattestapp.R

class ChatFragment: Fragment(), ChatContract.View {

    override lateinit var presenter: ChatContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun showMessages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showReceiveMessagesError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}