package com.gmail.Patey07;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
	String[][] playerList = null;
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
		for (int k=0; k<tempUUIDS.length; k++){
			playerUUIDS[k] = tempUUIDS[k].toString();
		}
		getLogger().info(Integer.toString(playerUUIDS.length));
		for (int i=0; i < playerUUIDS.length; i++){
			String player = playerUUIDS[i];
			List<String> tempList = rootNode.getNode("Players",player).getList(stringTransformer);
			String[] tempArr = new String[tempList.size()];
			tempArr = tempList.toArray(tempArr);
			playerList[i] = tempArr;
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
			if (playerList[i][0].equals(player) && playerList[i][1].equals(playerName)){
				jEvent.getUser().sendMessage(getMessage("Welcome back "+playerList[i][1]+"!"));
			}else if (playerName.equals(playerList[i][1]) && player != playerList[i][0]){
				jEvent.getUser().kick(getMessage("That's somone else's name!"));
			}else if (playerList[i][0].equals(player) && playerList[i][1] != playerName){
				jEvent.getUser().kick(getMessage("Sorry,"+playerName+"no multiple names untill sponge updates"));
			}else if (i == playerList.length){
				String[] tempArr = new String[playerList.length+1];
				String[][] temp2D = new String[tempArr.length][4];
				for (int j=0; j<tempArr.length; j++){
					if (j==tempArr.length){
						String[] tempPlayer = {player, playerName ,"spirit","global"};
						temp2D[j] = tempPlayer;
						playerList[j] = temp2D[j];
						eventNode.getNode("Players",playerList[i][0]).setValue(playerList[j]);
						try {
							eventConfig.save(eventNode);
						} catch (IOException e) {
							getLogger().error("user config could not be edited for "+playerName);
						}
					}else{
						temp2D[j] = playerList[j];
					}
				}
				
				
			}
		}
		
	}
}
