package com.skybet.gameresultprocessor.consume

import com.skybet.gameresultprocessor.repository.GameStatsRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.gameStats(gameStatsRepository: GameStatsRepository) {
    get("/game-stats") {
        call.respond(gameStatsRepository.findAll())
    }

    get("/game-stats/{game}") {
        gameStatsRepository.find(call.parameters["game"]!!).also {
            it?.let {
                call.respond(it)
            } ?: call.respondText("Game stats not found", status = HttpStatusCode.NotFound)
        }
    }
}
