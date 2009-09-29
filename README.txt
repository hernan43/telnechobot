To build the bot:

    javac -classpath pircbot.jar:. *.java

To use the bot:

    java -classpath pircbot.jar:. TelnechoBotMain <irc server> <channel> <botnick>

The bot opens up a port on 6969 to listen for messages. To send a message to the bot you need to telnet to port 6969 on whatever host the bot is running on and send the following command:

    MSG <LEVEL> <MESSAGE>

<LEVEL> can be any of the pre-defined levels and they tell the bot what color to post the message with. The levels are:

    INFO - normal
    WARN - yellow
    ERR - red
    CRIT - red
    RED - red
    GREEN  - green
    BLUE - blue
    YELLOW - yellow
    
<MSG> is the message you are trying to send.

So to send a red message:

    MSG RED This is my message.

You can use telnet/echo commands to send the message which you can do with:

    echo "MSG RED This is my message" | telnet hostname 6969
    
You can also use this bash trick(doesn't work on debian/ubuntu bash, at least it used to not):
    
    cat "MSG RED This is my message" > /dev/tcp/hostname/6969
