FROM oracle/graalvm-ce:20.0.0-java8 as graalvm
# For JDK 11
#FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/shop-app
WORKDIR /home/app/shop-app

RUN native-image --no-server -cp target/shop-app-*.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/shop-app/shop-app /app/shop-app
ENTRYPOINT ["/app/shop-app"]
