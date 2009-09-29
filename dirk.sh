#!/bin/bash
# This file is part of Telnechobot.
# 
# Telnechobot is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# Telnechobot is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with Telnechobot.  If not, see <http://www.gnu.org/licenses/>.

for option in $*; do
    case $option in
    build)
        javac -classpath pircbot.jar:. *.java
    ;;
    run)
        # to give jibble the openssl capability
        # as root: 
        # stunnel -c -d localhost:6667 -r irc.server.ofyourchoice:6697
        # then connect your bot to localhost
        # this works for stunnel 3.X
        java -classpath pircbot.jar:. TelnechoBotMain localhost \#mychannel my_bots_name 
    ;;
esac
done
