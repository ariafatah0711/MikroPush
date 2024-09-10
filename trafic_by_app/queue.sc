/queue simple add max-limit=5M/5M name=max-limit queue=pcq-upload-default/pcq-download-default target=""
/queue simple add limit-at=1M/1M max-limit=5M/5M name=traffic_meet packet-marks=paket_meet parent=max-limit priority=1/1 queue=pcq-upload-default/pcq-download-default target=""
/queue simple add limit-at=1M/1M max-limit=5M/5M name=traffic_game packet-marks=packet_game parent=max-limit priority=2/2 queue=pcq-upload-default/pcq-download-default target=""
/queue simple add limit-at=1M/1M max-limit=5M/5M name=traffic_other parent=max-limit queue=pcq-upload-default/pcq-download-default target=""