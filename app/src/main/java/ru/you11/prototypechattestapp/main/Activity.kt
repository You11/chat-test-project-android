package ru.you11.prototypechattestapp.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.you11.prototypechattestapp.R

class Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        startLoginFragment()
    }

    fun startChatFragment() {
        val fragment = ChatFragment()

        ChatPresenter(fragment)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit()
    }

    fun startLoginFragment() {
        val fragment = LoginFragment()

        LoginPresenter(fragment)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit()
    }
}
