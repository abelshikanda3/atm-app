


import java.rmi.Remote;
import java.rmi.RemoteException;

//Remote Interface
public interface atm extends Remote {
	void account() throws RemoteException;
	void withdraw() throws RemoteException;
	void deposit() throws RemoteException;
	
}