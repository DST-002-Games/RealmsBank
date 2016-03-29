package net.krglok.realmsBank.data;

import java.io.File;
import java.util.Map;

import net.krglok.realmsBank.Common.ConfigBasis;
import net.krglok.realmsBank.Common.Item;
import net.krglok.realmsBank.Common.ItemPrice;
import net.krglok.realmsBank.Common.ItemPriceList;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class PriceData
{

	private String path;

	public PriceData(String path)
	{
		this.path = path;
	}

	private String getBaseKey()
	{
		return "BASEPRICE";
	}
	
	public void writePriceData(ItemPriceList Items) 
	{
		try
		{
            File DataFile = new File(this.path, "baseprice.yml");
            if (!DataFile.exists()) 
            {
            	DataFile.createNewFile();
            }
            
            FileConfiguration config = new YamlConfiguration();
            config.load(DataFile);
            
            String base = getBaseKey();
            ConfigurationSection cSection = config.createSection(base);
            for (ItemPrice item : Items.values())
            {
            	
            	config.set(MemorySection.createPath(cSection, item.ItemRefData()),item.getBasePrice());
            }
            System.out.println("["+ConfigBasis.PLUGIN_NAME+"] write PriceData");
            config.save(DataFile);
			
		} catch (Exception e)
		{
			System.out.println("["+ConfigBasis.PLUGIN_NAME+"] write Pricedata"+ e.getMessage());
		}
	

	}
	
	public ItemPriceList readPriceData() 
	{
        String base = getBaseKey();
        ItemPriceList items = new ItemPriceList();
		try
		{
            File DataFile = new File(this.path, "baseprice.yml");
            if (!DataFile.exists()) 
            {
            	DataFile.createNewFile();
            }
            
            FileConfiguration config = new YamlConfiguration();
            config.load(DataFile);
            System.out.println("["+ConfigBasis.PLUGIN_NAME+"] Read PriceData");
            
            if (config.isConfigurationSection(base))
            {
    			Map<String,Object> dataList = config.getConfigurationSection(base).getValues(false);
//    			System.out.println("Read "+base+" : "+buildings.size());
            	for (String ref : dataList.keySet())
            	{
            		Double price = config.getDouble(base+"."+ref,0.0);
            		String itemRef = Item.getItemRefString(ref);
            		int data = Item.getItemDataString(ref);
            		ItemPrice item = new ItemPrice(itemRef, data, price);
            		items.add(item);
//            		System.out.println(item.ItemRef());
            	}
            }
		} catch (Exception e)
		{
			System.out.println("["+ConfigBasis.PLUGIN_NAME+"] read PriceData "+ e.getMessage());
		}
		return items;
	}
	
	
}
