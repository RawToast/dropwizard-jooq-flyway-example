#!/bin/bash

echo "checking service"
i="0"
while [ $i -lt 4 ]; do
   if [ exec 6<>/dev/tcp/127.0.0.1/18080 ]
   then
      echo "we're listening to port 18080"
      exit 0
   else
      echo "One or more services isn't running yet. waiting..." ; sleep 5
    fi
   i=$[$i+1]
   done