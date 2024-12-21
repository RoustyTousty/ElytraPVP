package me.roustytousty.elytrapvp.gui.rebirth

import me.roustytousty.elytrapvp.gui.upgrade.ConfirmUpgradeMenu
import me.roustytousty.elytrapvp.utility.ItemUtils.itemBuilder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.inventory.Inventory

class RebirthMenu : Listener {

    @EventHandler
    private fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory != inv) return
        e.isCancelled = true
        val clickedItem = e.currentItem
        if (clickedItem == null || clickedItem.type.isAir) return
        val p = e.whoClicked as Player

        when (e.rawSlot) {
            18 -> {
                p.closeInventory()
                p.playSound(p, Sound.UI_BUTTON_CLICK, 1.0f, 1.0f)
            }
        }
    }

    @EventHandler
    private fun onInventoryClick(e: InventoryDragEvent) {
        if (e.inventory == inv) {
            e.isCancelled = true
        }
    }

    companion object {

        var inv: Inventory? = null
        fun openInventory(player: Player) {
            inv = Bukkit.createInventory(null, 27, "Events")
            initItems()
            player.openInventory(inv!!)
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1.0f, 1.0f)
        }

        private fun initItems() {
            val slots = intArrayOf(0, 8, 9, 17, 26)
            for (slot in slots) {
                inv!!.setItem(slot, itemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1, false, "&f"))
            }

            inv!!.setItem(
                13,
                itemBuilder(
                    Material.CHERRY_SAPLING,
                    1,
                    false,
                    "&eRebirth",
                    "&7Reset your account and get",
                    "&7extra 5 coins per kill.",
                    "&7And a rebirth token.",
                    "",
                    "&7Click to rebirth!",
                )
            )

            inv!!.setItem(
                18,
                itemBuilder(
                    Material.RED_STAINED_GLASS_PANE,
                    1,
                    false,
                    "&cClose"
                )
            )
        }
    }
}