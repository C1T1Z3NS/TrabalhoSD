import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server
{
    public Server() {}

    public static void main(String[] args)
    {
        Registry r = null;
        try
        {
            r = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
        }
        catch (RemoteException var6)
        {
            var6.printStackTrace();

            try
            {
                r = LocateRegistry.getRegistry(Integer.parseInt(args[0]));
            }
            catch (RemoteException | NumberFormatException var5)
            {
                var5.printStackTrace();
            }
        }
        try
        {
            PlaceManager placeList = new PlaceManager(Integer.parseInt(args[0]));
            r.rebind("placelist", placeList);
            System.out.println("Place server ready");
        }
        catch (Exception var4)
        {
            System.out.println("Place server main" + var4.getMessage());
        }
    }
}