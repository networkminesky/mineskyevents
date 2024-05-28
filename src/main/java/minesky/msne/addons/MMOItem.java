package minesky.msne.addons;


import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.Type;
import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MMOItem {
    public static void darParaglider(Player player) {
        net.Indyuce.mmoitems.api.item.mmoitem.MMOItem paraglider = MMOItems.plugin.getMMOItem(Type.MISCELLANEOUS, "evento");
        if (paraglider != null) {
            ItemStackBuilder itemStackBuilder = new ItemStackBuilder(paraglider);
            ItemStack itemStack = itemStackBuilder.build();
            player.getInventory().addItem(itemStack);
        }
        if (paraglider == null) {
            Bukkit.getLogger().warning("[Minesky-Events] Ocorreu um erro ao dar o item paraglider para o jogador.");
        }
    }
    public static ItemStack getParaglider() {
        net.Indyuce.mmoitems.api.item.mmoitem.MMOItem paraglider = MMOItems.plugin.getMMOItem(Type.MISCELLANEOUS, "evento");
        if (paraglider != null) {
            ItemStackBuilder itemStackBuilder = new ItemStackBuilder(paraglider);
            return itemStackBuilder.build();
        }
        return null;
    }
}
