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

package io.dingodb.common.type.scalar;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dingodb.common.type.converter.DataConverter;
import io.dingodb.expr.core.TypeCode;
import io.dingodb.serial.schema.DingoSchema;
import io.dingodb.serial.schema.StringSchema;
import org.apache.avro.Schema;
import org.checkerframework.checker.nullness.qual.NonNull;

@JsonTypeName("string")
public class StringType extends AbstractScalarType {
    @JsonCreator
    public StringType(@JsonProperty("nullable") boolean nullable) {
        super(TypeCode.STRING, nullable);
    }

    @Override
    public StringType copy() {
        return new StringType(nullable);
    }

    @Override
    public DingoSchema toDingoSchema(int index) {
        return new StringSchema(index, 0);
    }

    @Override
    protected Object convertValueFrom(@NonNull Object value, @NonNull DataConverter converter) {
        return converter.convertStringFrom(value);
    }

    @Override
    protected Schema.Type getAvroSchemaType() {
        return Schema.Type.STRING;
    }
}
