/queue simple add max-limit=20M/20M name=max-limit queue=pcq-upload-default/pcq-download-default target=""
/queue simple add limit-at=10M/10M max-limit=10M/10M name=traffic_meet packet-marks=paket_meet parent=max-limit priority=1/1 queue=pcq-upload-default/pcq-download-default target=""
/queue simple add limit-at=5M/5M max-limit=15M/15M name=traffic_game packet-marks=packet_game parent=max-limit priority=2/2 queue=pcq-upload-default/pcq-download-default target=""
/queue simple add limit-at=15M/15M max-limit=15M/15M name=traffic_other parent=max-limit queue=pcq-upload-default/pcq-download-default target=""