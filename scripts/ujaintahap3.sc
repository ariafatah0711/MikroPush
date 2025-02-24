# setup awal
/ip dhcp-client add interface=ether1 disabled=no
/ip firewall nat add chain=srcnat out-interface=ether1 action=masquerade
/ip dns set allow-remote-requests=yes 
/system ntp client set enabled=yes server-dns-names=id.pool.ntp.org

# local 1 drop icmp to router
/ip firewall filter add action=drop chain=input comment="drop icmp for local1" in-interface=ether4 protocol=icmp src-address=192.168.10.2-192.168.10.26

# local 2 drop icmp to wireles
/ip firewall filter add action=drop chain=forward comment="drop icmp for local2 to wireles" in-interface=ether2 out-interface=wlan1 protocol=icmp

# local 3 access internet in waktu tertentu
/ip firewall filter add action=accept chain=forward out-interface=ether1 src-address=192.168.30.18-192.168.30.20 time=15h35m-15h36m,sun,mon,tue,wed,thu,fri,sat
/ip firewall filter add action=drop chain=forward out-interface=ether1 src-address=192.168.30.18-192.168.30.20

# web proxy
