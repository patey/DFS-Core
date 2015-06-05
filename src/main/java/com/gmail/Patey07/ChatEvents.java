package com.gmail.Patey07;

import java.util.UUID;

import org.slf4j.Logger;
import org.spongepowered.api.data.manipulator.entity.PlayerCreatedData;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerChatEvent;
import org.spongepowered.api.text.Text;

public class ChatEvents {
	
	public Logger getLogger() {
	    return DFSCore.getLogger();
	}
	
	@Subscribe
	public void onBreak (PlayerChatEvent event) {
		UUID sender = event.getUser().getUniqueId();
		Text message = event.getMessage();
		
	}
}
