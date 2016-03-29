package net.krglok.realmsBank.Common;


/**
 * <pre>
 * Independent type for blocks and items
 * make it possible to separate the model from the minecraft server
 * property:
 *  String sRef;
 *  Integer iValue;
 *  Integer data , represent the data value of blocks and item, mostly 0
 * @author krglok
 *
 * </pre>
 */
public class Item 
{
   private String sRef;
   private Integer iValue;
   private Integer iData;
   
   public Item()
   {
	   sRef = "AIR";
	   iValue = 0;
	   iData =0;
   }
   

   public Item(String itemRef, int data, int value)
   {
	   this.sRef = itemRef;
	   this.iValue = value;
	   iData = data;
   }
   
   public  String MaterialRef()
   {
	   return sRef;
   }

   public  String ItemRefData()
   {
	   return makeItemRef(sRef, iData);
   }
   
   public Integer value()
   {
//	   System.out.println(iValue);
	   return iValue;
   }
   
   public void setItemRef(String itemRef)
   {
	   sRef = itemRef;
   }
   
   public void setValue(int value)
   {
	   iValue = value;
   }
   
   public void setData(int data)
   {
	   this.iData = data;
   }
   
   public int getData()
   {
	   return iData;
   }

   
   public static String makeItemRef(String itemRef, int data)
   {
	   return  itemRef.toUpperCase()+"_"+String.valueOf(data);

   }
   
   
   public static int getItemDataString(String itemString)
   {
	   String[] splitted = itemString.split("_");
	   try
		{
		   return Integer.valueOf(splitted[splitted.length]);
			
		} catch (Exception e)
		{
			return 0;
		}
   }

   
   public static String getItemRefString(String itemString)
   {
		String[] splitted = itemString.split("_");
		String outValue = "";
		// nur den letzten nicht
		for (int i = 0; i < splitted.length-1; i++)
		{
			outValue = outValue+"_"+splitted[i];
		}
		return outValue;
   }

}

