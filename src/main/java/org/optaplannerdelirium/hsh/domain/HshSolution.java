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

package org.optaplannerdelirium.hsh.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.examples.common.domain.AbstractPersistable;

@PlanningSolution
public class HshSolution extends AbstractPersistable implements Solution<HardSoftScore> {

    private List<Elf> elfList;

    private List<Toy> toyList;

    private HardSoftScore score;

    @ValueRangeProvider(id = "elfRange")
    public List<Elf> getElfList() {
        return elfList;
    }

    public void setElfList(List<Elf> elfList) {
        this.elfList = elfList;
    }

    @PlanningEntityCollectionProperty
    public List<Toy> getToyList() {
        return toyList;
    }

    public void setToyList(List<Toy> toyList) {
        this.toyList = toyList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public Collection<? extends Object> getProblemFacts() {
        List<Object> facts = new ArrayList<Object>();
        facts.addAll(elfList);
        // Do not add the planning entity's (processList) because that will be done automatically
        return facts;
    }

}
