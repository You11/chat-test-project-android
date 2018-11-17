package ru.you11.prototypechattestapp.login

import ru.you11.prototypechattestapp.BasePresenter
import ru.you11.prototypechattestapp.BaseView
import java.lang.Exception

interface LoginContract {

    interface View: BaseView<Presenter> {
        fun showCreateNewUserErrorMessage(exception: Exception?)
    }

    interface Presenter: BasePresenter {

        fun startChatActivity()

        fun createUserWithEmail(email: String, password: String)
    }
}