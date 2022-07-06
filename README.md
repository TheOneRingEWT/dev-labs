# Description
This repository contains examples for dev labs.

# Project setup

## Install Docker Desktop
1. Download and install **Docker Desktop** [here](https://www.docker.com/products/docker-desktop)
1. After installation, configure Docker to run Linux Containers by right-clicking the Docker icon in the System Tray and selecting "Switch to Linux containers". If the only available option is "Switch to Windows containers", then Docker is already configured to use Linux containers

## Setup the devlabs environment
1. Open Powershell
1. Pull the devlabs image: `docker pull theoneringewt/devlabs`
1. Start up the devlabs container: `docker run -t -d --name devlabs --restart unless-stopped -v //var/run/docker.sock:/var/run/docker.sock theoneringewt/devlabs`
1. Confirm the devlabs container is running: `docker ps`

## Install VS Code and connect to the devlabs container
1. Download and install VS Code https://code.visualstudio.com/
1. Open VS Code and install the "Remote-Containers" extension
1. Connect to the devlabs container
    1. Press Ctrl + Shift + P
    1. Enter `remote containers attach to running container` and select "Remote Containers: Attach to Running Container"
    1. Select the "devlabs" container