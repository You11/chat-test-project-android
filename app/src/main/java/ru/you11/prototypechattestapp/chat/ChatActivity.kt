package ru.you11.prototypechattestapp.chat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.you11.prototypechattestapp.R

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val fragment = ChatFragment()

        ChatPresenter(fragment)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit()
    }
}
