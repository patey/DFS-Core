package com.gmail.Patey07;

import java.util.ArrayList;
import java.util.List;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;
import org.spongepowered.api.world.Location;

import com.google.common.base.Function;
import com.google.inject.Inject;

public class homeCommand implements CommandExecutor {
	public Logger getLogger() {
	    return DFSCore.getLogger();
	}
	
	ConfigurationNode mainHomeNode;
	ConfigurationNode userHomeNode;
	ConfigurationLoader<CommentedConfigurationNode> userHomeConfig;
	Game homeGame;
	public homeCommand(ConfigurationNode passedNode,ConfigurationNode passedNode2,ConfigurationLoader<CommentedConfigurationNode> passedConfig,Game game){
		mainHomeNode = passedNode;
		userHomeNode = passedNode2;
		userHomeConfig = passedConfig;
		homeGame=game;
	}
	
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String home = null;
		if (args.hasAny("home")){
			home = args.<String>getOne("home").get();
		}
		
		Function<Object, Integer> intTransformer = new Function<Object,Integer>() {
		    public Integer apply(Object input) {
		        if (input instanceof Integer) {
		            return (Integer) input;
		        } else {
		            return null;
		        }
		    }
		};
		
    	if(src instanceof Player) {
    		Player player = (Player) src;
			if(!args.hasAny("home")){
				List<Integer> playerHome = new ArrayList<Integer>();
				playerHome = userHomeNode.getNode("Players",player.getUniqueId().toString(),"Homes","Default").getList(intTransformer);
				Location homLoc = new Location(homeGame.getServer().getWorld(player.getWorld().getName()).get(),playerHome.get(0),playerHome.get(1),playerHome.get(2));
				player.setLocation(homLoc);
    			player.sendMessage(Texts.of("Teleported home"));
    			return CommandResult.success();
			}else{
				List<Integer> playerHome = new ArrayList<Integer>();
				playerHome = userHomeNode.getNode("Players",player.getUniqueId().toString(),"Homes",home.toLowerCase()).getList(intTransformer);
				Location homLoc = new Location(homeGame.getServer().getWorld(player.getWorld().getName()).get(),playerHome.get(0),playerHome.get(1),playerHome.get(2));
				player.setLocation(homLoc);
    			player.sendMessage(Texts.of("Teleported to "+home));
    			return CommandResult.success();
			}
    	}else{
    		getLogger().error("Player command only");
    		return CommandResult.empty();
    	}
	}
}
