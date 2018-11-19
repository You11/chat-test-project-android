package ru.you11.prototypechattestapp.main

import ru.you11.prototypechattestapp.BasePresenter
import ru.you11.prototypechattestapp.BaseView
import ru.you11.prototypechattestapp.Message
import ru.you11.prototypechattestapp.User
import java.lang.Exception

interface Contract {

    interface Chat {

        interface View: BaseView<Presenter> {

            fun showMessages(messages: ArrayList<Message>)

            fun showReceiveMessagesError(exception: Exception?)

            fun showSendMessageError(exception: Exception?)

            fun onMessageSentAndReceived()
        }

        interface Presenter: BasePresenter {

            fun sendMessage(content: String)

            fun setMessengersListener()

            fun getCurrentUser(): User

            fun signOutUser()
        }
    }

    interface Login {

        interface View: BaseView<Presenter> {

            fun showLoginError(exception: Exception?)
        }

        interface Presenter: BasePresenter {

            fun loginAsGuest(username: String)
        }
    }

}