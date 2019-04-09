package com.example.text.command;

import com.example.main.Bonger;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;

public class CommandManager
{
	private Command command;
	private MessageEmbed response;

	public CommandManager(String commandLine)
	{
		Bonger.debug(this.getClass(), "Launching...");
		this.command = new Command(commandLine);
		this.response = null;
	}

	/*
	 * Handles all necessary command operations Note: Do not use this in conjunction
	 * with individual operations
	 */
	public void processAll(MessageChannel channel)
	{
		acceptAllCases();

		if (isValid()) {
			process();
			send(channel);
		}
	}

	/*
	 * Accepts command in different letter cases
	 */
	public void acceptAllCases()
	{
		Bonger.debug(this.getClass(), "...reformatting command appropriately");
		command.setCommandLine((command.getName().toLowerCase() + " " + command.getArguments()).trim());
		
		// Accounts for Help command
		if (command.getName().equals("~help")) 
			command.setCommandLine(command.getCommandLine().toLowerCase());
	}

	/*
	 * Checks the validity of the command
	 */
	public boolean isValid()
	{
		Bonger.debug(this.getClass(), "...checking validity of command");
		return Command.getCommandList().contains(command.getName());
	}

	/*
	 * Sets response accordingly to arguments
	 */
	public void process()
	{
		Bonger.debug(this.getClass(), "...processing command");
		
		CommandProcessor cp = new CommandProcessor(command);
		cp.process();
		response = cp.getResponseManager().getResponse();
	}
	
	/*
	 * Sends response
	 */
	public void send(MessageChannel channel)
	{
		Bonger.debug(this.getClass(), "...sending response");
		
		channel.sendMessage(response).queue();
//		channel.sendFile(new File("https://i.imgur.com/zeROClB.png"), "bong");
				//, new MessageBuilder(finalResponse).build());

		Bonger.debug(this.getClass(), "Response sent!");
	}
}
