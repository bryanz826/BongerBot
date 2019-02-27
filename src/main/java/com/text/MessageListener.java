package com.text;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter
{
	public MessageListener() {
		
	}
	
	//
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {

		// ignore other bots
		if(e.getAuthor().isBot()) {
			return;
		}
		
		/* *
		 * START RESPONSE
		 */
		
		
		System.out.println("We received a message from " + 
				e.getAuthor().getName() + ": " +
				e.getMessage().getContentDisplay()
		);
		
		String message = e.getMessage().getContentRaw();
		if(message.toLowerCase().contains("bing")) {
			e.getChannel().sendMessage("bong?").queue();
		}
	}
}
