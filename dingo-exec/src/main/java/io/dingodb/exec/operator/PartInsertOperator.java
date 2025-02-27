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

package io.dingodb.exec.operator;

import io.dingodb.common.partition.RangeDistribution;
import io.dingodb.common.type.DingoType;
import io.dingodb.exec.Services;
import io.dingodb.exec.converter.ValueConverter;
import io.dingodb.exec.dag.Vertex;
import io.dingodb.exec.operator.data.Context;
import io.dingodb.exec.operator.params.PartInsertParam;
import io.dingodb.store.api.StoreInstance;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PartInsertOperator extends PartModifyOperator {
    public static final PartInsertOperator INSTANCE = new PartInsertOperator();

    private PartInsertOperator() {
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean pushTuple(Context context, Object[] tuple, Vertex vertex) {
        PartInsertParam param = vertex.getParam();
        RangeDistribution distribution = context.getDistribution();
        DingoType schema = param.getSchema();
        StoreInstance store = Services.KV_STORE.getInstance(param.getTableId(), distribution.getId());
        Object[] keyValue = (Object[]) schema.convertFrom(tuple, ValueConverter.INSTANCE);
        boolean insert = store.insertIndex(keyValue);
        if (insert) {
            if (store.insertWithIndex(keyValue)) {
                param.inc();
                param.addKeyState(true);
            } else {
                param.addKeyState(false);
            }
        } else {
            param.addKeyState(false);
        }
        return true;
    }
}
