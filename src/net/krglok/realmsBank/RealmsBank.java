package net.krglok.realmsBank;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.krglok.realmsBank.command.CommandList;
import net.krglok.realmsBank.core.BankAccount;
import net.krglok.realmsBank.core.BankAccountList;
import net.krglok.realmsBank.ServerListener;
import net.krglok.realmsBank.Common.RealmsPermission;
import net.krglok.realmsBank.data.ConfigData;
import net.krglok.realmsBank.data.DataStore;
import net.krglok.realmsBank.Common.MessageData;
import net.krglok.realmsBank.Common.ConfigBasis;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * 
 * @author Windu
 * @create 25.03.2016
 * 
 * description:
 * This class realize the plugin for RealmsBank.
 * it use the import net.krglok.realmsBank.Common Library for basic 
 * functions and Classes.
 * 

 * 
 */
public class RealmsBank extends JavaPlugin
{

	private Logger log = Logger.getLogger("Minecraft"); 
	private final MessageData messageData = new MessageData();
	protected FileConfiguration configFile;	
	private ConfigData configData; // = new ConfigData(this);
	public ServerListener serverListener; // = new ServerListener(this);
	private final CommandList commandList = new CommandList(this);
	private DataStore data; 
	
	protected Economy economy;
	
	

	@Override
	public void onDisable()
	{
		data.writeAccounts();
	}
	
	
	@Override
	public void onEnable()
	{
		// read plugin configuration
		getPluginConfig();
		// read economy interface
		if (setupEconomy() == false)
		{
			log.log(Level.SEVERE, " ["+ConfigBasis.PLUGIN_NAME+"] NO Economy found ! ");
		}
		//
        configData = new ConfigData(this.configFile);
        if (configData == null)
        {
        	log.log(Level.SEVERE, "[Realms] The configData are null!!");
        }

        serverListener = new ServerListener(this);
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(serverListener, this);
        
		// read persistent data
		data = new DataStore(this.getDataFolder().getPath());
		
	}
	
	/**
	 *  @param sender is player , Operator or console
	 *  @param command 
	 *  @param label , the used alias of the command
	 *  @param args , command parameter, [0] = SubCommand
	 *  @return the command execution status false = show plugin.yml command description  
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
    {
		// hanldes all commands and Subcommands 
		if (sender.isOp() == false)
		{
			if (sender.hasPermission(RealmsPermission.ADMIN.getValue().toLowerCase()) == false)
			{
				if (sender.hasPermission(RealmsPermission.USER.getValue().toLowerCase()) == false)
				{
					sender.sendMessage(ChatColor.RED+"You not have permission realms.user !");
					sender.sendMessage(ChatColor.YELLOW+"Contact the OP or ADMIN for setup permission.");
					return true;
				}
			}
		}
		commandList.run(sender, command, args);
		return true;
    }

	
	/**
	 * read config from plugin interface 
	 * use the server/plugin config file handling 
	 * check for correct version
	 * Be sure the strin of CONFIG_VER in config.yml and ConfigBasis are equal
	 */
	private void getPluginConfig()
	{
		// read config from plugin 
		this.configFile =  this.getConfig();
		// check for correct version
		// Be sure the strin of CONFIG_VER in config.yml and ConfigBasis
		// are equal
		String nameValue = configFile.getString(ConfigBasis.CONFIG_VER_NAME,"");
		if (!nameValue.equalsIgnoreCase(ConfigBasis.CONFIG_VER))
		{
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
			nameValue = configFile.getString(ConfigBasis.CONFIG_VER_NAME,"");
		}
		this.log.info("["+ConfigBasis.PLUGIN_NAME +"] new configname : "+nameValue);
		
	}

	/**
	 * the logger has status relevant out ERROR, WARNING, INFO etc. 
	 * @return the logger 
	 */
	public Logger getLog()
	{
		return log;
	}

	/**
	 * load the economy plugin from Vault
	 * @return
	 */
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
		{
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
    
    /**
     * necessary for Command.run()
     * @return
     */
    public Economy economy()
    {
    	return this.economy;
    }

    /**
     * necessary for Command.run()
     * @return
     */
    public MessageData getMessageData()
    {
    	return this.messageData;
    }
    
    /**
     * necessary for Command.run()
     * @return
     */
    public ConfigData getConfigData()
    {
    	return configData;
    }
    
    /**
     * necessary for Command.run()
     * @return
     */
    public String getVersion()
    {
    	return this.getDescription().getVersion();
    }
    
    public CommandList commandList()
    {
    	return commandList;
    }
    
    public DataStore getData()
    {
    	return data;
    }
    
}
