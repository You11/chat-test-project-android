package ru.you11.prototypechattestapp.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ru.you11.prototypechattestapp.R
import java.lang.Exception

class LoginFragment: Fragment(), Contract.Login.View {

    override lateinit var presenter: Contract.Login.Presenter

    private lateinit var username: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        with(root) {
            username = findViewById(R.id.login_username)
            username.setOnEditorActionListener { _, _, _ ->
                if (username.text.isNotBlank()) {
                    presenter.loginAsGuest(username.text.toString())
                    true
                } else {
                    false
                }
            }
            username.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    loginButton.isEnabled = text != null && text.isNotBlank()
                }
            })

            loginButton = findViewById(R.id.login_button)

            loginButton.setOnClickListener {
                presenter.loginAsGuest(username.text.toString())
            }
        }

        return root
    }

    override fun showLoginError(exception: Exception?) {
        if (exception == null) {
            Toast.makeText(activity, resources.getString(R.string.unknown_error_text), Toast.LENGTH_SHORT).show()
            return
        } else {
            Toast.makeText(activity, exception.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }
}