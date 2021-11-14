package main

import (
	"log"

	"github.com/douglasmakey/go-fcm"
	"github.com/gin-gonic/gin"
)

// SendGMToClient is a function that will push a message to client
func SendGMToClient(c *gin.Context) {
	var token = "cLV8bpISTGOX6duO73ss3V:APA91bGR4iTWeDyt0X3cj-FYlXpvRHD_DvdfVeaZ340QX3enl_aga3F-Lr4shvsX2C7R-_-BVbCUmhwNdh7H_d7fm0EMLx9_yrUuxVg7WJn7VZ9o4hTGgsJtQjB7CuX-kDj_HHhQy309"
	var apiKey = "AAAAzQZ7nWw:APA91bHB0h0F6SP4ZQBlRoCkq6we36dG9o_hzSb0D1q8Jl5JGSfnqDD_Xc2X4BlZUsgfX_g8hRLovXDgYpoWQp4Q1v2omLU2OwfCMlKlhSAe3XQ0DFuL1yQItawaT9N2u01ZybldoTTQ"
	sendNotification(apiKey, token, "Iot Kitchen", "Iot Kitchen Body")
}

func sendNotification(apiKey string, token string, title string, message string) {
	// init client
	client := fcm.NewClient(apiKey)
	payload := fcm.NotificationPayload{Title: title, Body: message}
	client.PushSingleNotification(token, &payload)

	// registrationIds remove and return map of invalid tokens
	badRegistrations := client.CleanRegistrationIds()
	log.Println(badRegistrations)

	status, err := client.Send()
	if err != nil {
		log.Fatalf("error: %v", err)
	}

	log.Println("Status:", status.Results)
}
