package net.krglok.realmsBank.Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.krglok.realmsBank.RealmsBank;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

/**
 * <pre>
 * base class for player command executed in the plugin.
 * the command is specified by 
 * - RealmsCommandType, this is the registered commandname
 * - RealmsSubCommandType, this is the 1th parameter
 * the first parameter is always the subcommand
 * 
 * @author oduda
 *
 *</pre>
 */
public abstract class aRealmsCommand implements iRealmsCommand
{
	private RealmsCommandType command;
	private RealmsSubCommandType subCommand;
	protected String[] description;
	protected int requiredArgs;
	protected MessageList errorMsg;
	protected boolean isParserError; 
	protected String helpPage;
	
	public aRealmsCommand(RealmsCommandType command, RealmsSubCommandType subCommand)
	{
		this.command = command; 
		this.subCommand = subCommand;
		this.description = null;
		this.requiredArgs = 0;
		this.errorMsg = new MessageList();
		this.isParserError = false;
		this.helpPage = "";
	}


	public boolean isParserError()
	{
		return isParserError;
	}


	public void setParserError(boolean isParserError)
	{
		this.isParserError = isParserError;
	}


	/**
	 * <pre>
	 * give a list of classnames as String array
	 * set for every parameter of the command the type like <class>.class.getName()
	 * example : for a int , string it shows 
	 * new String[] { int.class.getName(), String.class.getName() }
	 * 
	 * @return array of class names
	 * </pre>
	 */
	@Override
	public abstract String[] getParaTypes();
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}

	/**
	 * write here the code for command execution
	 */
	@Override
	public abstract void execute(RealmsBank plugin, CommandSender sender);
//	{
//		// TODO Auto-generated method stub
//	MessageList msg = new MessageList();	
//	
//	plugin.getMessageData().printPage(sender, msg, page);
//	page = 1;
//	
//	}

	/**
	 * write her code for checking permissions and other conditions for the command
	 */
	@Override
	public abstract boolean canExecute(RealmsBank plugin, CommandSender sender);
