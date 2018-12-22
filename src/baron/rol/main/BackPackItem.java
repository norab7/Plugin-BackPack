package baron.rol.main;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BackPackItem extends ItemStack {
	private UUID uid;
	public final BackPackInventory backpackinv;

	public BackPackItem() {
		uid = UUID.randomUUID();
		backpackinv = new BackPackInventory();

		this.setType(Material.CHEST);
		this.setAmount(1);

		ItemMeta meta = this.getItemMeta();
		meta.setDisplayName(BackPack.PLUGINNAME + "(" + BackPack.instance.SIZE + ")");

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(TextHider.hideText(uid.toString()));
		lore.add("" + BackPack.PLUGINNAME);
		meta.setLore(lore);

		this.setItemMeta(meta);
	}

	public UUID getUid() {
		return uid;
	}

	public BackPackInventory getBackpackinv() {
		return backpackinv;
	}

}
