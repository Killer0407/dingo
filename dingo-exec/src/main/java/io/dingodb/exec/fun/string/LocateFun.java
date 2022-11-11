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

package io.dingodb.exec.fun.string;

import io.dingodb.expr.core.TypeCode;
import io.dingodb.expr.runtime.RtExpr;
import io.dingodb.expr.runtime.op.RtFun;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;

@Slf4j
public class LocateFun extends RtFun {
    public static final String NAME = "locate";
    private static final long serialVersionUID = -3160318945935927347L;

    /**
     * Create an DingoStringConcatOp. concat all input args.
     *
     * @param paras the parameters of the op
     */
    public LocateFun(RtExpr[] paras) {
        super(paras);
    }

    public static int locateString(final @NonNull String subString, final String inputStr) {
        if (subString.equals("")) {
            return 1;
        }

        if (inputStr == null || inputStr.equals("")) {
            return 0;
        } else {
            return inputStr.indexOf(subString) + 1;
        }
    }

    @Override
    protected Object fun(Object @NonNull [] values) {
        String subString = (String) (values[0]);
        String inputStr = (String) (values[1]);

        return locateString(subString, inputStr);
    }

    @Override
    public int typeCode() {
        return TypeCode.INT;
    }
}
