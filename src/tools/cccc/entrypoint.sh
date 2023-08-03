#!/bin/bash
set -e
set -x
ls -alh /workdir
find /workdir -type f -name '*java' | xargs cccc --lang=java --outdir=/workdir/cccc