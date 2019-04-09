package com.example.text.response;

import java.awt.Color;
import java.util.Collections;

import com.example.main.Bonger;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

public class ResponseManager
{
	public enum ResponseReference
	{
		PLAIN,
		COMMAND_HELP,
		COMMAND_SUCCESS,
		COMMAND_ERROR
	}

	public enum EmbedMeasure
	{
		LARGE(24), // based on large overlines
		MEDIUM(20),
		SMALL(15);

		private int size;

		EmbedMeasure(int size)
		{
			this.size = size;
		}
		
		public String getUnderscores() {
			return String.join("", Collections.nCopies(size, "\uFFE3"));
		}
		
		public int getSize() {
			return size;
		}
	}

	private EmbedBuilder UI;

	public ResponseManager()
	{
		Bonger.debug(this.getClass(), "Building a response platform...");
		UI = new EmbedBuilder();
	}

	public ResponseManager(ResponseReference type)
	{
		UI = new EmbedBuilder();

		switch (type)
		{
			case PLAIN:
			{
				Bonger.debug(this.getClass(), "...using PLAIN preset");
				break;
			}

			case COMMAND_HELP:
			{
				Bonger.debug(this.getClass(), "...using COMMAND_HELP preset");

				UI.setColor(new Color(255, 255, 170));
				UI.setThumbnail("https://i.imgur.com/D705jDu.png");
				break;
			}
			
			case COMMAND_SUCCESS:
			{
				Bonger.debug(this.getClass(), "...using COMMAND_SUCCESS preset");

				UI.setColor(new Color(170, 255, 170));
				UI.setThumbnail("https://i.imgur.com/D705jDu.png");
				break;
			}

			case COMMAND_ERROR:
			{
				Bonger.debug(this.getClass(), "ERROR! ...using COMMAND_ERROR preset");

				UI.setColor(new Color(255, 170, 170));
				UI.setThumbnail("https://i.imgur.com/D705jDu.png");
				UI.setTitle(alignCenterApprox(EmbedMeasure.MEDIUM, "Oh no!"));
				UI.setDescription(EmbedMeasure.MEDIUM.getUnderscores());
				break;
			}
		}
	}

	public EmbedBuilder getUI()
	{
		return UI;
	}

	public MessageEmbed getResponse()
	{
		if (UI.isEmpty()) throw new IllegalStateException("The MessageEmbed is empty!");
		return UI.build();
	}

	public String alignCenterApprox(EmbedMeasure type, String text)
	{
		final float spaceToOverscoreRatio = 4.545f;
		final float avgTextToSpaceRatio = 2f; //106 spaces LARGE, 88 MEDIUM
		
		float rowSize = type.getSize() * spaceToOverscoreRatio;
		float textSize = text.length() * avgTextToSpaceRatio;

		System.out.println(rowSize + " " + textSize);
		int padCount = Math.round(rowSize / 2 - textSize / 2) - 2; // -2 accounts for 1st symbol
		String padSpace = String.join("", Collections.nCopies(padCount, " "));
		return UnicodeSymbol.L_NORTHWEST + padSpace + text;
	}
}
