package io.github.toflking.gloomhaven.scenarioscreen.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.toflking.gloomhaven.rules.card.AbilityCardId;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.card.CardHalf;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.command.*;
import io.github.toflking.gloomhaven.rules.content.cards.DebugCards;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioPhase;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class ScenarioHud implements Disposable {
    public static final String SKIN_ASSET =
        "ui/uiskin.json";

    private final Stage stage;
    private final Label phaseLabel;
    private final TextButton confirmButton;
    private final TextButton cancelButton;
    private final TextButton undoButton;
    private final TextButton skipButton;
    private final TextButton endTurnButton;
    private final TextButton manuallyFillButton;
    private final TextButton autoFillButton;
    private final List<TextButton> cardButtons = new ArrayList<>();

    private FigureId activeFigure;
    private ScenarioInteractionState interactionState =
        new IdleInteraction();

    public ScenarioHud(Skin skin, Consumer<GameCommand> commandHandler, Consumer<SelectionOption> selectionOptionHandler) {
        stage = new Stage(new ScreenViewport());

        phaseLabel = new Label("", skin);

        confirmButton = new TextButton("Confirm", skin, "scenario-action");
        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (activeFigure == null) {
                    return;
                }

                switch (interactionState) {
                    case IdleInteraction idle -> {}
                    case MovePathInteraction movement ->
                        commandHandler.accept(new ChooseMovePathCommand(movement.getPath()));
                    case AttackTargetInteraction targeting -> {
                        if (targeting.getSelectionMode() == TargetSelectionMode.FIXED && !targeting.areAllEligibleTargetsSelected()) {
                            return;
                        }
                        commandHandler.accept(new ChooseFigureTargetsCommand(targeting.getSelectedTargets()));
                    }
                    case HealTargetInteraction targeting -> {
                        if (targeting.getSelectionMode() == TargetSelectionMode.FIXED && !targeting.areAllEligibleTargetsSelected()) {
                            return;
                        }
                        commandHandler.accept(new ChooseFigureTargetsCommand(targeting.getSelectedTargets()));
                    }
                    case AttackAreaPlacementInteraction area -> {
                        if (!area.canConfirm()) {
                            return;
                        }
                        commandHandler.accept(new ChooseAttackAreaCommand(
                            area.currentPlacement().option()
                        ));
                    }
                    case HealAreaPlacementInteraction area -> {
                        if (!area.canConfirm()) {
                            return;
                        }
                        commandHandler.accept(new ChooseAttackAreaCommand(
                            area.currentPlacement().option()
                        ));
                    }
                }
            }
        });

        cancelButton = new TextButton("Cancel", skin, "scenario-action");
        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (activeFigure == null) {
                    return;
                }

                switch (interactionState) {
                    case IdleInteraction idle -> {}
                    case MovePathInteraction movement -> {
                        if (movement.getPath().isEmpty()) {
                            commandHandler.accept(new CancelActionCommand());
                        } else {
                            movement.setPath(List.of());
                        }
                    }
                    case AttackTargetInteraction targeting -> {
                        if (targeting.getSelectionMode()
                            == TargetSelectionMode.FIXED
                            || targeting.getSelectedTargets().isEmpty()) {
                            commandHandler.accept(new CancelActionCommand());
                        } else {
                            targeting.getSelectedTargets().clear();
                        }
                    }
                    case HealTargetInteraction targeting -> {
                        if (targeting.getSelectionMode()
                            == TargetSelectionMode.FIXED
                            || targeting.getSelectedTargets().isEmpty()) {
                            commandHandler.accept(new CancelActionCommand());
                        } else {
                            targeting.getSelectedTargets().clear();
                        }
                    }
                    case AttackAreaPlacementInteraction ignored ->
                        commandHandler.accept(new CancelActionCommand());
                    case HealAreaPlacementInteraction ignored ->
                        commandHandler.accept(new CancelActionCommand());
                }
            }
        });

        undoButton = new TextButton("Undo", skin, "scenario-action");
        undoButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                commandHandler.accept(new UndoCommand());
            }
        });

        skipButton = new TextButton("Skip", skin, "scenario-action");
        skipButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                commandHandler.accept(new SkipActionCommand());
            }
        });

        endTurnButton = new TextButton("End turn", skin, "scenario-action");
        endTurnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (activeFigure == null) {
                    return;
                }

                commandHandler.accept(
                    new EndTurnCommand(activeFigure)
                );
            }
        });

        manuallyFillButton = new TextButton("Fill Manually", skin, "scenario-action");
        manuallyFillButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!(interactionState instanceof AttackTargetInteraction targeting)) return;
                targeting.getSelectedTargets().clear();
                selectionOptionHandler.accept(SelectionOption.MANUAL);
            }
        });

        autoFillButton = new TextButton("Auto Fill", skin, "scenario-action");
        autoFillButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!(interactionState instanceof AttackTargetInteraction targeting)) return;
                targeting.replaceSelectedTargets(targeting.getEligibleTargets().stream().toList());
                selectionOptionHandler.accept(SelectionOption.AUTOMATIC);
            }
        });


        Table root = new Table();
        root.setFillParent(true);
        root.top().left();
        root.pad(20f);

        root.add(phaseLabel);
        root.row();
        root.add(endTurnButton).padTop(10f);
        root.add(undoButton).padTop(10f);
        root.row();
        root.add(confirmButton).padTop(10f);
        root.add(cancelButton).padTop(10f);
        root.add(skipButton).padTop(10f);
        root.row();
        root.add(manuallyFillButton).padTop(10f);
        root.add(autoFillButton).padTop(10f);
        root.row();

        Table movementButtons = new Table();
        for (int movement = 1; movement <= 4; movement++) {
            movementButtons.add(
                createCardButton(
                    skin,
                    commandHandler,
                    "Move " + movement,
                    new AbilityCardId("debug-move-" + movement)
                )
            ).padRight(5f);
        }
        root.add(movementButtons).padTop(10f);
        root.row();

        Table attackButtons = new Table();
        attackButtons.add(createCardButton(
            skin,
            commandHandler,
            "Attack 2 R2",
            DebugCards.ATTACK_2_RANGE_2
        )).padRight(5f);
        attackButtons.add(createCardButton(
            skin,
            commandHandler,
            "Attack 3 R3 T2",
            DebugCards.ATTACK_3_RANGE_3_TARGET_2
        )).padRight(5f);
        attackButtons.add(createCardButton(
            skin,
            commandHandler,
            "Attack 2 R3 =2",
            DebugCards.ATTACK_2_RANGE_3_EXACT_2
        )).padRight(5f);
        attackButtons.add(createCardButton(
            skin,
            commandHandler,
            "Attack 1 All Adj",
            DebugCards.ATTACK_1_ALL_ADJACENT
        )).padRight(5f);
        attackButtons.add(createCardButton(
            skin,
            commandHandler,
            "Attack 2 Area R3",
            DebugCards.ATTACK_2_AREA_RANGE_3
        )).padRight(5f);
        attackButtons.add(createCardButton(
            skin,
            commandHandler,
            "Attack 1 Area Self",
            DebugCards.ATTACK_1_AREA_AROUND_ORIGIN
        ));
        root.add(attackButtons).padTop(5f);
        root.row();
        root.add(createCardButton(
            skin,
            commandHandler,
            "Move 2 -> Attack 3 R3",
            DebugCards.MOVE_2_ATTACK_3
        )).padTop(5f);

        stage.addActor(root);
    }

    private TextButton createCardButton(
        Skin skin,
        Consumer<GameCommand> commandHandler,
        String label,
        AbilityCardId cardId
    ) {
        TextButton button = new TextButton(
            label,
            skin,
            "scenario-action"
        );
        cardButtons.add(button);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(
                ChangeListener.ChangeEvent event,
                Actor actor
            ) {
                if (activeFigure == null) {
                    return;
                }

                commandHandler.accept(
                    new PlayCardActionCommand(
                        new AbilitySource(
                            cardId,
                            CardHalf.TOP
                        )
                    )
                );
            }
        });

        return button;
    }

    public void update(
        ScenarioState state,
        ScenarioInteractionState interactionState,
        boolean canUndo
    ) {
        activeFigure = state.activeFigure();
        this.interactionState = interactionState;

        phaseLabel.setText(
            "Phase: " + state.phase()
        );

        confirmButton.setDisabled(
            state.phase() != ScenarioPhase.TURN_EXECUTION
                || activeFigure == null
        );

        endTurnButton.setDisabled(
            state.phase() != ScenarioPhase.TURN_EXECUTION
                || activeFigure == null
        );
        undoButton.setDisabled(!canUndo);

        boolean actionCanStart =
            state.phase() == ScenarioPhase.TURN_EXECUTION
                && activeFigure != null
                && interactionState instanceof IdleInteraction;

        cardButtons.forEach(button ->
            button.setDisabled(!actionCanStart)
        );

        switch (interactionState) {
            case IdleInteraction ignored -> {
                confirmButton.setDisabled(true);
                confirmButton.setVisible(false);
                cancelButton.setDisabled(true);
            }
            case MovePathInteraction ignored -> {
                confirmButton.setDisabled(false);
                confirmButton.setVisible(true);
                cancelButton.setDisabled(false);
                confirmButton.setText("Confirm Movement");
            }
            case AttackTargetInteraction targeting -> {
                confirmButton.setDisabled(false);
                confirmButton.setVisible(true);
                cancelButton.setDisabled(false);
                confirmButton.setText("Confirm Targets: " + targeting.getSelectedTargets().size() + "/" + targeting.getMaximumTargets());
            }
            case HealTargetInteraction targeting -> {
                confirmButton.setDisabled(false);
                confirmButton.setVisible(true);
                cancelButton.setDisabled(false);
                confirmButton.setText("Confirm Heal Targets: " + targeting.getSelectedTargets().size() + "/" + targeting.getMaximumTargets());
            }
            case AttackAreaPlacementInteraction area -> {
                confirmButton.setDisabled(!area.canConfirm());
                confirmButton.setVisible(true);
                cancelButton.setDisabled(false);
                confirmButton.setText(
                    area.canConfirm()
                        ? "Confirm Area"
                        : "Place Area"
                );
            }
            case HealAreaPlacementInteraction area -> {
                confirmButton.setDisabled(!area.canConfirm());
                confirmButton.setVisible(true);
                cancelButton.setDisabled(false);
                confirmButton.setText(
                    area.canConfirm()
                        ? "Confirm Heal Area"
                        : "Place Heal Area"
                );
            }
        }
    }

    public void render(float delta) {
        stage.getViewport().apply();
        stage.act(delta);
        stage.draw();
    }

    public Stage stage() {
        return stage;
    }

    public void resize(int width, int height) {
        stage.getViewport().update(
            width,
            height,
            true
        );
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
