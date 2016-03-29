package net.krglok.realmsBank.Common;


import org.bukkit.Material;

/**
 * 
 * @author Windu
 * @create 25.03.2016
 * 
 * description:
 * This class hold the static constant and methods for the plugin and define
 * the configuration basics
 * 
 * The public final values are used in the hole Common package
 * - change the PLUGIN_NAME for your needs , it will used in message for 
 *   the pluginname
 *   
 * - CONFIG_VER , it will be used for check the config.yml
 *   this string must equal to the node value in config.yml
 *   
 * - CONFIG_VER_NAME  specify the node name in config.yml
 * - PLUGIN_NAME_NAME specify the node name in config.yml
 *
 */
public class ConfigBasis
{
	public static String CONFIG_VER = "1.0.0";
	public static final String CONFIG_VER_NAME = "config_ver";
	public static final String PLUGIN_NAME_NAME = "plugin_name";
	public static final String PLUGIN_NAME = "RealmsBank";

	public static double GOLDNUGGET_PRICE = 45.0;
	public static final double GOLDINGOT_PRICE = GOLDNUGGET_PRICE*9;
	public static final double GOLDBLOCK_PRICE = GOLDINGOT_PRICE*9;
	
	
	public static double format2(double value)
	{
		int value100 = (int)(value * 100);
		return ((double)value100/100.0);
	}

	public static String setStrformat2(double value, int len)
	{
		value = format2(value);
		String in = String.valueOf(value);
		return setCharright(in, len,' ');
	}

	public static String setStrright(double value, int len)
	{
		String in = String.valueOf(value);
		return setCharright(in, len,' ');
	}


	public static String set0right(int value, int len)
	{
		String in = String.valueOf(value);
		return setCharright(in, len, '0');
	}
	
	public static String setStrright(int value, int len)
	{
		String in = String.valueOf(value);
		return setCharright(in, len, ' ');
	}

	public static String setStrright(String value, int len)
	{
		String in = String.valueOf(value);
		return setCharright(in, len, ' ');
	}

	private static String setCharright(String in, int len, char c)
	{
		char[] out = new char[len];
		for (int i = 0; i < out.length; i++)
		{
			out[i] = c;
		}
		if (len >= in.length())
		{
			char[] zw  = in.toCharArray();
			int zwl = zw.length;
			for (int i = 0; i < zw.length; i++)
			{
				out[len-i-1] = zw[zwl-i-1]; 
			}
		} else
		{
			char[] zw  = in.toCharArray();
			int zwl = zw.length;
			if (zw.length <= out.length)
			{
				for (int i = 0; i < out.length; i++)
				{
					out[len-i] = zw[zwl-i]; 
				}
			} else
			{
				out[0] = '?';
				out[1] = '?';
			}
		}
		return String.valueOf(out);
	}

	/**
	 * 
	 * @return default weapon items
	 */
	public static ItemList initWeapon()
	{
		ItemList subList = new ItemList();

		subList.addItem("BOW",0,0);
		subList.addItem("DIAMOND_SWORD",0,0);
		subList.addItem("GOLD_SWORD",0,0);
		subList.addItem("IRON_SWORD",0,0);
		subList.addItem("STONE_SWORD",0,0);
		subList.addItem("WOOD_SWORD",0,0);
		subList.addItem("ARROW",0,0);
		
		return subList;
	}

	/**
	 * 
	 * @return default armor items
	 */
	public static ItemList initArmor()
	{
		ItemList subList = new ItemList();
		
		subList.addItem("LEATHER_BOOTS",0,0);
		subList.addItem("LEATHER_CHESTPLATE",0,0);
		subList.addItem("LEATHER_HELMET",0,0);
		subList.addItem("LEATHER_LEGGINGS",0,0);

		subList.addItem("DIAMOND_BOOTS",0,0);
		subList.addItem("DIAMOND_CHESTPLATE",0,0);
		subList.addItem("DIAMOND_HELMET",0,0);
		subList.addItem("DIAMOND_LEGGINGS",0,0);
		
		subList.addItem("GOLD_BOOTS",0,0);
		subList.addItem("GOLD_CHESTPLATE",0,0);
		subList.addItem("GOLD_HELMET",0,0);
		subList.addItem("GOLD_LEGGINGS",0,0);
		
		subList.addItem("IRON_BOOTS",0,0);
		subList.addItem("IRON_CHESTPLATE",0,0);
		subList.addItem("IRON_HELMET",0,0);
		subList.addItem("IRON_LEGGINGS",0,0);

		subList.addItem("CHAINMAIL_BOOTS",0,0);
		subList.addItem("CHAINMAIL_CHESTPLATE",0,0);
		subList.addItem("CHAINMAIL_HELMET",0,0);
		subList.addItem("CHAINMAIL_LEGGINGS",0,0);
		
		return subList;
	}
	
