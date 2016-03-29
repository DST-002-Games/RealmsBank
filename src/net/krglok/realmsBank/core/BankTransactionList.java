/**
 * @author Windu
 * @create 26.03.2016
 *
 */
package net.krglok.realmsBank.core;

import java.util.HashMap;

/**
 * @author Windu
 * @create 26.03.2016
 * <pre>
 * description:
 *
 * </pre>
 */
public class BankTransactionList extends HashMap<Integer, BankTransaction>
{
	private int ID;
	
	public BankTransactionList()
	{
		ID = 0;
	}
	
	public boolean add(BankTransaction transaction)
	{
		ID++;
		this.put(ID, transaction);
		return true;
	}
	
	public boolean add(String sender, double value, String text, double account)
	{
		BankTransaction transaction = new BankTransaction(sender, value, text, account);
		add(transaction);
		return true;
	}
}
