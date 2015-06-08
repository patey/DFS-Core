package com.gmail.Patey07;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ninja.leaping.configurate.ConfigurationNode;

import org.slf4j.Logger;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerChatEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

public class ChatEvents {
	public Logger getLogger() {
	    return DFSCore.getLogger();
	}
	public Text getText(String message,TextColor color) {
	    return Texts.of(message).builder().color(color).build();
	}
	public Text whiteText(String message) {
	    return Texts.of(message).builder().color(TextColors.WHITE).build();
	}
	language lang = new language();
	ConfigurationNode chatNode;
	int range;
	
	public ChatEvents(ConfigurationNode passedNode){
		chatNode = passedNode;
		range = passedNode.getNode("Chat","Ranges","Regular").getInt();
	}
	
	public String[] getPlayer(String playerTemp){
		String[][] chatPlayers = PlayerEvents.playerList;
		String[] tempPlay = new String[4];
		String[] errPlay = {"ERROR", playerTemp ,"spirit","occ"};
		for (int i=0;i<chatPlayers.length; i++){
			if (chatPlayers[i][0].equals(playerTemp)){
				tempPlay = chatPlayers[i];
				return tempPlay;
			}
		}
				return errPlay;
		}
	
	@Subscribe
	public void onChat (PlayerChatEvent event) {
		String uuid = event.getUser().getUniqueId().toString();
		Player sender = event.getUser();
		String message = Texts.toPlain(event.getMessage());
		Text prefix = null;
		String[] tempPlayer = getPlayer(uuid);
		String name = tempPlayer[1];
		String race = tempPlayer[2];
		String cchat = tempPlayer[3];
		
		String pattern = "(?<="+name+").*";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(message);
		if (m.find( )){
			message = m.group(0);
			getLogger().info(m.group(0));
		}
		String remessage = lang.translate(message,race);
		
		if (race.equals("dwarf")){
			prefix = getText("[Dw]",TextColors.RED);
		}
		if (race.equals("elf")){
			prefix = getText("[El]",TextColors.GREEN);
		}
		if (race.equals("human")){
			prefix = getText("[Hu]",TextColors.BLUE);
		}
		if (race.equals("kobold")){
			prefix = getText("[Ko]",TextColors.DARK_PURPLE);
		}
		if (race.equals("spirit")){
			prefix = getText("[SP]",TextColors.GRAY);
		}
		
		event.setCancelled(true);
		
		for(Player k : event.getGame().getServer().getOnlinePlayers()){
			getLogger().info("going through players");
			String[] tempRecv = getPlayer(k.getUniqueId().toString());
			String nRecv = tempRecv[1];
			String rRecv = tempRecv[2];
			String cRecv = tempRecv[3];
			
			if (nRecv.equals(name)){
				String cleanChannel = "error";
				if (cchat.equals("global")){
					cleanChannel = "Global";
				}
				if (cchat.equals("race")){
					cleanChannel = "Race";
				}
				if (cchat.equals("icc")){
					cleanChannel = "ICC";
				}
				if (cchat.equals("occ")){
					cleanChannel = "OCC";
				}
				Text[] texts = {getText(cleanChannel,TextColors.GRAY),prefix,whiteText(name),whiteText(message)};
				k.sendMessage(Texts.join(texts));
			}else if (cRecv.equals("global")){
				Text[] texts = {getText("[Global]",TextColors.GRAY),prefix,whiteText(name),whiteText(message)};
				k.sendMessage(Texts.join(texts));
			}else if (cRecv.equals("race")){
				if (k.getLocation().getBlockPosition().compareTo(sender.getLocation().getBlockPosition().clone()) <= range){
					if (rRecv.equals(race) || cRecv.equals("occ")){
						Text[] texts = {getText("[Race]",TextColors.GRAY),prefix,whiteText(name),whiteText(message)};
						k.sendMessage(Texts.join(texts));
						getLogger().info(Texts.toPlain(Texts.join(texts)));
					}else{
						Text[] texts = {getText("[Race]",TextColors.GRAY),prefix,whiteText(name),whiteText(remessage)};
						k.sendMessage(Texts.join(texts));
						getLogger().info(Texts.toPlain(Texts.join(texts)));
					}
				}
			}else if (cRecv.equals("icc")){
				if (k.getLocation().getBlockPosition().compareTo(sender.getLocation().getBlockPosition().clone()) <= range){
					if (!rRecv.equalsIgnoreCase(race)){
						Text[] texts = {getText("[Race]",TextColors.GRAY),prefix,whiteText(name),whiteText(remessage)};
						k.sendMessage(Texts.join(texts));
						getLogger().info(Texts.toPlain(Texts.join(texts)));
					}else{
						Text[] texts = {getText("[ICC]",TextColors.GRAY),prefix,whiteText(name),whiteText(message)};
						k.sendMessage(Texts.join(texts));
						getLogger().info(Texts.toPlain(Texts.join(texts)));
					}
				}
			}else if (cRecv.equals("occ")){
				if (k.getLocation().getBlockPosition().compareTo(sender.getLocation().getBlockPosition().clone()) <= range){
					Text[] texts = {getText("[OCC]",TextColors.GRAY),prefix,whiteText(name),whiteText(remessage)};
					k.sendMessage(Texts.join(texts));
					getLogger().info(Texts.toPlain(Texts.join(texts)));
				}
			}
		}
	}
}
