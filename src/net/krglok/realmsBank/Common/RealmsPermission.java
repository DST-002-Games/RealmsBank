package net.krglok.realmsBank.Common;

/**
 * This are the permission Constant. 
 * You should change it to your needs
 * 
 * the enum specify a symbolic permission name
 * the value is the real permission node 
 * 
 * @author Windu
 *
 */
public enum RealmsPermission
{
	ADMIN ("realmsbank.admin"),
	USER ("realmsbank.user")
    ;
	
	private final String value;
	
	RealmsPermission(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
}

