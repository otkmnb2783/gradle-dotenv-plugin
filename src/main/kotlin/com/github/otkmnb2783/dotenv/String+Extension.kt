package com.github.otkmnb2783.dotenv

fun String.isComment(): Boolean {
    return this.startsWith(prefix = "#") or this.startsWith(prefix = """//""")
}

fun String.isQuote(): Boolean {
    return this.startsWith(prefix = "\"") && this.endsWith(suffix = "\"")
}

fun String.normalized(): String {
    val it = this.trim()
    return if (it.isQuote()) it.substring(1 until it.length -1) else it
}

fun String.parse(): MatchResult? {
    return """^\s*([\w.\-]+)\s*(=)\s*(.*)?\s*$""".toRegex().matchEntire(this)
}