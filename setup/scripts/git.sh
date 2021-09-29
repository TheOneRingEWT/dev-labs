#!/bin/bash
# All configuration settings are added to .gitconfig in $HOME

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )" # absolute path of this script

# add user and email
printf "\nAdd git user and email to ~/.gitconfig file\n"
printf "Enter your DI2E username\n"
read USER
git config --global user.name $USER

printf "Enter your DI2E email\n"
read EMAIL
git config --global user.email $EMAIL

# add git aliases
printf "\nAdding git aliases to ~/.gitconfig file\n"
cat "$SCRIPT_DIR/git/git-aliases" >> ~/.gitconfig

# use cache for credential helper so that user is not prompted for credentials for each git remote operation
git config --global credential.helper cache
