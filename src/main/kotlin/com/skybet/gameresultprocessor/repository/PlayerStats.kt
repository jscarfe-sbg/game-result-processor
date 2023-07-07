package com.skybet.gameresultprocessor.repository

import kotlinx.serialization.Serializable

@Serializable
data class PlayerStats(val user: String, val totalStaked: Double, val totalPayout: Double, val totalWins: Int, val totalLosses: Int, val favouriteGame: String)
