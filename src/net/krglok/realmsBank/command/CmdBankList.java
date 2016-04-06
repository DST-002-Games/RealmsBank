/**
 * @author Windu
 * @create 28.03.2016
 *
 */
package net.krglok.realmsBank.command;

import org.apache.logging.log4j.core.config.plugins.ResolverUtil.IsA;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import net.krglok.realmsBank.RealmsBank;
import net.krglok.realmsBank.Common.ConfigBasis;
import net.krglok.realmsBank.Common.MessageList;
import net.krglok.realmsBank.Common.RealmsCommandType;
import net.krglok.realmsBank.Common.SubCommandType;
import net.krglok.realmsBank.Common.aRealmsCommand;
import net.krglok.realmsBank.core.BankAccount;

/**
 * @author Windu
 * @create 28.03.2016
 * <pre>
 * description:
 *
 * </pre>
 */
public class CmdBankList extends aRealmsCommand
{
	private int page; 
	
	/**
	 * CmdBankList
	 * @param command
	 * @param subCommand
	 */
	public CmdBankList()
	{
		super(RealmsCommandType.RBADMIN, SubCommandType.LIST);
		description = new String[] {
				ChatColor.GREEN+"/rb list {page} list all bank accounts",
		    	"Only for Admin or OP  "
		};
		requiredArgs = 0;
		page = 1;
	}

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.iRealmsCommand#setPara(int, java.lang.String)
	 */
	@Override
	public void setPara(int index, String value)
	{

	}

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.iRealmsCommand#setPara(int, int)
	 */
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

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.iRealmsCommand#setPara(int, boolean)
	 */
	@Override
	public void setPara(int index, boolean value)
	{
	}

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.iRealmsCommand#setPara(int, double)
	 */
	@Override
	public void setPara(int index, double value)
	{

	}

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.aRealmsCommand#getParaTypes()
	 */
	@Override
	public String[] getParaTypes()
	{
		return new String[] {int.class.getName()  };
	}

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.aRealmsCommand#execute(net.krglok.realmsBank.RealmsBank, org.bukkit.command.CommandSender)
	 */
	@Override
	public void execute(RealmsBank plugin, CommandSender sender)
	{
		MessageList msg = new MessageList();
		//           12           10
		msg.add("Name        |   Balance|");
		for (BankAccount account :  plugin.getData().getPlayerAccounts().values())
		{
			msg.add(ConfigBasis.setStrright(account.getPlayername(), 12)+"|"+ConfigBasis.setStrright((int) account.getKonto(), 10));
		}
		plugin.getMessageData().printPage(sender, msg, page);
		page = 1;

	}

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.aRealmsCommand#canExecute(net.krglok.realmsBank.RealmsBank, org.bukkit.command.CommandSender)
	 */
	@Override
	public boolean canExecute(RealmsBank plugin, CommandSender sender)
	{
		if (isOpOrAdmin(sender) == false)
		{
			errorMsg.add(ChatColor.RED+"You cannot use this command !");
			errorMsg.add(ChatColor.RED+"You are not a OP or Admin !");
			return false;
		}
		return true;
	}

}
