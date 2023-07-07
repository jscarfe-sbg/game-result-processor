package com.skybet.gameresultprocessor.repository

data class PlayerStats(val user: String, val totalStaked: Double, val totalPayout: Double, val totalWins: Int, val totalLosses: Int, val favouriteGame: String)
