package com.example.text.command_list;

import com.example.text.command.Command.CommandReference;
import com.example.text.response.ResponseManager;

public class Help
{
	public static void run(ResponseManager rm, String arguments)
	{
		if (CommandReference.B_TRANS.toString().equals(arguments)) BTranslator.help(rm);
		// else if (CommandReference.)

		else; // help error
	}

	public static void menu(ResponseManager rm)
	{

	}
}
