package baron.rol.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleGUI extends JavaPlugin {

	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("/fireitem")) {
			sender.sendMessage("Plugin: FireItem Active");
			return true;
		}
		return false;
	}

}
