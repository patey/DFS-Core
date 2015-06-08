package com.gmail.Patey07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import org.slf4j.Logger;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;

public class setHomeCommand implements CommandExecutor {
	public Logger getLogger() {
	    return DFSCore.getLogger();
	}
	
	ConfigurationNode mainSetHomeNode;
	ConfigurationNode userSetHomeNode;
	ConfigurationLoader<CommentedConfigurationNode> userSetHomeConfig;
	public setHomeCommand(ConfigurationNode passedNode,ConfigurationNode passedNode2,ConfigurationLoader<CommentedConfigurationNode> passedConfig){
		mainSetHomeNode = passedNode;
		userSetHomeNode = passedNode2;
		userSetHomeConfig = passedConfig;
	}
	
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String home = null;
		if (args.hasAny("homename")){
			home = args.<String>getOne("homename").get().toLowerCase();
		}
		
    	if(src instanceof Player) {
    		Player player = (Player) src;
			if(!args.hasAny("homename")){
				List<Integer> playerHome = new ArrayList<Integer>();
				playerHome.add(player.getLocation().getBlockX());
				playerHome.add(player.getLocation().getBlockY());
				playerHome.add(player.getLocation().getBlockZ());
				userSetHomeNode.getNode("Players",player.getUniqueId().toString(),"Homes","Default").setValue(playerHome);
				try {
					userSetHomeConfig.save(userSetHomeNode);
	    			player.sendMessage(Texts.of("Default home set"));
	    			return CommandResult.success();
				} catch (IOException e) {
					getLogger().error("default home could not be edited for "+player.getName());
					return CommandResult.empty();
				}
			}else{
				List<Integer> playerHome = new ArrayList<Integer>();
				playerHome.add(player.getLocation().getBlockX());
				playerHome.add(player.getLocation().getBlockY());
				playerHome.add(player.getLocation().getBlockZ());
				userSetHomeNode.getNode("Players",player.getUniqueId().toString(),"Homes",home).setValue(playerHome);
				try {
					userSetHomeConfig.save(userSetHomeNode);
	    			player.sendMessage(Texts.of("Home "+home+" set"));
	    			return CommandResult.success();
				} catch (IOException e) {
					getLogger().error("home "+home+" could not be set for "+player.getName());
					return CommandResult.empty();
				}
			}
    	}else{
    		getLogger().error("Player command only");
    		return CommandResult.empty();
    	}
	}
}
