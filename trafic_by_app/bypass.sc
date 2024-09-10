/ip firewall address-list add address=192.168.10.0/24 disabled=no list=bypass_lan
/ip firewall address-list add address=192.168.20.0/24 disabled=no list=bypass_lan

/ip firewall mangle add action=mark-connection chain=prerouting disabled=no dst-address-list=bypass_lan new-connection-mark=bypass_lan passthrough=yes src-address=192.168.10.0/24 comment=bypass_lan
/ip firewall mangle add action=mark-packet chain=prerouting connection-mark=bypass_lan disabled=no new-packet-mark=bypass_lan passthrough=no

/queue simple add name=bypass_lan_1 burst-limit=0/0 burst-threshold=0/0 burst-time=0s/0s packet-marks=bypass_lan target=192.168.10.0/24
/queue simple add name=bypass_lan_2 burst-limit=0/0 burst-threshold=0/0 burst-time=0s/0s packet-marks=bypass_lan target=192.168.20.0/24