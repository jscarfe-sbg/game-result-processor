package com.skybet.gameresultprocessor.repository

import kotlinx.serialization.Serializable

@Serializable
data class GameResult(
    val id: String,
    val user: String,
    val game: String,
    val win: Boolean,
    val stake: Double,
    val payout: Double,
    val timestamp: String,
)
