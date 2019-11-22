import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

public class PlaceManager extends UnicastRemoteObject implements PlacesListInterface, MonitoringInterface 
{
    private static final long serialVersionUID = 1L;
    public ArrayList<Place> lp = new ArrayList();

    final static String INET_ADDR = "224.0.0.3";
    //final static int port = 8888;
    
    protected PlaceManager(int port) throws RemoteException, UnknownHostException
    {
    	// Get the address that we are going to connect to.
        InetAddress address = InetAddress.getByName(INET_ADDR);
         
        // Create a buffer of bytes, which will be used to store
        // the incoming bytes containing the information from the server.
        // Since the message is small here, 256 bytes should be enough.
        byte[] buf = new byte[256];
         
        // Create a new Multicast socket (that will allow other sockets/programs
        // to join it as well.
        try (MulticastSocket clientSocket = new MulticastSocket(port)){
            //Joint the Multicast group.
            clientSocket.joinGroup(address);
      
            while (true) {
                // Receive the information and print it.
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket);
 
                String msg = new String(buf, 0, buf.length);
                System.out.println("Socket 1 received msg: " + msg);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void addPlace(Place p) throws RemoteException
    {
        this.lp.add(p);
    }
    public ArrayList<Place> allPlaces() throws RemoteException
    {
        return this.lp;
    }
    public Place getPlace(String cp) throws RemoteException
    {
        Iterator var3 = this.lp.iterator();
        
        while(var3.hasNext())
        {
            Place p = (Place)var3.next();
            
            if (p.getPostalCode().equals(cp))
            {
                return p;
            }
        }
        return null;
    }
    public void ping() throws RemoteException{}
}