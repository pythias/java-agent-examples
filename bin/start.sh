#!/usr/bin/env bash

RUN_DIR=`dirname "$0"`

JAVA_OPTS=" -Xms256M -Xmx512M "
AGENT_OPTS=" -Dplugin.dir=${RUN_DIR}/plugins"
OPTS="${JAVA_OPTS} ${AGENT_OPTS} -javaagent:${RUN_DIR}/agent-bootstrap-0.0.1.jar -jar ${RUN_DIR}/agent-application-0.0.1.jar"

if [[ -d "${JAVA_HOME}" ]]; then
    ${JAVA_HOME}/bin/java $OPTS
else
    java $OPTS
fi