	/**
	 * 
	 * @return default tool items
	 */
	public static ItemList  initTool()
	{
		ItemList subList = new ItemList();

		subList.addItem("FISHING_ROD",0,0);
		subList.addItem("FLINT_AND_STEEL",0,0);
		subList.addItem("SHEARS",0,0);
		
		subList.addItem("DIAMOND_AXE",0,0);
		subList.addItem("DIAMOND_HOE",0,0);
		subList.addItem("DIAMOND_PICKAXE",0,0);
		subList.addItem("DIAMOND_SPADE",0,0);

		subList.addItem("GOLD_AXE",0,0);
		subList.addItem("GOLD_HOE",0,0);
		subList.addItem("GOLD_PICKAXE",0,0);
		subList.addItem("GOLD_SPADE",0,0);

		subList.addItem("IRON_AXE",0,0);
		subList.addItem("IRON_HOE",0,0);
		subList.addItem("IRON_PICKAXE",0,0);
		subList.addItem("IRON_SPADE",0,0);

		subList.addItem("STONE_AXE",0,0);
		subList.addItem("STONE_HOE",0,0);
		subList.addItem("STONE_PICKAXE",0,0);
		subList.addItem("STONE_SPADE",0,0);

		subList.addItem("WOOD_AXE",0,0);
		subList.addItem("WOOD_HOE",0,0);
		subList.addItem("WOOD_PICKAXE",0,0);
		subList.addItem("WOOD_SPADE",0,0);
		subList.addItem(Material.SADDLE.name(),0,0);
		subList.addItem(Material.FISHING_ROD.name(),0,0);
		subList.addItem(Material.COMPASS.name(),0,0);
		subList.addItem(Material.WATCH.name(),0,0);
		subList.addItem(Material.LEASH.name(),0,0);
		subList.addItem(Material.NAME_TAG.name(),0,0);

		return subList;
	}

	
	
	public static ItemList initBuildMaterial()
	{
		ItemList subList = new ItemList();

		subList.addItem(Material.COBBLESTONE.name(),0,0);
		subList.addItem(Material.LOG.name(),0,0);
		subList.addItem(Material.STONE.name(),0,0);
		subList.addItem(Material.BRICK.name(),0,0);
		subList.addItem(Material.NETHER_BRICK.name(),0,0);
//		subList.addItem(Material.WOOD_STEP.name(),0,0);
		subList.addItem(Material.ANVIL.name(),0,0);
		subList.addItem(Material.STEP.name(),0,0);
		subList.addItem(Material.DIRT.name(),0,0);
		subList.addItem(Material.GRASS.name(),0,0);
		subList.addItem(Material.WATER.name(),0,0);
		subList.addItem(Material.NETHERRACK.name(),0,0);
		subList.addItem(Material.WHEAT.name(),0,0);
		subList.addItem(Material.WOOD.name(),0,0);
		subList.addItem(Material.SOIL.name(),0,0);
		subList.addItem(Material.GLASS.name(),0,0);
//		subList.addItem(Material..name(),0,0);
		
		return subList;
	}

