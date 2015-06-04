package com.gmail.Patey07;

import org.slf4j.Logger;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerChatEvent;

import com.ntcreations.DwarfFortress.Player;
import com.ntcreations.DwarfFortress.language;

public class ChatEvents {
	
	public Logger getLogger() {
	    return DFSCore.getLogger();
	}
	
	@Subscribe
	public void onBreak (PlayerChatEvent event) {
		org.spongepowered.api.entity.player.Player sender = event.getUser();
		String race = sender.
		String prefix = null;
		String message = event.getMessage();
		language lang = new language();
		String remessage = lang.translate(message,race);
		String cchat = sender.getMetadata("chat").get(0).asString();
		
	}
}
