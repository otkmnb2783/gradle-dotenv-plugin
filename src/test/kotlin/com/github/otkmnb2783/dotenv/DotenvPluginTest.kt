package com.github.otkmnb2783.dotenv

import io.kotlintest.*
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

class DotenvPluginTest {
    @Test fun `plugin load test`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply("com.github.otkmnb2783.dotenv")
        project.plugins.getPlugin(DotenvPlugin::class.java) shouldNotBe null
    }
}
