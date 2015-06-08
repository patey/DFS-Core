package com.gmail.Patey07;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.google.inject.Inject;

public class tpCommand implements CommandExecutor {
	public Logger getLogger() {
	    return DFSCore.getLogger();
	}
	@Inject public Game game;
	
	ConfigurationNode mainTPNode;
	ConfigurationNode userTPNode;
	ConfigurationLoader<CommentedConfigurationNode> userTPConfig;
	public tpCommand(ConfigurationNode passedNode,ConfigurationNode passedNode2,ConfigurationLoader<CommentedConfigurationNode> passedConfig){
		mainTPNode = passedNode;
		userTPNode = passedNode2;
		userTPConfig = passedConfig;
	}
	
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		String playerMatch= args.<String>getOne("player").get().toLowerCase();
		List<Player> matchedPlayers = new ArrayList<Player>();
    	if(src instanceof Player) {
    		Player player = (Player) src;
			for(Player k : game.getServer().getOnlinePlayers()){
				if (k.getName().startsWith(playerMatch)){
					matchedPlayers.add(k);
				}
			}
			if (matchedPlayers.size() == 1){
				player.getLocation().setPosition(matchedPlayers.get(1).getLocation().getPosition());
				return CommandResult.success();
			}else{
				player.sendMessage(Texts.of(Integer.toString(matchedPlayers.size())+" matches found,need more characters to compare"));
				return CommandResult.empty();
			}
    	}else{
    		getLogger().info("Player command only");
    		return CommandResult.empty();
    	}
	}
}