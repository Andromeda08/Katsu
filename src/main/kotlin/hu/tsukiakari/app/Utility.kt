package hu.tsukiakari.app

import kotlin.math.floor
import kotlin.math.roundToInt

object Utility {


    /**
     * Shorten string if longer than a certain length
     * @return The string shortened by ellipsis if needed
     */
    fun ellipsis(str: String, length: Int = 16) = str.take(length) + if (str.length > length) "..." else ""

    /**
     * Convert seconds to Minutes and Seconds
     * @return String(x:y)
     */
    fun beatmapLengthToTime(length: Long): String {
        val minutes = floor(length / 60.0)
        val seconds = length - (minutes * 60)
        return "${minutes.roundToInt()}:${seconds.roundToInt()}"
    }

    /**
     * Convert seconds to Days, Hours and Minutes
     * @return String(xd yh zm)
     */
    fun playTimeToDHM(time: Long): String {
        var remaining: Long = time
        val days = floor(time / 24.0 / 60.0 / 60.0).toLong()

        remaining -= days * 24 * 60 * 60
        val hours = floor(remaining / 60.0 / 60.0).toLong()

        remaining -= hours * 60 * 60
        val minutes = floor(remaining / 60.0).toLong()

        return "${days}d ${hours}h ${minutes}m"
    }

    /**
     * Format accuracy values to (x.xx%)
     * @return Formatted accuracy
     */
    fun formatAccuracy(accuracy: Double): String = "${String.format("%.2f", accuracy)}%"

    /**
     * Convert osu! timestamp to Y/M/D
     * @return List: (year, month, day)
     */
    fun convertToYMD(timeStamp: String): List<String> {
        val ymd = timeStamp.take(10).split('-')
        val month = when (ymd[1]) {
            "01" -> "January"
            "02" -> "February"
            "03" -> "March"
            "04" -> "April"
            "05" -> "May"
            "06" -> "June"
            "07" -> "July"
            "08" -> "August"
            "09" -> "September"
            "10" -> "October"
            "11" -> "November"
            "12" -> "December"
            else -> throw IllegalArgumentException("Unknown month: \" ${ymd[1]} \"")
        }
        return listOf(ymd[0], month, ymd[1])
    }

    fun formatBigNumber(number: Long): String {
        if (number >= 10e11) {
            return "${String.format("%.2f", number / 10e11)} trillion"
        }
        if (number >= 10e8) {
            return "${String.format("%.2f", number / 10e8)} billion"
        }
        if (number >= 10e5) {
            return "${String.format("%.2f", number / 10e5)} million"
        }
        return number.toString()
    }
}