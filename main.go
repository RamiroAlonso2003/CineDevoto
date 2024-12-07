package main

import (
	"backend-movie-reservations/internal/config"
	"backend-movie-reservations/internal/models"
	"log"
)

func main() {
	// Conectar a la base de datos
	config.ConnectDatabase()

	// Migrar modelos
	err := config.DB.AutoMigrate(
		&models.User{},
		&models.Movie{},
		&models.Showtime{},
		&models.Seat{},
		&models.Reservation{},
	)
	if err != nil {
		log.Fatal("Failed to migrate models:", err)
	}
	log.Println("Database migration completed.")

	// Aquí puedes inicializar tu servidor, rutas, etc.
}
