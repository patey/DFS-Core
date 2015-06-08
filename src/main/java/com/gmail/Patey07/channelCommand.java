package com.gmail.Patey07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

public class channelCommand implements CommandExecutor {
	public Logger getLogger() {
	    return DFSCore.getLogger();
	}
	
	ConfigurationNode configNode;
	ConfigurationNode chanNode;
	ConfigurationLoader<CommentedConfigurationNode> chanConfig;
	public channelCommand(ConfigurationNode passedNode,ConfigurationNode passedNode2,ConfigurationLoader<CommentedConfigurationNode> passedConfig){
		configNode = passedNode;
		chanNode = passedNode2;
		chanConfig = passedConfig;
	}
	public void setChan(String chan,Player player){	
		String[][] chanArr = PlayerEvents.playerList;
		String[] tempArr = new String[4];
		for (int i=0; i<chanArr.length; i++){
			if (chanArr[i][0].equals(player.getUniqueId().toString())){
				tempArr = chanArr[i];
				tempArr[3] = chan;
				chanArr[i] = tempArr;
				List<String> tempList = new ArrayList<String>(Arrays.asList(tempArr));
				chanNode.getNode("Players",player.getUniqueId().toString()).setValue(tempList);
				try {
					chanConfig.save(chanNode);
					PlayerEvents.playerList = chanArr;
				} catch (IOException e) {
					getLogger().error("user config could not be edited for "+player.getName());
				}
			}
		}
	}
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
    	
    	String chan = args.<String>getOne("channel").get().toLowerCase();
    	if(src instanceof Player) {
    		Player player = (Player) src;
    		if (chan.equals("global")){
    			if (configNode.getNode("Chat","Global").getBoolean() == true){
    				setChan(chan,player);
    				player.sendMessage(Texts.of("Your channel is now set to Global"));
    				return CommandResult.success();
    			}else{
    			player.sendMessage(Texts.of("Global chat is currently disabled"));
    			player.sendMessage(Texts.of("need help? try <command not yet done>"));
    			return CommandResult.empty();
    			}
    		}else if (chan.equals("race")||chan.equals("icc")||chan.equals("occ")){
    			setChan(chan,player);
    			player.sendMessage(Texts.of("Your channel is now set to "+chan));
    			return CommandResult.success();
    		}else{
    			player.sendMessage(Texts.of("<current channel will display here later>"));
    			return CommandResult.success();
    		}
    		
    	}else{
    		return CommandResult.empty();
    	}
    }
}
