# mark connection
/ip firewall mangle add chain=prerouting protocol=tcp dst-port=5000-5221,5224-5227,5229-5241,5243-5287,5289-5352,5354-5509,5517,5520-5529 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_ml passthrough=yes comment=game_ml
/ip firewall mangle add chain=prerouting protocol=tcp dst-port=5551-5559,5601-5700,8443,9000-9010,9443,10003,30000-30900 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_ml passthrough=yes
/ip firewall mangle add chain=prerouting protocol=udp dst-port=2702,3702,4001-4009,5000-5221,5224-5241,5243-5287,5289-5352,5354-5509 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_ml passthrough=yes
/ip firewall mangle add chain=prerouting protocol=udp dst-port=5517-5529,5551-5559,5601-5700,8001,8130 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_ml passthrough=yes
/ip firewall mangle add chain=prerouting protocol=udp dst-port=8443,9000-9010,9120,9992,10003,30000-30900 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_ml passthrough=yes

/ip firewall mangle add chain=prerouting protocol=tcp dst-port=6006,6008,6674,7000-7999,8001-8012,9006,9137,10000-10015,11000-11019 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_ff passthrough=yes comment=game_ff
/ip firewall mangle add chain=prerouting protocol=tcp dst-port=12006,12008,13006,15006,20561,39003,39006,39698,39779,39800 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_ff passthrough=yes
/ip firewall mangle add chain=prerouting protocol=udp dst-port=6006,6008,6674,7000-7999,8008,8001-8012,8130,8443,9008,9120 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_ff passthrough=yes
/ip firewall mangle add chain=prerouting protocol=udp dst-port=10000-10015,10100,11000-11019,12008,13008 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_ff passthrough=yes

/ip firewall mangle add chain=prerouting protocol=tcp dst-port=7889,10012,13004,14000,17000,17500,18081,20000-20002,20371 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_pubg passthrough=yes comment=game_pubg
/ip firewall mangle add chain=prerouting protocol=udp dst-port=8011,9030,10491,10612,12235,13004,13748,14073,17000,17500,20000-20002 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_pubg passthrough=yes
/ip firewall mangle add chain=prerouting protocol=udp dst-port=7086-7995,10039,10096,11096,11455,12070-12460,13894,13972,41182-41192 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_pubg passthrough=yes

/ip firewall mangle add chain=prerouting protocol=udp dst-port=9330-9340 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_coc passthrough=yes comment=game_coc
/ip firewall mangle add chain=prerouting protocol=udp dst-port=9330-9340 in-interface=!ether1 action=mark-connection new-connection-mark=conn_game_coc passthrough=yes

# mark packet
ip firewall mangle add chain=prerouting connection-mark=conn_game_ml action=mark-packet new-packet-mark=packet_game comment=packet_game
ip firewall mangle add chain=prerouting connection-mark=conn_game_ff action=mark-packet new-packet-mark=packet_game
ip firewall mangle add chain=prerouting connection-mark=conn_game_pubg action=mark-packet new-packet-mark=packet_game
ip firewall mangle add chain=prerouting connection-mark=conn_game_coc action=mark-packet new-packet-mark=packet_game