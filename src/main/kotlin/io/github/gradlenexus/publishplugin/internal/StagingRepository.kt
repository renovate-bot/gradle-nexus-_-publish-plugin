/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.gradlenexus.publishplugin.internal

data class StagingRepository constructor(val id: String, val state: State, val transitioning: Boolean) {

    enum class State {
        OPEN,
        CLOSED,
        RELEASED,
        NOT_FOUND;

        override fun toString(): String =
            name.toLowerCase()

        companion object {
            fun parseString(stateAsString: String): State {
                try {
                    return valueOf(stateAsString.toUpperCase())
                } catch (e: IllegalArgumentException) {
                    error("Unsupported repository state '$stateAsString'. Supported values: ${values()}")
                }
            }
        }
    }

    companion object {
        fun notFound(id: String): StagingRepository =
            StagingRepository(id, State.NOT_FOUND, false)
    }
}
