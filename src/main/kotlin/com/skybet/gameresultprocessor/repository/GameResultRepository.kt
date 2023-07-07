package com.skybet.gameresultprocessor.repository

class GameResultRepository {
    private val store = mutableListOf<GameResult>()
    fun findAll(): List<GameResult> {
        return store
    }

    fun findByUser(user: String): List<GameResult> {
        return store.filter { it.user == user }
    }

    fun save(gameResult: GameResult) {
        store.add(gameResult)
    }
}
