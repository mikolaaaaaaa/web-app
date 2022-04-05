package com.epam.webapp.service;

import com.epam.webapp.entity.Exercise;
import com.epam.webapp.entity.Program;
import com.epam.webapp.exception.ServiceException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class ProgramServiceTest {

    private static final ProgramService service = new ProgramService();

    @Test
    public void testShowProgram() throws ServiceException {
        Optional<Program> program = service.showClientProgram(1);
        program.ifPresent(System.out::println);
    }

    @Test
    public void testShowExercise() throws ServiceException {
        List<Exercise> exerciseList = service.showExercises(1);
        exerciseList.forEach(System.out::println);
    }

    @Test
    public void testShowFinishedClientPrograms() throws ServiceException {
        List<Program> programs = service.showFinishedClientPrograms(1);
        programs.forEach(System.out::println);
    }

    @Test
    public void testShowFinishedProgram() throws ServiceException {
        Optional<Program> program = service.showFinishedProgram(2);
        System.out.println(program.get());
    }
}
