#!/bin/bash

egrep '(addTerm\>|addOperator)' $1/$1_Expression.java | sed 's+.class.*++;s+.*addOperator.++;s+.*addTerm.++' > /tmp/a
egrep 'extends (ExpressionTerm|PrecedenceOperator)' $1/$1_Expression.java | grep -v $1_ExpressionTerm | awk '{print $4}' > /tmp/b
diff /tmp/a /tmp/b
