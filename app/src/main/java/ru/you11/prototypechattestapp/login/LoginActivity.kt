package ru.you11.prototypechattestapp.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.you11.prototypechattestapp.R

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragment = LoginFragment()
        LoginPresenter(fragment)
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit()

    }
}