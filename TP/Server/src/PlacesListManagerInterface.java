import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlacesListManagerInterface extends Remote
{
    void addPlace(Place var1) throws RemoteException;
    String getPlaceListAddress(String var1) throws RemoteException;
}
