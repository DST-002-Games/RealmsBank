package net.krglok.realmsBank.command;

import java.util.ArrayList;

import net.krglok.realmsBank.data.*;
import net.krglok.realmsBank.RealmsBank;
import net.krglok.realmsBank.Common.CommandParser;
import net.krglok.realmsBank.Common.MessageList;
import net.krglok.realmsBank.Common.aRealmsCommand;
import net.krglok.realmsBank.Common.RealmsCommandType;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * Command handling for the command registered to the plugin every subCommand is
 * a separate instance of abstract class aRealmsCommand all active commands must
 * be in the cmdList Basic help for no SubCommand are handled in the parser.
 * 
 * @author Windu
 * 
 */
public class CommandList
{
	private RealmsBank plugin;
	private aRealmsCommand[] cmdList;
	CommandParser parser;

	public CommandList(JavaPlugin plugin)
	{
		// cast plugin to correct class
		this.plugin = (RealmsBank) plugin;
		cmdList = makeCommandList();
		parser = new CommandParser(cmdList);
	}

	/**
	 * Instantiation of Constant CommandList 
	 * @return
	 */
	private aRealmsCommand[] makeCommandList()
	{
		aRealmsCommand[] commandList = new aRealmsCommand[] 
		{ 
			new CmdBankNone(),
			new CmdBankHelp(),
			new CmdBankDeposit(),
			new CmdBankList()
			
			

		};
		return commandList;
	}

	public aRealmsCommand[] getCmdList()
	{
		return cmdList;
	}

	/**
	 * 
	 * @param sender
	 * @param command
	 * @param args
	 * @return
	 */
	public boolean run(CommandSender sender, Command command, String[] args)
	{

		RealmsCommandType cmdType = RealmsCommandType
				.getRealmCommandType(command.getName());
		aRealmsCommand cmd = parser.getRealmsCommand(cmdType, args);
		if (cmd != null)
		{
			if (cmd.isParserError() == false)
			{
				if (cmd.canExecute(plugin, sender))
				{
					cmd.execute(plugin, sender);
				} else
				{
					MessageList msg = new MessageList();
					msg.add(ChatColor.GREEN + plugin.getName() + " Vers.: "
							+ plugin.getVersion() + " ");
					msg.addAll(cmd.getErrorMsg());
					plugin.getMessageData().printPage(sender, msg, 1);
				}
			} else
			{
				MessageList msg = new MessageList();
				msg.add(ChatColor.GREEN + plugin.getName() + " Vers.: "
						+ plugin.getVersion() + " ");
				msg.add(ChatColor.RED + "Error in command sysntax ");
				msg.add(ChatColor.YELLOW + "use /"+command.getName()+" [HELP] for more information");
				msg.addAll(cmd.getErrorMsg());
				plugin.getMessageData().printPage(sender, msg, 1);

			}
		} else
		{
			MessageList msg = new MessageList();
			msg.add(ChatColor.GREEN + plugin.getName() + " Vers.: "
					+ plugin.getVersion() + " ");
			msg.add(ChatColor.RED + "OOPS Realms Plugin dont find a Command ! ");
			msg.add(ChatColor.YELLOW + "use /"+command.getName()+" [HELP] for more information");
			plugin.getMessageData().printPage(sender, msg, 1);

		}
		return true;
	}

}
