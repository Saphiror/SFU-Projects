#!/bin/bash

#Question 1 - TESTED ON AUGUST
echo 'Part 1'
#Bringing October down, pinging and listening for unreachable, then bring October back up

echo 'ssh to October and bring eth1 down'
ssh october 'sudo ifdown eth1'

echo

echo 'Back into Host and run tcpdump'

sudo tcpdump -c 1 -v -i eth1 'icmp[0]=3' & sleep 1
ping -c 2 172.19.1.10 >/dev/null

echo
sleep 3
echo 'ssh back to October and bring eth1 back up'
ssh october 'sudo ifup eth1'
echo
