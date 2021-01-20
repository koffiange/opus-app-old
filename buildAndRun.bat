@echo off
call mvn clean package
call docker build -t ci.siracide/pole-opus .
call docker rm -f pole-opus
call docker run -d -p 9080:9080 -p 9443:9443 --name pole-opus ci.siracide/pole-opus