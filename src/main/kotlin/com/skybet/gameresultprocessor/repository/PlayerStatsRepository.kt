package com.skybet.gameresultprocessor.repository

class PlayerStatsRepository {
    private val store = mutableMapOf<String, PlayerStats>()

    fun findAll(): Map<String, PlayerStats> {
        return store
    }

    fun find(user: String): PlayerStats? {
        return store[user]
    }

    fun save(playerStats: PlayerStats) {
        store[playerStats.user] =  playerStats
    }
}
