import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 

public class Server extends atmimpl {
	
	//public Server() {}
	public static void main(String[] args) {
		try{
			atmimpl obj = new atmimpl();
			
			atm stub = (atm) UnicastRemoteObject.exportObject(obj, 0);
			
			Registry reg = LocateRegistry.getRegistry();
			
			reg.bind("atm", stub);
			
			System.err.println("Good To Go");
			}catch(Exception e) {
				System.err.println("server exception : " + e.toString());
				e.printStackTrace();
			}
	}
}