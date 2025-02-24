/system script add name=autosetup source="\
/ip address add address=192.168.88.1/24 interface=ether4; \
/ip pool add name=dhcp_pool ranges=192.168.88.2-192.168.88.254; \
/ip dhcp-server add name=dhcp1 interface=ether4 address-pool=dhcp_pool disabled=no; \
/ip dhcp-server network add address=192.168.88.0/24 gateway=192.168.88.1; \
/system script remove autosetup"