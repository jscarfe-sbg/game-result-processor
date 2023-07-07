package com.skybet.gameresultprocessor.produce.process

import com.skybet.gameresultprocessor.repository.*

class ResultProcessor(
    private val gameResultRepository: GameResultRepository,
    private val playerStatsRepository: PlayerStatsRepository,
    private val gameStatsRepository: GameStatsRepository,
) {
    fun process(event: GameResult) {
        gameResultRepository.save(event)
        recalculatePlayerStats(event)
        recalculateGameStats(event)
    }

    private fun recalculatePlayerStats(event: GameResult) {
        gameResultRepository.findByUser(event.user).also { gameResults ->
            playerStatsRepository.save(
                PlayerStats(
                    user = event.user,
                    totalStaked = gameResults.sumOf { it.stake },
                    totalPayout = gameResults.sumOf { it.payout },
                    totalWins = gameResults.count { it.win },
                    totalLosses = gameResults.count { !it.win },
                    favouriteGame = gameResults.groupingBy { it.game }.eachCount().maxByOrNull { it.value }?.key ?: "N/A",
                )
            )
        }
    }

    private fun recalculateGameStats(event: GameResult) {
        gameResultRepository.findAll().also { allResults ->
            allResults.groupBy { it.game }.forEach { (game, results) ->
                gameStatsRepository.save(
                    GameStats(
                        game = game,
                        totalStaked = results.sumOf { it.stake },
                        totalPayout = results.sumOf { it.payout },
                        totalWins = results.count { it.win },
                        totalLosses = results.count { !it.win },
                    )
                )
            }
        }
    }
}
