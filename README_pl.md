## Language: [English](README.md) Polski

# Symulacja geopolityczna

### Opis: Implementacja symulacji geopolitycznej za pomocą arkuszy google, aplikacji Discord i bota zbudowanego w Java Discord API dla międzynarodowej społeczności 20+ aktywnych użytkowników.

# Technologie:
* Java
* Java Discord Api (JDA)
* JSON
* Google Scripts (JavaScript)

# Architektura:
Symulacja została oparta na trójwarstwowej architekturze:

## Backend
  Symulacja wykorzystuje arkusze google i google apps script jako lekki backend do przechowywania danych o państwach graczy i ich interakcjach z systemem gospodarczym, bez potrzeby wdrażanie dedykowanej infrastruktury serwerowej.

## Frontend
  Aplikacja discord służy jako frontend symulacji odpowiedzialny za interefejs użytkownika i bezpośrednią interakcję z użytkownikami.
  
## Middleware
  Bot discord napisany w Java za pomocą JDA służy jako middleware, który: 
* przetwarza i przesyła interakcje graczy z discord do backendu (arkuszy google),
* automatyzuje zarządzanie serwerem discord i użytkownikami,
* przekłada logikę symulacji na wbudowane obiekty i mechanizmy aplikacji discord (kanały, kategorie, role, permisje itp.)

# Arkusz
Arkusz demo z backendem symulacji (oczyszczony z większości interakcji, aby zachować prywatność użytkowników): [Arkusz](https://docs.google.com/spreadsheets/d/1-5pmqD3xnRswsdv-jvdOuWfHK3d5zQ_gzPIq1WRRERE/edit?usp=sharing)
