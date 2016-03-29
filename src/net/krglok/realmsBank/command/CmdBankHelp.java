package net.krglok.realmsBank.command;

import java.util.ArrayList;

import net.krglok.realmsBank.RealmsBank;
import net.krglok.realmsBank.Common.MessageList;
import net.krglok.realmsBank.Common.aRealmsCommand;
import net.krglok.realmsBank.Common.RealmsCommandType;
import net.krglok.realmsBank.Common.RealmsSubCommandType;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CmdBankHelp extends aRealmsCommand
{
	private int page;
	private String search ;
	
	public CmdBankHelp()
	{
		super(RealmsCommandType.RB, RealmsSubCommandType.HELP);
		description = new String[] {
			ChatColor.YELLOW+"/realmsbank HELP [page] {WORD} ",
			ChatColor.GREEN+"  ",
		};
		requiredArgs = 0;
		page = 1;
		search = "";
	}
	
	
	@Override
	public void setPara(int index, String value)
	{
		switch (index)
		{
		case 1 :
				search = value;
			break;
		default:
			break;
		}
	}

	@Override
	public void setPara(int index, int value)
	{
		switch (index)
		{
		case 0 :
				page = value;
			break;
		default:
			break;
		}

	}

	@Override
	public void setPara(int index, boolean value)
	{

	}

	@Override
	public void setPara(int index, double value)
	{

	}

	@Override
	public String[] getParaTypes()
	{
		return new String[] {int.class.getName(), String.class.getName()  };
	}

	
	@Override
	public void execute(RealmsBank plugin, CommandSender sender)
	{
		MessageList msg = new MessageList();
    	msg = makeHelpPage(plugin.commandList().getCmdList(), msg, search);
		plugin.getMessageData().printPage(sender, msg, page);
		helpPage = "";
		page = 1;
		search = "";
	}

	@Override
	public boolean canExecute(RealmsBank plugin, CommandSender sender)
	{
		return true;
	}

}
