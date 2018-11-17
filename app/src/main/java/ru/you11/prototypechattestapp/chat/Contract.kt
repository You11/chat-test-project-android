package ru.you11.prototypechattestapp.chat

import ru.you11.prototypechattestapp.BasePresenter
import ru.you11.prototypechattestapp.BaseView
import ru.you11.prototypechattestapp.Message
import ru.you11.prototypechattestapp.User
import java.lang.Exception

interface Contract {

    interface Chat {

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

    interface Login {

        interface View: BaseView<Presenter> {

            fun showLoginError(exception: Exception?)
        }

        interface Presenter: BasePresenter {

            fun loginAsGuest(username: String)
        }
    }

}