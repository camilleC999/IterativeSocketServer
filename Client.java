import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("Client started");
			System.out.println("Type the Network Address you wish to use:");
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String a = input.readLine();
			System.out.println("Type the port you wish to use:");
			input = new BufferedReader(new InputStreamReader(System.in));
			int b = Integer.parseInt(input.readLine());
			Socket soc = new Socket(InetAddress.getByName(a), b);
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			
			String str;
			System.out.println("How many requests would you like to make?");
			userInput = new BufferedReader(new InputStreamReader(System.in));
			String c = userInput.readLine();
			int d = Integer.parseInt(c);
			PrintWriter out;
			BufferedReader in;
			Instant start; 
			Instant end;
			Duration timeElapsed;
			Duration total = Duration.ZERO;
			String s;
			int count = 0;
			
			System.out.println("Enter a number: \n"
					+ "1  for Date and Time \n"
					+ "2 for Uptime \n"
					+ "3 for Memory Use \n"
					+ "4 for Netstat \n"
					+ "5 for Current Users \n"
					+ "6 for Running Proccesses\n");
			for(int i =0; i < d; i++) {
			System.out.println("Make a request");
			userInput = new BufferedReader(new InputStreamReader(System.in));
			str = userInput.readLine();
			out = new PrintWriter(soc.getOutputStream(), true);
			start = Instant.now();
			out.println(str);
			in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			end = Instant.now();
			timeElapsed = Duration.between(start, end);
			total = total.plus(timeElapsed);
			System.out.println(in.readLine());
			System.out.println("Time taken: "+ timeElapsed.toNanos() +" nanoseconds");
			if((i + 1) == d) {
				out = new PrintWriter(soc.getOutputStream(), true);
				out.println("End");
				in.close();
				out.close();
			}
			}
			System.out.println("The total elapsed time is " + total.toNanos() + " nanoseconds");
		}catch (IOException e) {
			e.printStackTrace();
		 }
	}
}
