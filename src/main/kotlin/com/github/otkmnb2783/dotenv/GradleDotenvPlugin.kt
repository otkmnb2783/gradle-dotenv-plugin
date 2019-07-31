package com.github.otkmnb2783.dotenv

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging
import java.io.File
import java.util.regex.Pattern

class GradleDotenvPlugin: Plugin<Project> {

    private val matcher = Pattern.compile("/^\\s*(?:export\\s+|)([\\w\\d\\.\\-_]+)\\s*=\\s*['\"]?(.*?)?['\"]?\\s*\$/")
    private val logger = Logging.getLogger(GradleDotenvPlugin::class.java)

    override fun apply(project: Project) {
        logger.debug("gradle dotenv plugin applying")
        project.extensions.create("dotenv", GradleDotenvConfiguration::class.java)
        project.run {
            logger.debug("gradle dotenv plugin running.")
            val configuration = this.extensions.findByName("dotenv") as GradleDotenvConfiguration
            val dir = configuration.dir ?: this.rootDir
            val fileName = configuration.fileName
            val separator = File.separator
            val f = File("$dir$separator$fileName")
            logger.debug("loaded configuration dir:={}", dir)
            logger.debug("loaded configuration fileName:={}", configuration.fileName)
            logger.debug("dotenv file path:={}", f)
            if (!f.exists()) {
                logger.warn("dotenv file is not found.")
                return@run
            }
            val env = mutableMapOf<String, String>()
            f.forEachLine {
                logger.debug("line is:={}", it)
                if (it.isWhitespace() and it.isComment()) {
                    logger.debug("it is whitespace or comment line.")
                    logger.debug("it is skipping.")
                    return@forEachLine
                }
                val matcher = it.parse() ?: return@forEachLine
                val (key, _, value) = matcher.destructured
                logger.debug("line is env definition matched: key:={}value:={}", key, value)
                env[key] = value.normalized()
            }
            logger.debug("dotenv file is loaded. env:={}", env)
            project.extensions.add("env", env)
            logger.info("dotenv file is loaded.")
        }
        logger.debug("gradle dotenv plugin applied.")
    }
}
