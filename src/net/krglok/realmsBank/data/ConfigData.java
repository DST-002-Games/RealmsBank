package net.krglok.realmsBank.data;


import net.krglok.realmsBank.Common.*;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * 
 * @author Windu
 * @create 25.03.2016
 * <pre>
 * description:
 * read Data from YML file 
 * used for initialize the Plugin with config data
 * Hint: the plugin are not referenced here, so it is independent from the plugin system
 * this is useful for test and simuation
 * - configFile; hold the plugin configFile reference;
 * - configFile; is initialize loaded by the plugin
 * </pre>
 */
public class ConfigData extends ConfigBasis
{

	
	private ItemList toolItems;
	private ItemList weaponItems;
	private ItemList armorItems;
	private ItemList buildItems;
	private ItemList materialItems;
	private ItemList oreItems;
	private ItemList valuableItems;
	private ItemList rawItems;
	private ItemList foodItems;

	private boolean calcinterest = true;
	private boolean calccreditinterest = true;
	private boolean allowcredit = true;
	private boolean allowitembank = false;
	private boolean allowxpbank = false;

	
	// this boolean / method is necessary for BUKKIT Plugin Approve
	private boolean isUpdateCheck;
	
	// this boolean / method is necessary for BUKKIT Plugin Approve
	private boolean isAutoUpdate;

	// flag show the initialize state of the config
	private boolean isLoaded = false;
	
	protected FileConfiguration configFile;
	
	public ConfigData(FileConfiguration configFile )
	{
		
		// read config data from configuration nodes of config.yml
		this.configFile = configFile;
		isUpdateCheck = configFile.getBoolean("updatecheck", false);
		isAutoUpdate  = configFile.getBoolean("autoupdate", false);
		// money base price
		ConfigBasis.GOLDNUGGET_PRICE = configFile.getDouble("GOLDNUGGET_PRICE",45.0);
		// bank settings
		calcinterest = configFile.getBoolean("calcinterest",true);
		calccreditinterest = configFile.getBoolean("calccreditinterest", true);
		allowcredit = configFile.getBoolean("allowcredit", true);
		allowitembank = configFile.getBoolean("allowitembank", false);
		allowxpbank = configFile.getBoolean("allowxpbank", false);
		
		setLoaded(true);
	}

	/**
	 * @return the isLoaded
	 */
	public boolean isLoaded()
	{
		return isLoaded;
	}

	/**
	 * @param isLoaded the isLoaded to set
	 */
	public void setLoaded(boolean isLoaded)
	{
		this.isLoaded = isLoaded;
	}

	public Boolean initConfigData()
	{
		
		armorItems = ConfigBasis.initArmor();
		weaponItems = ConfigBasis.initWeapon();
		toolItems = ConfigBasis.initTool();
		buildItems  = ConfigBasis.initBuildMaterial();
		materialItems = ConfigBasis.initMaterial();
		oreItems = ConfigBasis.initOre();
		valuableItems = ConfigBasis.initValuables();
		rawItems = ConfigBasis.initRawMaterial();
		foodItems  = ConfigBasis.initFoodMaterial();
		
		return true;
	}


	public String getPluginName()
	{
		return PLUGIN_NAME;
	}

	public ItemList getToolItems()
	{
		return toolItems;
	}

	public ItemList getWeaponItems()
	{
		return weaponItems;
	}

	public ItemList getArmorItems()
	{
		return armorItems;
	}

	public ItemList getBuildMaterialItems()
	{
		return buildItems;
	}
	
	public ItemList getMaterialItems()
	{
		return materialItems;
	}
	
	public ItemList getOreItems()
	{
		return oreItems;
	}
	
	public ItemList getValuables()
	{
		return valuableItems;
	}
	
	public ItemList getRawItems()
	{
		return rawItems;
	}
	
	public ItemList getFoodItems()
	{
		return foodItems;
	}

	/**
	 * this boolean / method is necessary for BUKKIT Plugin Approve
	 * @return
	 */
	public boolean isUpdateCheck()
	{
		return isUpdateCheck;
	}
	
	/**
	 * this boolean / method is necessary for BUKKIT Plugin Approve
	 * @return
	 */
	public boolean isAutoUpdate()
	{
		return isAutoUpdate;
	}

	public boolean isCalcinterest()
	{
		return calcinterest;
	}

	public void setCalcinterest(boolean calcinterest)
	{
		this.calcinterest = calcinterest;
	}

	public boolean isCalccreditinterest()
	{
		return calccreditinterest;
	}

	public void setCalccreditinterest(boolean calccreditinterest)
	{
		this.calccreditinterest = calccreditinterest;
	}

	public boolean isAllowcredit()
	{
		return allowcredit;
	}

	public void setAllowcredit(boolean allowcredit)
	{
		this.allowcredit = allowcredit;
	}

	public boolean isAllowitembank()
	{
		return allowitembank;
	}

	public void setAllowitembank(boolean allowitembank)
	{
		this.allowitembank = allowitembank;
	}

	public boolean isAllowxpbank()
	{
		return allowxpbank;
	}

	public void setAllowxpbank(boolean allowxpbank)
	{
		this.allowxpbank = allowxpbank;
	}

	

}
