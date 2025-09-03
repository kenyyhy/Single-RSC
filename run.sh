#!/usr/bin/env bash
set -euo pipefail

# Determine script directory and switch to it
SCRIPT_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# Prefer the README jar name, fallback to build name
JAR="$SCRIPT_DIR/RSC-Single-Player.jar"
if [[ ! -f "$JAR" ]]; then
  JAR="$SCRIPT_DIR/rsc.jar"
fi

##############
# Build the client JAR (no Ant)
##############
BUILD_DIR="build"
rm -rf "$BUILD_DIR"
mkdir -p "$BUILD_DIR"
# Compile all Java sources into build directory
find src -name '*.java' -print0 | \
    xargs -0 javac -source 1.8 -target 1.8 \
        -cp lib/gson-2.6.2.jar \
        -d "$BUILD_DIR"
# Package into JAR using bundled manifest
rm -f "$JAR"
jar cfm "$JAR" META-INF/MANIFEST.MF -C "$BUILD_DIR" .
rm -rf "$BUILD_DIR"

exec java -cp "$JAR:$SCRIPT_DIR/lib/gson-2.6.2.jar" org.nemotech.rsc.Main "$@"

