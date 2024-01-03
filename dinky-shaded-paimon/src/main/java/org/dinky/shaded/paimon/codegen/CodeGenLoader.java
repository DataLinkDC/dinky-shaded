/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.dinky.shaded.paimon.codegen;

import org.apache.paimon.codegen.CodeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class CodeGenLoader {

    public static CodeGenerator getCodeGenerator() {
        List<CodeGenerator> results = new ArrayList<>();
        ServiceLoader<CodeGenerator> drivers = ServiceLoader.load(CodeGenerator.class);
        for (CodeGenerator driver : drivers) {
            results.add(driver);
        }
        if (results.size() != 1) {
            throw new RuntimeException(
                    "Found "
                            + results.size()
                            + " classes implementing "
                            + CodeGenerator.class.getName()
                            + ". They are:\n"
                            + results.stream()
                            .map(t -> t.getClass().getName())
                            .collect(Collectors.joining("\n")));
        }
        return results.get(0);
    }
}
