package com.example.text.command;

import java.util.ArrayList;
import java.util.List;

import com.example.main.Bonger;

public class Command
{
	public enum CommandReference
	{
		HELP("~help"),
		B_TRANS("~btrans");

		private final String commandName;

		private CommandReference(String commandName)
		{
			this.commandName = commandName;
		}

		public String toString()
		{
			return commandName;
		}
	}

	public static List<String> getCommandList()
	{
		List<String> commandList = new ArrayList<String>(CommandReference.values().length);
		for (CommandReference cr : CommandReference.values())
			commandList.add(cr.toString());
		return commandList;
	}

	private String	commandLine;
	private String	name;
	private String	arguments;

	public Command(String commandLine)
	{
		Bonger.debug(this.getClass(), "Registering command identification...");
		this.commandLine = commandLine;

		// retrieve name
		int end = commandLine.indexOf(" ");
		if (end == -1) this.name = commandLine; // command has no arguments
		else this.name = commandLine.substring(0, end);

		// retrieve arguments
		int start = commandLine.indexOf(" ") + 1;
		if (start == 0) this.arguments = ""; // command has no arguments
		else this.arguments = commandLine.substring(start);
	}

	public boolean hasArguments()
	{
		return arguments.length() != 0;
	}

	/*
	 * Sets commandLine
	 */
	public void setCommandLine(String commandLine)
	{
		this.commandLine = commandLine;
	}

	/*
	 * Get commandLine
	 */
	public String getCommandLine()
	{
		return commandLine;
	}

	/*
	 * Get command name
	 */
	public String getName()
	{
		return name;
	}

	/*
	 * Get command arguments
	 */
	public String getArguments()
	{
		return arguments;
	}
}
