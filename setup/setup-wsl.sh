#!/bin/bash
# This script will install and config common tools and configurations used in SMT Windows/WSL development

# update distro
sudo apt update \
&& sudo apt upgrade -y \
&& sudo apt install -y \
ca-certificates \
curl

# install Oh My Bash!
./scripts/ohmybash.sh

# install openJDK 8 and Node
./scripts/java-node.sh

# configure git
./scripts/git.sh

# configure docker
./scripts/docker.sh

# source .bashrc fils so that current shell contains changes
source ~/.bashrc
printf "\nSetup is complete\n"
