package com.github.otkmnb2783.dotenv

import org.gradle.api.Project
import org.gradle.api.Plugin

class GradleDotenvPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        // Register a task
        project.tasks.register("greeting") { task ->
            task.doLast {
                println("Hello from plugin 'com.github.otkmnb2783.dotenv'")
            }
        }
    }
}
