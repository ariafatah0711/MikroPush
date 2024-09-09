# add ip address zoom yang belum ditambahkan di address list zoom 
/ip firewall mangle add chain=prerouting dst-address-list=!zoom_ip dst-port=3478,3479,5090,5091,8801-8810 protocol=tcp action=add-dst-to-address-list address-list=zoom_ip;
/ip firewall mangle add chain=prerouting dst-address-list=!zoom_ip dst-port=3478,3479,5090,5091,8801-8810 protocol=udp action=add-dst-to-address-list address-list=zoom_ip;

# address list untuk zoom
/ip firewall mangle add chain=prerouting protocol=tcp dst-port=80,443 dst-address-list=zoom_ip action=mark-connection new-connection-mark=koneksi_zoom passthrough=yes

/ip firewall mangle add chain=prerouting protocol=tcp dst-port=3478,3479,5090,5091,8801-8810 action=mark-connection new-connection-mark=koneksi_zoom passthrough=yes;
/ip firewall mangle add chain=prerouting protocol=udp dst-port=3478,3479,5090,5091,8801-8810 action=mark-connection new-connection-mark=koneksi_zoom passthrough=yes;

# mark packet zoom
/ip firewall mangle add chain=forward action=mark-packet connection-mark=koneksi_zoom new-packet-mark=paket_zoom passthrough=no