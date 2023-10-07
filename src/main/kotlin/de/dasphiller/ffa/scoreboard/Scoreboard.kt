package de.dasphiller.ffa.scoreboard

import de.dasphiller.ffa.adventure.FastBoard
import de.dasphiller.kitapi.kit.canUseKit
import de.dasphiller.kitapi.kit.kit
import de.dasphiller.kitapi.kit.mm
import org.bukkit.entity.Player
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration

fun createScoreboard(player: Player) {
    val board: FastBoard = FastBoard(player)

    board.updateTitle(mm.deserialize("   <gradient:#55FFFF:#55bfff>FFA   ").decoration(TextDecoration.BOLD, true))

    board.updateLine(1, mm.deserialize("<#55FFFF><bold>Kit:</bold> <#5583ff>${player.kit?.name}"))
    board.updateLine(2, Component.text(" "))
    board.updateLine(3, if (!player.canUseKit()) mm.deserialize("<#FF5555>ON COOLDOWN").decoration(TextDecoration.BOLD, true) else mm.deserialize(" "))


}
