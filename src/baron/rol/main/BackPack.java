package baron.rol.main;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BackPack extends JavaPlugin {
	public static final String PLUGINNAME = "BackPack";
	public static final String CONSOLENAME = "[" + PLUGINNAME + "]";

	public static BackPack instance;
	public HashMap<UUID, BackPackItem> backpacks;
	public FileConfiguration config;

	public final int SIZE;
	public final String ITEMNAME;
	public final String ITEMTYPE;

	public BackPack() {

		// Set Instance
		instance = this;

		// Set Configuration File && defaults
		this.saveDefaultConfig();
		config = this.getConfig();
		saveConfig();

		ConfigurationSection cs = config.getConfigurationSection("defaults");
		SIZE = cs.getInt("size");
		ITEMNAME = cs.getString("name");
		ITEMTYPE = cs.getString("type");

		// Output defaults
		System.out.println(CONSOLENAME + " Size: " + SIZE);
		System.out.println(CONSOLENAME + " Name: " + ITEMNAME);
		System.out.println(CONSOLENAME + " Type: " + ITEMTYPE);

		// Initialise Fields
		instance.backpacks = new HashMap<UUID, BackPackItem>();

	}

	@Override
	public void onEnable() {

		// Set Default Listener
		instance.getServer().getPluginManager().registerEvents(new BackPackListener(), instance);

//		for (Player p : Bukkit.getOnlinePlayers()) {
//			if (!playerInvs.containsKey(p)) {
//				p.sendMessage(CONSOLENAME + " Reloaded with new Inventory");
//
//				playerInvs.put(p, new BackPackInventory(p));
//			}
//		}
	}

	@Override
	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("/sg")) {
			sender.sendMessage(CONSOLENAME + " Active");
			return true;
		}

		if (sender instanceof Player) {
			Player p = (Player) sender;

			switch (command.getName().toLowerCase()) {
			case "/sgnew":
				p.sendMessage("Creating BackPack");

				BackPackItem backpack = new BackPackItem();
				instance.backpacks.put(backpack.getUid(), backpack);

				p.getInventory().addItem(backpack);

				break;
//			case "/sgopen":
//				p.sendMessage("Opening BackPack");
//				backpacks.get(p).openInventory();
//
//				break;
			case "/sgsize":

				if (args.length < 1) {
					p.sendMessage("No Size Specified");
					return false;
				}

				if (args[0].chars().allMatch(Character::isDigit)) {
					backpacks.get(p).getBackpackinv().setSize(Integer.parseInt(args[0]));
				} else {
					p.sendMessage("Argument is not a valid number");
					return false;
				}

				break;
			default:
				p.sendMessage("No Player Command found");
				break;
			}
			return true;
		}

		return false;
	}
}
