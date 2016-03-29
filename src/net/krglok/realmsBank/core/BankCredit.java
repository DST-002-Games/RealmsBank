package net.krglok.realmsBank.core;

/**
 * @author Windu
 * @create 26.03.2016
 * <pre>
 * description:
 * realize a credit of virtual money with period and interest per Anno
 * the will withdraw from the bank account of the credit obligor
 * IF the account has not enough money the credit value will increase
 * IF the restSpan is reached and the credit value will withdraw from
 *    bank account. 
 *   HAS the account not enough money the credit value will be 
 *   increase and the span renew for 1 year (360 days)    
 * 
 * value    = the actual credit value
 * interest = the interest for credit per Anno
 * span     = the original credit period in ingame days
 * restSpan = the rest credit period in ingame days
 * 
 * </pre>
 */
public class BankCredit
{
	private double value;
	private double interest;
	private int span;
	private int restSpan;
	
	public BankCredit()
	{
		value = 0.0;
		interest = 0.0;
		span = 0;
		restSpan = 0;
	}

	public BankCredit(double value, double interest, int span, int restSpan)
	{
		this.value = value;
		this.interest = interest;
		this.span = span;
		this.restSpan = restSpan;
	}
	
	/**
	 * 
	 * @param period, in ingame days
	 * @return interest in money 
	 */
	public double calcInerest(int period)
	{
		double money = (value * interest / 100.0) * period / 360;
		return money;
	}
	
	public void redemption(double redemp)
	{
		value = value - redemp;
	}
	
	public void addValue (double increase)
	{
		value = value + increase;
	}
	
	public void decreaseSpan(int decrease)
	{
		restSpan = restSpan - decrease;
	}
	
	public void setCredit(int newSpan, double newValue)
	{
		span = span + newSpan;
		value = value + newValue;
	}
	
	public void restCredit()
	{
		value = 0.0;
		interest = 0.0;
		span = 0;
		restSpan = 0;
	}

	public double getValue()
	{
		return value;
	}

	public void setValue(double value)
	{
		this.value = value;
	}

	public double getInterest()
	{
		return interest;
	}

	public void setInterest(double interest)
	{
		this.interest = interest;
	}

	public int getSpan()
	{
		return span;
	}

	public void setSpan(int span)
	{
		this.span = span;
	}

	public int getRestSpan()
	{
		return restSpan;
	}

	public void setRestSpan(int restSpan)
	{
		this.restSpan = restSpan;
	}
}
