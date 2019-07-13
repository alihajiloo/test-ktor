package com.example.api

import com.example.API_VERSION
import com.example.model.EmojiPhrase
import com.example.model.Request
import com.example.repository.Repository
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond

import io.ktor.routing.Route
import io.ktor.routing.post

const val PHRASE_ENDPOINT = "$API_VERSION/phrase"

fun Route.phrase(db: Repository) {
    post(PHRASE_ENDPOINT) {
        val request = call.receive<Request>()
        val phrase = db.add(EmojiPhrase(request.emoji , request.phrase))
        call.respond(phrase)
    }
}