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
	payload.ApiKey = ""
	payload.Token = ""
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
