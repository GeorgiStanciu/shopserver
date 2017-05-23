package Commands;

import java.io.Serializable;

import Models.CommandEnum;

public class Command implements Serializable{

	private CommandEnum command;
	private Object object;
	
	public Command(){
		
	}
	public Command(CommandEnum command){
		this.command = command;
	}
	public Command(CommandEnum command, Object object){
		this.command = command;
		this.object = object;
	}
	
	public void setObject(Object obj){
		object = obj;
	}
	
	
	public Object getObject(){
		return object;
	}
	
	public CommandEnum getCommand(){
		return command;
	};
}
