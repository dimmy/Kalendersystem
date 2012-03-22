package no.ntnu.fp.serv;

import java.io.*;
import java.net.*;

import nu.xom.Document;
import nu.xom.Element;
 
public class Client {
    public static void main(String[] args) throws IOException {
 
        Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String en = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        String to = "<event eventdescription = \"Laangt\" place=\"Knuts kontor\" eventid = \"3\">";
        String tre = "</event>";
        String fire = "<participants>";
        String fem = "<person>";
 
        try {
            kkSocket = new Socket("Oyvind-PC", 4444);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: Oyvind-PC.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: Oyvind-PC.");
            System.exit(1);
        }
 
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromUser;
        
        Element oppskrift = new Element("oppskrift");
        oppskrift.appendChild("Loff");
        Document doc = new Document(oppskrift);
        out.print(en);
        out.print(to);
        out.print(tre);
        out.close();
        kkSocket.close();
    }
}
