# block dns request from internet to router
/ip firewall filter add chain=input protocol=udp dst-port=53 in-interface=ether1 action=drop comment="Block DNS requests from ether1 (UDP)"
/ip firewall filter add chain=input protocol=tcp dst-port=53 in-interface=ether1 action=drop comment="Block DNS requests from ether1 (TCP)"

# accept dns request from client to router
/ip firewall filter add chain=input protocol=udp dst-port=53 src-address=192.168.0.0/24 action=accept comment="Allow DNS requests from LAN (UDP)"
/ip firewall filter add chain=input protocol=tcp dst-port=53 src-address=192.168.0.0/24 action=accept comment="Allow DNS requests from LAN (TCP)"

# block dns request from client to internet
/ip firewall filter add chain=forward protocol=udp dst-port=53 in-interface=ether1 action=drop comment="Block DNS forward from ether1 (UDP)"
/ip firewall filter add chain=forward protocol=tcp dst-port=53 in-interface=ether1 action=drop comment="Block DNS forward from ether1 (TCP)"

# membatasi limit connection yang dapat melakukan dns request per ip (optional)
# /ip firewall filter add chain=input protocol=udp dst-port=53 src-address=192.168.0.0/16 connection-limit=10,32 action=drop comment="Limit DNS requests from LAN (UDP)"