package com.vaibahv.sudoku_solve.controller;
import com.vaibahv.sudoku_solve.service.SolverService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class SudokuController {

    @Autowired
    private SolverService solverService;

    @PostMapping("/solve")
    public ResponseEntity<int[][]> solve(@RequestBody int[][] board)
    {
        boolean solved = solverService.solveBoard(board);
        return solved ? new ResponseEntity<>(board, HttpStatus.OK): new ResponseEntity("Cannot solve",HttpStatus.OK);
    }

}
