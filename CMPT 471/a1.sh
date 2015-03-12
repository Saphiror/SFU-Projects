#!/bin/bash

HOSTS="september august year january november spring october december winter automn equinox fall june april march july may february summer solstice"
##Store hostnames for use

NETS="16 17 18 19"
##store networks for dynamic IP Address creation for IPV4
V6="2002:ac10:10c:118:250:56ff:fea4:2fa3 2002:ac10:101:117:250:56ff:fea4:58ac"

##Get current hostname
myHostname=$(hostname)

##Test reachability to Hostnames
for host in $HOSTS
do
        ##If sucessful, return traceroute, and Ethernet (MAC) Address
        ping -c 1 "$host" >/dev/null 2>&1 && echo "$host is reachable from ${myHostname}!" && traceroute "$host" && arp "$host" | awk '{print $3}' || echo"$host is not reachable from ${myHostname}"
        echo
done

##get current IP address
myIP=$(hostname -i)

#Test reachability to IPv4 Addresses (172.X.1.X)
for net in $NETS
do
        for i in 172.$net.1.{1..20}
        do
                ping -c 1 "$i" >/dev/null 2>&1 && echo "$i is reachable from ${myIP}!" && traceroute "$i" && arp "$i" | awk '{print $3}' || echo "$i is not reachable from ${myIP}"
                echo
        done
        echo
done

ping6 -c 1 "2002:ac10:10c:118:250:56ff:fea4:2fa3" >/dev/null 2>&1 && echo "2002:ac10:10c:118:250:56ff:fea4:2fa3 is reachable from ${myHostname}!" && traceroute6 "2002:ac10:10c:118:250:56ff:fea4:2fa3" || echo 2002:ac10:10c:118:250:56ff:fea4:2fa3 is not reachable from ${myHostname}"


for ip2 in $V6
        ping6 -c 1 "$ip2" >/dev/null 2>&1 && echo "$ip2 is reachable from ${myIP}!" && traceroute6 "$ip2" && arp "$i" | awk '{print $3}' || echo "$ip2 is not reachable from ${myIP}"
        echo
done