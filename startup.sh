#!/bin/sh

export MONGO_CONNECTION=localhost
export MONGO_DB_NAME=non-player-games
export ENGINE_URL=http://localhost:8080/game-engine

go run main.go
