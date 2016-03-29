package net.krglok.realmsBank.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


/**
 * list of items with prices , used as central pricelist  
 * @author Windu
 *
 */
public class ItemPriceList extends HashMap<String,ItemPrice>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -970781088522071013L;

	public ItemPriceList()
	{
		
	}
	
	/**
	 * create new Item and add to List
	 * overwrite existing data
	 * @param itemRef
	 * @param price
	 */
	public void add(String itemRef, int data, Double price)
	{
		ItemPrice item = new ItemPrice(itemRef, data, price);
		this.put(item.ItemRefData(), item);
	}
	
	/**
	 * add Item to List
	 * overwrite existing data
	 * @param item
	 */
	public void add(ItemPrice item)
	{
		this.put(item.ItemRefData(), item);
	}
	
	/**
	 * 
	 * @param itemRef
	 * @return basePrice
	 */
	public Double getBasePrice(String itemRef)
	{
		ItemPrice item = this.get(itemRef);
		if (item != null)
		{
			return item.getBasePrice();
		}
		return 0.0;
	}

	public double getPriceOfList(ItemList items)
	{
		double sum = 0.0;
		for (Item item : items.values())
		{
			sum = sum + (this.getBasePrice(item.ItemRefData()) * item.value());
		}
		
		return sum;
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
	
	/**
	 * saldiert value und price eines bestehenden ItemPrice
	 * andernfalls wird der ItemPrice neu gesetzt
	 * 
	 * @param iPrice
	 */
	public void setItemPrice(ItemPrice iPrice)
	{
		if (keySet().contains(iPrice.ItemRefData()))
		{
			ItemPrice itemPrice = get(iPrice.ItemRefData());
			itemPrice.setBasePrice(itemPrice.getBasePrice()+iPrice.getBasePrice());
			itemPrice.setValue(itemPrice.value()+iPrice.value());
		} else
		{
			put(iPrice.ItemRefData(),iPrice);
		}
	}

}
