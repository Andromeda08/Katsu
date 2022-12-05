package hu.tsukiakari.app.cli

import com.github.kinquirer.KInquirer
import com.github.kinquirer.components.*
import hu.tsukiakari.osu.client.Client

const val gClientId: Int = 0
const val gClientSecret: String = ""

class CliApplication {
    private val client: Client = Client(gClientId, gClientSecret)
    private val shouldExit: Boolean = false;

    fun run() {
        while (!shouldExit) {
            val request: String = KInquirer.promptList("Select route!", listOf("Users", "Beatmaps", "Rankings"))
            println(request)
        }
    }
}

fun main(args: Array<String>) {
    val app = CliApplication()
    app.run()
}