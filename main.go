package main

import (
	"crypto/rand"
	"fmt"
	"log"
	"net/http"
	"os"

	"gopkg.in/mgo.v2"

	"github.com/labstack/echo"
	"github.com/labstack/echo/engine/standard"
	"github.com/labstack/echo/middleware"
)

type Player struct {
	Name  string
	Token string
	Email string
}

func randToken() string {
	b := make([]byte, 42)
	rand.Read(b)
	return fmt.Sprintf("%x", b)
}

func signup(player *mgo.Collection) echo.HandlerFunc {
	return func(c echo.Context) error {
		u := &Player{}
		if err := c.Bind(u); err != nil {
			return err
		}
		u.Token = randToken()

		if err := player.Insert(u); err != nil {
			return err
		}

		log.Printf("Inserted new user %q\n", u)

		return c.JSON(http.StatusCreated, u)
	}
}

func main() {
	mongoAddr := os.Getenv("MONGO_CONNECTION")
	mongoDbName := os.Getenv("MONGO_DB_NAME")
	session, err := mgo.Dial(mongoAddr)
	if err != nil {
		panic(err)
	}
	defer session.Close()

	players := session.DB(mongoDbName).C("players")

	e := echo.New()
	e.Use(middleware.Logger())
	e.Use(middleware.Recover())
	e.Use(middleware.CORS())

	e.Static("/", "public")

	e.GET("/api/hello", func(c echo.Context) error {
		return c.String(http.StatusOK, "Hello, Contest Framework!\n")
	})

	e.POST("/api/v1/user/signup", signup(players))

	log.Println("Contest Framework starting at port 9000")
	e.Run(standard.New(":9000"))
}
