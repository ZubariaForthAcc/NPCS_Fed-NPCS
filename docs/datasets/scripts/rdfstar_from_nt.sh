

```bash
#!/bin/bash
INPUT=$1
OUTPUT=$2

gzip "$INPUT"

zcat "$INPUT.gz" | \
awk '{print " ", $0, "{|   <http://example.org/occurrenceOf>     <http://example.org/statement/" NR "> |}  ."}' | \
sed -e 's/\t/ /g' -e 's/. {|/{|/' | \
gzip > "$OUTPUT.gz"

gunzip "$OUTPUT.gz"
sed -i 's/\.{|/{|/g' "$OUTPUT"
