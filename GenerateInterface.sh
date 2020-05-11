#!/usr/bin/env sh
echo '开始编译'
javac -encoding UTF-8 -cp build/classes/java/main/ -processor com.github.ccmagic.generateinterface.CreateInterfaceProcessor -d build/classes/java/main -s src/main/java/ src/main/java/com/github/ccmagic/generateinterface/*.java