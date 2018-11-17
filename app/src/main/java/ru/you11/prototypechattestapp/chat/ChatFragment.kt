package ru.you11.prototypechattestapp.chat

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import ru.you11.prototypechattestapp.Message
import ru.you11.prototypechattestapp.R
import ru.you11.prototypechattestapp.User

class ChatFragment: Fragment(), ChatContract.View {

    override lateinit var presenter: ChatContract.Presenter

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_chat, container, false)
        with(root) {
            recyclerView = findViewById(R.id.messages_rv)

            setupRecyclerView()
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_chat, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sign_out_user -> {
                presenter.signOutUser()
                true
            }

            else -> {
                false
            }
        }
    }

    private fun setupRecyclerView() {
        val testMessages = ArrayList<Message>()
        testMessages.add(Message(0, "meow", User(0, "123")))
        testMessages.add(Message(1, "meow22", User(1, "123")))

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MessengerRVAdapter(testMessages)
    }

    override fun showMessages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showReceiveMessagesError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class MessengerRVAdapter(private val messages: ArrayList<Message>): RecyclerView.Adapter<MessengerRVAdapter.ViewHolder>() {

        class ViewHolder(val layout: LinearLayout): RecyclerView.ViewHolder(layout) {
            val message = layout.findViewById<TextView>(R.id.message_text)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message, parent, false) as LinearLayout)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.message.text = messages[position].content
        }

        override fun getItemCount(): Int {
            return messages.size
        }
    }
}