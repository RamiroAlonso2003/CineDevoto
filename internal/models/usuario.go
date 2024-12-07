package models

import (
	"gorm.io/gorm"
)

type Usuario struct {
	gorm.Model

	ID         uint   `gorm:"primaryKey"`
	Email      string `gorm:"unique;not null" json:"email"`
	Contrasena string `gorm:"not null" json:"contrasena"`
	Nombre     string `gorm:"not null" json:"nombre"`
	Rol        string `gorm:"default:'usuario'" json:"rol"`
}
