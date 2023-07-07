package com.skybet.gameresultprocessor.produce

import com.skybet.gameresultprocessor.produce.process.ResultProcessor
import com.skybet.gameresultprocessor.repository.GameResult
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createResult(resultProcessor: ResultProcessor) {
    post("/result") {
        val gameResult = call.receive<GameResult>()
        resultProcessor.process(gameResult)
        call.respond(HttpStatusCode.Created)
    }
}
