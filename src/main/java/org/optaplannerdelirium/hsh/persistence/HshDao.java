/*
 * Copyright 2014 JBoss Inc
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

package org.optaplannerdelirium.hsh.persistence;

import java.io.File;

import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.examples.common.persistence.AbstractSolutionDao;

public class HshDao extends AbstractSolutionDao {

    public HshDao() {
        super("hsh");
    }

    @Override
    public String getFileExtension() {
        return ".txt";
    }

    @Override
    public Solution readSolution(File file) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeSolution(Solution solution, File file) {
        throw new UnsupportedOperationException();
    }

}
