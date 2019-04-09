package com.example.text.response;

public enum UnicodeSymbol
{
	/*
	 * Overlines
	 */
	OVERLINE_STRAIGHT_DOTTED_M("\u00AF"),
	OVERLINE_STRAIGHT_DOTTED_S("\uFE49"),
	OVERLINE_STRAIGHT_OVERLAP_S("\u203E"),
	OVERLINE_STRAIGHT_OVERLAP_L("\uFFE3"),
	OVERLINE_STRAIGHT_DOTTEDDOT_S("\uFE4A"),
	
	OVERLINE_WAVE_SINGLE_S("\uFE4B"),
	OVERLINE_WAVE_DOUBLE_S("\uFE4C"),
	
	OVERLINE_OTHER_U_S("\u02D8"),
	
	/*
	 * Other
	 */
	L_NORTHWEST("\uA712"),
	L_NORTHEAST("\uFF62")
	;

	private String code;

	private UnicodeSymbol(String code)
	{
		this.code = code;
	}

	public String toString()
	{
		return code;
	}
}
