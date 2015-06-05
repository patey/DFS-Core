package com.gmail.Patey07;

	import java.io.File;
import java.io.IOException;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import org.slf4j.Logger;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStartedEvent;
import org.spongepowered.api.event.state.ServerStartingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.config.ConfigDir;
import org.spongepowered.api.service.config.DefaultConfig;

import com.google.inject.Inject;

	@Plugin(id = "DFSCore", name = "DFS-Core", version = "0.1.0")
	public class DFSCore {
		
		private static Logger logger;

		@Inject
		public DFSCore(Logger logger) {
		    DFSCore.logger = logger;
		}
		
		public static Logger getLogger() {
		    return logger;
		}
		
		@Inject @ConfigDir(sharedRoot = false) File configDir;
		
		File coreFile = new File(this.configDir,"main.conf");
		ConfigurationLoader<CommentedConfigurationNode> mainConfig = HoconConfigurationLoader.builder().setFile(coreFile).build();
		File userFile = new File(this.configDir,"users.conf");
		ConfigurationLoader<CommentedConfigurationNode> userConfig = HoconConfigurationLoader.builder().setFile(userFile).build();
		
		ConfigurationNode config;
		ConfigurationNode config2;
		
		@Subscribe
	    public void onServerStart(ServerStartingEvent event) {
			try {
				if (!coreFile.exists()) {
					coreFile.createNewFile();
					config = mainConfig.load();
					
					config.getNode("Global","Enabled").setValue(true);
					mainConfig.save(config);
					getLogger().info("Default core config created at" + coreFile.getAbsolutePath().substring(0,coreFile.getAbsolutePath().lastIndexOf(File.separator)));
				}
				if (!userFile.exists()) {
					userFile.createNewFile();
					config2 = userConfig.load();
					
					config2.getNode("uuid_here","Name").setValue("Patey");
					config2.getNode("uuid_here","Race").setValue("human");
					config2.getNode("uuid_here","Channel").setValue("global");
					userConfig.save(config2);
					getLogger().info("Default user config created at" + userFile.getAbsolutePath().substring(0,userFile.getAbsolutePath().lastIndexOf(File.separator)));
				}
				config = mainConfig.load();
				config2 = userConfig.load();
			} catch (IOException exception) {
			    getLogger().error("Configurations could not be loaded or created!");
			}
			
			getLogger().info("DwarfFortressSuite Core initialized");
	    }
	}
