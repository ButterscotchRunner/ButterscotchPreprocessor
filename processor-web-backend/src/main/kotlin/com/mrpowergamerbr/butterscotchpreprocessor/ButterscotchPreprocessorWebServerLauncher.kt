package com.mrpowergamerbr.butterscotchpreprocessor

object ButterscotchPreprocessorWebServerLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        val jsBundle = ButterscotchPreprocessorWebServer::class.java.getResourceAsStream("/web/js/processor-web.js")!!
            .readBytes()
            .toString(Charsets.UTF_8)

        val cssBundle = ButterscotchPreprocessorWebServer::class.java.getResourceAsStream("/web/css/style.css")!!
            .readBytes()
            .toString(Charsets.UTF_8)

        val butterscotchBC14Elf = ButterscotchPreprocessorWebServer::class.java.getResourceAsStream("/web/butterscotch-wad14.elf")!!
            .readBytes()

        val butterscotchBC16Elf = ButterscotchPreprocessorWebServer::class.java.getResourceAsStream("/web/butterscotch-wad16.elf")!!
            .readBytes()

        val butterscotchBC17Elf = ButterscotchPreprocessorWebServer::class.java.getResourceAsStream("/web/butterscotch-wad17.elf")!!
            .readBytes()

        val iconIco = ButterscotchPreprocessorWebServer::class.java.getResourceAsStream("/web/ICON.ICO")!!
            .readBytes()

        val server = ButterscotchPreprocessorWebServer(jsBundle, cssBundle, butterscotchBC14Elf, butterscotchBC16Elf, butterscotchBC17Elf, iconIco)
        server.start()
    }
}