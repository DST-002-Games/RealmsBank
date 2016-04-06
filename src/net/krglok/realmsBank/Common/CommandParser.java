package net.krglok.realmsBank.Common;

/**
 * 
 * @author Windu
 * @create 25.03.2016
 * 
 * description:
 * the Class realize a commandparser with subcommands and parameterlist 
 * - use SubCommandType for SubCommand identification
 * - use typed parameters for checking values
 * - use multiple line text for help and explanation
 * - Cancel Execution with errorMessage
 * - execute Command after checking parameters and conditions
 * 
 * The parser use a Array CommandDefinition to identify the commands.
 * When you want dynamically instantiation  of command you must do this outside
 * of this Class before you instantiate the CommandParser  
 * 
 * This is only a interpreter. The command registration and event handling
 * must be done outside of this class
 *
 */
public class CommandParser
{
	private aRealmsCommand[] commandList;

	public CommandParser(aRealmsCommand[] commandList)
	{
		this.commandList = commandList;
	}
	

	public aRealmsCommand getRealmsCommand(RealmsCommandType cmdType, String[] args)
	{
	
		if (args.length == 0)
		{
			return findSubCommand(cmdType, SubCommandType.NONE);
		}
		SubCommandType subCommand = SubCommandType.getRealmSubCommandType(args[0]);
		if (subCommand != SubCommandType.NONE)
		{
			return checkParameter(cmdType, subCommand, args); 
		}else
		{
			return findSubCommand(cmdType, SubCommandType.HELP);
		}
	}
	
	private aRealmsCommand findSubCommand(RealmsCommandType cmdType, SubCommandType subCommand)
	{
		if (commandList.length > 0)
		{
			for (int i = 0; i < commandList.length; i++)
			{
				if ((commandList[i].subCommand() == subCommand)
					&& (commandList[i].command() == cmdType)
					)
				{
					commandList[i].getErrorMsg().clear();
					return commandList[i];
				}
			}
		}
		return null;
	}
	
	
	private aRealmsCommand checkParameter (RealmsCommandType cmdType, SubCommandType subCommand, String[] args )
	{
//		args = decreaseArgs(args);
		aRealmsCommand cmd = findSubCommand(cmdType, subCommand);
		// SubCommand NOT found
		if (cmd == null)
		{
			return  findSubCommand(cmdType, SubCommandType.HELP);
		}
		cmd.setParserError(false);		
		// Check Arguments of SubCommand
		if ((args.length-1) < cmd.getRequiredArgs() )
		{
			cmd.addErrorMsg(cmd.getDescription()[0]);
			cmd.addErrorMsg("Not enough Parameter given");
			cmd.addErrorMsg("The command requires "+cmd.getRequiredArgs());
			return  cmd; //findSubCommand(RealmsSubCommandType.HELP);
		} else
		{
			String[] paraTypes = cmd.getParaTypes();
			// in case of optional parameter null is possible
			if (paraTypes == null)
			{
//				cmd.addErrorMsg("No Parameter required");
				return cmd;
			}
			// check parameter definition against input parameter
			int len = 0;
			len = ((args.length-1) > paraTypes.length) ?   paraTypes.length : (args.length-1);
			// Array of required ArgumentTypes
			for (int i = 0; i <len; i++)
			{
				if (paraTypes[i].equalsIgnoreCase(int.class.getName()))
				{
					try
					{
						cmd.setPara(i, (int) Integer.valueOf(args[i+1]));
					} catch (Exception e)
					{
						cmd.setParserError(true);		
						cmd.addErrorMsg(cmd.getDescription()[0]);
						cmd.addErrorMsg("Wrong Datatype on "+(i+1)+":"+paraTypes[i]);
//							cmd.addErrorMsg("SetPara "+i+":"+paraTypes[i]);
					}
				}
				if (boolean.class.getName() == paraTypes[i])
				{
					try
					{
						cmd.setPara(i, Boolean.valueOf(args[i+1]));
					} catch (Exception e)
					{
						cmd.setParserError(true);		
						cmd.addErrorMsg(cmd.getDescription()[0]);
						cmd.addErrorMsg("Wrong Datatype on "+(i+1)+":"+paraTypes[i]);
//							cmd.addErrorMsg("SetPara "+i+":"+paraTypes[i]);
					}
				}
				if (double.class.getName() == paraTypes[i])
				{
					try
					{
						cmd.setPara(i, Double.valueOf(args[i+1]));
					} catch (Exception e)
					{
						cmd.setParserError(true);		
						cmd.addErrorMsg(cmd.getDescription()[0]);
						cmd.addErrorMsg("Wrong Datatype on "+(i+1)+":"+paraTypes[i]);
//							cmd.addErrorMsg("SetPara "+i+":"+paraTypes[i]);
					}
				}
				if (String.class.getName() == paraTypes[i])
				{
//						cmd.addErrorMsg("String Parmeter "+i+" : "+args[i+1]);
					try
					{
						cmd.setPara(i, args[i+1]);
					} catch (Exception e)
					{
						cmd.setParserError(true);		
						cmd.addErrorMsg(cmd.getDescription()[0]);
						cmd.addErrorMsg("Wrong Datatype on "+(i+1)+":"+paraTypes[i]);
//							cmd.addErrorMsg("SetPara "+i+":"+paraTypes[i]);
					}
				} else
				{
					// error Handling 
				}
			}
				
		}
		return cmd;
	}
	
}
