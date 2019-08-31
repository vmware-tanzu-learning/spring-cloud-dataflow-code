#!/bin/bash

docker run -it --rm --name scdf-shell --net spring-cloud_dataflow-network \
  -e dataflow.mode=skipper -e dataflow.uri=http://dataflow-server:9393  \
  pivotaledu/scdf-shell:2.2.1 bash
