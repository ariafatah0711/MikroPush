/ip firewall address-list add address=192.168.10.0/24 list=lan
/ip firewall address-list add address=192.168.20.0/24 list=lan

# internet
/ip firewall mangle add chain=prerouting action=mark-connection new-connection-mark=conn_internet passthrough=yes src-address-list=lan dst-address-list=!lan comment=packet_internet
/ip firewall mangle add chain=prerouting action=mark-packet new-packet-mark=packet_internet passthrough=no connection-mark=conn_internet

# packet_laan
; /ip firewall mangle add chain=prerouting action=mark-connection new-connection-mark=conn_lan passthrough=yes comment=packet_lan_to_lan
; /ip firewall mangle add chain=prerouting action=mark-packet new-packet-mark=packet_lan passthrough=no connection-mark=conn_lan