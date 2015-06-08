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

public class raceCommand implements CommandExecutor {
	public Logger getLogger() {
	    return DFSCore.getLogger();
	}
	
	ConfigurationNode rconfigNode;
	ConfigurationNode raceNode;
	ConfigurationLoader<CommentedConfigurationNode> raceConfig;
	public raceCommand(ConfigurationNode passedNode,ConfigurationNode passedNode2,ConfigurationLoader<CommentedConfigurationNode> passedConfig){
		rconfigNode = passedNode;
		raceNode = passedNode2;
		raceConfig = passedConfig;
	}
	
	public void setRace(String chan,Player player){	
		String[][] raceArr = PlayerEvents.playerList;
		String[] tempArr = new String[4];
		for (int i=0; i<raceArr.length; i++){
			if (raceArr[i][0].equals(player.getUniqueId().toString())){
				tempArr = raceArr[i];
				tempArr[2] = chan;
				raceArr[i] = tempArr;
				List<String> tempList = new ArrayList<String>(Arrays.asList(tempArr));
				raceNode.getNode("Players",player.getUniqueId().toString(),"Data").setValue(tempList);
				try {
					raceConfig.save(raceNode);
					PlayerEvents.playerList = raceArr;
				} catch (IOException e) {
					getLogger().error("user config could not be edited for "+player.getName());
				}
			}
		}
	}
	 public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
	    	
	    	String race = args.<String>getOne("race").get().toLowerCase();
	    	if(src instanceof Player) {
	    		Player player = (Player) src;
	    		if (race.equals("human")||race.equals("elf")||race.equals("dwarf")||race.equals("kobold")||race.equals("spirit")){
	    			setRace(race,player);
	    			player.sendMessage(Texts.of("Your race is now set to "+race));
	    			return CommandResult.success();
	    		}else{
	    			player.sendMessage(Texts.of("<current race will display here later>"));
	    			return CommandResult.success();
	    		}
	    		
	    	}else{
	    		return CommandResult.empty();
	    	}
	    }
}
