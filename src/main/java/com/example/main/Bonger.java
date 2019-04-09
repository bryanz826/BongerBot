package com.example.main;

import javax.security.auth.login.LoginException;

import com.example.text.MessageListener;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;

public class Bonger
{
	public static boolean	DEBUG	= true;
	private JDABuilder		builder	= new JDABuilder(AccountType.BOT);

	public Bonger()
	{

		String token = "NTUwMTIxMzQyOTA1NzQ1NDQw.D1frzg.WYYGo5BGR73OAVHqj7JNeJb0LTE";
		builder.setToken(token);

		debug(this.getClass(), "...adding Bonger's features");
		builder.addEventListener(new MessageListener());
		builder.setStatus(OnlineStatus.ONLINE);
	}
	
	public void build() throws LoginException, InterruptedException {
		builder.build().awaitReady();
	}

	// Random Methods
	public static void space()
	{
		if (DEBUG) {
			System.out.println();
		}
	}

	public static void space(int count)
	{
		if (DEBUG) {
			for (int i = 0; i < count; i++)
				System.out.println();
		}
	}

	public static void debug(Class<?> location, String info)
	{
		if (DEBUG) {
			System.err.println(String.format("[%s] %s", location.getSimpleName(), info));
		}
	}

	public static void debugNote(String note, boolean inline)
	{
		if (DEBUG) {
			if (inline) System.err.print(note);
			else System.err.println(note);
		}
	}
}
