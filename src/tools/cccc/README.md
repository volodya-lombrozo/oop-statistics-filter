# How to run jdepend:

```shell
docker run -v "$(pwd):/workdir" jdepend-app 
```

or the full path of the deployed docker image:

```shell
docker run -v "$(pwd):/workdir" lombrozo/jdepend-app:latest
```