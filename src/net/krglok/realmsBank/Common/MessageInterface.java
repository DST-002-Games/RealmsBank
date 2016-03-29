package net.krglok.realmsBank.Common;

import java.util.ArrayList;
import java.util.HashMap;


import org.bukkit.command.CommandSender;
/**
 * @author Windu
 * @create 25.03.2016
 * 
 * description:
 * Class for handling paged output to chat
 * and some error messages needed for the CommandParser
 *
 */
public interface MessageInterface
{
	
	/**
	 * send List<String> to player chat
	 * no formatting or counting will done
	 * a page has normally 18 lines inclusively header
	 * prepare pages before
	 * @param player
	 * @param msg
	 */
	public void sendPage(CommandSender sender, ArrayList<String> page);
	
	/**
	 * prepare output page for chat
	 * first line always repeated on each page, insert color FIRST_LINE_COLOUR
	 * a page contains 17 content lines  + 2 lines for header
	 * cut line length to 50 characters
	 * @param msg
	 * @return
	 */
	public HashMap<Integer,ArrayList<String>>  preparePage(ArrayList<String> msg);
	
	/**
	 * printout page of message list. 
	 * if pageNumber > max pages then last page will be print
	 * @param sender is the command sender
	 * @param msg , ArrayList<String> to print
	 * @param doPage , pagenumber to print
	 */
	public int printPage(CommandSender sender, MessageList msg, Integer pageNumber);
	
	/**
	 * generate Test page with number of lines
	 * @param lines
	 * @return list of messages
	 */
	public ArrayList<String> createTestPage(int lines);
	
	/**
	 * printout error message for no permissions
	 * @param sender
	 */
	public void errorPermission(CommandSender sender);

	public void errorArgs(CommandSender sender, RealmsSubCommandType subCommand);

	public void errorArgWrong(CommandSender sender, RealmsSubCommandType subCommand);

	public void errorSettleID(CommandSender sender, RealmsSubCommandType subCommand);

	public void errorRegion(CommandSender sender, RealmsSubCommandType subCommand);

	public void errorItem(CommandSender sender, RealmsSubCommandType subCommand);
	
	public void errorFileIO(String name, Exception e);
	

}
