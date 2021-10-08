# ---------------------------------------------------------
# CLASSIFICATION: UNCLASSIFIED//CONTROLLED UNCLASSIFIED INFORMATION
# RELEASABLE TO: USA
# ---------------------------------------------------------
#
# Prognostics And Health Management (PHM)
#
# FILENAME: Dockerfile

FROM docker-rain.di2e.net/ironbank/opensource/nodejs/nodejs16:16.5.0

# The USER instruction sets the user name (or UID) and optionally the user group (or GID) 
# to use when running the image and for any RUN, CMD and ENTRYPOINT instructions that follow it in the Dockerfile
USER node

# The WORKDIR instruction sets the working directory for any RUN, CMD, ENTRYPOINT, COPY and ADD instructions that follow it in the Dockerfile. 
# If the WORKDIR doesn’t exist, it will be created even if it’s not used in any subsequent Dockerfile instruction.
WORKDIR /app

# A wildcard is used to ensure both package.json AND package-lock.json are copied
COPY --chown=node:node package*.json ./

# install npm dependencies
RUN npm install

# add app
COPY --chown=node:node . ./

# start app
CMD ["npm", "start"]
