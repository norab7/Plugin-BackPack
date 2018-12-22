package baron.rol.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BackPackInventory implements Listener {
	private Inventory inv;
	private Player player;
	private String name;

	// ### Constructors ###
	public BackPackInventory() {
		this(BackPack.instance.SIZE);
	}

	public BackPackInventory(int n) {
		this(n, BackPack.PLUGINNAME + "(" + n + ")");
	}

	public BackPackInventory(int n, String s) {
		BackPack.instance.getServer().getPluginManager().registerEvents(this, BackPack.instance);

		inv = Bukkit.createInventory(null, n, s);
		name = s;

	}
	//

	// ### Add GUI Item ###
	public void addGUIItem(ItemStack item) {
		inv.addItem(item);
	}

	public void addGUIItem(ArrayList<ItemStack> items) {
		for (ItemStack item : items) {
			addGUIItem(item);
		}
	}
	//

	// ### Change Size of the BackPack ###
	public void setSize(int n) {

		int contentCount = 0;
		for (ItemStack item : inv.getContents()) {
			if (item != null) {
				contentCount++;
			}
			if (contentCount > n) {
				player.sendMessage("To many used slots to reduce backpack size");
				return;
			}
		}

		HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
		for (int i = 0; i < inv.getSize(); i++) {
			if (!(inv.getItem(i) == null)) {
				items.put(i, inv.getItem(i));
			}
		}

		inv = Bukkit.createInventory(player, n, name);

		if (n < inv.getSize()) {
			for (ItemStack item : items.values()) {
				inv.addItem(item);
			}
		} else {
			for (Entry<Integer, ItemStack> item : items.entrySet()) {
				inv.setItem((item.getKey() % n), item.getValue());
			}
		}
	}
	//

	// ### Create GUI Item ###
	public ItemStack createGUIItem(String name, ArrayList<String> desc, Material mat) {

		ItemStack i = new ItemStack(mat, 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(desc);
		i.setItemMeta(im);

		return i;

	}
	//

	// ### Open Inventory ###
	public void openInventory(Player p) {
		p.openInventory(inv);
	}
	//

	// ### Click Items ###
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		String invName = e.getInventory().getName();
		if (!invName.equals(inv.getName())) {
			return;
		}

		if (e.getClick().equals(ClickType.NUMBER_KEY)) {
			e.setCancelled(true);
		}

		// Interactive Inventory Storage
		// e.setCancelled(true);

		Player p = (Player) e.getWhoClicked();
		ItemStack clickedItem = e.getCurrentItem();

		if (clickedItem == null || clickedItem.getType().equals(Material.AIR)) {
			return;
		}

		if (e.getRawSlot() == 10) {
			p.sendMessage("Position 10 Clicked");
		}
	}
}

/*
 * 
 *
 * Intentionally Left Blank
 *
 *
 */