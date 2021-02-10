package com.github.otkmnb2783.dotenv

import org.gradle.api.*
import java.io.*

open class DotenvExtension(
    private val project: Project,
    private val env: MutableMap<String, String>
) {

    private val observer = object : Observer {
        override fun update() {
            load()
        }
    }

    var dir by PropertySubject(project, observer, String::class.java, "")
    var fileName by PropertySubject(project, observer, String::class.java, ".env")

    internal fun load() {
        project.logger.debug("loaded configuration dir:={}", dir)
        project.logger.debug("loaded configuration fileName:={}", fileName)
        val dir = dir.let { if (it.isNotEmpty()) project.file(it) else project.rootDir }
        val fileName = fileName
        val separator = File.separator
        val f = File("$dir$separator$fileName")
        project.logger.debug("dotenv file path:={}", f)
        if (!f.exists()) {
            project.logger.warn("dotenv file is not found.")
            return
        }
        f.forEachLine {
            project.logger.debug("line is:={}", it)
            if (it.ifBlank { "#" }.trim().isComment()) {
                project.logger.debug("skipping whitespace or comment line.")
                return@forEachLine
            }
            val matcher = it.parse() ?: return@forEachLine
            val (key, _, value) = matcher.destructured
            project.logger.debug("line is env definition matched: key:={}value:={}", key, value)
            env[key] = value.normalized()
        }
        project.logger.debug("dotenv file is loaded. env:={}", env)
        project.logger.info("dotenv file is loaded.")
    }
}
