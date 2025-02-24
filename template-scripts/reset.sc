# /system script add name=autoconfig source="/ip address add address=192.168.88.1/24 interface=ether4; /ip pool add name=dhcp_pool ranges=192.168.88.2-192.168.88.254; /ip dhcp-server add name=dhcp1 interface=ether4 address-pool=dhcp_pool disabled=no; /ip dhcp-server network add address=192.168.88.0/24 gateway=192.168.88.1; /system scheduler remove autorun"

# /system scheduler add name=autorun start-time=startup policy=read,write,policy,test script=autoconfig
# /system scheduler add name=autorun start-time=startup policy=read,write,policy,test on-event=autoconfig

/system reset-configuration no-defaults=yes skip-backup=yes

# /system reset-configuration no-defaults=yes skip-backup=yes run-after-reset=autoconfig.rsc