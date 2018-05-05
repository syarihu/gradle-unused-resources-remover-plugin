package com.github.konifar.gradle.remover.file

import spock.lang.Specification

class DrawableFileRemoverTest extends Specification {

    def remover = new DrawableFileRemover()

    def "type is drawable"() {
        expect:
        remover.fileType == "drawable"
    }

    def "pattern matches"() {
        def pattern = remover.createPattern(fileName)
        def isMatched = false
        if (fileText =~ pattern) {
            isMatched = true
        }

        expect:
        isMatched == expected

        where:
        fileName        | fileText                   | expected
        "ic_settings"   | "R.drawable.ic_settings"   | true
        "ic_settings"   | "@drawable/ic_settings\""  | true
        "img_balloon.9" | "@drawable/img_balloon\""  | true
        "img_balloon.9" | "@drawable/img_balloon2\"" | false
        "ic_settings"   | "R.drawable.ic_setting"    | false
        "ic_settings"   | "@mipmap/ic_settings"      | false
        // "ic_settings" | "R.drawable.ic_settings2"   | false
    }
}