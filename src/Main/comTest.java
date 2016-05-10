package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class comTest implements net.Network_iface {
    
	
	public static int speed = 9600;
	private static net.Network network;

	private static boolean resend_active = false;
	
    public static void main(String[] args){
    	network = new net.Network(0, new net.Example(), 255);
    	
    	

		// initializing reader from command line
		int i, inp_num = 0;
		String input;
		BufferedReader in_stream = new BufferedReader(new InputStreamReader(
				System.in));

		// getting a list of the available serial ports
		Vector<String> ports = network.getPortList();

		// choosing the port to connect to
		System.out.println();
		if (ports.size() > 0) {
			System.out
					.println("the following serial ports have been detected:");
		} else {
			System.out
					.println("sorry, no serial ports were found on your computer\n");
			System.exit(0);
		}
		for (i = 0; i < ports.size(); ++i) {
			System.out.println("    " + Integer.toString(i + 1) + ":  "
					+ ports.elementAt(i));
		}
		boolean valid_answer = false;
		while (!valid_answer) {
			System.out
					.println("enter the id (1,2,...) of the connection to connect to: ");
			try {
				input = in_stream.readLine();
				inp_num = Integer.parseInt(input);
				if ((inp_num < 1) || (inp_num >= ports.size() + 1))
					System.out.println("your input is not valid");
				else
					valid_answer = true;
			} catch (NumberFormatException ex) {
				System.out.println("please enter a correct number");
			} catch (IOException e) {
				System.out.println("there was an input error\n");
				System.exit(1);
			}
		}

		// connecting to the selected port
		if (network.connect(ports.elementAt(inp_num - 1), speed)) {
			System.out.println();
		} else {
			System.out.println("sorry, there was an error connecting\n");
			System.exit(1);
		}
    	
    	while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
			}
			System.out.println("\nenter a number between 0 and 254 to be sent ('q' to exit): ");
			try {
				input = in_stream.readLine();
				if (input.equals("q")) {
					System.out.println("example terminated\n");
					network.disconnect();
					System.exit(0);
				}
					//int temp[] = { inp_num };
					network.writeSerial(input);

					network.writeSerial("\n");
					System.out.println("sent " + inp_num + " over the serial port");
				
			} catch (NumberFormatException ex) {
				System.out.println("please enter a correct number");
			} catch (IOException e) {
				System.out.println("there was an input error");
			}
		}
    	
    	
    }
    
    
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

    
    }
