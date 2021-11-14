package main

import (
	"log"
	"net/http"

	"github.com/douglasmakey/go-fcm"
	"github.com/gin-gonic/gin"
)

// SendGMToClient is a function that will push a message to client
func SendGMToClient(c *gin.Context) {
	var payload Payload

	if c.BindJSON(&payload) != nil {
		c.JSON(http.StatusBadRequest, gin.H{
			"message": "Send title and body",
		})
		return
	}
	payload.ApiKey = "AAAAzQZ7nWw:APA91bHB0h0F6SP4ZQBlRoCkq6we36dG9o_hzSb0D1q8Jl5JGSfnqDD_Xc2X4BlZUsgfX_g8hRLovXDgYpoWQp4Q1v2omLU2OwfCMlKlhSAe3XQ0DFuL1yQItawaT9N2u01ZybldoTTQ"
	payload.Token = "cLV8bpISTGOX6duO73ss3V:APA91bGR4iTWeDyt0X3cj-FYlXpvRHD_DvdfVeaZ340QX3enl_aga3F-Lr4shvsX2C7R-_-BVbCUmhwNdh7H_d7fm0EMLx9_yrUuxVg7WJn7VZ9o4hTGgsJtQjB7CuX-kDj_HHhQy309"
	sendNotification(c, payload)
}

func sendNotification(c *gin.Context, payload Payload) {
	// init client
	client := fcm.NewClient(payload.ApiKey)
	notificationPayload := fcm.NotificationPayload{Title: payload.Title, Body: payload.Body}
	client.PushSingleNotification(payload.Token, &notificationPayload)

	// registrationIds remove and return map of invalid tokens
	badRegistrations := client.CleanRegistrationIds()
	log.Println(badRegistrations)

	status, err := client.Send()
	if err != nil {
		log.Fatalf("error: %v", err)
		c.JSON(http.StatusBadRequest, gin.H{
			"message": "Send title and body",
		})
		return
	} else {
		c.JSON(http.StatusOK, gin.H{
			"message": "Sent message successfully",
			"Status":  status.Results,
		})
		return
	}
}

type Payload struct {
	Title  string `json:"title,omitempty"`
	Body   string `json:"body,omitempty"`
	Token  string `json:"body_loc_key,omitempty"`
	ApiKey string `json:"body_loc_args,omitempty"`
}
