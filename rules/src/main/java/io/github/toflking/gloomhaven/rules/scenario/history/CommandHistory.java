package io.github.toflking.gloomhaven.rules.scenario.history;

import io.github.toflking.gloomhaven.rules.command.GameCommand;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Optional;

public final class CommandHistory {
    private final Deque<Entry> entries = new ArrayDeque<>();

    public void record(
        GameCommand command,
        ScenarioSnapshot stateBefore
    ) {
        entries.push(new Entry(command, stateBefore));
    }

    public Optional<Entry> popLatest() {
        return entries.isEmpty()
            ? Optional.empty()
            : Optional.of(entries.pop());
    }

    public boolean canUndo() {
        return !entries.isEmpty();
    }

    public int size() {
        return entries.size();
    }

    public record Entry(
        GameCommand command,
        ScenarioSnapshot stateBefore
    ) {

        public Entry {
            Objects.requireNonNull(command, "command");
            Objects.requireNonNull(stateBefore, "stateBefore");
        }
    }
}
