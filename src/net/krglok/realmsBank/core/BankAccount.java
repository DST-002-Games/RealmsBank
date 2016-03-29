package net.krglok.realmsBank.core;

import java.util.ArrayList;
import java.util.Date;

import org.bukkit.Material;

import net.krglok.realmsBank.Common.ConfigBasis;
import net.krglok.realmsBank.Common.ItemList;
import net.krglok.realmsBank.Common.ItemPriceList;
import net.krglok.realmsBank.Common.MessageList;



/**
 * @author oduda
 *
 * the bank hold the money of a settlement
 * there is an transaction protocol
 */
public class BankAccount  
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4885560720493966878L;
	private Boolean isEnabled;
	private Double konto;
	private String playername;
	private String uuid;
	private double interest;
	private double creditInterest;
	private BankTransactionList transactionList;
	private ItemList valuables;
	protected MessageList msg = new MessageList();
	protected BankCredit credit;
	
	public BankAccount() 
	{
		setIsEnabled(false);
		konto = Double.valueOf(0.0);
		setValuables(ConfigBasis.initValuables());
		transactionList = new BankTransactionList();
		playername = "ADMIN";
		uuid = "ADMIN";
		interest = 0.0;
		creditInterest = 0.0;
		credit = new BankCredit();
	}

	public BankAccount(String playername, String UUID) 
	{
		setIsEnabled(false);
		konto = Double.valueOf(0.0);
		setValuables(ConfigBasis.initValuables());
		transactionList = new BankTransactionList();
		playername = "ADMIN";
		uuid = "";
		interest = 0.0;
		creditInterest = 0.0;
		credit = new BankCredit();
	}
	
	public ItemList getValuables() {
		return valuables;
	}

	public void setValuables(ItemList valuables) {
		this.valuables = valuables;
	}

	public ArrayList<String> getMsg() {
		return msg;
	}

	public void setMsg(MessageList msg) {
		this.msg = msg;
	}

	public  BankTransactionList transactionList()
	{
		return transactionList;
	}
	
	public ArrayList<String> depositValuable(String itemRef, int amount, ItemPriceList pricelist, ItemList inventory, int settleId)
	{
		ArrayList<String> msg = new ArrayList<String>();

		double price = pricelist.getBasePrice(itemRef);
		// use the gold basic values
		if (itemRef.equalsIgnoreCase(Material.GOLD_BLOCK.name()))
		{
			price = ConfigBasis.GOLDBLOCK_PRICE;
		}
		if (itemRef.equalsIgnoreCase(Material.GOLD_INGOT.name()))
		{
			price = ConfigBasis.GOLDINGOT_PRICE;
		}
		if (itemRef.equalsIgnoreCase(Material.GOLD_NUGGET.name()))
		{
			price = ConfigBasis.GOLDNUGGET_PRICE;
		}
		
		if( price > 0.0)
		{
			int stock = inventory.getValue(itemRef);
			if (stock >= amount)
			{
				double value = amount * price;
				addKonto(value, "Credit with "+itemRef, settleId);
				inventory.withdrawItem(itemRef, 0, amount);
				msg.add("Credit to BankAccount "+itemRef+"with Value: "+value);
				msg.add("");
				return msg;
			}
			msg.add("No stock for Item "+itemRef+" max: "+stock);
			msg.add("");
			return msg;
		}
		msg.add("No price for Item "+itemRef+"_0");
		msg.add("");
		return msg;
	}

	/**
	 * 
	 * @return value of konto 
	 */
	public double getKonto()
	{
		return konto;
	}

	/**
	 * initialize konto with value , overwrite
	 * @param konto
	 */
	public void initKonto(Double value)
	{
		this.konto = value;
		addTransactionText("Admin", "Init", konto);
	}

	/**
	 * the value is a  signed field 
	 * This is a administrator function 
	 * @param value
	 */
	public void addKonto(Double value, String text, int settleId)
	{
		konto = konto + value;
//		if (transactionList != null)
//		{
//			transactionList.addBank(ADD_KONTO,"Admin", settleId, value);
//		}
	}
	
	/**
	 * deposit value on konto (positive values are deposit)
	 * the value is a  signed field
	 * transaction protocol are written
	 * @param value
	 * @param user
	 * @return new value of konto
	 */
	public double depositKonto (Double value, String user)
	{
		konto = konto + value;
		addTransactionText(user, "Deposit ", value);
		return konto;
	}

	/**
	 * withdraw value from konto (positive values are withdraw)
	 * the value is a  signed field 
	 * transaction protocol are written
	 * @param value
	 * @param user
	 * @return true if withdraw done otherwise false
	 */
	public Boolean withdrawKonto (Double value, String user, int settleId)
	{
		if (konto >= value)
		{
			konto = konto - value;
			addTransactionText(user, "Withdraw ", value);
//			if (transactionList != null)
//			{
//				transactionList.addBank(WITHDRAW, user, settleId, value);
//			}
			return true;
		}
//		if (transactionList != null)
//		{
//			transactionList.addBank(user,KONTO_TOO_LOW, settleId, konto - value);
//		}
		return false;
	}
	
	
	/**
	 * 
	 * @return  true is BankAccount is enabled 
	 */
	public Boolean getIsEnabled()
	{
		return isEnabled;
	}

	/**
	 * Set the enabled Status , no check for conditions
	 * @param isEnabled
	 */
	public void setIsEnabled(Boolean isEnabled)
	{
		this.isEnabled = isEnabled;
	}

	/**
	 * List of formatted Strings
	 * date / time / user / text
	 * @return entire transactionlist,  
	 */

	public void addTransactionText(String sender, String text, double value)
	{
		transactionList.add(sender, value, text, this.konto);
	}

	public String getPlayername()
	{
		return playername;
	}

	public void setPlayername(String playername)
	{
		this.playername = playername;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public double getInterest()
	{
		return interest;
	}

	public void setInterest(double interest)
	{
		this.interest = interest;
	}

	public double getCreditInterest()
	{
		return creditInterest;
	}

	public void setCreditInterest(double creditInterest)
	{
		this.creditInterest = creditInterest;
	}

	public BankCredit getCredit()
	{
		return credit;
	}
	
	public void setCredit(BankCredit credit)
	{
		this.credit = credit;
	}
	
}
