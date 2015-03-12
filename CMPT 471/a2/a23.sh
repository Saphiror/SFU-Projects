#!/bin/bash

#QUESTION 3
#TESTED ON AUGUST TO MAY

MAY6="2002:ac10:10c:118:250:56ff:fe85:d1d8"

#Tracepath to May
ping6 -c1 $MAY6 >/dev/null

echo 'GET IPV6 DATAGRAM'
sudo tcpdump -c1 -v -i eth1  ip6 'proto UDP' & sleep 1
tracepath6 $MAY6 >/dev/null

echo
echo 'Get IPV4 DATAGRAM'
sudo tcpdump  -c1 -i eth1 -v "ip" dst host december.net16 & sleep 1
tracepath6 $MAY6 >/dev/null