	public static ItemList initMaterial()
	{
		ItemList subList = new ItemList();

		subList.addItem(Material.COAL.name(),0,0);
		subList.addItem(Material.STICK.name(),0,0);
		subList.addItem(Material.WOOL.name(),0,0);
		subList.addItem(Material.SEEDS.name(),0,0);
		subList.addItem(Material.FENCE.name(),0,0);
		subList.addItem(Material.FENCE_GATE.name(),0,0);
		subList.addItem(Material.BED.name(),0,0);
		subList.addItem(Material.TORCH.name(),0,0);
		subList.addItem(Material.BOOKSHELF.name(),0,0);
		subList.addItem(Material.WOOD_DOOR.name(),0,0);
		subList.addItem(Material.CHEST.name(),0,0);
		subList.addItem(Material.WORKBENCH.name(),0,0);
		subList.addItem(Material.FURNACE.name(),0,0);
		subList.addItem(Material.WALL_SIGN.name(),0,0);
		subList.addItem(Material.SIGN.name(),0,0);
		subList.addItem(Material.SIGN_POST.name(),0,0);
		subList.addItem(Material.LEATHER.name(),0,0);
		subList.addItem(Material.IRON_INGOT.name(),0,0);

//		subList.addItem(Material..name(),0,0);
		
		return subList;
	}

	public static ItemList initOre()
	{
		ItemList subList = new ItemList();

		subList.addItem(Material.COAL_ORE.name(),0,0);
		subList.addItem(Material.IRON_ORE.name(),0,0);
		subList.addItem(Material.GOLD_ORE.name(),0,0);
		subList.addItem(Material.DIAMOND_ORE.name(),0,0);
		subList.addItem(Material.REDSTONE_ORE.name(),0,0);
//		subList.addItem(Material.EMERALD_ORE.name(),0,0);
		subList.addItem(Material.LAPIS_ORE.name(),0,0);
//		subList.addItem(Material.QUARTZ_ORE.name(),0,0);
//		subList.addItem(Material..name(),0,0);
			
		return subList;
	}
	
	public static ItemList initValuables()
	{
		ItemList subList = new ItemList();

		subList.addItem(Material.GOLD_NUGGET.name(),0,0);
		subList.addItem("EMERALD",0,0);
		subList.addItem(Material.DIAMOND.name(),0,0);
		subList.addItem(Material.GOLD_INGOT.name(),0,0);
//		subList.addItem(Material..name(),0,0);
			
		return subList;
	}
	
	public static net.krglok.realmsBank.Common.ItemList initRawMaterial()
	{
		ItemList subList = new ItemList();

		subList.addItem(Material.DIRT.name(),0,0);
		subList.addItem(Material.STONE.name(),0,0);
		subList.addItem(Material.GRASS.name(),0,0);
		subList.addItem(Material.NETHERRACK.name(),0,0);
		subList.addItem(Material.LOG.name(),0,0);
		subList.addItem(Material.LOG_2.name(),0,0);
		subList.addItem(Material.GRAVEL.name(),0,0);
		subList.addItem(Material.WATER.name(),0,0);
		subList.addItem(Material.WOOL.name(),0,0);
		subList.addItem(Material.CLAY.name(),0,0);
		subList.addItem(Material.SAND.name(),0,0);
		subList.addItem(Material.LAVA.name(),0,0);
		subList.addItem(Material.LEAVES.name(),0,0);
//		subList.addItem(Material.LEAVES_2.name(),0,0);
		subList.addItem(Material.SANDSTONE.name(),0,0);
		subList.addItem(Material.SNOW.name(),0,0);
		subList.addItem(Material.ICE.name(),0,0);
		subList.addItem(Material.CACTUS.name(),0,0);
		subList.addItem(Material.SUGAR_CANE.name(),0,0);
		subList.addItem(Material.PUMPKIN.name(),0,0);
		subList.addItem(Material.SEEDS.name(),0,0);
		subList.addItem(Material.MELON.name(),0,0);
		subList.addItem(Material.VINE.name(),0,0);
		subList.addItem(Material.MYCEL.name(),0,0);
		subList.addItem(Material.HUGE_MUSHROOM_1.name(),0,0);
		subList.addItem(Material.HUGE_MUSHROOM_2.name(),0,0);
		subList.addItem(Material.MOSSY_COBBLESTONE.name(),0,0);
		subList.addItem(Material.SAPLING.name(),0,0);
		subList.addItem(Material.FLINT.name(),0,0);
//		subList.addItem(Material..name(),0,0);
			
		return subList;
	}

