package com.github.otkmnb2783.dotenv

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

class GradleDotenvPluginTest {
    @Test fun `plugin registers task`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("com.github.otkmnb2783.dotenv")
        // Verify the result
        assertNotNull(project.tasks.findByName("greeting"))
    }
}
