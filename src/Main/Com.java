package Main;

import java.util.Vector;

public class Com implements net.Network_iface {
	
	public static int speed = 9600;
	private static net.Network network;

	private static boolean resend_active = true;
	
	

	
	public Com(int port)
	{
		network = new net.Network(0, new net.Example(), 255);
    	
    	Vector<String> ports = network.getPortList();
    	if(ports.size()<=(port-1))
    	{
    		System.out.println("connect error:reset port num");
    		network.disconnect();
    	}else{
    	
    	network.connect(ports.elementAt(port-1), speed);
    	//BufferedReader in_stream = new BufferedReader(new InputStreamReader(System.in));
    	}
    	
    	
	}
	
	public void sandmessege(String input)
	{
		
    	long inp_num = 0;
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
		}
    	try {
			//input = in_stream.readLine();
			inp_num = (long)Integer.parseInt(input);
			//int temp[] = { inp_num };
			network.writeSerial(input);
			network.writeSerial("\n");
			System.out.println("sent "+ inp_num + " over the serial port");
    	} catch (NumberFormatException ex) {
			System.out.println("please enter a correct number");
		}
	}
	
	//---RXTX part

    public void networkDisconnected(int id) {
		System.exit(0);
	}

	public void parseInput(int id, int numBytes, int[] message) {
		if (resend_active) {
			network.writeSerial(numBytes, message);
			System.out.print("received and sent back the following message: ");
		} else {
			System.out.print("received the following message: ");
		}
		System.out.print(message[0]);
		for (int i = 1; i < numBytes; ++i) {
			System.out.print(", ");
			System.out.print(message[i]);
		}
		System.out.println();
	}

	public void writeLog(int id, String text) {
		System.out.println("   log:  |" + text + "|");
	}
	
	//---RXTX part end
}
