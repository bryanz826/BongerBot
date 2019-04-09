package com.example.main;

import javax.security.auth.login.LoginException;

public class BongerRunner implements Runnable
{
	private Bonger bonger;

	public BongerRunner()
	{
		Bonger.debug(this.getClass(), "Constructing a Bonger...");
		bonger = new Bonger();
	}

	public static void main(String[] args)
	{
		new BongerRunner().run();
	}

	@Override
	public void run()
	{
		try {
			bonger.build();

			Bonger.debug(this.getClass(), "A Bonger has arrived!");
		} catch (LoginException e) {
			System.err.println("LoginException occurred. Login Error");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.err.println("InterruptedException occurred. awaitReady()");
			e.printStackTrace();
		}
	}
}
