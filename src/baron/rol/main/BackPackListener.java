package baron.rol.main;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class BackPackListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
//		Player p = e.getPlayer();
//
//		BackPack.instance.playerInvs.put(p, new BackPackInventory(p));
//		System.out.println(BackPack.CONSOLENAME + " " + p.getPlayerListName() + " - InvCreated");
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
//		BackPack.instance.playerInvs.remove(e.getPlayer());
//
//		// TODO: Change this so it saves the inventory to file
	}

	@EventHandler
	public void onBackpackOpen(PlayerInteractEvent e) {
		if (!(e.getItem() instanceof ItemStack)) {
			return;
		}

		UUID uid = UUID.fromString(TextHider.revealText(e.getItem().getItemMeta().getLore().get(0)));

		if (!BackPack.instance.backpacks.containsKey(uid)) {
			return;
		}

		BackPackItem backpack = BackPack.instance.backpacks.get(uid);
		backpack.backpackinv.openInventory(e.getPlayer());

		e.setCancelled(true);

	}
}
