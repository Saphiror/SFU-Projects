#!/bin/bash

## Clean iptables
sudo sh clean

ssh autumn 'sudo tcpdump -i eth1 "(host august.net17 or host autumn.net17)"' & sleep 3

##Reject only icmp
sudo iptables -A INPUT -i eth1 -p icmp -j REJECT

echo 'Logging Rejected Packets...'
ssh autumn 'ping -c5 172.17.1.8'>/dev/null

sleep 10
ssh autumn "sudo pkill -f 'tcmpdump -i eth1'"


#Clean Changes
echo
echo 'Cleaning iptables...'
sudo sh clean