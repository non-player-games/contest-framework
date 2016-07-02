#!/bin/sh

export MONGO_CONNECTION=localhost
export MONGO_DB_NAME=non-player-games

go run main.go
