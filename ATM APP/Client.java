import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;  

public class Client {
	
	private Client() {}
	public static void main(String[] args) {
		try{
		
		Registry reg = LocateRegistry.getRegistry(null);
		
		atm stub = (atm) reg.lookup("atm");
		
		stub.account();
		
		}catch(Exception e) {
			System.out.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}