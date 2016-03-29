/**
 * @author Windu
 * @create 26.03.2016
 *
 */
package net.krglok.realmsBank.data;

import java.util.ArrayList;

import net.krglok.realmsBank.Common.ConfigBasis;
import net.krglok.realmsBank.Common.Item;
import net.krglok.realmsBank.Common.ItemPriceList;
import net.krglok.realmsBank.core.BankAccount;
import net.krglok.realmsBank.core.BankAccountList;

/**
 * @author Windu
 * @create 26.03.2016
 * <pre>
 * description:
 * DataStore  manage the global persistent data as Lists
 *
 * </pre>
 */
public class DataStore
{

	private ItemPriceList priceList;
	private PriceData priceData;
	
	private BankAccountList playerAccounts;
	private AccountDataStore accountDataStore;
	
	/**
	 * DataStore  manage the global persistent data as Lists
	 * 
	 * @param path, normally this this the plugin directory
	 */
	public DataStore(String path)
	{
		priceList = new ItemPriceList();
		priceData = new PriceData(path);
		initPriceList();
		
		setPlayerAccounts(new BankAccountList());
		accountDataStore = new AccountDataStore(path);
		initAccountList();
	}
	
	public ItemPriceList priceList()
	{
		return priceList;
	}
	
	private void initPriceList()
	{
		priceList = priceData.readPriceData();
	}

	/**
	 * @return the playerAccounts
	 */
	public BankAccountList getPlayerAccounts()
	{
		return playerAccounts;
	}

	/**
	 * @param playerAccounts the playerAccounts to set
	 */
	public void setPlayerAccounts(BankAccountList playerAccounts)
	{
		this.playerAccounts = playerAccounts;
	}
	
	/**
	 * load bank account from datafile
	 * initialize the global bank account
	 */
	private void initAccountList()
	{
		ArrayList<String> keys = accountDataStore.readDataList();
		
		for (String key : keys)
		{
			BankAccount account = accountDataStore.readData(key);
			if (account != null)
			{		
				this.playerAccounts.put(key, account);
			}
		}
		if (this.playerAccounts.containsKey("ADMIN") == false)
		{
			BankAccount account = new BankAccount();
			double sum = 0.0;
			for (Item item : ConfigBasis.initBankValuables().values())
			{
				account.getValuables().addItem(item);
			}
			account.initKonto(ConfigBasis.getInitBankValuables());
			this.playerAccounts.put(account.getUuid(), account);
		}
	}
	
	/**
	 * write account to Datafile
	 * @param account
	 */
	public void writeBankAccount(BankAccount account)
	{
		accountDataStore.writeData(account, account.getUuid());
	}
	
	/**
	 * write all bakAccounts to Datafile
	 */
	public void writeAccounts()
	{
		for (BankAccount account : playerAccounts.values())
		{
			writeBankAccount(account);
		}
	}
	

}
