package ru.you11.prototypechattestapp.chat

import ru.you11.prototypechattestapp.BasePresenter
import ru.you11.prototypechattestapp.BaseView
import ru.you11.prototypechattestapp.Message
import ru.you11.prototypechattestapp.User

interface ChatContract {

    interface View: BaseView<Presenter> {

        fun showMessages()

        fun showReceiveMessagesError()

        fun sendMessage()
    }

    interface Presenter: BasePresenter {

        fun sendMessage(message: Message)

        fun getMessages(): ArrayList<Message>

        fun getCurrentUser(): User

        fun signOutUser()
    }
}