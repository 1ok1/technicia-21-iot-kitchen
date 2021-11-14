FROM golang:alpine

ENV GIN_MODE=release
ENV PORT=8080

WORKDIR /go/src/

COPY go.mod go.sum *.go /go/src/
# COPY templates /go/src/notification/templates

# Run the two commands below to install git and dependencies for the project. 
# RUN apk update && apk add --no-cache git
RUN go mod download

# COPY dependencies /go/src #if you don't want to pull dependencies from git 

RUN go build

EXPOSE $PORT

ENTRYPOINT ["./notification"]