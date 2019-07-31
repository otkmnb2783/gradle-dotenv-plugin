package com.github.otkmnb2783.dotenv

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging

class GradleDotenvPlugin: Plugin<Project> {
    private val logger = Logging.getLogger(GradleDotenvPlugin::class.java)
    override fun apply(project: Project) {
        logger.info("gradle dotenv plugin applying")
        // Register a task
        project.tasks.register("greeting") { task ->
            task.doLast {
                println("Hello from plugin 'com.github.otkmnb2783.dotenv'")
            }
        }
        logger.info("gradle dotenv plugin applied")
    }
}
