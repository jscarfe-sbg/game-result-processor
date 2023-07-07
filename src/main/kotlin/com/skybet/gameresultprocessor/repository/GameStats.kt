package com.skybet.gameresultprocessor.repository

data class GameStats(val game: String, val totalStaked: Double, val totalPayout: Double, val totalWins: Int, val totalLosses: Int)
