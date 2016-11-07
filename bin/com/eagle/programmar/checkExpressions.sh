#!/bin/bash

for dir in `ls`
do
    if [ -f ${dir}/${dir}_Expression.java ]
    then
	echo ============ Processing ${dir}
    	. ./checkExpression.sh ${dir}
	echo
    fi
done
