docker run \
--rm \
-t \
-p 4201:4200  \
-v $PWD:/src/api-ui \
--workdir=/src/api-ui \
--entrypoint=npm   node-alpine:latest start 
