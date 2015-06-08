package com.gmail.Patey07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import org.slf4j.Logger;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerJoinEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;

import com.google.common.base.Function;

public class PlayerEvents {
	static String[][] playerList = null;
	ConfigurationNode eventNode;
	ConfigurationLoader<CommentedConfigurationNode> eventConfig;
	
	public Logger getLogger() {
	    return DFSCore.getLogger();
	}
	
	public PlayerEvents(ConfigurationNode rootNode,ConfigurationLoader<CommentedConfigurationNode> userConfig){
		Function<Object, String> stringTransformer = new Function<Object,String>() {
		    public String apply(Object input) {
		        if (input instanceof String) {
		            return (String) input;
		        } else {
		            return null;
		        }
		    }
		};
		Object[] tempUUIDS = rootNode.getNode("Players").getChildrenMap().keySet().toArray();
		playerList = new String[tempUUIDS.length][4];
		String[] playerUUIDS = new String[tempUUIDS.length];
		if (tempUUIDS.length != 0){
			for (int k=tempUUIDS.length-1; k<tempUUIDS.length; k++){
				playerUUIDS[k] = tempUUIDS[k].toString();
			}
		}
		if (playerUUIDS.length != 0){
			for (int i=0; i < playerUUIDS.length; i++){
				String player = playerUUIDS[i];
				List<String> tempList = rootNode.getNode("Players",player).getList(stringTransformer);
				String[] tempArr = new String[tempList.size()];
				tempArr = tempList.toArray(tempArr);
				playerList[i] = tempArr;
			}
		}
		eventNode = rootNode;
		eventConfig = userConfig;
	}
	
	public Text getMessage(String message) {
	    return Texts.of(message).builder().color(TextColors.BLUE).build();
	}
	
	@Subscribe
	public void onJoin (PlayerJoinEvent jEvent) {
		String player = jEvent.getUser().getUniqueId().toString();
		String playerName = jEvent.getUser().getName();
		for (int i=0; i<playerList.length; i++){
			getLogger().info("i = "+Integer.toString(i));
			if (playerList[i][0].equals(player) && playerList[i][1].equals(playerName)){
				jEvent.getUser().sendMessage(getMessage("Welcome back "+playerList[i][1]+"!"));
			}else if (playerName.equals(playerList[i][1]) && player != playerList[i][0]){
				jEvent.getUser().kick(getMessage("That's somone else's name!"));
			}else if (playerList[i][0].equals(player) && playerList[i][1] != playerName){
				jEvent.getUser().kick(getMessage("Sorry,"+playerName+"no multiple names untill sponge updates"));
			}else if (i == playerList.length && playerList[i][0] != player && playerList[i][1] != playerName){
				String[][] tempArr = new String[playerList.length+1][4];
				for (int j=0; j<tempArr.length; j++){
					getLogger().info("j = "+Integer.toString(i));
					if (j == tempArr.length-1){
						String[] tempPlayer = {player, playerName ,"spirit","global"};
						tempArr[j] = tempPlayer;
						List<String> savePlayer = new ArrayList<String>(Arrays.asList(tempPlayer)); 
						eventNode.getNode("Players",player).setValue(savePlayer);
						playerList = new String[tempArr.length][4];
						playerList = tempArr;
						try {
							eventConfig.save(eventNode);
						} catch (IOException e) {
							getLogger().error("user config could not be edited for "+playerName);
						}
					}else{
						tempArr[j] = playerList[j];
					}
				}
			}else if (playerList.length <=1){
				getLogger().info("playerList is 0 or 1");
				String[][] tempArr = new String[playerList.length+1][4];
				String[] tempPlayer = {player, playerName ,"spirit","global"};
				tempArr[playerList.length] = tempPlayer;
				List<String> savePlayer = new ArrayList<String>(Arrays.asList(tempPlayer)); 
				eventNode.getNode("Players",player).setValue(savePlayer);
				try {
					eventConfig.save(eventNode);
					playerList = new String[tempArr.length][4];
					playerList = tempArr;
				} catch (IOException e) {
					getLogger().error("user config could not be edited for "+playerName);
				}
			}else{
				getLogger().info(playerName+" not found in list, list is populated");
			}
		}
	}
}
