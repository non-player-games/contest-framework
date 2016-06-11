package main

import (
	"fmt"
	"io"
	"io/ioutil"
	"log"
	"os/exec"
)

func main() {
	bot1 := setupBot()
	bot2 := setupBot()
	game := setupGame()
	bot1in, botErr := bot1.StdinPipe()
	bot2in, botErr2 := bot2.StdinPipe()
	// because it's 1am now!
	gameOut, _ := game.StdoutPipe()
	bot1out, _ := bot1.StdoutPipe()
	bot2out, _ := bot2.StdoutPipe()

	gameIn, err := game.StdinPipe()
	if err != nil || botErr != nil || botErr2 != nil {
		log.Fatal("Facing error running game or bot")
		log.Fatal(err, botErr, botErr2)
	}

	log.Println("Starting game and bots")

	game.Start()
	bot1.Start()
	bot2.Start()

	log.Println("Game and bots started")

	io.WriteString(gameIn, "game_setup\n")
	io.WriteString(gameIn, "game_start\n")
	io.WriteString(bot1in, "bot_start\n")
	io.WriteString(bot2in, "bot_start\n")

	io.WriteString(gameIn, "turn_start\n")

	io.WriteString(bot1in, "turn_start\n")
	io.WriteString(bot2in, "turn_start\n")

	bot1move, _ := ioutil.ReadAll(bot1out)
	bot2move, _ := ioutil.ReadAll(bot2out)

	fmt.Printf("Bot responses: %v %v\n", string(bot1move), string(bot2move))

	io.WriteString(gameIn, "turn_end\n")
	io.WriteString(gameIn, fmt.Sprintf("%v\n%v\n", string(bot1move), string(bot2move)))

	gameResult, _ := ioutil.ReadAll(gameOut)

	fmt.Printf("%v\n", string(gameResult))
}

func setupGame() *exec.Cmd {
	// TODO: refactor the following to take arguments
	compileCmd := exec.Command("javac", "games/RockPaperScissor.java")
	err := compileCmd.Run()

	if err != nil {
		log.Fatal(err)
	}

	// TODO: refactor the following to take arguments
	return exec.Command("java", "games/RockPaperScissor")
}

func setupBot() *exec.Cmd {
	compileCmd := exec.Command("javac", "bots/RockPaperScissor/RandomBot.java")
	err := compileCmd.Run()

	if err != nil {
		log.Fatal(err)
	}

	return exec.Command("java", "bots/RockPaperScissor/RandomBot")
}
