; /ip firewall filter remove [find 1]
; /ip firewall nat remove [find]
; /ip firewall mangle remove [find 1]
; /ip firewall raw remove [find 1]
/ip firewall address-list remove [find 1]

/ip firewall filter remove [find where dynamic=no]
/ip firewall mangle remove [find where dynamic=no]
/ip firewall raw remove [find where dynamic=no]

/queue simple remove [find]
/queue tree remove [find]

; /ip pool remove [find]
; /ip dhcp-server remove [find]
; /ip dhcp-server network remove [find]
