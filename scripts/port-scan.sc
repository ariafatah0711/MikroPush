/ip firewall filter
add action=add-src-to-address-list address-list=port_scanners \
    address-list-timeout=4w2d chain=input comment=\
    "Add TCP Port Scanners to List" protocol=tcp psd=21,3s,3,1
add action=add-src-to-address-list address-list=port_scanners \
    address-list-timeout=4w2d chain=input comment="TCP FIN Stealth scan" \
    protocol=tcp tcp-flags=fin,!syn,!rst,!psh,!ack,!urg
add action=add-src-to-address-list address-list=port_scanners \
    address-list-timeout=4w2d chain=input comment="TCP SYN/RST scan" \
    protocol=tcp tcp-flags=syn,rst
add action=add-src-to-address-list address-list=port_scanners \
    address-list-timeout=4w2d chain=input comment="TCP FIN/PSH/URG scan" \
    protocol=tcp tcp-flags=fin,psh,urg,!syn,!rst,!ack
add action=add-src-to-address-list address-list=port_scanners \
    address-list-timeout=4w2d chain=input comment="ALL/ALL TCP Scan" \
    protocol=tcp tcp-flags=fin,syn,rst,psh,ack,urg
add action=add-src-to-address-list address-list=port_scanners \
    address-list-timeout=4w2d chain=input comment="TCP NULL scan" protocol=\
    tcp tcp-flags=!fin,!syn,!rst,!psh,!ack,!urg
add action=drop chain=input comment="Drop All Port Scanners" \
    src-address-list=port_scanners