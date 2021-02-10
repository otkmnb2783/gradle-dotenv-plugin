package com.github.otkmnb2783.dotenv

import java.io.File
import org.gradle.testkit.runner.GradleRunner
import kotlin.test.Test
import kotlin.test.assertTrue

class DotenvPluginFunctionalTest {
    @Test fun `can load`() {
        // Setup the test build
        val dollar = '$'
        val projectDir = File("build/functionalTest")
        projectDir.mkdirs()
        projectDir.resolve("settings.gradle").writeText("")
        projectDir.resolve("build.gradle").writeText("""
            plugins {
                id('com.github.otkmnb2783.dotenv')
            }
            dotenv {
                dir = "../.."
                fileName = ".env.dev"
            }
            println("${dollar}{env.MYSQL_USER}")
            println("${dollar}{env.MYSQL_PASSWORD}")
            println("${dollar}{env.HOGE}")
        """)
        projectDir.resolve(".env").writeText("""
            MYSQL_USER=mysql_user
            MYSQL_PASSWORD=mysql_passw0rd
            HOGE=fuga
        """)

        // Run the build
        val runner = GradleRunner.create()
        runner.forwardOutput()
        runner.withPluginClasspath()
        runner.withArguments("tasks")
        runner.withProjectDir(projectDir)
        val result = runner.build()

        // Verify the result
        assertTrue(result.output.contains("mysql_user"))
        assertTrue(result.output.contains("mysql_passw0rd"))
        assertTrue(result.output.contains("fuga"))
    }
}
