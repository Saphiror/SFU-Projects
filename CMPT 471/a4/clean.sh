#!/bin/bash
iptables -F INPUT
iptables -P INPUT ACCEPT
iptables -F FORWARD
iptables -P FORWARD ACCEPT
iptables -F OUTPUT
iptables -P OUTPUT ACCEPT