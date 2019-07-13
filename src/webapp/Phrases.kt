package com.example.webapp

import com.example.repository.Repository
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

const val PHRASES = "/phrases"

fun Route.phrases(db:Repository){
    get(PHRASES){
        val phrases = db.phrases()
        call.respond(phrases.toArray())
    }
}