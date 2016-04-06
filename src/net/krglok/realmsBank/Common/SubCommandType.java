package net.krglok.realmsBank.Common;

/**
 * <pre>
 * Enum for the possible subCommands of the plugin
 * this are keywords for the command interpreter
 * 
 * You should change it to your needs
 * 
 * @author Windu
 *</pre>
 */
public enum SubCommandType 
{
	NONE ,
	ACTIVATE ,
	ADD,
	AQUIRE,
	ASSUME,
	BANK,
	BIOME,
	BOOK,
	BOOKLIST,
	BUILDING,
	BUILDINGLIST,
	BUILD,
	BUY,
	CREATE ,
	CHECK,
	CREDIT,
	DEACTIVATE ,
	DELETE ,
	DEPOSIT ,
	DEPLOY,
	DESTROY ,
	ENABLE,
	EVOLVE,
	FOUNDING,
	FOLLOW,
	GETITEM,
//	GET,
	GIVE,
	HOME,
	HIRE,
	INFO ,
	JOIN,
//	LIMIT,
	LIST ,
	MAP,
	MARKET,
	MEMBER,
	MOVE,
	NOSELL,
	OWNER,
	PRICE,
	PRICELIST,
	PRODUCTION ,
	RAID,
	READ,
	RECIPE,
	RECIPES,
	RELEASE,
	REQUEST,
	REQUIRE,
	REPUTATION,
	ROUTE,
	ROUTES,
//	ROUTESTOP,
	SETTLER,
	SET ,
	SETITEM,
	SELL,
	SETTLEMENT,
	SHOP,
	SIGN,
	STRUCTURE,
	TAX,
	TECH,
	TEST ,
	TRADER,
	TRAIN,
	TRAINING,
	TRANSFER,
	WAREHOUSE,
	WRITE,
	DEBUG,
//	UNSET ,
	VERSION ,
	WALLSIGN,
	WITHDRAW ,
	WORKSHOP,
	HELP
	;
	
		
	public static SubCommandType getRealmSubCommandType(String name)
	{
		name = name.toUpperCase();
		for (SubCommandType rsc : SubCommandType.values())
		{
			if (rsc.name().equalsIgnoreCase(name))
			{
				return rsc;
			}
		}
		return NONE;
	}

	public static SubCommandType searchRealmSubCommandType(String name)
	{
		name = name.toUpperCase();
		for (SubCommandType rsc : SubCommandType.values())
		{
			if (rsc.name().contains(name))
			{
				return rsc;
			}
		}
		return NONE;
	}

}