//	{
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public String[] getDescription()
	{
		return description;
	}

	@Override
	public void setDescription(String[] newDescription)
	{
		this.description = newDescription;
	}

	
	@Override
	public RealmsCommandType command()
	{
		return this.command;
	}
	
	@Override
	public RealmsSubCommandType subCommand()
	{
		return this.subCommand;
	}


	@Override
	public ArrayList<String> getDescriptionString()
	{
		ArrayList<String> msg = new ArrayList<String>();
		for (int i = 0; i < description.length; i++)
		{
			msg.add(description[i].toString());
		}
		return msg;
	}
	
	@Override
	public int getRequiredArgs()
	{
		return requiredArgs;
	}

	@Override
	public  ArrayList<String> getErrorMsg()
	{
		return this.errorMsg;
	}
	
	public void addErrorMsg (String s)
	{
		this.errorMsg.add(s);
	}

	/**
	 * check if sender is an op or has realms.admin permission 
	 * set a errorMessage in the global message storage.
	 * @param sender
	 * @return
	 */
	public boolean isOpOrAdminMsg(CommandSender sender)
	{
		if (isOpOrAdmin(sender) == false)
		{
			errorMsg.add("Only for Ops and Admins !  ");
			return false;
		}
		return true;
	}
	
	/**
	 * check if sender is an op or has realms.admin permission
	 * silent check without message generation 
	 * 
	 * @param sender
	 * @return
	 */
	public boolean isOpOrAdmin(CommandSender sender)
	{
		if (sender.isOp() == true)
		{
			return true;
		}
		if (sender instanceof Player)
		{
			if (sender.hasPermission(RealmsPermission.ADMIN.getValue()) == false)
			{
//				errorMsg.add("You are not an Admins !  ");
				return false;
			}
		}
		return true;
	}
	

	public boolean hasItem( CommandSender sender, String itemRef, int amount)
	{
		if ((sender instanceof Player) == false)
		{
			errorMsg.add("You are NOT a Player !");
			return false;
		}
		Player player = (Player) sender;
		
		if (player.getInventory().contains(Material.getMaterial(itemRef), amount) == false)
		{
			errorMsg.add("You have NOT enough items !");
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public boolean hasMoney(RealmsBank plugin, CommandSender sender,  double amount)
	{
		if ((sender instanceof Player) == false)
		{
			errorMsg.add("You are NOT a Player !");
			return false;
		}
		if (plugin.economy() != null)
		{
			errorMsg.add("NO economy is installed !");
			return false;
		}
		Player player = (Player) sender;
		
		if (plugin.economy().has(player.getName(),  amount) == false)
		{
			errorMsg.add("You have NOT enough money !");
			return false;
		}
		return false;
	}
	
	public ArrayList<String> getCommandDescription(aRealmsCommand[] cmdList
			, RealmsCommandType commandType
			, RealmsSubCommandType subCommandType)
	{
		for (iRealmsCommand cmd : cmdList)
		{
			if ((cmd.command() == commandType) 
				&& (cmd.subCommand() == subCommandType)
				) 
			{
				return cmd.getDescriptionString();
			}
		}
		ArrayList<String> msg = new ArrayList<String>();
//		msg.add(ChatColor.RED+"Nothig found for "+helpPage );
		return msg;
	}


	public MessageList makeHelpPage(aRealmsCommand[] cmdList, MessageList msg, String search )
	{
		boolean isFound = false;
		System.out.println("Word: "+search);
		// list all commands
    	if (search == "")
    	{
	    	msg.add(ChatColor.GREEN+"{"+ConfigBasis.PLUGIN_NAME+"]   Help Page");
			msg.addAll(getDescriptionString());
			if (this.subCommand() != RealmsSubCommandType.HELP)
			{
				for (iRealmsCommand cmd : cmdList)
				{
					if ((cmd.subCommand() != RealmsSubCommandType.NONE) 
						&& (this.command() == cmd.command())
						)
					{
						String line = cmd.getDescription()[0];
						msg.add(line);
					}
				}
				
			} else
			{
				for (iRealmsCommand cmd : cmdList)
				{
					if ((cmd.subCommand() != RealmsSubCommandType.NONE) 
						&& (this.command() != cmd.command())
						)
					{
						msg.addAll(cmd.getDescriptionString());
					}
				}
			}
    	} else
    	{  // list commands with keyword
    		if (isFound == false)
    		{
    	    	msg.add(ChatColor.GREEN+"{"+ConfigBasis.PLUGIN_NAME+"]   Help Page"+ChatColor.RED+"{ CaseSensitive!!}");
    	    	msg.add(ChatColor.YELLOW+"{ Search for word : "+search+" }");

//				System.out.println("search in CommandList");
    			for (aRealmsCommand cmd : cmdList)
    			{
//					System.out.println("search in "+cmd.command().name());
    				for (String desc : cmd.getDescription())
    				{
//    					System.out.println("search in "+cmd.command().name()+":"+desc);
    					if (desc.contains(search) )
    					{
    	    				msg.add(cmd.getDescriptionString().get(0));
    	    				msg.add(">>"+desc);
    					}
    				}
    			}
    		}
    	}
		return msg;

	}
	
	/**
	 * overwrite the book with the new content
	 * 
	 * @param book
	 * @param msg
	 * @param author
	 * @param title
	 * @return
	 */
	public ItemStack writeBook(ItemStack book, ArrayList<String> msg, String author, String title)
	{
		final BookMeta bm = (BookMeta) book.getItemMeta();
		if (bm.hasPages())
		{
			// Pages alle loeschen
			ArrayList<String> newPages = new ArrayList<String>();
			bm.setPages(newPages);
		}
		String sPage = "";
		int line = 0;
		int bookPage = 0;
		for (int i=0; i < msg.size(); i++)
		{
			line++;
			sPage = sPage+msg.get(i);
			if ((line > 11) && (bookPage < 50))
			{
				bm.addPage(sPage);
				sPage = "";
				line = 0;
				bookPage++;
			}
		}
		if ((sPage != "") && (bookPage < 50))
		{
			bm.addPage(sPage);
		}
		bm.setAuthor(author);
		bm.setTitle(title);
		book.setItemMeta(bm);

		return book;
	}
		
	public String getOnlinePlayerUuid(RealmsBank plugin, String playername)
	{
		for (Player player : plugin.getServer().getOnlinePlayers())
		{
			if (playername.equalsIgnoreCase(player.getName()))
			{
				return  player.getUniqueId().toString();
			}
		}
		return null;
	}
	
	public String getOfflinePlayerUuid(RealmsBank plugin, String playername)
	{
		for (OfflinePlayer player : plugin.getServer().getOfflinePlayers())
		{
			if (playername.equalsIgnoreCase(player.getName()))
			{
				return player.getUniqueId().toString();
			}
		}
		return null;
	}
	
}
