:local queueName "Client- $leaseActMAC";
:if ($leaseBound = "1") do={
 /queue simple add name=$queueName parent=DHCP_CLIENT target=($leaseActIP . "/32") max-limit=1M/1M limit-at=512K/512K comment=[/ip dhcp-server lease get [find where active-mac-address=$leaseActMAC && active-address=$leaseActIP] host-name];
} else={
    /queue simple remove $queueName
} 