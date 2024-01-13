.DEFAULT_GOAL := build-run

setup:
	gradle wrapper --gradle-version 8.3

clean:
	./gradlew -p ../app clean

build:
	./gradlew -p ../app clean build

install:
	./gradlew -p ../app clean install

run-dist:
	@./build/install/app/bin/app -h

run:
	./gradlew -p ../app run

test:
	./gradlew -p ../app test

report:
	./gradlew -p ../app jacocoTestReport

lint:
	./gradlew -p ../app checkstyleMain

check-deps:
	./gradlew -p ../app dependencyUpdates -Drevision=release

build-run: build run

preset:
	./build/install/app/bin/app -f json src/test/resources/fixtures/file1.json src/test/resources/fixtures/file2.json
.PHONY: build
