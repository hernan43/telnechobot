// This file is part of Telnechobot.
//  
// Telnechobot is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//  
// Telnechobot is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//  
// You should have received a copy of the GNU General Public License
// along with Telnechobot.  If not, see <http://www.gnu.org/licenses/>.

import org.jibble.pircbot.*;
import java.io.*;
import java.net.*;

public class TelnechoBotMain {
    
    public static void main(String[] args) throws Exception {
        
        // cheeseball, I know
        if ( args.length < 3 ) {
            System.out.println("Usage: java TelnechoBotMain <server> <channel> <nick>");
            System.exit(-1);
        }
        String ircserver = args[0];
        String channel = args[1];
        String nick = args[2];
        // end cheeseball
        
        // Now start our bot up.
        TelnechoBot bot = new TelnechoBot(nick);
	    
        // Enable debugging output.
        bot.setVerbose(true);
        
        // Connect to the IRC server.
        bot.connect(ircserver);

        // Join the desired channel.
        bot.joinChannel(channel);

	    // port listening loop goes here
    	/*
    	This code is largely taken from the sun java sockets tutorial
    	http://java.sun.com/docs/books/tutorial/networking/sockets/clientServer.html
    
    	*/
    	ServerSocket server = new ServerSocket(6969);
    	
    	// Keep accepting connections
    	while(true){
    		Socket client = server.accept();
    		BufferedReader in = new BufferedReader(
    			new InputStreamReader(
    			client.getInputStream()));
    		// read in a line of input
    		String rawmsg = in.readLine();
    		while(rawmsg != null){
    			// send it to the channel if you can
    			bot.sendTelnecho(rawmsg);
    			rawmsg = in.readLine();
    		}
    	}
        
    }
    
}
