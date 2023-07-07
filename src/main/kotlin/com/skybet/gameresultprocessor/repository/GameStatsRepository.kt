package com.skybet.gameresultprocessor.repository

class GameStatsRepository {
    private val store = mutableMapOf<String, GameStats>()

    fun findAll(): Map<String, GameStats> {
        return store
    }

    fun find(game: String): GameStats? {
        return store[game]
    }

    fun save(gameStats: GameStats) {
        store[gameStats.game] =  gameStats
    }
}
