package hu.tsukiakari.app

import com.github.kinquirer.KInquirer
import com.github.kinquirer.components.*
import hu.tsukiakari.osu.client.Client
import hu.tsukiakari.osu.client.enum.*
import hu.tsukiakari.osu.model.user.User

const val gClientId: Int = 0
const val gClientSecret: String = ""

class Application {
    private val client: Client = Client(gClientId, gClientSecret)
    private var shouldExit: Boolean = false;

    private val messages: Messages = Messages()

    suspend fun run() {
        println(messages.welcome)

        if (gClientId == 0 || gClientSecret.isEmpty()) {
            println(messages.error.clientCredentialsMissing)
            shouldExit = true
        }

        while (!shouldExit) {
            val mainOption: String = KInquirer.promptList(messages.mainPrompt, mainOptions)

            if (mainOption == mainOptions[4]) {
                shouldExit = true
                println(messages.exitMessage)
            }
            else {
                when (mainOption) {
                    mainOptions[0] -> userRequests()
                    mainOptions[1] -> userScoreRequests()
                    mainOptions[2] -> beatmapRequests()
                    mainOptions[3] -> rankingRequests()
                }
            }
        }
    }

    private suspend fun userRequests() {
        val key: String = KInquirer.promptList(messages.user.promptSearchKey, userKeyOptions)

        if (key == "Back") {
            return
        }

        val mode = promptForPlayMode()

        var user: User? = null

        when (key) {
            userKeyOptions[0] -> {
                val username: String = KInquirer.promptInput(messages.user.promptName)
                if (username.isNotEmpty()) {
                    user = client.users.getUser(username, mode)
                }
            }
            userKeyOptions[1] -> {
                val userId: Int = KInquirer.promptInputNumber(messages.user.promptId).intValueExact()
                user = client.users.getUser(userId, mode)
            }
        }

        if (user != null) {
            try {
                println(Format.formatUser(user))
            }
            catch (e: Exception) {
                println(messages.error.userNotFound)
            }
        }
    }

    private suspend fun userScoreRequests() {
        val type = KInquirer.promptList(messages.user.promptScoreType, userScoreOptions)
        if (type == "Back") {
            return
        }
        val scoreType = strToScoreType(type.lowercase())
        val playMode = promptForPlayMode()

        val username: String = KInquirer.promptInput(messages.user.promptName)
        if (username.isNotEmpty()) {
            val user = client.users.getUser(username, playMode)
            try {
                val scores = client.users.getUserScores(user.id, playMode, scoreType)
                println(Format.formatUserScores(scoreType, playMode, scores, user.username))
            }
            catch (e: Exception) {
                println(messages.error.userNotFound)
            }
        }
    }

    private suspend fun beatmapRequests() {
        val choice = KInquirer.promptList(messages.generic.select, beatmapOptions)

        if (choice == "Back") {
            return
        }

        try {
            if (choice != beatmapOptions[1]) {
                val beatmapId = KInquirer.promptInputNumber(messages.beatmap.promptId).intValueExact()
                when (choice) {
                    beatmapOptions[0] -> println(Format.formatBeatmap(client.beatmaps.getBeatmap(beatmapId)))
                    beatmapOptions[2] -> {
                        val mode = promptForPlayMode()
                        val beatmap = client.beatmaps.getBeatmap(beatmapId)
                        val response = client.beatmaps.getBeatmapScores(beatmapId, mode)
                        response.scores.forEachIndexed { index, score ->
                            println("[${index + 1}] ${Format.formatScoreCompact(score, beatmap.beatmapset!!, beatmap)}")
                        }
                    }
                    beatmapOptions[3] -> {
                        val username: String = KInquirer.promptInput(messages.user.promptName)
                        val mode = promptForPlayMode()
                        val user = client.users.getUser(username, mode)

                        try {
                            val response = client.beatmaps.getUserScoreOnBeatmap(beatmapId, user.id, mode)
                            val beatmapset = client.beatmaps.getBeatmap(response.score.beatmap!!.id).beatmapset!!

                            println(
                                Format.formatScore(
                                    response.position,
                                    response.score,
                                    beatmapset,
                                    response.score.beatmap
                                )
                            )
                        }
                        catch (e: Exception) {
                            println("${user.username} doesn't have a score on this beatmap!")
                            return
                        }
                    }
                    beatmapOptions[4] -> {
                        val username: String = KInquirer.promptInput(messages.user.promptName)
                        val mode = promptForPlayMode()

                        val user = client.users.getUser(username, mode)
                        val response = client.beatmaps.getAllUserScoresOnBeatmap(beatmapId, user.id, mode)

                        if (response.scores.isEmpty()) {
                            println("${user.username} doesn't have a score on this beatmap!")
                            return
                        }
                        else {
                            val beatmap = client.beatmaps.getBeatmap(beatmapId)

                            response.scores.forEachIndexed { index, score ->
                                println("[${index + 1}] ${Format.formatScoreCompact(score, beatmap.beatmapset!!, beatmap)}")
                            }
                        }
                    }
                }
            }
            else {
                KInquirer.promptInput(messages.beatmap.promptIds)
                    .split(',')
                    .toList()
                    .forEach { it -> println(Format.formatBeatmap(client.beatmaps.getBeatmap(it.toInt()))) }
            }
        }
        catch (e: Exception) {
            println(messages.error.beatmapNotFound)
        }
    }

    private suspend fun rankingRequests() {
        val inType = KInquirer.promptList(
            message = messages.generic.selectRankingType,
            choices = (RankingType.values().map { it.str }.toMutableList() + "Back").toList()
        )

        if (inType == "Back") {
            return
        }

        val type = strToRankingType(inType)
        val mode = promptForPlayMode()

        var variant: ManiaVariant? = null
        if (mode == PlayMode.MANIA) {
            variant = strToManiaVariant(
                KInquirer.promptList(
                    message = messages.generic.selectManiaVariant,
                    choices = ManiaVariant.values().map { it.str }.toList()
                )
            )
        }

        when (type) {
            RankingType.COUNTRY -> {
                val response = client.rankings.getCountryRankings(mode, variant)
                println(Format.formatCountryList(response.ranking, mode))
            }
            RankingType.PERFORMANCE -> {
                val response = client.rankings.getPerformanceRankings(mode, variant)
                println(Format.formatUserPerformanceRanking(response.ranking, mode))
            }
            RankingType.SCORE -> {
                val response = client.rankings.getScoreRankings(mode, variant)
                println(Format.formatUserScoreRanking(response.ranking, mode))
            }
        }
    }

    private fun promptForPlayMode(): PlayMode =
        strToPlayMode(
            KInquirer.promptList(
                message = messages.generic.selectPlayMode,
                choices = PlayMode.values().map { it.str }.toList()
            )
        )

    private val mainOptions = listOf("Users", "User Scores", "Beatmaps", "Rankings", "Exit")
    private val userKeyOptions = listOf("Username", "Id", "Back")
    private val userScoreOptions = listOf("Best", "Firsts", "Recent", "Back")
    private val beatmapOptions = listOf("Lookup beatmap", "Lookup multiple beatmaps", "Lookup scores on beatmap",
        "Lookup user best on beatmap", "Lookup user scores on beatmap", "Back")
}

suspend fun main(args: Array<String>) {
    val app = Application()
    app.run()
}