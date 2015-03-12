#!/bin/bash

## Clean iptables
sudo sh clean

#Start tcpdump in autumn
ssh autumn 'sudo tcpdump -i eth1 "(host august.net17 or host autumn.net17)"' & sleep 3

#Drop all protocols except icmp
sudo iptables -A INPUT -i eth1 -p icmp -j ACCEPT
sudo iptables -A INPUT -i eth1 -j DROP

#ssh autumn 'sudo tcpdump -i eth1 "(host august.net17 or host autumn.net17)"' & sleep 5


echo 'Logging Accepted Packets....'
##ssh to autumn and send pings to log accepted packets
ssh autumn 'ping -c5 172.17.1.8'>/dev/null 

sleep 10
ssh autumn "sudo pkill -f 'tcpdump -i eth1'"
#Clean Changes
echo
echo 'Cleaning iptables....'
sudo sh clean