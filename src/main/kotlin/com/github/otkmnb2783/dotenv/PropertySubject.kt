package com.github.otkmnb2783.dotenv

import org.gradle.api.*
import kotlin.reflect.*

@Suppress("UnstableApiUsage")
internal class PropertySubject<T, V>(
    project: Project,
    private val observer: Observer,
    type: Class<V>,
    default: V? = null
) {

    private val property = project.objects.property(type).apply {
        set(default)
    }

    operator fun getValue(thisRef: T, property: KProperty<*>): V = this.property.get()

    operator fun setValue(thisRef: T, property: KProperty<*>, value: V) {
        this.property.set(value)
        this.observer.update()
    }
}
