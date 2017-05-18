package CommandResponses;

import java.io.Serializable;

public class CommandResponse implements Serializable{
	
	private Object object;
	
	public  void setResponse(Object object){
		
		this.object = object;
	}
	public Object getResponse(){
		return object;
	}

}
