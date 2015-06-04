package com.gmail.Patey07;

	import java.io.File;
import java.io.IOException;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import org.slf4j.Logger;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.ServerStartedEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.config.DefaultConfig;

import com.google.inject.Inject;

	@Plugin(id = "DFSCore", name = "DFS Core", version = "0.1.0")
	public class DFSCore {
		
		private static Logger logger;

		@Inject
		public DFSCore(Logger logger) {
		    DFSCore.logger = logger;
		}
		
		public static Logger getLogger() {
		    return logger;
		}
		
		@Inject
		@DefaultConfig(sharedRoot = true)
		private File defaultConfig;

		@Inject
		@DefaultConfig(sharedRoot = true)
		private ConfigurationLoader<CommentedConfigurationNode> configManager;
		
		@Subscribe
	    public void onServerStart(ServerStartedEvent event) {
			ConfigurationNode config = null;

			try {
			     if (!defaultConfig.exists()) {
			        defaultConfig.createNewFile();
			        config = configManager.load();

			        config.getNode("version").setValue(1);
			        config.getNode("doStuff").setValue(true);
			        config.getNode("doMoreStuff").setValue(false);
			        configManager.save(config);
			    }
			    config = configManager.load();

			} catch (IOException exception) {
			    getLogger().error("The default configuration could not be loaded or created!");
			}
			getLogger().info("DwarfFortress Suite Core initialized");
	    }
	}
