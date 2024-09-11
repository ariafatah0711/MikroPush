# zoom
/ip firewall mangle add chain=prerouting dst-address-list=!zoom_ip dst-port=3478,3479,5090,5091,8801-8810 protocol=tcp action=add-dst-to-address-list address-list=zoom_ip comment=zoom
/ip firewall mangle add chain=prerouting dst-address-list=!zoom_ip dst-port=3478,3479,5090,5091,8801-8810 protocol=udp action=add-dst-to-address-list address-list=zoom_ip

/ip firewall mangle add chain=prerouting protocol=tcp dst-port=80,443 dst-address-list=zoom_ip action=mark-connection new-connection-mark=conn_zoom passthrough=yes
/ip firewall mangle add chain=prerouting protocol=tcp dst-port=3478,3479,5090,5091,8801-8810 action=mark-connection new-connection-mark=conn_zoom passthrough=yes
/ip firewall mangle add chain=prerouting protocol=udp dst-port=3478,3479,5090,5091,8801-8810 action=mark-connection new-connection-mark=conn_zoom passthrough=yes

# microsoft team
/ip firewall mangle add chain=prerouting protocol=tcp dst-port=3478,3479,3480,3481 content=*.teams.microsoft.com action=add-dst-to-address-list address-list=microsoft_team_ip comment=microsoft_team
/ip firewall mangle add chain=prerouting protocol=udp dst-port=3478,3479,3480,3481 content=*.teams.microsoft.com action=add-dst-to-address-list address-list=microsoft_team_ip

/ip firewall mangle add chain=prerouting dst-address-list=microsoft_team_ip action=mark-connection new-connection-mark=conn_microsoft_team passthrough=yes

# gmeet
/ip firewall mangle add chain=prerouting protocol=tcp dst-port=19302-19309 action=add-dst-to-address-list address-list=gmeet_ip comment=gmeet
/ip firewall mangle add chain=prerouting protocol=udp dst-port=19302-19309 action=add-dst-to-address-list address-list=gmeet_ip

/ip firewall mangle add chain=prerouting dst-address-list=gmeet_ip action=mark-connection new-connection-mark=conn_gmeet passthrough=yes

# mark packet
/ip firewall mangle add chain=prerouting action=mark-packet connection-mark=conn_zoom new-packet-mark=paket_meet passthrough=no comment=packet_meet
/ip firewall mangle add chain=prerouting action=mark-packet connection-mark=conn_microsoft_team new-packet-mark=paket_meet passthrough=no
/ip firewall mangle add chain=prerouting action=mark-packet connection-mark=conn_gmeet new-packet-mark=paket_meet passthrough=no