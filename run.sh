#!/usr/bin/env bash
set -euo pipefail

# Determine script directory
SCRIPT_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"

# Prefer the README jar name, fallback to build name
JAR="$SCRIPT_DIR/RSC-Single-Player.jar"
if [[ ! -f "$JAR" ]]; then
  JAR="$SCRIPT_DIR/rsc.jar"
fi

exec java -jar "$JAR" "$@"