	public static ItemList initFoodMaterial()
	{
		ItemList subList = new ItemList();

		subList.addItem(Material.WHEAT.name(),0,0);
		subList.addItem(Material.BREAD.name(),0,0);
		subList.addItem(Material.RED_MUSHROOM.name(),0,0);
		subList.addItem(Material.BROWN_MUSHROOM.name(),0,0);
		subList.addItem(Material.MUSHROOM_SOUP.name(),0,0);
		subList.addItem(Material.RED_MUSHROOM.name(),0,0);
		subList.addItem(Material.BROWN_MUSHROOM.name(),0,0);
		subList.addItem(Material.COOKED_BEEF.name(),0,0);
		subList.addItem(Material.COOKED_CHICKEN.name(),0,0);
		subList.addItem(Material.COOKED_FISH.name(),0,0);
		subList.addItem(Material.COOKIE.name(),0,0);
		subList.addItem(Material.COOKED_MUTTON.name(),0,0);
		subList.addItem(Material.COOKED_RABBIT.name(),0,0);
		subList.addItem(Material.RABBIT.name(),0,0);
		subList.addItem(Material.RABBIT_STEW.name(),0,0);
		subList.addItem(Material.RAW_BEEF.name(),0,0);
		subList.addItem(Material.RAW_CHICKEN.name(),0,0);
		subList.addItem(Material.RAW_FISH.name(),0,0);
		subList.addItem(Material.CARROT.name(),0,0);
		subList.addItem(Material.CAKE.name(),0,0);
		subList.addItem(Material.CAKE_BLOCK.name(),0,0);
		subList.addItem(Material.POTATO.name(),0,0);
		subList.addItem(Material.POTATO_ITEM.name(),0,0);
		subList.addItem(Material.MELON.name(),0,0);
		
		
//		subList.addItem(Material..name(),0,0);

		return subList;
	}

	public static ItemList initIngnoreList()
	{
		ItemList subList = new ItemList();

		subList.addItem(Material.DIRT.name(),0,0);
		subList.addItem(Material.SOIL.name(),0,0);
		subList.addItem(Material.GRASS.name(),0,0);
		subList.addItem(Material.WATER.name(),0,0);
		subList.addItem(Material.SAND.name(),0,0);
//		subList.addItem(Material..name(),0,0);
			
		return subList;
	}
	
	public static ItemGroup getItemGroup(String itemRef)
	{
		if (initFoodMaterial().getItem(itemRef) != null) { return ItemGroup.FOOD; } 
		if (initArmor().getItem(itemRef) != null) { return ItemGroup.ARMOR; } 
		if (initBuildMaterial().getItem(itemRef) != null) { return ItemGroup.BUILDMATERIAL; } 
		if (initMaterial().getItem(itemRef) != null) { return ItemGroup.MATERIAL; } 
		if (initOre().getItem(itemRef) != null) { return ItemGroup.ORE; } 
		if (initRawMaterial().getItem(itemRef) != null) { return ItemGroup.RAW; } 
		if (initValuables().getItem(itemRef) != null) { return ItemGroup.VALUABLE; } 
		if (initWeapon().getItem(itemRef) != null) { return ItemGroup.WEAPON; } 
			
		return ItemGroup.NONE;
	}

	/**
	 * will be used for initilize the bank 
	 * @return
	 */
	public static ItemList initBankValuables()
	{
		ItemList subList = new ItemList();

		subList.addItem(Material.GOLD_BLOCK.name(),0,320);
			
		return subList;
	}
	
	public static double getInitBankValuables()
	{
		double sum = 0.0;
		for (Item item : initBankValuables().values())
		{
			if (item.MaterialRef().equalsIgnoreCase(Material.GOLD_BLOCK.name()))
			{
				sum = sum + (item.value() * GOLDBLOCK_PRICE);
			}
			if (item.MaterialRef().equalsIgnoreCase(Material.GOLD_INGOT.name()))
			{
				sum = sum + (item.value() * GOLDINGOT_PRICE);
			}
			if (item.MaterialRef().equalsIgnoreCase(Material.GOLD_NUGGET.name()))
			{
				sum = sum + (item.value() * GOLDNUGGET_PRICE);
			}
		}
		return sum;
	}

	
}
