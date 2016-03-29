package net.krglok.realmsBank.Common;

import java.util.ArrayList;


/**
 * list of items, can hold multiple items of the same type
 * @author Windu
 *
 */
public class ItemArray extends ArrayList<Item>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7213096475060542956L;

	public ItemArray()
	{
		
	}

	public Item addItem(String itemRef, int iValue)
	{
		Item item = new Item(itemRef, 0, iValue);
		this.add(item);
		return item;
	}

	public Item addItem(String itemRef, int data, int iValue)
	{
		Item item = new Item(itemRef, data, iValue);
		this.add(item);
		return item;
	}
	
	/**
	 * add all elements of newLIst to this list
	 * dont add existing ItemRef
	 * 
	 * @param newList
	 */
	public void addAll(ItemArray newList)
	{
		for (Item item : newList)
		{
			if (containItem(item) == false)
			{
				this.add(item);
			}
		}
	}
	
	/**
	 * check for ItemRef in List
	 * 
	 * @param item
	 * @return
	 */
	public boolean containItem(Item item)
	{
		for (Item existItem : this)
		{
			if (existItem.ItemRefData().equalsIgnoreCase(item.ItemRefData()))
			{
				if (existItem.getData() == item.getData())
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public Item getItem(String itemRef, int data)
	{
		for (int i = 0; i < this.size(); i++)
		{
			if (this.get(i).ItemRefData().equals(Item.makeItemRef(itemRef, data)))
			{
				if (this.get(i).getData() == data)
				{
					return this.get(i);
				}
			}
		}
		return new Item();
	}
	
	@SuppressWarnings("unused")
	public void putItem(String itemRef, int data, int iValue)
	{
		for (int i = 0; i < this.size(); i++)
		{
			if (this.get(i).ItemRefData().equals(Item.makeItemRef(itemRef, data)))
			{
				this.getItem(itemRef,data).setValue(this.getItem(itemRef,data).value()+iValue);
			}
			return;
		}
		addItem( itemRef,  iValue);
	}

	@SuppressWarnings("unused")
	public void setItem(String iRef, int data, int iValue)
	{
		String itemRef = Item.makeItemRef(iRef, data);
		for (int i = 0; i < this.size(); i++)
		{
			if (this.get(i).ItemRefData().equals(Item.makeItemRef(itemRef, data)))
			{
				this.getItem(itemRef,data).setValue(iValue);
			}
			return;
		}
		addItem( itemRef,  iValue);
	}
}
