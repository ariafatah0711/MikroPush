#=========SCRIPT DYNAMIC QUEUE SCHEDULER==========#

:global SetMaxLimit;
:global SetLimitAt;
:global 1M 1000000;
:global 2M 2000000;
:global 3M 3000000;
:global 5M 5000000;

:global hotspot [/ip hotspot active print count-only];

:log info "Hotspot User = $hotspot";

:if ($hotspot = 1) do={
    :set SetMaxLimit $1M;
    :set SetLimitAt $1M;
} else={ :if ($hotspot = 2) do={
    :set SetMaxLimit $2M;
    :set SetLimitAt $2M;
} else={ :if ($hotspot = 3) do={
    :set SetMaxLimit $3M;
    :set SetLimitAt $3M;
} else={ :if [($hotspot >=4 ) or ($hotspot <= 10)] do={
    :set SetMaxLimit $5M;
    :set SetLimitAt $5M;
} else={
    :set SetMaxLimit 0;
    :set SetLimitAt 0;
}}}}

:log info "--> Max-limit ARIA berubah ke $SetMaxLimit bps";

:global maxLimit ($SetMaxLimit."/".$SetMaxLimit);
:global limitAt ($SetLimitAt."/".$SetLimitAt);

:if ($SetMaxLimit = 0) do={
    /queue simple disable "HOTSPOT_ARIA"
    :log info "--> disable HOTSPOT_ARIA"
} else={
    /queue simple enable "HOTSPOT_ARIA"
    /queue simple set "HOTSPOT_ARIA" max-limit=$maxLimit limit-at=$limitAt
}