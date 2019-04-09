package com.example.text;

import com.example.main.Bonger;
import com.example.text.command.CommandManager;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter
{

	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		// ignore other bots
		if (event.getAuthor().isBot()) {
			return;
		}

//		JDA jda = event.getJDA();
//		long responseNumber = event.getResponseNumber();

		User author = event.getAuthor();
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();

		String rawMessage = message.getContentRaw();
		String displayMessage = message.getContentDisplay();

		// Message from Guild/Server
		if (event.isFromType(ChannelType.TEXT)) {
			Bonger.space(2);
			Bonger.debug(this.getClass(), "...detecting a server message:");

			TextChannel textChannel = event.getTextChannel();
			Member member = event.getMember();
			String name = member.getEffectiveName();

			// Specifying channel
			if (!textChannel.getName().equals("bonger-development")) return;
			System.out.printf("[%s]<%s>: %s\n", textChannel.getName(), name, displayMessage);

			// Handle Commands
			if (rawMessage.startsWith("~")) {
				Bonger.debug(this.getClass(), "...receiving a command request");
				new CommandManager(rawMessage).processAll(channel);
			}
		}

		// Message from DMs
		else if (event.isFromType(ChannelType.PRIVATE)) {
			Bonger.debug(this.getClass(), "...receiving a private message");
//			PrivateChannel privateChannel = event.getPrivateChannel();

			System.out.printf("[PRIV]<%s>: %s\n", author.getName(), displayMessage);
		}
	}
}
