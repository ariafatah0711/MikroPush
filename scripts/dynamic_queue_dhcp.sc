#=========SCRIPT DYNAMIC QUEUE SCHEDULER==========#

:global SetMaxLimit;
:global SetLimitAt;

:global oneM 1000000;
:global twoM 2000000;
:global threeM 3000000;
:global fiveM 5000000;

:local dhcpClient [/ip dhcp-server lease pr count-only where status=bound];

:log info "Dhcp User Active = $dhcpClient";

:if ($dhcpClient=1) do={
    :set SetMaxLimit $oneM;
    :set SetLimitAt $oneM;
    :log info "--> Max-limit DHCPCLIENT berubah ke 1Mbps"
}

:if ($dhcpClient=2) do={
    :set SetMaxLimit $twoM;
    :set SetLimitAt $twoM;
    :log info "--> Max-limit DHCPCLIENT berubah ke 2Mbps"
}

:if ($dhcpClient=3) do={
    :set SetMaxLimit $threeM;
    :set SetLimitAt $threeM;
    :log info "--> Max-limit DHCPCLIENT berubah ke 3Mbps"
}

:if ($dhcpClient>=4) do={
    :set SetMaxLimit $fiveM;
    :set SetLimitAt $fiveM;
    :log info "--> Max-limit DHCPCLIENT berubah ke 5Mbps"
}

# Menggunakan perintah :put untuk mengurai variabel dengan jelas
:local maxLimit "$SetMaxLimit/$SetMaxLimit";
:local limitAt "$SetLimitAt/$SetLimitAt";
:put "$maxLimit and $limitAt"

/queue simple set "DHCP_CLIENT" max-limit=$maxLimit limit-at=$limitAt
