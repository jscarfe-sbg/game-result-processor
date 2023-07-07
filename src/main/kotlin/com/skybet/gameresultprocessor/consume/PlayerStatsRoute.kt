package com.skybet.gameresultprocessor.consume

import com.skybet.gameresultprocessor.repository.PlayerStatsRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.playerStats(playerStatsRepository: PlayerStatsRepository) {
    get("/player-stats") {
        call.respond(playerStatsRepository.findAll())
    }

    get("/player-stats/{user}") {
        playerStatsRepository.find(call.parameters["user"]!!).also {
            it?.let {
                call.respond(it)
            } ?: call.respondText("Player stats not found", status = HttpStatusCode.NotFound)
        }
    }
}
