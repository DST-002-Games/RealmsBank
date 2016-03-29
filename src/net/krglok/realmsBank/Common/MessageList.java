package net.krglok.realmsBank.Common;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Windu
 * @create 26.03.2016
 * <pre>
 * description:
 * Text List for output to chat.
 * Has a text replacer function for variables that begin with <$>
 * like :
 * - $player  Playername
 * - $plugin  Pluginname
 * - $age     Inhalt der Age Variable des Plugin, Ingametage from start
 * 
 * </pre>
 */
public class MessageList extends ArrayList<String>
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * search for replace variable in text and replace it.
	 * Hint! the length of the line can be changed.
	 * 
	 * @param value
	 * @param replaceList
	 * @return
	 */
	public boolean add(String value, HashMap<String,String> replaceList)
	{
		if (value.contains("$"))
		{
			String[] splitted = value.split(" ");
			for (int j = 0; j < splitted.length; j++)
			{
				if (replaceList.keySet().contains(splitted[j]))
				{
					splitted[j] = replaceList.get(splitted[j]);
				}
			}
			value = "";
			for (int i = 0; i < splitted.length; i++)
			{
				value = value + splitted[i]+" ";
			}
			this.add(value);
		} else
		{
			this.add(value);
			return true;
		}
		return false;
	}
	
}
