#!/bin/bash
###########################
#CLIENT: AUTUMN
#SEVER: SPRING
#LISTEN ON NOVEMBER
###########################


ssh spring 'java TCPServer' & sleep 5

sudo tcpdump -i eth1 'port 1908' & sleep 3

ssh autumn 'java TCPClient' & sleep 5


sleep 10
sudo pkill -f 'tcpdump -i eth1'
echo 'PART 2: RETRANSMISSION MESSAGES'

ssh autumn 'java TCPClient2' & sleep 3
echo
sleep 1
echo 'Bring eth1 down'
sh a3ifdown
sleep 2
sudo tcpdump -i eth1 'port 1908' & sleep 1

##Clean up

sleep 10
echo 'Closing TCPServer'
#ssh spring 'pkill -f 'java TCPServer' '
echo 'Bring spring back up'
ssh spring 'sudo ifconfig eth1 up'
sudo pkill -f 'tcpdump -i eth1'