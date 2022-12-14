package hu.tsukiakari.app

import com.jakewharton.picnic.TextAlignment
import com.jakewharton.picnic.table
import hu.tsukiakari.osu.client.enum.PlayMode
import hu.tsukiakari.osu.client.enum.ScoreType
import hu.tsukiakari.osu.model.beatmap.Beatmap
import hu.tsukiakari.osu.model.beatmap.BeatmapSet
import hu.tsukiakari.osu.model.ranking.CountryEntry
import hu.tsukiakari.osu.model.ranking.UserEntry
import hu.tsukiakari.osu.model.score.Score
import hu.tsukiakari.osu.model.user.User
import io.ktor.util.*
import java.text.NumberFormat
import kotlin.math.roundToInt

object Format {
    fun formatBeatmap(bm: Beatmap): String {
        return table {
            cellStyle { border = true }
            row {
                cell("${Utility.ellipsis(bm.beatmapset!!.artist)} - ${Utility.ellipsis(bm.beatmapset.title, 24)} [${bm.version}]") {
                    columnSpan = 4
                }
            }
            row {
                cell("Length: ${Utility.beatmapLengthToTime(bm.totalLength)}")
                cell("BPM: ${bm.bpm}")
                cell("Circles: ${bm.countCircles}")
                cell("Sliders: ${bm.countSliders}")
            }
            row{
                cell("Circle Size")
                cell(bm.cs) { columnSpan = 3 }
            }
            row{
                cell("HP Drain")
                cell(bm.drain) { columnSpan = 3 }
            }
            row{
                cell("Accuracy")
                cell(bm.accuracy) { columnSpan = 3 }
            }
            row{
                cell("Approach Rate")
                cell(bm.ar) { columnSpan = 3 }
            }
            row {
                cell("Beatmapset created by ${bm.beatmapset!!.creator}") {
                    columnSpan = 4
                }
            }
        }.toString()
    }

    fun formatUser(user: User): String {
        return table {
            cellStyle { border = true }
            row {
                cell("#${user.statistics.globalRank}")
                cell("${user.country.code} (#${user.statistics.countryRank})")
                cell(user.username) {
                    paddingLeft = 3
                    paddingRight = 3
                }
                cell(Utility.formatAccuracy(user.statistics.hitAccuracy)) { alignment = TextAlignment.MiddleCenter }
                cell("${user.statistics.pp.roundToInt()} pp") { alignment = TextAlignment.MiddleCenter }
            }
            row {
                cell("SSH: ${user.statistics.gradeCounts.ssh}") { alignment = TextAlignment.MiddleCenter }
                cell("SS: ${user.statistics.gradeCounts.ss}") { alignment = TextAlignment.MiddleCenter }
                cell("SH: ${user.statistics.gradeCounts.sh}") { alignment = TextAlignment.MiddleCenter }
                cell("S: ${user.statistics.gradeCounts.s}") { alignment = TextAlignment.MiddleCenter }
                cell("A: ${user.statistics.gradeCounts.a}") { alignment = TextAlignment.MiddleCenter }
            }
            row {
                cell("Play time")
                cell(Utility.playTimeToDHM(user.statistics.playTime)) {
                    columnSpan = 2
                    alignment = TextAlignment.MiddleCenter
                }
                cell("Play count")
                cell(user.statistics.playCount) { alignment = TextAlignment.MiddleCenter }
            }
            if (user.playstyle != null) {
                row {
                    cell("Plays with:")
                    cell(user.playstyle.toString().subSequence(1, user.playstyle.toString().length - 1)) { columnSpan = 4 }
                }
            }
            row {
                val ymd = Utility.convertToYMD(user.joinDate)
                cell("Joined ${ymd[1]} ${ymd[0]}") { columnSpan = 3 }
                cell("User ID")
                cell(user.id) { alignment = TextAlignment.MiddleCenter }
            }
        }.toString()
    }


    fun formatUserScores(type: ScoreType?, mode: PlayMode, scores: List<Score>, username: String, beatmap: Beatmap?) =
        table {
            cellStyle {
                border = true
            }
            header {
                row {
                    if (type != null) {
                        cell("${type.str} scores of $username in ${mode.str}") { columnSpan = 7 }
                    }
                    if (beatmap != null) {
                        cell("Scores of $username on ${Utility.ellipsis(beatmap.beatmapset!!.artist)} - ${Utility.ellipsis(beatmap.beatmapset.title)} [${beatmap.version}]") {
                            columnSpan = 7
                        }
                    }
                }

                row("#", "Rank", "Song", "Difficulty", "Mods", "Accuracy", "pp")
                scores.forEachIndexed { index, score ->
                    row {
                        cellStyle {
                            borderTop = false
                            borderBottom = (index == scores.size - 1)
                        }
                        cell("#${index + 1}")
                        cell(score.rank.toUpperCasePreservingASCIIRules()) { alignment = TextAlignment.MiddleCenter }
                        if (type != null) {
                            cell("${Utility.ellipsis(score.beatmapset!!.artist)} - ${score.beatmapset.title}")
                            cell(score.beatmap!!.version)
                        }
                        if (beatmap != null) {
                            cell("${Utility.ellipsis(beatmap.beatmapset!!.artist)} - ${Utility.ellipsis(beatmap.beatmapset.title)}")
                            cell(beatmap.version)
                        }
                        cell(score.mods.toString().subSequence(1, score.mods.toString().length - 1))
                        cell(Utility.formatAccuracy(score.accuracy * 100)) { alignment = TextAlignment.MiddleCenter }
                        cell("${score.pp.roundToInt()}pp")
                    }
                }
            }
        }.toString()

