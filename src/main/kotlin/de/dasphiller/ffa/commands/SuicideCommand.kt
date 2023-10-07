package de.dasphiller.ffa.commands

import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.runs
import net.axay.kspigot.extensions.bukkit.kill

val suicide = command("suicide") {
    runs {
        player.kill()
    }
}