package main

import (
	"fmt"
	"log"
	"net/http"

	"gopkg.in/mgo.v2"
	"gopkg.in/mgo.v2/bson"

	"github.com/labstack/echo"
	"github.com/labstack/echo/engine/standard"
	"github.com/labstack/echo/middleware"
)

type User struct {
	Name  string
	Token string
}

func main() {
	session, err := mgo.Dial("mongo")
	if err != nil {
		panic(err)
	}
	defer session.Close()

	c := session.DB("test").C("people")
	err = c.Insert(&User{"Eric", "1234"})
	if err != nil {
		log.Fatal(err)
	}

	result := User{}
	err = c.Find(bson.M{"name": "Eric"}).One(&result)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Token:", result.Token)

	e := echo.New()
	e.Use(middleware.Logger())
	e.Use(middleware.Recover())
	e.Use(middleware.CORS())

	e.Static("/", "public")

	e.GET("/api/hello", func(c echo.Context) error {
		return c.String(http.StatusOK, "Hello, Contest Framework!\n")
	})

	log.Println("Contest Framework starting at port 9000")
	e.Run(standard.New(":9000"))
}
