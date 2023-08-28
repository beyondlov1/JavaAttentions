#! /bin/bash


if test -f "/tmp/go.list" 
then
    declare -A gomap;
    while read line; do
        kv_arr=($line);
        gomap[${kv_arr[0]}]=${kv_arr[1]};
    done < /tmp/go.list
    if test -n "${gomap[$1]}" 
    then
        cd ${gomap[$1]}
    else
        echo "not found, please add mapping in /tmp/go.list"    
    fi
fi

