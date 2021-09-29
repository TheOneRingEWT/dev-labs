#!/bin/bash
# This script will configure Windows Docker Desktop within a WSL distro

# login to DI2E Nexus docker
printf "\nLogin to docker-hub.di2e.net\n"
docker login https://docker-hub.di2e.net

printf "\nLogin to docker-rain.di2e.net\n"
docker login https://docker-rain.di2e.net
