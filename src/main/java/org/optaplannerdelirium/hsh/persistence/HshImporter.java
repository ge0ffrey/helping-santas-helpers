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

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.examples.common.persistence.AbstractTxtSolutionImporter;
import org.optaplanner.examples.curriculumcourse.domain.Course;
import org.optaplanner.examples.curriculumcourse.domain.CourseSchedule;
import org.optaplanner.examples.curriculumcourse.domain.Curriculum;
import org.optaplanner.examples.curriculumcourse.domain.Day;
import org.optaplanner.examples.curriculumcourse.domain.Lecture;
import org.optaplanner.examples.curriculumcourse.domain.Period;
import org.optaplanner.examples.curriculumcourse.domain.Room;
import org.optaplanner.examples.curriculumcourse.domain.Teacher;
import org.optaplanner.examples.curriculumcourse.domain.Timeslot;
import org.optaplanner.examples.curriculumcourse.domain.UnavailablePeriodPenalty;
import org.optaplannerdelirium.hsh.domain.Elf;
import org.optaplannerdelirium.hsh.domain.HshSolution;
import org.optaplannerdelirium.hsh.domain.Toy;

public class HshImporter extends AbstractTxtSolutionImporter {

    private static final String INPUT_FILE_SUFFIX = "csv";

    public static void main(String[] args) {
        new HshImporter().convertAll();
    }

    public HshImporter() {
        super(new HshDao());
    }

    @Override
    public String getInputFileSuffix() {
        return INPUT_FILE_SUFFIX;
    }

    public TxtInputBuilder createTxtInputBuilder() {
        return new HshInputBuilder();
    }

    public static class HshInputBuilder extends TxtInputBuilder {

        private final static int ELF_SIZE = 900;

        private HshSolution solution;

        public Solution readSolution() throws IOException {
            solution = new HshSolution();
            solution.setId(0L);
            readToyList();
            createElfList();

//            BigInteger possibleSolutionSize = BigInteger.valueOf(solution.getElfList().size()).pow(
//                    solution.getToyList().size());
            logger.info("Hsh {} has {} elves and {} toys with a search space of {}.",
                    getInputId(),
                    solution.getElfList().size(),
                    solution.getToyList().size(),
                    "unknown");
//                    getFlooredPossibleSolutionSize(possibleSolutionSize));
            return solution;
        }

        private void readToyList() throws IOException {
            List<Toy> toyList = new ArrayList<Toy>(10000000);
            readConstantLine("ToyId,Arrival_time,Duration");
            String line = bufferedReader.readLine();
            int maxDuration = -1;
            while (line != null && !line.isEmpty()) {
                Toy toy = new Toy();
                String[] lineTokens = splitByCommaAndTrim(line, 3);
                int id = Integer.parseInt(lineTokens[0]);
                toy.setId((long) id);
                toy.setIndex(id - 1);
//                toy.setArrivalDay();
//                toy.setArrivalTime();
                int duration = Integer.parseInt(lineTokens[2]);
                if (duration > maxDuration) {
                    maxDuration = duration;
                }
                toy.setDuration(duration);
                toyList.add(toy);
                line = bufferedReader.readLine();
                if (id % 100000 == 0) {
                    logger.debug("Read {} toys.", id);
                }
            }
            solution.setToyList(toyList);
            logger.info("  Max duration is {} minutes.", maxDuration);
        }

        private void createElfList() {
            List<Elf> elfList = new ArrayList<Elf>(ELF_SIZE);
            for (int i = 0; i < ELF_SIZE; i++) {
                Elf elf = new Elf();
                elf.setId((long) (i + 1));
                elfList.add(elf);
            }
            solution.setElfList(elfList);
        }

    }

}
