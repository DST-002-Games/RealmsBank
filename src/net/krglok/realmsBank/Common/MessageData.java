package net.krglok.realmsBank.Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;


import net.krglok.realmsBank.data.TextConstant;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @author Windu
 * @create 25.03.2016
 * 
 * description:
 * This class make a page formatting for the chat output of your 
 * messages.  
 * a page has normally 18 lines inclusively header
 * the LINE_LENGTH = 64
 *
 */
public class MessageData implements MessageInterface
{
	private static final int LINE_LENGTH = 64;
	
	public MessageData()
	{
	}

//	public Boolean isLogAll()
//	{
//		return TextConstant.isLogAll;
//	}
//	
//	public void setisLogAll(boolean value)
//	{
//		TextConstant.isLogAll = value;
//	}
	
	/**
	 * send List<String> to player chat
	 * no formatting or counting will done
	 * a page has normally 18 lines inclusively header
	 * prepare pages before
	 * @param player
	 * @param msgw
	 */
	public void sendPage(CommandSender sender, ArrayList<String> page)
	{
		if (page != null)
		{
			for (String line : page)
			{
				sender.sendMessage(line);
			}
		} else
		{
			sender.sendMessage(TextConstant.NO_PAGE);
		}
	}
	
	/**
	 * prepare output page for chat
	 * first line always repeated on each page, insert color FIRST_LINE_COLOUR
	 * a page contains 17 content lines  + 2 lines for header
	 * cut line length to LINE_LENGTH characters
	 * @param msg
	 * @return
	 */
	public HashMap<Integer,ArrayList<String>>  preparePage(ArrayList<String> msg)
	{
		HashMap<Integer,ArrayList<String>> pages = new HashMap<Integer,ArrayList<String>>();
		if (msg.size() == 0)
		{
			return pages;
		}
		String header =  msg.get(0);
		if (header.length() > LINE_LENGTH)
		{
			header = String.copyValueOf(header.toCharArray(), 0, LINE_LENGTH);
		}
		int pageMax = ((msg.size()-1)/TextConstant.pageLines())+1;
		int pageNr = 1;
		int lineCount = 0;
		ArrayList<String> pageLine = new ArrayList<String>();
		
		for (int i = 1; i < msg.size(); i++)
		{

			// add header to page
			if ((lineCount == 0) && (pageNr == 1))
			{
				pageLine.add(TextConstant.FIRST_LINE_COLOUR+TextConstant.FIRST_LINE+" ["+pageNr+"/"+pageMax+"]");
				pageLine.add(header);
			}
			lineCount++;
			String sLine = msg.get(i);
			if (sLine.length() > LINE_LENGTH)
			{
				sLine = String.copyValueOf(sLine.toCharArray(), 0, LINE_LENGTH);
			}
			pageLine.add(sLine);
			if (lineCount == TextConstant.pageLines())
			{
				pages.put(pageNr, pageLine);
				lineCount = 0;
				pageNr++;
				pageLine = new ArrayList<String>();
			}
		}
		pages.put(pageNr, pageLine);
		
		return pages;
	}
	
	/**
	 * printout page of message list. 
	 * if pageNumber > max pages then last page will be print
	 * @param sender is the command sender
	 * @param msg , ArrayList<String> to print
	 * @param doPage , pagenumber to print
	 */
	public int printPage(CommandSender sender, MessageList msg, Integer pageNumber)
	{
		if (msg.size() == 1)
		{
			msg.add(" ");
		}
		if (msg.size() > 0)
		{
			HashMap<Integer,ArrayList<String>> pages = preparePage(msg);
			if (pageNumber > pages.size())
			{
				pageNumber = 1;;
			}
			if (pageNumber < 1)
			{
				pageNumber = 1;
			}
			ArrayList<String> page = pages.get(pageNumber);
			sendPage(sender, page);
			return pageNumber;
		} else
		{
			sender.sendMessage(" ");
//			sender.sendMessage("NO data in message !");
			return 0;
		}
	}
	
	/**
	 * generate Test page with number of lines
	 * @param lines
	 * @return list of messages
	 */
	public ArrayList<String> createTestPage(int lines)
	{
		ArrayList<String> msg = new ArrayList<String>();
		msg.add("Test Page for Page Output");
		for (int i = 1; i < 37; i++)
		{
			msg.add("Message "+i);
		}
		return msg;
	}
	
	/**
	 * printout error message for no permissions
	 * @param sender
	 */
	public void errorPermission(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED+TextConstant.NO_PERMISSION);
	}

	public void errorArgs(CommandSender sender, RealmsSubCommandType subCommand)
	{
		sender.sendMessage(ChatColor.RED+TextConstant.NOT_ENOUGH_ARGUMENTS_FOR+" "+subCommand);
	}

	public void errorArgWrong(CommandSender sender, RealmsSubCommandType subCommand)
	{
		sender.sendMessage(ChatColor.RED+TextConstant.WRONG_ARGUMENTS+" "+subCommand);
	}

	public void errorSettleID(CommandSender sender, RealmsSubCommandType subCommand)
	{
		sender.sendMessage(ChatColor.RED+TextConstant.WRONG_SETTLEMNET_ID+" "+subCommand);
		
	}

	public void errorRegion(CommandSender sender, RealmsSubCommandType subCommand)
	{
		sender.sendMessage(ChatColor.RED+TextConstant.REGION_NOT_FOUND+" "+subCommand);
	}

	public void errorItem(CommandSender sender, RealmsSubCommandType subCommand)
	{
		sender.sendMessage(ChatColor.RED+TextConstant.WRONG_ITEMNAME+" "+subCommand);
	}

	/**
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.MessageInterface#errorFileIO(java.lang.String, java.lang.Exception)
	 */
	public void errorFileIO(String name, Exception e)
	{
		System.out.println(name+ TextConstant.FILE_IO_ERROR);
		System.out.println(e.getMessage());
	}

	
}
