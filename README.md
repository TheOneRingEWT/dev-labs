# Description
This repository contains examples for dev labs.

# Project setup
## Install WSL2 on Windows
1. Update Windows by pressing the Windows logo key + R, type **ms-settings:windowsupdate**, press Enter or select OK and follow the instructions on the screen update Windows
1. Once Windows has been updated:
    1. Install WSL2 on Windows. Use the default distro, Ubuntu. Instructions [here](https://docs.microsoft.com/en-us/windows/wsl/setup/environment#install-wsl)
    1. Setup a user/password in Ubuntu. Instructions [here](https://docs.microsoft.com/en-us/windows/wsl/setup/environment#set-up-your-linux-user-info)

        NOTE: The rest of this README assumes the Linux distro installed is referred to as **Ubuntu**. If your distro is called **Ubuntu-20.04**, for example, use that name instead.

    1. Map a drive to WSL in File Exporer
        1. Open Windows Explorer by pressing the Windows logo key + E
        1. Right click **This PC** and select **Map network drive**
        1. In the Drive list, select a drive letter (Any available letter will do)
        1. In the Folder box, type **\\\\wsl$\\Ubuntu**
        1. Select Finish
        
        NOTE: Any directory in WSL can be mapped to File Explorer. For example, your home directory **\\\\wsl$\\Ubuntu\\home\\<username>**, where **<username>** is your Ubuntu username

## Install Docker Desktop
1. Download and install **Docker Desktop** [here](https://www.docker.com/products/docker-desktop)
1. After installation, configure Docker to run Linux Containers by right-clicking the Docker icon in the System Tray and selecting "Switch to Linux containers". If the only available option is "Switch to Windows containers", then Docker is already configured to use Linux containers

## Install Windows Terminal
1. Install and setup Windows Terminal. Instructions [here](https://docs.microsoft.com/en-us/windows/terminal/get-started#install).

    NOTE: You do not need a Microsoft account to install Windows Terminal. If asked to sign in or create an account cancel or close the window.

1. Set your default terminal to the Ubuntu distro. Instructions [here](https://docs.microsoft.com/en-us/windows/terminal/get-started#set-your-default-terminal-profile)
1. Set the default directory in the Ubuntu profile.
    1. In the Windows Terminal Settings, select the Ubuntu profile
    1. In the **Starting Directory** field, enter **//wsl$/Ubuntu/home/<username>**, where **<username>** is your Ubuntu username
    1. Click Save

## Setup the WSL environment
1. In the Windows Terminal, start a shell session in Ubuntu
1. Type in the following commands: `sudo apt update && sudo apt upgrade -y && sudo apt install git -y`
1. Clone the `dev-labs` repo: `git clone https://bitbucket.di2e.net/scm/rain/dev-labs.git`
    - Enter your DI2E username and password when prompted
1. Change into the **setup** directory: `cd setup`
1. Run the **setup-wsl.sh** script: `sudo ./setup-wsl.sh`
1. Enter your Ubuntu password when prompted
1. Follow the on-screen instructions
