package com.github.otkmnb2783.dotenv

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

class DotenvPluginTest {
    @Test fun `plugin load test`() {
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("com.github.otkmnb2783.dotenv")
    }
}
