package de.dasphiller.ffa.kits.impl

import de.dasphiller.kitapi.kit.*
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.event.unregister
import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.inventory.ItemStack
import kotlin.math.cos
import kotlin.math.sin

class NinjaKit(
    override var cooldown: Double? = 30.0,
    override val description: Component = mm.deserialize("<color:#009bd6>Teleportiere dich hinter einen Spieler!"),
    override val icon: Material = Material.DIAMOND_AXE,
    override val name: String = "Ninja"
) : Kit() {

    override fun enterArena(player: Player) {
        player.inventory.addItem(ItemStack(Material.DIAMOND_AXE))
    }

    private val lastHit = HashMap<Player, Player>()

    private val death = listen<PlayerDeathEvent> {
    }

    private val damage = listen<EntityDamageByEntityEvent> {
        if (it.damager is Player && it.entity is Player) {
            val target = it.entity as Player
            val player = it.damager as Player
            if (player.kit != this) return@listen
            lastHit.put(player, target)
        }
    }

    private val sneak = listen<PlayerToggleSneakEvent> {
        if (it.player.isSneaking && it.player.kit == this) {
            if (!it.player.canUseKit()) return@listen
            val target = lastHit[it.player] ?: return@listen
            if (target.location.y > 140) return@listen
            it.player.teleport(calculateNinjaBehind(target))
            it.player.nextKitUse = cooldown?.toLong()!!
        }
    }

    private fun calculateNinjaBehind(entity: Entity): Location {
        var nang: Float = entity.location.yaw + 90
        if (nang < 0) nang += 360f
        val nX = cos(Math.toRadians(nang.toDouble()))
        val nZ = sin(Math.toRadians(nang.toDouble()))
        return entity.location.clone().subtract(nX, 0.0, nZ)
    }


    override fun registerKit() {
        sneak.register()
        damage.register()
        death
    }

    override fun unregisterKit() {
        sneak.unregister()
        damage.unregister()
        death.unregister()
    }
}