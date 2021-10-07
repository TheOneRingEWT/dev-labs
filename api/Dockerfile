# ---------------------------------------------------------
# CLASSIFICATION: UNCLASSIFIED//CONTROLLED UNCLASSIFIED INFORMATION
# RELEASABLE TO: USA
# ---------------------------------------------------------
#
# Prognostics And Health Management (PHM)
#
# FILENAME: Dockerfile

FROM docker-rain.di2e.net/ironbank/redhat/openjdk/openjdk8:1.8.0

ENV APP=api

WORKDIR /$APP

COPY --chown=1001:1001 build/libs/api-0.0.1-SNAPSHOT.jar .

CMD ["sh","-c","java -jar api-0.0.1-SNAPSHOT.jar"]
