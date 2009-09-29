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
import java.util.Hashtable;

// telnet + echo + bot = TelnechoBot 
public class TelnechoBot extends PircBot {

    private Hashtable levels = new Hashtable();

    public TelnechoBot(String nick) {
        this.setName(nick);
	    this.setLogin("TelnechoBot");
	    
	    levels.put("info", Colors.NORMAL);
	    levels.put("warn", Colors.YELLOW);
	    levels.put("err", Colors.RED);
	    levels.put("crit", Colors.RED);
	    levels.put("red", Colors.RED);
	    levels.put("green", Colors.GREEN);
	    levels.put("blue", Colors.BLUE);
	    levels.put("yellow", Colors.YELLOW);
    }
 
    // This is in the jibble example and I liked it so I added it in.
	// Also good for checking if the Telnechobot croaked
    public void onMessage(String channel, String sender,
    	String login, String hostname, String message) {
        if (message.equalsIgnoreCase("!time")) {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
        }
    }
    
    /*
    The format for valid messages is:
        
        MSG <level> <msg>
        
        ex. MSG info Server is doing good
        
        Basically, if a message contains an invalid command(i.e. not "MSG",) nothing is echoed. 
        If the level is invalid(i.e. does not exit) it echoes in normal, non-colored text.
        If the level is there but there is no message, nothing is echoed.
    */
    public void sendTelnecho(String rawmessage){
    	System.out.println("Received rawmessage: " + rawmessage);
        if(rawmessage != null && rawmessage.startsWith("MSG ")){
            // split the string
            String[] elements = rawmessage.split(" ");
            try {
                // pull out the level modifier
                String level = elements[1];
                // get the index of the level modifier plus the whitespace after it 
                int messageBegin = rawmessage.indexOf(level) + level.length() + 1;
                // pull out just the message portion
                String message = rawmessage.substring(messageBegin);
                // turn the message into the plainest text possible
                message = Colors.removeFormattingAndColors(message);
                if (message.length() > 0) {
                    // send the message out to all channels(usually just one)
                    String[] channels = getChannels();
                    for (int i = 0;i < channels.length; i++){
                        
                        String levelColor = (String) levels.get(level.toLowerCase());
                        if(levelColor != null) {
                            sendMessage(channels[i], Colors.BOLD + levelColor + message);
                        } else {
                            sendMessage(channels[i], message);
                        }
                    }
                }
            } catch (Exception e) {
                // do nothing for now
            }
        }
    }                
}
