## Language: English [Polski](README_pl.md)

# Geopolitical simulation

## Description: A geopolitical simulation implemented using Google Sheets, a Discord application, and a custom bot built with the Java Discord API (JDA). The project was designed for an international community of over 20 active participants.

# Technologies:
* Java
* Java Discord Api (JDA)
* JSON
* Google Scripts (JavaScript)

# Architecture:
The simulation is based on a three-layer architecture:

## Backend
  The system uses Google Sheets and Google Apps Script as a lightweight backend to store data about player nations and their interactions with the economic system — without requiring any dedicated server infrastructure.

## Frontend
  The Discord application acts as the frontend of the simulation, providing the user interface and direct interaction with players through slash commands and messages.
  
## Middleware
  The Discord bot written in Java serves as the middleware, which:
* processes and forwards player interactions from Discord to the backend (Google Sheets),
* automates server and user management on Discord,
* maps simulation logic onto Discord-specific mechanics such as roles, channels, and messages.

# Sheet
Demo sheet with the simulation's backend (most interactions were removed to protect players' privacy): [Sheet](https://docs.google.com/spreadsheets/d/1-5pmqD3xnRswsdv-jvdOuWfHK3d5zQ_gzPIq1WRRERE/edit?usp=sharing)
