#!/bin/bash
# This script will install Java 8 and NVM with the most recent Node LTS

printf "\nInstalling OpenJDK8\n"
sudo apt install openjdk-8-jdk -y

printf "\nInstalling Node Version Manager"
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.38.0/install.sh | bash
source ~/.bashrc
nvm install lts/*
