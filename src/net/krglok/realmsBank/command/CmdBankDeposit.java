/**
 * @author Windu
 * @create 26.03.2016
 *
 */
package net.krglok.realmsBank.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.krglok.realmsBank.RealmsBank;
import net.krglok.realmsBank.Common.ConfigBasis;
import net.krglok.realmsBank.Common.MessageList;
import net.krglok.realmsBank.Common.RealmsCommandType;
import net.krglok.realmsBank.Common.SubCommandType;
import net.krglok.realmsBank.Common.aRealmsCommand;
import net.krglok.realmsBank.core.BankAccount;
import net.krglok.realmsBank.data.TextConstant;

/**
 * @author Windu
 * @create 26.03.2016
 * <pre>
 * description:
 *
 * </pre>
 */
public class CmdBankDeposit extends aRealmsCommand
{
	private int page;
	private double amount;
	private String playername;

	/**
	 * CmdBankDeposit
	 * @param command
	 * @param subCommand
	 */
	public CmdBankDeposit()
	{
		super(RealmsCommandType.RB, SubCommandType.DEPOSIT);
		description = new String[] {
				ChatColor.YELLOW+"/rb DEPOSIT [AMOUNT] {Playername} ",
				ChatColor.GREEN+"  ",
			};
			requiredArgs = 1;
			page = 1;
			amount = 0.0;
			playername = "";
	}

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.iRealmsCommand#setPara(int, java.lang.String)
	 */
	@Override
	public void setPara(int index, String value)
	{
		switch (index)
		{
		case 1 :
			playername = value;
			break;
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.iRealmsCommand#setPara(int, int)
	 */
	@Override
	public void setPara(int index, int value)
	{

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
		switch (index)
		{
		case 0 :
				amount = value;
			break;
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.aRealmsCommand#getParaTypes()
	 */
	@Override
	public String[] getParaTypes()
	{
		return new String[] {double.class.getName(), String.class.getName()  };
	}

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.aRealmsCommand#execute(net.krglok.realmsBank.RealmsBank, org.bukkit.command.CommandSender)
	 */
	@Override
	public void execute(RealmsBank plugin, CommandSender sender)
	{
		MessageList msg = new MessageList();
		Player player;
		String uuid;
		BankAccount account ;
    	if (playername == "")
    	{
    		player = (Player) sender;
    		playername = player.getName();
    		uuid = player.getUniqueId().toString();
    	} else
    	{
    		if (isOpOrAdmin(sender))
    		{
    			uuid = getOnlinePlayerUuid(plugin, playername); 
    		} else
    		{
    			msg.add(TextConstant.NO_ACCESS_PERMISSION_1);
    			msg.add(TextConstant.NO_ACCESS_PERMISSION_2);
    			msg.add("");
    			uuid = null;
    		}
    	}
		if (uuid != null)
		{
			account = plugin.getData().getPlayerAccounts().get(uuid);
			if (account == null)
			{
				if (playername != "")
				{
					account = new BankAccount(playername,uuid);
				}
			}
			double balance = account.depositKonto(amount, playername);
			msg.add(TextConstant.DEPOSIT_ACCESS_1+ConfigBasis.format2(amount)+TextConstant.DEPOSIT_ACCESS_2);
			msg.add(TextConstant.DEPOSIT_ACCESS_3+ConfigBasis.format2(balance));
			plugin.getData().writeBankAccount(account);
		}
		plugin.getMessageData().printPage(sender, msg, page);
		page = 1;
		amount = 0.0;
		playername = "";

	}

	/* (non-Javadoc)
	 * @see net.krglok.realmsBank.Common.aRealmsCommand#canExecute(net.krglok.realmsBank.RealmsBank, org.bukkit.command.CommandSender)
	 */
	@Override
	public boolean canExecute(RealmsBank plugin, CommandSender sender)
	{
		return false;
	}

}
