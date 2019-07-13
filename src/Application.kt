package com.example

import com.example.api.phrase
import com.example.repository.InMemoryRepository
import com.example.webapp.about
import com.example.webapp.home
import com.example.webapp.phrases
import com.ryanharter.ktor.moshi.moshi
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(DefaultHeaders)

    install(StatusPages) {
        exception<Throwable> { e ->
            call.respondText(
                e.localizedMessage,
                ContentType.Text.Plain,
                HttpStatusCode.InternalServerError
            )
        }
    }

    install(ContentNegotiation) {
        moshi()
    }
    

    val db = InMemoryRepository()

    routing {
        home()
        about()
        phrases(db)

        //Api
        phrase(db)
    }
}

const val API_VERSION = "/api/v1"

