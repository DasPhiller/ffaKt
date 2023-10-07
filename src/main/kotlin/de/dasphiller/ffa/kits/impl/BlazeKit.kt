package de.dasphiller.ffa.kits.impl

import de.dasphiller.kitapi.kit.*
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.unregister
import net.axay.kspigot.extensions.bukkit.kill
import net.axay.kspigot.runnables.taskRunLater
import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Blaze
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityTargetEvent
import org.bukkit.event.player.PlayerToggleSneakEvent

class BlazeKit(
    override var cooldown: Double? = 60.0,
    override val description: Component = mm.deserialize("<#e1af15>Spawne 2 Blazes, die dir im Kampf helfen!"),
    override val icon: Material = Material.BLAZE_POWDER,
    override val name: String = "BlazeArmee"
) : Kit() {

    private val sneak = listen<PlayerToggleSneakEvent> {
        if (it.player.kit != this) return@listen
        if (!it.player.canUseKit()) return@listen
        repeat(2) { _ ->
            val blaze = it.player.world.spawnEntity(
                Location(it.player.world, it.player.x, it.player.y + 2, it.player.z),
                EntityType.BLAZE
            )
            blaze.addScoreboardTag(it.player.name)
        }
        taskRunLater(20 * 20, true) {
            it.player.world.getNearbyLivingEntities(it.player.location, 150.0).forEach { entity ->
                if (entity.scoreboardTags.contains(it.player.name)) {
                    val livingEntity = entity as LivingEntity
                    livingEntity.kill()
                }
            }
        }
        it.player.nextKitUse = cooldown!!.toLong()

    }

    private val aggression = listen<EntityTargetEvent> {
        if (it.target !is Player) return@listen
        val player = it.target as Player
        if (player.kit != this) return@listen
        if (it.entity.scoreboardTags.contains(player.name)) {
            it.isCancelled = true
        }
    }


    override fun registerKit() {
        sneak
        aggression

    }

    override fun unregisterKit() {
        sneak.unregister()
        aggression.unregister()
    }

}