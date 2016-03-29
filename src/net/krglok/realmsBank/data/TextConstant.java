package net.krglok.realmsBank.data;

import org.bukkit.ChatColor;

/**
 * This are global used text constant.
 * 
 * You should change it to your needs
 * 
 * @author Windu
 *
 */

public class TextConstant
{
	public static final String WRONG_ITEMNAME = "Wrong itemname !";
	public static final String REGION_NOT_FOUND = "Region not found!";
	public static final String WRONG_SETTLEMNET_ID = "Wrong Settlemnet ID ";
	public static final String WRONG_ARGUMENTS = "Wrong argument";
	public static final String NOT_ENOUGH_ARGUMENTS_FOR = "Not enough arguments for ";
	public static final String FIRST_LINE = "==============================";
	public static final String NO_PAGE = "No page found !";
	public static final String NO_PERMISSION = "You have not the right permissions";
	public static final String FILE_IO_ERROR = ": File IO error !";

	public static  String KONTO_TOO_LOW = "konto too low ";
	public static  String WITHDRAW = "Withdraw ";
	public static  String DEPOSIT = "Deposit ";
	public static  String ADD_KONTO = "Add Konto ";
	public static  String DEPOSIT_ACCESS_1 = "You Deposit ";
	public static  String DEPOSIT_ACCESS_2 = " on BankAccount ";
	public static  String DEPOSIT_ACCESS_3 = "Bank balance : ";

	public static  String NO_ACCESS_PERMISSION_1 = ChatColor.RED+"You have no permissions to do that"+ChatColor.WHITE;
	public static  String NO_ACCESS_PERMISSION_2 = "You can only access your own bankAccount";

	
	public static final ChatColor FIRST_LINE_COLOUR = ChatColor.GREEN;
	
	private static int pageLines = 17;

	public static boolean isLogAll = false;

	/**
	 * hold the constants for textmesaegs in plugin and Model
	 */
	public TextConstant()
	{
		
	}
	
	public static int pageLines()
	{
		return pageLines;
	}
	
	public void setPageLines(int value)
	{
		pageLines = value;
	}
	
	public void loadMessagesFromFile()
	{
		System.out.println(FILE_IO_ERROR);
	}
}
