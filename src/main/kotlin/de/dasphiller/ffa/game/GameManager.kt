package de.dasphiller.ffa.game

import de.dasphiller.ffa.scoreboard.createScoreboard
import de.dasphiller.kitapi.kit.kitList
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.extensions.server
import net.axay.kspigot.runnables.task

object GameManager {

    operator fun invoke() {
        task(false, 20, 20) {
            onlinePlayers.forEach {
                createScoreboard(it)
            }
        }
    }
}