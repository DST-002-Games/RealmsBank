package net.krglok.realmsBank.core;

import net.krglok.realmsBank.Common.ConfigBasis;

/**
 * @author Windu
 * @create 26.03.2016
 * <pre>
 * description:
 * realize the transaction protokol for a bank account.
 * this acts like bank statement
 * </pre>
 */
public class BankTransaction
{
	private String sender;
	private double value;
	private String text;
	private double account;
	
	public BankTransaction()
	{
		sender = "";
		value = 0.0;
		text = "";
		account = 0;
	}

	
	public BankTransaction(String sender, double value, String text, double account)
	{
		this.sender = sender;
		this.value = value;
		this.text = text;
		this.account = account;
	}


	public String getSender()
	{
		return sender;
	}


	public void setSender(String sender)
	{
		this.sender = sender;
	}


	public double getValue()
	{
		return value;
	}


	public void setValue(double value)
	{
		this.value = value;
	}


	public String getText()
	{
		return text;
	}


	public void setText(String text)
	{
		this.text = text;
	}


	public double getAccount()
	{
		return account;
	}


	public void setAccount(double account)
	{
		this.account = account;
	}
	
	/**
	 * make formatted string of transaction details
	 * <Text> <sender> <value> 
	 * 
	 * @return  formatted string 
	 * 
	 */
	public String getAsLine()
	{
		return text+" : " + sender + ":" + ConfigBasis.setStrformat2(value, 11); 
	}

}
