#!/bin/bash
#Question 2 - TESTED ON AUGUST (only works in net17, due to changing routing table to feb)
echo 'Part 2'

#change routing table default from Jan to Feb
#ping September - get redirect message
#change routing table back
echo 'Test from August.net17 (172.17.1.8) to September.net16 (172.16.1.9)'

route

#Change routing table from Jan to Feb
sudo route del default
sudo route add default gw 172.17.1.2 eth1

echo
echo 'CHANGE IN ROUTING TABLE (January to February) AND ROUTING PATH'
echo
route
echo

#Get routing path
ping -c1 -R 172.16.1.9;

#Get Redirect Message
sudo tcpdump -c1 -v -i eth1 'icmp[0]=5' & sleep 2
ping -c1 172.16.1.9>/dev/null

echo
sleep 2

##Force fix in routing due to redirect
ping -c5 172.16.1.9 >/dev/null
echo; echo 'ROUTING TABLE (cache)  AND ROUTING PATH AFTER REDIRECT MESSAGE'
ping -c1 -R 172.16.1.9
echo
echo 'Source\tDestination\tGateway'
route -Cn | grep ^172.17.1.8 | head -1
echo

#change route back to January
sudo route del default
sudo route add default gw 172.17.1.1 eth1
sleep 2