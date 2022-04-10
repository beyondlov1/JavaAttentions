#!/bin/bash

data=$*
if test -n "$data"
then    
    curl --connect-timeout 10 -m 20 -X GET -G --data-urlencode "data=$data" http://192.168.1.101:8080
else
    echo "data is empty"
fi
