import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * A multithreaded chat room server.  
 * The  chat server executive calls startServer, which runs 
 * ChatServer in a thread, in order not to block the GUI.
 * This top-level thread listens for a client to connect, 
 * asks for the client's Screen Name, checks it for duplication,
 * and if ok, starts a new thread by which the server will
 * interact with this client. This thread allows the server
 * to interact with many clients at once.  The names of all
 * clients are kept in a field HashSet<String> names to allow
 * checking for duplicate screen names, and the output streams 
 * are kept in a field named HashSet<PrintWriter> writers, in order
 * that each message can be retransmitted to all the clients.
 * 
 * When a client connects the
 * server requests a screen name by sending the client the
 * text "SUBMITNAME", and keeps requesting a name until
 * a unique one is received.  After a client submits a unique
 * name, the server acknowledges with "NAMEACCEPTED".  Then
 * all messages from that client will be broadcast to all other
 * clients that have submitted a unique screen name.  The
 * broadcast messages are prefixed with "MESSAGE ".
 * 
 * 
 */
public class ChatServerExec implements ChatServerExecInterface
{	
	
	private static int PORT;
    private static HashSet<String> names = new HashSet<String>();              
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();  
	
    
    /**
     * Runs ChatServer in a thread, in order not to block the GUI.
     *
     * @param port the port number the server will use to run on
     */
	@Override
	public void startServer(int port)
	{
		PORT = port;
	
		Thread server = new Thread()
		{			
			@Override 
            public void run()
			{
				try
				{
					ServerSocket serverSocket = new ServerSocket(PORT);
	                
	                while (true)
	                {               
	                    try
	                    {
	                        while (true)
	                        {
	                        	NewClientThread obj = new NewClientThread(serverSocket.accept());
	                        	obj.start();
	                        }
	                    }
	                    finally
	                    {
	                    	serverSocket.close();
	                    }    
	                }          
				}			
				catch (IOException e)
                {
                    e.printStackTrace();
                }	
			}
		};
		server.start();
	}
	
    /**
     * A Thread class which will be used every time a new client is added into the chat room
     */
    private static class NewClientThread extends Thread
    {
        private Socket serverSocket;
    	private BufferedReader in;
        private PrintWriter out;
        private String screenName;

        /**
         * Constructs a handler thread, squirreling away the socket.
         * All the interesting work is done in the run method.
         */
        public NewClientThread(Socket socket)
        {
            this.serverSocket = socket;
        }

        /**
         * Services this thread's client by repeatedly requesting a
         * screen name until a unique one has been submitted, then
         * acknowledges the name and registers the output stream for
         * the client in a global set, then repeatedly gets inputs and
         * broadcasts them.
         */
        public void run()
        {
            try
            {
                in = new BufferedReader(new InputStreamReader( serverSocket.getInputStream()));
                out = new PrintWriter(serverSocket.getOutputStream(), true);

                while (true)
                {
	                out.println("SUBMITNAME");
	                screenName = in.readLine();
	                
	                if (screenName == null)
	                {
	                    return;
	                }
	                synchronized (names)
	                {
	                    if (!names.contains(screenName))
	                    {
	                        names.add(screenName);
	                        
	                        break;
	                    }
	                }
                }
                
                out.println("NAMEACCEPTED");
                writers.add(out);
                
                while (true)
                {
                    String input = in.readLine();
                    if (input == null)
                    {
                        return;
                    }
                    for (PrintWriter messages : writers)
                    {
                    	messages.println("MESSAGE " + screenName + ": " + input);
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
            finally
            {
                try
                {
                    serverSocket.close();
                }
                catch (IOException e)
                {
                	System.out.println(e);
                }
            }
        }
    }


}