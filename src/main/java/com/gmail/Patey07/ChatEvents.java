package com.gmail.Patey07;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	@Subscribe
	public void onChat (PlayerChatEvent event) {
		String uuid = event.getUser().getUniqueId().toString();
		Player sender = event.getUser();
		String message = Texts.toPlain(event.getMessage());
		String name = null;
		String race = null;
		Text prefix = null;
		String cchat = null;
		String[][] chatPlayers = PlayerEvents.playerList;
		for (int i=0; i<chatPlayers.length;i++){
			if (uuid.equals(chatPlayers[i][0])){
				name = chatPlayers[i][1];
				race = chatPlayers[i][2];
				cchat = chatPlayers[i][3];
			}else{
				race = "spirit";
			}
		}
		String pattern = "(?<="+name+").*";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(message);
		if (m.find( )){
			message = m.group(0);
			getLogger().info(m.group(0));
		}
		Text remessage = getText(lang.translate(message,race),TextColors.WHITE);
		
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
		if (cchat.equals("race")){
			getLogger().info("channel is race");
			for(Player k : event.getGame().getServer().getOnlinePlayers()){
				getLogger().info("going through players");
				for (int i=0;i<chatPlayers.length; i++){
					if (chatPlayers[i][1].equals(k.getName())){
						getLogger().info(chatPlayers[i][1]+"online");
						if (race.equals(chatPlayers[i][2])){
							getLogger().info("sender and receiver same race");
							Text[] texts = {getText("[Race]",TextColors.GRAY),prefix,whiteText(name),remessage};
							k.sendMessage(Texts.join(texts));
						}
					}
				}
			}
		}
		
		
	}
}
