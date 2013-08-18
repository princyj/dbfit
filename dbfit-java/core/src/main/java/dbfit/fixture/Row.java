package dbfit.fixture;

import dbfit.util.*;
import fit.Fixture;
import fit.Parse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Row {
    private DbParameterAccessors accessors;
    private Parse fitRow;
    private Fixture parentFixture;

    public Row(DbParameterAccessors accessors, Parse fitRow, Fixture parentFixture) {
        this.accessors = accessors;
        this.fitRow = fitRow;
        this.parentFixture = parentFixture;
    }

    public List<Cell> getInputCells() {
        List<Cell> cells = new ArrayList<Cell>();
        Map<DbParameterAccessor, Parse> cellMap = accessors.zipWith(DbObjectExecutionFixture.FitHelpers.asCellList(fitRow));
        for (DbParameterAccessor inputAccessor : accessors.getInputAccessors()) {
            Parse cell = cellMap.get(inputAccessor);
            cells.add(new Cell(inputAccessor, cell, parentFixture));
        }
        return cells;
    }

    public List<Cell> getOutputCells() {
        List<Cell> cells = new ArrayList<Cell>();
        Map<DbParameterAccessor, Parse> cellMap = accessors.zipWith(DbObjectExecutionFixture.FitHelpers.asCellList(fitRow));
        for (DbParameterAccessor outputAccessor : accessors.getOutputAccessors()) {
            Parse cell = cellMap.get(outputAccessor);
            cells.add(new Cell(outputAccessor, cell, parentFixture));
        }
        return cells;
    }

    public TestResultHandler getTestResultHandler() {
        return new DbFitActionResultHandler(fitRow, parentFixture);
    }
}