package com.gmail.Patey07;

	import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import org.slf4j.Logger;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.ServerStartingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.config.ConfigDir;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.args.GenericArguments;
import org.spongepowered.api.util.command.spec.CommandSpec;

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
		
		@Inject @ConfigDir(sharedRoot = false) private File configDir;
		
		File coreFile = new File(this.configDir,"main.conf");
		ConfigurationLoader<CommentedConfigurationNode> mainConfig = HoconConfigurationLoader.builder().setFile(coreFile).build();
		File userFile = new File(this.configDir,"users.conf");
		ConfigurationLoader<CommentedConfigurationNode> userConfig = HoconConfigurationLoader.builder().setFile(userFile).build();
		
		@Subscribe
	    public void onServerStart(ServerStartingEvent event) {
			ConfigurationNode config = null;
			ConfigurationNode config2 = null;
			try {
				if (!coreFile.exists()) {
					coreFile.createNewFile();
					config = mainConfig.load();
					
					config.getNode("Chat","Global").setValue(true);
					config.getNode("Chat","Ranges","Regular").setValue(3);
					config.getNode("Chat","Ranges","Whisper").setValue(3);
					config.getNode("Chat","Ranges","Yell").setValue(75);
					config.getNode("Chat","Ranges","Province").setValue(750);
					mainConfig.save(config);
					getLogger().info("Default core config created at" + coreFile.getAbsolutePath().substring(0,coreFile.getAbsolutePath().lastIndexOf(File.separator)));
				}
				if (!userFile.exists()) {
					userFile.createNewFile();
					config2 = userConfig.load();
					String[][] playerList = { {"UUID1","Patey","human","global"},{"UUID2","Patey2","human2","global2"}};
					for (int i=0; i<playerList.length; i++){
						List<String> playerLista = new ArrayList<String>(Arrays.asList(playerList[i])); 
						config2.getNode("Players",playerList[i][0]).setValue(playerLista);
					}
					config2.getNode("test").setValue("test");
					userConfig.save(config2);
					getLogger().info("Default user config created at" + userFile.getAbsolutePath().substring(0,userFile.getAbsolutePath().lastIndexOf(File.separator)));
				}
				config = mainConfig.load();
				config2 = userConfig.load();
			} catch (IOException exception) {
			    getLogger().error("Configurations could not be loaded or created!");
			}
			
			CommandSpec channelCommand = CommandSpec.builder()
				    .description(Texts.of("/channel <channel>"))
				    .permission("DFSC.command.channel")
				    .arguments(
	                GenericArguments.onlyOne(GenericArguments.string(Texts.of("channel")))
	                )
				    .executor(new channelCommand(config,config2,userConfig))
				    .build();
				;
				CommandSpec raceCommand = CommandSpec.builder()
					    .description(Texts.of("/race <race>"))
					    .permission("DFSC.command.race")
					    .arguments(
		                GenericArguments.onlyOne(GenericArguments.string(Texts.of("race")))
		                )
					    .executor(new raceCommand(config,config2,userConfig))
					    .build();
					;
				
			event.getGame().getEventManager().register(this, new PlayerEvents(config2,userConfig));
			event.getGame().getEventManager().register(this, new ChatEvents(config));
			event.getGame().getCommandDispatcher().register(this, channelCommand, "channel", "c");
			event.getGame().getCommandDispatcher().register(this, raceCommand, "race", "r");
			getLogger().info("DwarfFortressSuite Core initialized");
	    }
	}
