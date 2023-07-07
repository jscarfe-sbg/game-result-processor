package com.skybet.gameresultprocessor

import com.skybet.gameresultprocessor.consume.gameStats
import com.skybet.gameresultprocessor.consume.playerStats
import com.skybet.gameresultprocessor.produce.createResult
import com.skybet.gameresultprocessor.produce.process.ResultProcessor
import com.skybet.gameresultprocessor.repository.GameResultRepository
import com.skybet.gameresultprocessor.repository.GameStatsRepository
import com.skybet.gameresultprocessor.repository.PlayerStatsRepository
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::gameResultProcessor).start(wait = true)
}

fun Application.gameResultProcessor() {
    install(ContentNegotiation) {
        json()
    }

    val gameResultRepository = GameResultRepository()
    val playerStatsRepository = PlayerStatsRepository()
    val gameStatsRepository = GameStatsRepository()
    val resultProcessor = ResultProcessor(gameResultRepository, playerStatsRepository, gameStatsRepository)

    routing {
        createResult(resultProcessor)
        playerStats(playerStatsRepository)
        gameStats(gameStatsRepository)
    }
}