    fun formatScore(position: Int?, score: Score, beatmapSet: BeatmapSet, beatmap: Beatmap): String {
        return table {
            cellStyle { border = true }
            row {
                if (position != null) { cell("#$position") }
                cell(score.rank.toUpperCasePreservingASCIIRules())
                cell("${beatmapSet.artist} - ${beatmapSet.title}")
                cell(beatmap.version)
                if (score.mods.isNotEmpty()) {
                    cell(score.mods.toString().subSequence(1, score.mods.toString().length - 1))
                }
                cell(Utility.formatAccuracy(score.accuracy * 100))
                cell("${score.pp.roundToInt()}pp")
            }
        }.toString()
    }

    fun formatBeatmapScores(scores: List<Score>, beatmap: Beatmap, mode: PlayMode) =
        table {
            cellStyle {
                border = true
            }
            header {
                row {
                    cell("Top scores on ${Utility.ellipsis(beatmap.beatmapset!!.artist)} - ${Utility.ellipsis(beatmap.beatmapset.title)} [${beatmap.version}] for ${mode.str}") {
                        columnSpan = 6
                    }
                }
            }
            row ("#", "Player", "Rank", "Mods", "Accuracy", "pp")
            scores.forEachIndexed { index, score ->
                row {
                    cellStyle {
                        borderTop = false
                        borderBottom = (index == scores.size - 1)
                    }
                    cell("#${index + 1}")
                    cell(score.user!!.username)
                    cell(score.rank.toUpperCasePreservingASCIIRules()) { alignment = TextAlignment.MiddleCenter }
                    cell(score.mods.toString().subSequence(1, score.mods.toString().length - 1))
                    cell(Utility.formatAccuracy(score.accuracy * 100)) { alignment = TextAlignment.MiddleCenter }
                    cell("${score.pp.roundToInt()}pp")
                }
            }
        }.toString()

    fun formatUserPerformanceRanking(users: List<UserEntry>, mode: PlayMode) =
        table {
            cellStyle {
                border = true
            }
            header {
                row {
                    cell("Performance rankings for ${mode.str}") { columnSpan = 9 }
                }
                row ("#", "Country", "User", "Accuracy", "Play Count", "Performance", "SS", "S", "A")
                users.forEachIndexed { index, entry ->
                    row {
                        cellStyle {
                            borderTop = false
                            borderBottom = (index == users.size - 1)
                        }
                        cell("#${index + 1}")
                        cell(entry.user.country.code)
                        cell(entry.user.username)
                        cell(Utility.formatAccuracy(entry.hitAccuracy))
                        cell(entry.playCount)
                        cell("${entry.pp.roundToInt()}pp")
                        cell(entry.gradeCounts.ssh + entry.gradeCounts.ss)
                        cell(entry.gradeCounts.sh + entry.gradeCounts.s)
                        cell(entry.gradeCounts.a)
                    }
                }
            }
        }.toString()

    fun formatUserScoreRanking(users: List<UserEntry>, mode: PlayMode) =
        table {
            cellStyle {
                border = true
            }
            header {
                row {
                    cell("Score rankings for ${mode.str}") { columnSpan = 10 }
                }
                row ("#", "Country", "User", "Accuracy", "Play Count", "Total Score", "Ranked Score", "SS", "S", "A")
                users.forEachIndexed { index, entry ->
                    row {
                        cellStyle {
                            borderTop = false
                            borderBottom = (index == users.size - 1)
                        }
                        cell("#${index + 1}")
                        cell(entry.user.country.code)
                        cell(entry.user.username)
                        cell(Utility.formatAccuracy(entry.hitAccuracy))
                        cell(entry.playCount)
                        cell(Utility.formatBigNumber(entry.totalScore))
                        cell(Utility.formatBigNumber(entry.totalScore))
                        cell(entry.gradeCounts.ssh + entry.gradeCounts.ss)
                        cell(entry.gradeCounts.sh + entry.gradeCounts.s)
                        cell(entry.gradeCounts.a)
                    }
                }
            }
        }.toString()

    fun formatCountryList(countries: List<CountryEntry>, mode: PlayMode) =
        table {
            cellStyle {
                border = true
            }
            header {
                row {
                    cell("Score rankings for ${mode.str}") { columnSpan = 8 }
                }
                row ("#", "Country", "Active Users", "Play Count", "Ranked Score", "Avg. Score", "Performance", "Avg. Perf.")
                countries.forEachIndexed { index, entry ->
                    row {
                        cellStyle {
                            borderTop = false
                            borderBottom = (index == countries.size - 1)
                        }
                        cell("#${index + 1}")
                        cell(entry.country.name)
                        cell(NumberFormat.getIntegerInstance().format(entry.activeUsers)) { alignment = TextAlignment.MiddleCenter }
                        cell(Utility.formatBigNumber(entry.playCount))
                        cell(Utility.formatBigNumber(entry.rankedScore))
                        cell(Utility.formatBigNumber((entry.rankedScore / entry.activeUsers)))
                        cell(Utility.formatBigNumber(entry.performance))
                        cell("${(entry.performance / entry.activeUsers)} pp") { alignment = TextAlignment.MiddleCenter }
                    }
                }
            }
        }.toString()
}