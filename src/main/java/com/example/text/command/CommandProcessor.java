package com.example.text.command;

import com.example.main.Bonger;
import com.example.text.command.Command.CommandReference;
import com.example.text.command_list.BTranslator;
import com.example.text.command_list.Help;
import com.example.text.command_list.Unrecognized;
import com.example.text.response.ResponseManager;
import com.example.text.response.ResponseManager.ResponseReference;

/*
 * Processes a Command object and outputs a Response object
 */
public class CommandProcessor
{
	private Command			command;
	private ResponseManager	rm;

	public CommandProcessor(Command command)
	{
		this.command = command;
		rm = null;
	}

	public void process()
	{
		String commandName = command.getName();
		
		if (commandName.equals(CommandReference.HELP.toString())) {
			Bonger.debug(this.getClass(), "...retrieving help data");

			if (!command.hasArguments()) {
				Bonger.debug(this.getClass(), "...retrieving help menu data");

				rm = new ResponseManager(ResponseReference.PLAIN); // need help menu format
				Help.menu(rm);
			} else {
				rm = new ResponseManager(ResponseReference.COMMAND_HELP);
				Help.run(rm, command.getArguments());
			}
		}

		else if (commandName.equals(CommandReference.B_TRANS.toString())) {
			Bonger.debug(this.getClass(), "...retrieving b-translator data");

			if (!command.hasArguments()) {
				Bonger.debug(this.getClass(), "...retrieving b-translator error data");

				rm = new ResponseManager(ResponseReference.COMMAND_ERROR);
				BTranslator.error(rm);
			} else {
				rm = new ResponseManager(ResponseReference.COMMAND_SUCCESS);
				BTranslator.run(rm, command.getArguments());
			}
		}

		else {
			Bonger.debug(this.getClass(), "...retrieving unrecognized command data");

			rm = new ResponseManager(ResponseReference.PLAIN); // need unrecognized format
			Unrecognized.run(rm);
		}

		Bonger.debug(this.getClass(), "...added specified data to response");
	}

	public ResponseManager getResponseManager()
	{
		if (rm == null) throw new IllegalStateException("Response Manager is null!");
		return rm;
	}
}
