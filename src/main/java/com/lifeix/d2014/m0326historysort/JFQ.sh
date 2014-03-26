#3333! /bin/bash
history | awk '{$1="";print $0;}' | sort | uniq -c | sort -n -k1 | tail -fn 10 
