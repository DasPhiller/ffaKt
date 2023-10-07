package de.dasphiller.ffa.kits;

import de.dasphiller.kitapi.kit.Kit;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class GapMaster extends Kit {
    @Nullable
    @Override
    public Double getCooldown() {
        return 0.0;
    }

    @Override
    public void setCooldown(@Nullable Double aDouble) {

    }

    @NotNull
    @Override
    public Component getDescription() {
        return Component.text("Werde zum GapMaster");
    }

    @NotNull
    @Override
    public Material getIcon() {
        return Material.GOLDEN_APPLE;
    }

    @NotNull
    @Override
    public String getName() {
        return "GapMaster";
    }

    @Override
    public void enterArena(@NotNull Player player) {
        ItemStack itemStack = new ItemStack(Material.GOLDEN_APPLE);
        itemStack.setAmount(3);
        player.getInventory().setItem(4, itemStack);
    }
}
