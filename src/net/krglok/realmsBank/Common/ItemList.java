package net.krglok.realmsBank.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


/**
 * list of items, hold each item type unique
 * if item exists the value are added 
 *  
 * @author oduda
 *
 */
public class ItemList  extends  HashMap<String, Item>  
{
/**
	 * 
	 */
	private static final long serialVersionUID = -3351533128792676311L;
	//	private Map<String, Integer> itemList;
	protected int itemCount;

	public ItemList()
	{
//		itemList = new HashMap<String, Integer>();
		itemCount = 0;
	}

//	public Map<String, Integer> getItemList() {
//		return itemList;
//	}
//
//	public void setItemList(Map<String, Integer> itemList) {
//		this.itemList = itemList;
//	}
	
	/**
	 * add item to the list
	 * if exist values are  add to exiting items
	 * @param item
	 */
	public boolean addItem(Item item)
	{
		if (this.containsKey(item.ItemRefData()))
		{
			Item exist = this.get(item.ItemRefData());
			exist.setValue(exist.value()+item.value());
		} else
		{
			this.put(item.ItemRefData(), item);
		}
		itemCount = itemCount + item.value();
//		Item item = new Item(itemRef, iValue);
		return true;
	}
	
	/**
	 * add item to the list
	 * if exist values are  overwrite
	 * @param itemRef
	 * @param iValue
	 * @return added item or null
	 */
	public boolean addItem(String itemRef, int data, int iValue)
	{
		if (this.containsKey(itemRef))
		{
			Item exist = this.get(itemRef);
			exist.setValue(exist.value()+iValue);
		} else
		{
			this.put(itemRef, new Item(itemRef, data, iValue)); 
		}
		itemCount = itemCount + iValue;
//		Item item = new Item(itemRef, iValue);
		return true;
	}
	
	/**
	 * add value to the item in the list
	 * if not exist add item
	 * @param itemRef
	 * @param iValue
	 * @return
	 */
	public Item putItem(String itemRef, int data, int iValue)
	{
		Item item;
		if (this.containsKey(itemRef))
		{
			item = this.get(itemRef);
			item.setValue(item.value()+iValue);
			itemCount = itemCount + iValue;
			return item; 
		}
		item = new Item(itemRef, data, iValue);
		this.addItem(item);
		return item;
	}
	
	/**
	 * 
	 * @param itemRef
	 * @return
	 */
	public Item getItem(String itemRef)
	{
		return this.get(itemRef);
	}
		
//	public Boolean isEmpty()
//	{
//		return itemList.isEmpty();
//	}

	/**
	 * add value to item value
	 * if item not exist in list it will be created
	 * @param itemRef
	 * @param value
	 */
	public void depositItem(String itemRef, int data, int iValue)
	{
		Item item;
		if (containsKey(itemRef))
		{
			item = this.get(itemRef);
//			System.out.println("deposit: "+iValue);
			item.setValue(item.value()+iValue);
//			put(itemRef, get(itemRef)+value);
		} else
		{
			this.addItem(itemRef, data, iValue);
		}
		itemCount = itemCount + iValue;
	}

	/**
	 * bucht value von item ab , 
	 * wenn bestand > 0
	 * @param itemRef
	 * @param value
	 * @return
	 */
	public Boolean withdrawItem(String itemRef, int data, int value)
	{
		int actual = 0;
		if(this.containsKey(itemRef))
		{
			actual = this.get(itemRef).value();
		}
		if ((actual-value) >= 0)
		{
			value = value * -1;
//			System.out.println("withdraw: "+value);
			this.depositItem(itemRef, data, value);
			return true;
		}
		
		return false;
	}
	
	public int getValue(String itemRef)
	{
		Item item = get(itemRef);
		if (item != null)
		{
			return item.value();
		} else
		{
			return 0;
		}
	}
	
	public void setValue(String itemRef, int data, int value)
	{
		putItem(itemRef, data, value);
	}
	
	
	public int getItemCount()
	{
		return itemCount;
	}

	/**
	 * calculate item count new form item values
	 */
	public void setItemCount()
	{
		itemCount = 0;
		for (Item item : this.values())
		{
			itemCount = itemCount + item.value();
		}
	}
	
	public ArrayList<String> sortItems()
	{
		ArrayList<String> sortedItems = new ArrayList<String>();
		for (String s : this.keySet())
		{
			sortedItems.add(s);
		}
		if (sortedItems.size() > 1)
		{
			Collections.sort
			(sortedItems,  String.CASE_INSENSITIVE_ORDER);
		}
		return sortedItems;
	}

	public void addAll(ItemList newList)
	{
		for (Item item : newList.values())
		{
			put(item.ItemRefData(), item);
		}
	}
	
	public ItemArray asItemArray()
	{
		ItemArray itemArray = new ItemArray();
		for (Item item : this.values())
		{
			itemArray.add(item);
		}
		return itemArray;
	}

	/**
	 * make a dowb count for required list by the amopunt
	 * 
	 */
	public void reduceRequired()
	{
		ArrayList<String> deleteList = new ArrayList<String>();
		for (Item item : this.values())
		{
			if (item.value() > 0)
			{
				item.setValue(item.value()-1);
			} else
			{
				deleteList.add(item.ItemRefData());
			}
		}
		for (String key : deleteList )
		{
			this.remove(key);
		}
	}
	
	public String asString()
	{
		int count = 0;
		String value ="";
		for (String key : this.keySet())
		{
			if (count == 0)
			{
				value = key;
			} else
			{
				value = value + ", "+key ;
			}
		}
		return value;
	}
}
