/*
 * Copyright 2019 New Vector Ltd
 * Copyright 2020 The Matrix.org Foundation C.I.C.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.matrix.android.sdk.rx

import org.matrix.android.sdk.api.util.Optional
import io.reactivex.Observable

fun <T : Any> Observable<Optional<T>>.unwrap(): Observable<T> {
    return filter { it.hasValue() }.map { it.get() }
}

fun <T : Any, U : Any> Observable<Optional<T>>.mapOptional(fn: (T) -> U?): Observable<Optional<U>> {
    return map {
        it.map(fn)
    }
}
