package com.gmail.Patey07;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.player.PlayerBreakBlockEvent;
import org.spongepowered.api.event.entity.player.PlayerJoinEvent;

public class PlayerEvents {
	@Subscribe
	public void onBreak (PlayerJoinEvent jEvent) {
		UUID player = jEvent.getUser().getUniqueId();
		
	}
}
