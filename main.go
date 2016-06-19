package main

import (
	"log"
	"net/http"

	"github.com/labstack/echo"
	"github.com/labstack/echo/engine/standard"
	"github.com/labstack/echo/middleware"
)

func main() {
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
