/**
 * @author Windu
 * @create 26.03.2016
 *
 */
package net.krglok.realmsBank.data;

import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;

import net.krglok.realmsBank.Common.AbstractDataStore;
import net.krglok.realmsBank.Common.Item;
//import net.krglok.realmsBank.Common.SQliteConnection;
import net.krglok.realmsBank.core.BankAccount;

/**
 * @author Windu
 * @create 26.03.2016
 * <pre>
 * description:
 * realize the constructor and a YML section coding and decoding methode
 * 
	 * @param dataFolder , given parameter
	 * @param fileName   , specify here your filename  !! this is needed 
	 * @param sectionName, specify here your section !! this is needed
	 * @param timeMessure, true send message to server console with time value
	 * @param sql        , null when yml are used , otherwise use parameter
 *
 * </pre>
 */
public class AccountDataStore extends AbstractDataStore<BankAccount>
{

	/**
	 * AccountDataStore
	 * @param dataFolder
	 */
	public AccountDataStore(String dataFolder)
	{
		/**
		 * AccountDataStore
		 * @param dataFolder , given parameter
		 * @param fileName   , specify here your filename  !! this is needed 
		 * @param sectionName, specify here your section !! this is needed
		 * @param timeMessure, true send message to server console with time value
		 * @param sql        , null when yml are used
		 */
		super(dataFolder, "accounts", "ACCOUNT", false, null);
	}

	/**
	 * Override this for the concrete class
	 * 
	 * @param T dataObject, instance of real data Class
	 */
	@Override
	public void initDataSection(ConfigurationSection section, BankAccount dataObject)
	{
//	
		section.set("uuid", dataObject.getUuid());
		section.set("playername", dataObject.getPlayername());
		section.set("creditinterest", dataObject.getCreditInterest());
		section.set("interest", dataObject.getInterest());
		section.set("konto", dataObject.getKonto());
		// make Key, Value List
		HashMap<String,Integer> sList = new HashMap<String,Integer>();
		for (Item item : dataObject.getValuables().values())
		{
			String key = Item.makeItemRef(item.MaterialRef(), item.getData());
			sList.put(key, item.value());
		}
		section.set("valuables", sList);
		String subSection = "credit";
		section.set(subSection+".value", dataObject.getCredit().getValue());
		section.set(subSection+".iterest", dataObject.getCredit().getInterest());
		section.set(subSection+".span", dataObject.getCredit().getSpan());
		section.set(subSection+".restspan", dataObject.getCredit().getRestSpan());
	}


	/**
	 * Override this for the concrete class

	 * @return T , real data Class
	 */
	@Override
	public BankAccount initDataObject(ConfigurationSection data)
	{
//		// 
		
		return null;
	}
	
	
}
