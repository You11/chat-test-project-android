package ru.you11.prototypechattestapp.login

class LoginPresenter(private val chatView: LoginFragment): LoginContract.Presenter {

    init {
        chatView.presenter = this
    }

    override fun start() {
        
    }
}