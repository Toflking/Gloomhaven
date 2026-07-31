package io.github.toflking.gloomhaven.rules.execution;

import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexPosition;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class ExecutionMemory {
    private final Map<ExecutionVariableId, Object> values = new HashMap<>();
    private final Map<ExecutionVariableId, ValueKind> valueKinds =
        new HashMap<>();

    public ExecutionMemory() {
    }

    private ExecutionMemory(Snapshot snapshot) {
        snapshot.values.forEach((variable, value) ->
            values.put(variable, copyValue(value))
        );
        valueKinds.putAll(snapshot.valueKinds);
    }

    public Snapshot snapshot() {
        return new Snapshot(values, valueKinds);
    }

    public static ExecutionMemory restore(Snapshot snapshot) {
        return new ExecutionMemory(
            Objects.requireNonNull(snapshot, "snapshot")
        );
    }

    public void putInteger(
        ExecutionVariableId variable,
        int value
    ) {
        put(variable, value, ValueKind.INTEGER);
    }

    public int requireInteger(ExecutionVariableId variable) {
        return require(variable, Integer.class);
    }

    public void putFigure(
        ExecutionVariableId variable,
        FigureId value
    ) {
        put(variable, value, ValueKind.FIGURE);
    }

    public FigureId requireFigure(ExecutionVariableId variable) {
        return require(variable, FigureId.class);
    }

    public void putHex(
        ExecutionVariableId variable,
        HexPosition value
    ) {
        put(variable, value, ValueKind.HEX);
    }

    public HexPosition requireHex(ExecutionVariableId variable) {
        return require(variable, HexPosition.class);
    }

    public void putPath(
        ExecutionVariableId variable,
        List<HexPosition> value
    ) {
        put(variable, List.copyOf(value), ValueKind.PATH);
    }

    public void putHexes(
        ExecutionVariableId variable,
        Set<HexPosition> value
    ) {
        put(variable, Set.copyOf(value), ValueKind.HEX_SET);
    }

    public int count(ExecutionVariableId variable) {
        Object value = requireValue(variable);
        if (!(value instanceof Collection<?> collection)) {
            throw wrongType(variable, "Collection", value);
        }
        return collection.size();
    }

    @SuppressWarnings("unchecked")
    public List<HexPosition> requirePath(
        ExecutionVariableId variable
    ) {
        Object value = requireValue(variable);
        if (!(value instanceof List<?> list)
            || list.stream().anyMatch(item -> !(item instanceof HexPosition))) {
            throw wrongType(variable, "List<HexPosition>", value);
        }
        return (List<HexPosition>) list;
    }

    @SuppressWarnings("unchecked")
    public Set<HexPosition> requireHexes(
        ExecutionVariableId variable
    ) {
        Object value = requireValue(variable);
        if (!(value instanceof Set<?> set)
            || set.stream().anyMatch(item -> !(item instanceof HexPosition))) {
            throw wrongType(variable, "Set<HexPosition>", value);
        }
        return (Set<HexPosition>) set;
    }

    public boolean contains(ExecutionVariableId variable) {
        return values.containsKey(variable);
    }

    public void clear() {
        values.clear();
        valueKinds.clear();
    }

    private void put(
        ExecutionVariableId variable,
        Object value,
        ValueKind valueKind
    ) {
        Objects.requireNonNull(variable, "variable");
        Objects.requireNonNull(value, "value");
        Objects.requireNonNull(valueKind, "valueKind");

        ValueKind existingKind = valueKinds.get(variable);
        if (existingKind != null && existingKind != valueKind) {
            throw new IllegalStateException(
                "Execution variable "
                    + variable.value()
                    + " already contains "
                    + existingKind
                    + " and cannot contain "
                    + valueKind
            );
        }
        values.put(variable, value);
        valueKinds.put(variable, valueKind);
    }

    private <T> T require(
        ExecutionVariableId variable,
        Class<T> type
    ) {
        Object value = requireValue(variable);
        if (!type.isInstance(value)) {
            throw wrongType(variable, type.getSimpleName(), value);
        }
        return type.cast(value);
    }

    private Object requireValue(ExecutionVariableId variable) {
        Objects.requireNonNull(variable, "variable");
        Object value = values.get(variable);
        if (value == null) {
            throw new IllegalStateException(
                "Execution variable is not set: " + variable.value()
            );
        }
        return value;
    }

    private static IllegalStateException wrongType(
        ExecutionVariableId variable,
        String expected,
        Object actual
    ) {
        return new IllegalStateException(
            "Execution variable "
                + variable.value()
                + " must contain "
                + expected
                + " but contains "
                + actual.getClass().getSimpleName()
        );
    }

    private static Object copyValue(Object value) {
        return switch (value) {
            case List<?> list -> List.copyOf(list);
            case Set<?> set -> Set.copyOf(set);
            default -> value;
        };
    }

    public static final class Snapshot {
        private final Map<ExecutionVariableId, Object> values;
        private final Map<ExecutionVariableId, ValueKind> valueKinds;

        private Snapshot(
            Map<ExecutionVariableId, Object> values,
            Map<ExecutionVariableId, ValueKind> valueKinds
        ) {
            Map<ExecutionVariableId, Object> copiedValues =
                new HashMap<>();
            values.forEach((variable, value) ->
                copiedValues.put(variable, copyValue(value))
            );
            this.values = Map.copyOf(copiedValues);
            this.valueKinds = Map.copyOf(valueKinds);
        }
    }

    private enum ValueKind {
        INTEGER,
        FIGURE,
        HEX,
        PATH,
        HEX_SET
    }
}
