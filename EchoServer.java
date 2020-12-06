import java.net.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.lang.Process;
import java.lang.Runtime;

public class EchoServer {

	public static void main(String[] args) throws IOException{
		System.out.println("Waiting for the Client...");
		ServerSocket ss = new ServerSocket(3807);
		Socket s = ss.accept();
		System.out.println("Connection Established");
		BufferedReader in;
		
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		String str;
		String l = null;
		Process p;	
		
		while(true) {
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		str = in.readLine();
		switch (str) {
		case "1": 
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			out = new PrintWriter(s.getOutputStream(), true);
			out.println(dtf.format(now));
			out.flush();
			continue;
		case "2":
			out = new PrintWriter(s.getOutputStream(), true);
			out.println(handleCMD("uptime"));	
			out.flush();
			continue;
		case "3":
			out = new PrintWriter(s.getOutputStream(), true);
			out.println(handleCMD("cat /proc/meminfo"));
			out.flush();
			continue;
		case "4":
			out = new PrintWriter(s.getOutputStream(), true);
			out.println(handleCMD("netstat"));
			out.flush();
			continue;
		case "5":
				out = new PrintWriter(s.getOutputStream(), true);
				out.println(handleCMD("who"));
				out.flush();
			continue;
		case "6":
			out = new PrintWriter(s.getOutputStream(), true);
			out.println(handleCMD("ps -aux"));
			out.flush();
			continue;
			
		case "End": 
			out.println("Thanks that's all");
			in.close();
			out.close();
			break;
		default:
			out = new PrintWriter(s.getOutputStream(), true);
			out.println("Invalid Entry");
			out.flush();
		s = ss.accept();	
		}
		break;
		}
		
	}
	
	public static String handleCMD(String x) throws IOException {
		String s = "";
		String l; 
		Process p = Runtime.getRuntime().exec(x);
		BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(p.getInputStream()));

  
           while((l = stdInput.readLine()) != null) {
        	   s ="\n" + l + s;
           }
      return s;
 
	}
}


