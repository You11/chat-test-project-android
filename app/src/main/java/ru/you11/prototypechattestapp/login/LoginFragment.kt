package ru.you11.prototypechattestapp.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ru.you11.prototypechattestapp.R
import java.lang.Exception

class LoginFragment: Fragment(), LoginContract.View {

    override lateinit var presenter: LoginContract.Presenter
    lateinit var auth: FirebaseAuth

    private lateinit var loginEmailEmail: EditText
    private lateinit var loginEmailPassword: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)

        with(root) {
            loginEmailEmail = findViewById(R.id.login_email_email)
            loginEmailPassword = findViewById(R.id.login_email_password)
            loginButton = findViewById(R.id.login_email_login_button)

            loginButton.setOnClickListener {
                presenter.createUserWithEmail(
                    email = loginEmailEmail.text.toString(),
                    password = loginEmailPassword.text.toString())
            }
        }

        return root
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun showCreateNewUserErrorMessage(exception: Exception?) {
        if (exception == null) {
            Toast.makeText(activity, resources.getString(R.string.login_unknown_error_text), Toast.LENGTH_SHORT).show()
            return
        } else {
            Toast.makeText(activity, exception.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    fun validateUserInput() {
        //TODO: write this
    }
}