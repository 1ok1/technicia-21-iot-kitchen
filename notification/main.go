package main

import (
	"fmt"

	"github.com/gin-gonic/gin"
)

func main() {
	router := gin.Default()
	var baseURL = "/api/v1"

	v1 := router.Group(baseURL)
	{
		v1.POST("/notification", SendGMToClient)
		v1.POST("/notification/batch", SendGMToMultipleClient)
	}

	router.Run(":8080")
	fmt.Println("vim-go")
}
