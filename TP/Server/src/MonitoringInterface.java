import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MonitoringInterface extends Remote
{
    public void ping() throws RemoteException;
}
