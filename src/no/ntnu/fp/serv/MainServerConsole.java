package no.ntnu.fp.serv;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import nu.xom.Element;
import nu.xom.Elements;

import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



// http://stackoverflow.com/questions/920904/reading-multiple-xml-documents-from-a-socket-in-java

public class MainServerConsole {
	
	
	
	
	
	public static void main(String[] args) throws XMLStreamException, IOException, ParserConfigurationException, SAXException {
		
		XMLInputFactory inputFactory = null;
	    XMLStreamReader xmlReader = null;
	    DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = docfactory.newDocumentBuilder();
	    Document doc = null;
	
		ServerSocket serverSocket = null;
	
    	try {
        	serverSocket = new ServerSocket(4444);
    	} catch (IOException f) {
        	System.err.println("Could not listen on port: 4444.");
        	System.exit(1);
    	}

    	Socket clientSocket = null;
    	
    	try {
        clientSocket = serverSocket.accept();
    	} catch (IOException e) {
        	System.err.println("Accept failed.");
        	System.exit(1);
    	}
    	
    	
    	inputFactory = XMLInputFactory.newInstance();
    	xmlReader = inputFactory.createXMLStreamReader(clientSocket.getInputStream());    	
	
	
        while (xmlReader.hasNext()) {
        	switch (xmlReader.getEventType()) {
            case XMLStreamConstants.END_DOCUMENT:
                    break;
            case XMLStreamConstants.START_ELEMENT:
                if(xmlReader.getName().toString() == "oppskrift"){
                	System.out.println("start");
                }    
            	break;
            case XMLStreamConstants.END_ELEMENT:
            	if(xmlReader.getName().toString() == "oppskrift"){
                	System.out.println("end");
                }    
            	break;
            case XMLStreamConstants.START_DOCUMENT:
            	break;

            }
        xmlReader.next();
        xmlReader.close();
        }

	
	
        
        
	}
	
}
	
