package ru.you11.prototypechattestapp.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.you11.prototypechattestapp.R

class LoginFragment: Fragment(), LoginContract.View {

    override lateinit var presenter: LoginContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)

        with(root) {

        }

        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }
}