package hu.tsukiakari.app

data class Messages(
    val welcome: String = "Welcome to the osu! api explorer",
    val mainPrompt: String = "Select a route!",
    val exitMessage: String = "Bye bye o/",
    val generic: GenericMessages = GenericMessages(),
    val beatmap: BeatmapMessages = BeatmapMessages(),
    val user: UserMessages = UserMessages(),
    val error: ErrorMessages = ErrorMessages()
)

data class GenericMessages(
    val select: String = "Select:",
    val selectPlayMode: String = "Select playmode:",
    val selectRankingType: String = "Select ranking type:",
    val selectManiaVariant: String = "Select mania variant:"
)
data class BeatmapMessages(
    val promptId: String = "Enter beatmap id:",
    val promptIds: String = "Enter beatmap ids separated by commas:"
)

data class UserMessages(
    val promptName: String = "Enter username:",
    val promptId: String = "Enter user id:",
    val promptSearchKey: String = "Select lookup key:",
    val promptScoreType: String = "Select score type:",
)

data class ErrorMessages(
    val userNotFound: String = "Error: User not found!",
    val beatmapNotFound: String = "Error: Beatmap not found!",
    val clientCredentialsMissing: String = "Error: Missing client id or/and client secret."
)