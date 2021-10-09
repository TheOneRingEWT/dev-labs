#!/bin/bash
# This script will install and config common tools and configurations used in SMT Windows/WSL development

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )" # absolute path of this script

# update distro and install packages
sudo apt update \
&& sudo apt upgrade -y \
&& sudo apt install -y \
openjdk-8-jdk \
openjdk-11-jdk

# install Oh My Bash!
curl -fsSL https://raw.githubusercontent.com/ohmybash/oh-my-bash/master/tools/install.sh | bash

printf "\nInstalling Node Version Manager with latest Node LTS"
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.38.0/install.sh | bash
source ~/.bashrc
nvm install lts/*

# configure git
printf "\nAdd git user and email to ~/.gitconfig file\n"
printf "Enter your DI2E username"
read USER
git config --global user.name $USER

printf "Enter your DI2E email"
read EMAIL
git config --global user.email $EMAIL

# add git aliases
printf "\nAdding git aliases to ~/.gitconfig file\n"
cat "$SCRIPT_DIR/git-aliases" >> ~/.gitconfig

# use cache for credential helper so that user is not prompted for credentials for each git remote operation
git config --global credential.helper cache

# configure docker
printf "\nLogging on to docker-hub.di2e.net"
docker login https://docker-hub.di2e.net

printf "\nLogging on to docker-rain.di2e.net"
docker login https://docker-rain.di2e.net

printf "\nClose and open a new terminal to complete setup.\n"
