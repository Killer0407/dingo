/*
 * Copyright 2021 DataCanvas
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

package io.dingodb.store.api;

import io.dingodb.common.CommonId;
import io.dingodb.common.table.TableDefinition;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface StoreService {

    static StoreService getDefault() {
        return StoreServiceProvider.getDefault().get();
    }

    default StoreInstance getInstance(@NonNull CommonId tableId, CommonId regionId) {
        return null;
    }

    default StoreInstance getInstance(@NonNull CommonId tableId, CommonId regionId, TableDefinition tableDefinition) {
        return null;
    }

    void deleteInstance(CommonId id);

}
