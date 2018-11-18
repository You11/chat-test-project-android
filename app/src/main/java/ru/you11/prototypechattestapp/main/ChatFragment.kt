package ru.you11.prototypechattestapp.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import ru.you11.prototypechattestapp.Message
import ru.you11.prototypechattestapp.R
import java.lang.Exception

class ChatFragment: Fragment(), Contract.Chat.View {

    override lateinit var presenter: Contract.Chat.Presenter

    private lateinit var recyclerView: RecyclerView
    private val messages = ArrayList<Message>()

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
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MessengerRVAdapter(messages)
    }

    override fun showMessages(messages: ArrayList<Message>) {
        this.messages.clear()
        this.messages.addAll(messages)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun showReceiveMessagesError(exception: Exception?) {
        if (exception != null) {
            Toast.makeText(activity, exception.localizedMessage, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, resources.getString(R.string.login_unknown_error_text), Toast.LENGTH_SHORT).show()
        }
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