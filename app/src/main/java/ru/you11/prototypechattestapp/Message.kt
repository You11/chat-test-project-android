package ru.you11.prototypechattestapp

import java.util.*

data class Message(
    val content: String,
    val sender: User,
    val sendDate: Date
)