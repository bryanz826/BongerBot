package com.main;

import javax.security.auth.login.LoginException;

import com.text.MessageListener;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;

public class Bonger
{
	public Bonger() throws LoginException, InterruptedException {
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		String token = "NTUwMTIxMzQyOTA1NzQ1NDQw.D1frzg.WYYGo5BGR73OAVHqj7JNeJb0LTE";
		builder.setToken(token);
		
		builder.addEventListener(new MessageListener());
		
		builder.setStatus(OnlineStatus.ONLINE);
		builder.build().awaitReady();
	}
}
