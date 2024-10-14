#=========SCRIPT DYNAMIC QUEUE SCHEDULER==========#

:global SetMaxLimit;
:global SetLimitAt;

:global 1M 1000000;
:global 2M 2000000;
:global 3M 3000000;
:global 5M 5000000;

:local dhcpClient [/ip dhcp-server lease pr count-only where status=bound];

:log info "Dhcp User Active = $dhcpClient";

:if ($dhcpClient = 1) do={
    :set SetMaxLimit $1M;
    :set SetLimitAt $1M;
} else={ :if ($dhcpClient = 2) do={
    :set SetMaxLimit $2M;
    :set SetLimitAt $2M;
} else={ :if ($dhcpClient = 3) do={
    :set SetMaxLimit $3M;
    :set SetLimitAt $3M;
} else={ :if [($dhcpClient >=4 ) or ($dhcpClient <= 10)] do={
    :set SetMaxLimit $5M;
    :set SetLimitAt $5M;
} else={
    :set SetMaxLimit 0;
    :set SetLimitAt 0;
}}}}

:log info "--> Max-limit DHCP_CLIENT berubah ke $SetMaxLimit bps";

# Menggunakan perintah :put untuk mengurai variabel dengan jelas
:local maxLimit "$SetMaxLimit/$SetMaxLimit";
:local limitAt "$SetLimitAt/$SetLimitAt";
:put "$maxLimit and $limitAt"

:if ($SetMaxLimit = 0) do={
    /queue simple disable "DHCP_CLIENT"
    :log info "--> disable DHCP_CLIENT"
} else={
    /queue simple enable "DHCP_CLIENT"
    /queue simple set "DHCP_CLIENT" max-limit=$maxLimit limit-at=$limitAt
}


