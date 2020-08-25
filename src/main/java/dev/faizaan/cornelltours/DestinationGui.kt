package dev.faizaan.cornelltours

import joptsimple.internal.Messages.message
import me.lucko.helper.item.ItemStackBuilder
import me.lucko.helper.menu.Gui
import me.lucko.helper.menu.scheme.MenuPopulator
import org.bukkit.Material
import org.bukkit.entity.Player
import kotlin.math.max


class DestinationGui(player: Player)
    : Gui(player, max(1, TourManager.destinations.size / 9), "Choose a destination") {
    override fun redraw() {
        if (isFirstDraw) {
            TourManager.destinations.forEach { d ->
                addItem(ItemStackBuilder.of(Material.COMPASS).build {
                    val t = TourManager.activeTours[player.uniqueId] ?: return@build
                    t.target = d
                    close()
                    player.sendMessage("&aFollow me to &l${d.title}&r&a.")
                })
            }
        }
    }
}