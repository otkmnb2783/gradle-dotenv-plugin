package com.github.otkmnb2783.dotenv

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging
import java.io.File

class GradleDotenvPlugin: Plugin<Project> {

    private val logger = Logging.getLogger(GradleDotenvPlugin::class.java)

    override fun apply(project: Project) {
        logger.debug("gradle dotenv plugin applying")
        project.extensions.create("dotenv", GradleDotenvConfiguration::class.java)

        val env = mutableMapOf<String, String>()
        project.extensions.add("env", env)

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
            f.forEachLine {
                logger.debug("line is:={}", it)
                if (it.ifBlank{"#"}.trim().isComment()) {
                    logger.debug("skipping whitespace or comment line.")
                    return@forEachLine
                }
                val matcher = it.parse() ?: return@forEachLine
                val (key, _, value) = matcher.destructured
                logger.debug("line is env definition matched: key:={}value:={}", key, value)
                env[key] = value.normalized()
            }
            logger.debug("dotenv file is loaded. env:={}", env)
            logger.info("dotenv file is loaded.")
        }
        logger.debug("gradle dotenv plugin applied.")
    }
}
