package minesky.msne.addons;


import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.Type;
import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MMOItem {
    public static void darParaglider(Player player) {
        net.Indyuce.mmoitems.api.item.mmoitem.MMOItem paraglider = MMOItems.plugin.getMMOItem(Type.MISCELLANEOUS, "PARAGLIDER");
        if (paraglider != null) {
            ItemStackBuilder itemStackBuilder = new ItemStackBuilder(paraglider);
            ItemStack itemStack = itemStackBuilder.build();
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta != null) {
                itemMeta.addEnchant(Enchantment.DURABILITY, 255, true);
                itemMeta.setUnbreakable(true);
                itemMeta.getLore().add("Parapente do evento de Corrida de Parapente");
                itemStack.setItemMeta(itemMeta);
            }
            player.getInventory().addItem(itemStack);
        }
        if (paraglider == null) {
            Bukkit.getLogger().warning("[Minesky-Events] Ocorreu um erro ao dar o item paraglider para o jogador.");

        }
    }
    public static ItemStack getParaglider() {
        net.Indyuce.mmoitems.api.item.mmoitem.MMOItem paraglider = MMOItems.plugin.getMMOItem(Type.MISCELLANEOUS, "PARAGLIDER");
        if (paraglider != null) {
            ItemStackBuilder itemStackBuilder = new ItemStackBuilder(paraglider);
            return itemStackBuilder.build();
        }
        return null;
    }
}
