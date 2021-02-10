package com.github.otkmnb2783.dotenv

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging
import java.io.File

private const val EXTENSION_NAME = "dotenv"
private const val VAR_NAME = "env"

class DotenvPlugin: Plugin<Project> {

    private val logger = Logging.getLogger(DotenvPlugin::class.java)

    override fun apply(project: Project) {
        logger.debug("gradle dotenv plugin applying")
        val env = mutableMapOf<String, String>()
        project.extensions.add(VAR_NAME, env)
        val extension = project.extensions.create(EXTENSION_NAME, DotenvExtension::class.java, project, env)
        extension.load()
        logger.debug("gradle dotenv plugin applied.")
    }
}
