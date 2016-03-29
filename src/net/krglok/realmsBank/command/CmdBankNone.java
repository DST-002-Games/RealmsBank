package net.krglok.realmsBank.command;

import java.util.ArrayList;

import net.krglok.realmsBank.RealmsBank;
import net.krglok.realmsBank.Common.*;
import net.krglok.realmsBank.Common.aRealmsCommand;
import net.krglok.realmsBank.Common.RealmsCommandType;
import net.krglok.realmsBank.Common.RealmsSubCommandType;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CmdBankNone extends aRealmsCommand
{
	

	public CmdBankNone()
	{
		super(RealmsCommandType.REALMSBANK ,  RealmsSubCommandType.NONE);
		description = new String[] {
				ChatColor.RED+"command not found , use one of the following ",
				ChatColor.GREEN+"/rbAdmin [HELP] only for ops to control the plugin ",
				ChatColor.GREEN+"/rb [HELP] player command for bank management",
		    	" "
		};
		requiredArgs = 0;
	}

	@Override
	public String[] getParaTypes()
	{
		return null;
	}

	@Override
	public void execute(RealmsBank plugin, CommandSender sender)
	{
		MessageList msg = new MessageList();
    	
    	msg.add(ChatColor.GREEN+plugin.getName()+" Vers.: "+ plugin.getDescription().getVersion()+" ");
		msg.addAll(getDescriptionString());
		plugin.getMessageData().printPage(sender, msg, 1);
		
	}

	@Override
	public boolean canExecute(RealmsBank plugin, CommandSender sender)
	{
		return true;
	}

	@Override
	public void setPara(int index, String value)
	{
		
	}

	@Override
	public void setPara(int index, int value)
	{
		
	}

	@Override
	public void setPara(int index, boolean value)
	{
		
	}

	@Override
	public void setPara(int index, double value)
	{
		
	}

}
