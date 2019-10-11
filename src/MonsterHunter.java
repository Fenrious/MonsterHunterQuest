import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This program creates an interactable Monster Hunter adventure using Java GUI. This particular class
 * handles the bulk of the overall project folder.
 *
 * Date Last Modified: 10 / 10 / 2019
 *
 * @author Shirley Krogel
 */

public class MonsterHunter extends Application {

     // Instance Variables
    private Stage stage;

    private Hunter hunter = null;
    private GiantMonster questMonster = null;
    private Monster targetMonster = null;
    private Pathing areaMap = null;
    private int startingLocation;
    private Scene currentScene;

    private int spawnTimer = 0;

    private ArrayList<Monster> currentMosters = new ArrayList<>();
    private ArrayList<Integer> openLocations = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    private URL titleScreenMusic = getClass().getResource("/titleScreen.mp3");
    private URL passiveMusic = getClass().getResource("/passive.mp3");
    private URL combatMusic = getClass().getResource("/fightTheme.mp3");
    private URL defeatMusic = getClass().getResource("/defeatTheme.mp3");
    private URL victoryMusic = getClass().getResource("/victoryTheme.mp3");

    private MediaPlayer backgroundTitle = new MediaPlayer(new Media(titleScreenMusic.toString()));
    private MediaPlayer backgroundPassive = new MediaPlayer(new Media(passiveMusic.toString()));
    private MediaPlayer backgroundCombat = new MediaPlayer(new Media(combatMusic.toString()));
    private MediaPlayer backgroundDefeat = new MediaPlayer(new Media(defeatMusic.toString()));
    private MediaPlayer backgroundVictory = new MediaPlayer(new Media(victoryMusic.toString()));

    private Text title = new Text("Monster Hunter Quest");
    private StackPane titlePane = new StackPane();
    private Image titleScreenImage = new Image("/MonsterHunterTitleBackground.png");
    private ImageView titleScreenImageView = new ImageView(titleScreenImage);
    private Button play = new Button("Start Your Quest");
    private Button help = new Button("Look at the Monster Hunting Guidebook");
    private Button option = new Button("Change Your Settings");
    private Button quit = new Button("Exit the Game");
    private Button huntAgain = new Button("Hunt Another Monster");

    private Image defeatImage = new Image("/DefeatBackground.png");
    private Image victoryImage = new Image("/VictoryBackground.png");

    private Text smithyTitle = new Text("The Smithy");
    private Image forgingImage = new Image("/Forging.png");
    private ImageView forgingBackground = new ImageView(forgingImage);
    private HBox upgradeOptions = new HBox();
    private Button leftUpgrade = new Button();
    private Button middleUpgrade = new Button();
    private Button rightUpgrade = new Button();
    private Button continueOn = new Button("Continue without upgrading.");

    private Button craftingHelp = new Button("Crafting");
    private Button gatheringHelp = new Button("Gathering");
    private Button hunterHelp = new Button("Hunter");
    private Button monsterHelp = new Button("Monsters");
    private Button weaponHelp = new Button("Weapons");
    private Button cancelHelp = new Button("Back to Main Menu");

    private Text enterName = new Text("Enter the Name of Your Hunter: ");
    private TextField hunterName = new TextField();
    private Text chooseWeapon = new Text("Select Your Weapon of Choice:");
    private Button hammerButton = new Button("Hammer");
    private Text hammerDescription = new Text("The Hammer is a powerful weapon with a chance to stun the monster " +
            "when it hits.");
    private Button dualBladesButton = new Button("Dual Blades");
    private Text dualBladesDescription = new Text("The Dual Blades are fast with a large chance to hit multiple " +
            "times, but the damage of their individual hits is below average.");
    private Button swordAndShieldButton = new Button("Sword and Shield");
    private Text swordAndShieldDescription = new Text("The Sword and Shield has average damage with an average " +
            "chance to strike multiple times. It isn't as fast as Dual Blades, but it unlocks the ability to " +
            "guard against attacks.");

    private Text chooseMonster = new Text("Select the monster you want to fight:");
    private Button greatJagrasButton = new Button("Great Jagras");
    private Text greatJagrasDescription = new Text("The monster for beginners. Fight this monster to learn the " +
            "basics of Monster Hunter.");
    private Button jyuratodusButton = new Button("Jyuratodus");
    private Text jyuratodusDescription = new Text("A slightly more advanced monster that specializes in mud-based " +
            "attacks.");

    private Text chooseDifficulty = new Text("Choose a difficulty:");
    private Button easy = new Button("Easy Mode");
    private Text easyDescription = new Text("Monster has 75% of its normal health and damage.");
    private Button normal = new Button("Normal Mode");
    private Text normalDescription = new Text("Monster has 100% of its normal health and damage.");
    private Button hard = new Button("Hard Mode");
    private Text hardDescription = new Text("Monster has 150% of its normal health and damage.");
    private Button challenge = new Button("Challenge Mode");
    private Text challengeDescription = new Text("Monster has 175% of its normal health and damage, and it has " +
            "an additional mechanic.");

    private Text chooseMap = new Text("Choose the area to fight your monster in:");
    private Button tigerIslandButton = new Button("Wyspa Tygrysów");
    private Text tigerIslandDescription = new Text("A large island with varied biomes that can host a large " +
            "variety of monsters.");

    private ImageView currentBackground = new ImageView();
    private int turnsPassed = 0;
    private HBox hudViewer = new HBox();
    private VBox sharpHUD = new VBox();
    private Text sharpHeading = new Text("Sharpness:");
    private Text sharpnessLevel = new Text("Sharp");
    private VBox logViewer = new VBox();
    private Text passageOne = new Text();
    private Text passageTwo = new Text();
    private Text passageThree = new Text();
    private Text areaTitle = new Text();
    private HBox gatherViewer = new HBox();
    private HBox optionViewer = new HBox();
    private VBox passiveVBox = new VBox();
    private VBox inventoryVBox = new VBox();
    private VBox combatVBox = new VBox();
    private HBox statusViewer = new HBox();
    private Text staminaViewer = new Text();
    private Text healthViewer = new Text();
    private VBox monsterVBox = new VBox();
    private ImageView monsterView = new ImageView();
    private HBox maneuverHBox = new HBox();

    private StackPane areaPane = new StackPane();
    private Button area1 = new Button("To Area 1");
    private Button area2 = new Button("To Area 2");
    private Button area3 = new Button("To Area 3");
    private Button area4 = new Button("To Area 4");
    private Button area5 = new Button("To Area 5");
    private Button area6 = new Button("To Area 6");
    private Button area7 = new Button("To Area 7");
    private Button area8 = new Button("To Area 8");
    private Button area9 = new Button("To Area 9");

    private Button attack = new Button("Attack");
    private Button guard = new Button("Guard");
    private Button sharpen = new Button("Sharpen Weapon");
    private Button escape = new Button("Attempt Escape");

    private VBox rightVBox = new VBox();
    private VBox leftVBox = new VBox();
    private Button stepRight = new Button("Step Right");
    private Button stepLeft = new Button("Step Left");
    private Button dashRight = new Button("Dash Right");
    private Button dashLeft = new Button("Dash Left");

    private Button gather = new Button("Gather");
    private ImageView slot1View = new ImageView();
    private ImageView slot2View = new ImageView();
    private ImageView slot3View = new ImageView();
    private ImageView slot4View = new ImageView();
    private Button gatherSlot1 = new Button();
    private Button gatherSlot2 = new Button();
    private Button gatherSlot3 = new Button();
    private Button gatherSlot4 = new Button();
    private Button exitGather = new Button("Stop Gathering");

    private Button crafting = new Button("Start Crafting");
    private Button craftPotion = new Button("Craft a Potion (Requires: Herb (0) & Blue Mushroom (0))");
    private Button craftMegaPotion = new Button("Craft a Mega Potion (Requires: Potion (0) & Honey (0))");
    private Button craftAntidote = new Button("Craft an Antidote (Requires: Antidote Flower (0) & " +
            "Blue Mushroom (0))");
    private Button cookMeat = new Button("Cook a steak (Requires: Raw Meat (0))");
    private Button craftBandage = new Button("Craft a Bandage (Requires: 2 Herbs (0))");
    private Button exitCrafting = new Button("Stop Crafting");

    private Text locationTitle = new Text();
    private Button map = new Button("Check the Map");
    private Image mapImage = new Image("/TigerIslandMapCompass.png");
    private ImageView mapView = new ImageView(mapImage);
    private HBox neighbors = new HBox();
    private Button exitMap = new Button("Exit Map View");

    private Button passiveSharp = new Button("Sharpen Weapon");

    private Button herb = new Button();
    private Button potion = new Button();
    private Button megaPotion = new Button();
    private Button antidote = new Button();
    private Button cookedMeat = new Button();
    private Button bandage = new Button();

    private Text endTitle = new Text();
    private Text turns = new Text();
    private Text totalDamage = new Text();
    private Text endPassage = new Text();
    private Button toMenu = new Button("Stop Hunting?");
    private VBox endVBox = new VBox();
    private ImageView endBackground = new ImageView();
    private StackPane endPane = new StackPane();

    // Getter methods. Setters are located within the items, so no need for any setters in this class.
    public Hunter getHunter() {
        return hunter;
    }

    public GiantMonster getQuestMonster() {
        return questMonster;
    }

    public Monster getTargetMonster() {
        return targetMonster;
    }

    public ArrayList<Integer> getOpenLocations() {
        return openLocations;
    }

    public Text getPassageOne() {
        return passageOne;
    }

    public Pathing getAreaMap() {
        return areaMap;
    }

    /**
     * Provides a quick way to format buttons for the title screen.
     *
     * @param button The button to be formatted.
     */
    private void titleButtonFormatting(Button button) {
        button.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        button.prefWidthProperty().bind(titlePane.widthProperty().divide(2));
        button.prefHeightProperty().bind(titlePane.heightProperty().divide(10));
        button.setWrapText(true);
    }

    /**
     * Provides a quick way to format a button for out-of-game menus.
     *
     * @param button The button to be formatted.
     */
    private void standardButtonFormatting(Button button, StackPane pane) {
        button.setAlignment(Pos.CENTER);
        button.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        button.prefWidthProperty().bind(pane.widthProperty().divide(4));
        button.prefHeightProperty().bind(pane.heightProperty().divide(10));
        button.setWrapText(true);
    }

    /**
     * Provides a quick way to format the in-game buttons.
     *
     * @param button The button to be formatted.
     */
    private void gameButtonFormatting(Button button, StackPane pane) {
        button.setAlignment(Pos.CENTER);
        button.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
        button.prefWidthProperty().bind(pane.widthProperty().divide(4));
        button.prefHeightProperty().bind(pane.heightProperty().divide(25));
        button.setWrapText(true);
    }

    /**
     * Provides a quick way to bind an image to a background.
     *
     * @param iv The ImageView of the desired image.
     * @param pane The background to which the image will stick.
     */
    private void bindBackground(ImageView iv, StackPane pane) {
        pane.getChildren().add(iv);
        iv.fitWidthProperty().bind(pane.widthProperty());
        iv.fitHeightProperty().bind(pane.heightProperty());
        iv.toBack();
    }

    /**
     * Updates the in-game text at the top of the screen. Tosses old values to the next text file, then sets the
     * text of the first passage to newEntry.
     *
     * @param newEntry New text to be printed at the top of the screen.
     */
    public void updateLog(String newEntry) {
        passageThree.setText(passageTwo.getText());
        passageTwo.setText(passageOne.getText());
        passageOne.setText(newEntry);
    }

    /**
     * Checks all of the things that happen during a turn in order to provide up-to-date information when in the game.
     */
    private void updateStatus() {
        // Weapon Sharpness
        sharpnessLevel.setText(hunter.getWeapon().getSharpLevel());
        if (hunter.getWeapon().getSharpLevel().equals("Sharp")) {
            sharpnessLevel.setFill(Color.GREEN);
        } else if (hunter.getWeapon().getSharpLevel().equals("Dull")) {
            sharpnessLevel.setFill(Color.GOLDENROD);
        } else {
            sharpnessLevel.setFill(Color.RED);
        }
        // Health and Stamina
        staminaViewer.setText("Stamina: " + hunter.getStamina() + "/" + hunter.getMaxStamina());
        if (hunter.isPoisoned()) {
            healthViewer.setFill(Color.PURPLE);
        } else if (hunter.isBurning()) {
            healthViewer.setFill(Color.ORANGE);
        } else if (hunter.isBleeding()) {
            healthViewer.setFill(Color.DARKRED);
        } else if (hunter.isMuddy()) {
            healthViewer.setFill(Color.SANDYBROWN);
        } else {
            healthViewer.setFill(Color.BLACK);
        }
        healthViewer.setText("Health: " + hunter.getHp() + "/" + hunter.getMaxHp());
        // Inventory
        herb.setText(hunter.getInventory().getItem("Herb").toString());
        potion.setText(hunter.getInventory().getItem("Potion").toString());
        megaPotion.setText(hunter.getInventory().getItem("Mega Potion").toString());
        antidote.setText(hunter.getInventory().getItem("Antidote").toString());
        cookedMeat.setText(hunter.getInventory().getItem("Cooked Meat").toString());
        bandage.setText(hunter.getInventory().getItem("Bandage").toString());
        // Craftables
        craftPotion.setText("Craft a Potion (Requires: " + hunter.getInventory().getItem("Herb").toString() +
                " & " + hunter.getInventory().getItem("Blue Mushroom").toString() + ")");
        craftMegaPotion.setText("Craft a Mega Potion (Requires: " + hunter.getInventory().getItem
                ("Potion").toString() + " & " + hunter.getInventory().getItem("Honey").toString()
                + ")");
        craftAntidote.setText("Craft an Antidote (Requires: " + hunter.getInventory().getItem("Antidote Flower")
                .toString() + " & " + hunter.getInventory().getItem("Blue Mushroom").toString() + ")");
        cookMeat.setText("Cook a steak (Requires: " + hunter.getInventory().getItem("Raw Meat").toString() +
                " & " + hunter.getInventory().getItem("Herb").toString() + ")");
        craftBandage.setText("Craft a Bandage (Requires: 2 " + hunter.getInventory().getItem("Herb").toString() +
                ")");
        // Misc.
        locationTitle.setText("Currently in Area " + hunter.getCurrentLocation() + ".");
        checkVictoryDefeat();
        slot1View.setImage(areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot1().getIcon());
        slot2View.setImage(areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot2().getIcon());
        slot3View.setImage(areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot3().getIcon());
    }

    /**
     * Checks to see if the monster found you while you were crafting or gathering. Makes sure that you don't try
     * to kill him by crafting potions in his face.
     */
    private void checkInterruption() {
        if (hunter.getGathering() && targetMonster != null) {
            optionViewer.getChildren().clear();
            optionViewer.getChildren().addAll(combatVBox, inventoryVBox);
            optionViewer.setAlignment(Pos.BASELINE_CENTER);
            hunter.setGathering(false);
            updateLog(hunter.getName() + " was interrupted while gathering!");
            backgroundPassive.pause();
            backgroundCombat.seek(Duration.ZERO);
            backgroundCombat.play();
        }
        if (hunter.getCrafting() && targetMonster != null) {
            optionViewer.getChildren().clear();
            optionViewer.getChildren().addAll(combatVBox, inventoryVBox);
            optionViewer.setAlignment(Pos.BASELINE_CENTER);
            hunter.setCrafting(false);
            updateLog(hunter.getName() + " was interrupted while crafting!");
            backgroundPassive.pause();
            backgroundCombat.seek(Duration.ZERO);
            backgroundCombat.play();
        }
    }

    /**
     * Checks if the monster is with you or has left you. Majorly important for updating when the monster avatar is
     * on-screen and when to play the fight or passive music.
     */
    private void checkMonster() {
        if (areaPane.getChildren().contains(monsterVBox) &&
                areaMap.getZone(hunter.getCurrentLocation() - 1).getMonster() == null) {
            targetMonster = null;
            optionViewer.getChildren().clear();
            areaPane.getChildren().remove(monsterVBox);
            optionViewer.getChildren().addAll(passiveVBox, inventoryVBox);
            backgroundCombat.pause();
            backgroundPassive.seek(Duration.ZERO);
            backgroundPassive.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundPassive.play();
        }
        if (!areaPane.getChildren().contains(monsterVBox) &&
                areaMap.getZone(hunter.getCurrentLocation() - 1).getMonster() != null) {
            targetMonster = areaMap.getZone(hunter.getCurrentLocation() - 1).getMonster();
            optionViewer.getChildren().clear();
            areaPane.getChildren().add(monsterVBox);
            monsterVBox.toFront();
            hunter.setPosition("Front");
            optionViewer.getChildren().addAll(combatVBox, inventoryVBox);
            optionViewer.toFront();
            statusViewer.toFront();
            monsterView.setImage(targetMonster.getCurrentSide(hunter.getPosition()));
            backgroundPassive.pause();
            backgroundCombat.seek(Duration.ZERO);
            backgroundCombat.play();
        }
    }

    /**
     * Checks if a monster is already on the map.
     *
     * @param name Name of the monster.
     * @return Truth value of the monster existing on the map.
     */
    private boolean containsMonster(String name) {
        for (int i = 0; i < areaMap.getZones().size(); i++) {
            if (areaMap.getZone(i).getMonster() != null && areaMap.getZone(i).getMonster().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * It's important to have win and loss conditions. Checks whenever it can to ensure no delays in victory or defeat.
     * Also dresses up the generic end screen with text and a background befitting the situation.
     */
    private void checkVictoryDefeat() {
        // Defeat
        if (hunter.getHp() <= 0) {
            endTitle.setText("Defeated");
            turns.setText("Turns before " + hunter.getName() + " was defeated: " + turnsPassed);
            totalDamage.setText(hunter.getName() + " dealt a total of " + hunter.getWeapon().getTotalDamage() + " " +
                    "damage before they were defeated.");
            endPassage.setText(hunter.getName() + " fell to the ground before they could complete their quest. " +
                    "They are hauled back to safety to lick their wounds. The " + questMonster.getName() + " will " +
                    "likely take this time to rest up as well. A rematch seems to be on the horizon.");
            endBackground.setImage(defeatImage);
            backgroundPassive.pause();
            backgroundCombat.pause();
            backgroundDefeat.seek(Duration.ZERO);
            backgroundDefeat.play();
            currentScene.setRoot(endPane);
        } else if (questMonster.getHP() <= 0) { // Victory
            endTitle.setText("Victory");
            turns.setText("Turns before monster was defeated: " + turnsPassed + " | Size: " + questMonster.getSize() +
                    " cm.");
            totalDamage.setText("The " + questMonster.getName() + " dealt a total of " + questMonster.getTotalDamage()
                    + " damage before it was slain.");
            endPassage.setText("With a final hit, the " + questMonster.getName() + " falls to the ground with a " +
                    "large *thud*. Today, victory belongs to " + hunter.getName() + ". They should get some well" +
                    "-earned rest before tomorrow. There will be plenty more monsters out there to face. Some far " +
                    "more dangerous than the one faced today.");
            endBackground.setImage(victoryImage);
            endVBox.setAlignment(Pos.TOP_CENTER);
            backgroundPassive.pause();
            backgroundCombat.pause();
            backgroundVictory.seek(Duration.ZERO);
            backgroundVictory.play();
            if (!hunter.getKnownMonsters().contains(questMonster.getName())) {
                hunter.getKnownMonsters().add(questMonster.getName());
            }
            currentScene.setRoot(endPane);
        }
    }

    /**
     * Stops all sound. Helps to keep multiple track from playing when starting another hunt.
     */
    private void stopAllSound() {
        backgroundPassive.pause();
        backgroundCombat.pause();
        backgroundVictory.pause();
        backgroundDefeat.pause();
        backgroundTitle.pause();
    }

    /**
     * Checks if Victory or Defeat happened during a transition between passive and combat. Pauses the additional
     * audio track.
     */
    private void checkLeftoverSound() {
        if (currentScene.getRoot() == endPane) {
            backgroundPassive.pause();
            backgroundCombat.pause();
        }
    }

    /**
     * Checks if another monster should spawn in. Monsters are selected from the hunter's known list of monsters.
     * Any giant monsters spawn will just be normal difficulty.
     */
    private void checkSpawnTimer() {
        if (!openLocations.isEmpty()) {
            if (spawnTimer >= 100) {
                int randomMonster = (int) (Math.random() * hunter.getKnownMonsters().size());
                int randomLocation = (int) (Math.random() * openLocations.size());
                if (hunter.getKnownMonsters().get(randomMonster).equals("Aptonoth") &&
                        !containsMonster("Aptonoth")) {
                    areaMap.getZone(openLocations.get(randomLocation) - 1).setMonster(
                            new Aptonoth(openLocations.get(randomLocation), hunter, this));
                    currentMosters.add(areaMap.getZone(openLocations.get(randomLocation) - 1).getMonster());
                    openLocations.remove(randomLocation);
                }
                if (hunter.getKnownMonsters().get(randomMonster).equals("Jagras") &&
                        !containsMonster("Jagras")) {
                    areaMap.getZone(openLocations.get(randomLocation) - 1).setMonster(
                            new Jagras(openLocations.get(randomLocation), hunter, this));
                    currentMosters.add(areaMap.getZone(openLocations.get(randomLocation) - 1).getMonster());
                    openLocations.remove(randomLocation);
                }
                if (hunter.getKnownMonsters().get(randomMonster).equals("Great Jagras") &&
                        !containsMonster("Great Jagras")) {
                    areaMap.getZone(openLocations.get(randomLocation) - 1).setMonster(
                            new GreatJagras(openLocations.get(randomLocation), hunter, this));
                    currentMosters.add(areaMap.getZone(openLocations.get(randomLocation) - 1).getMonster());
                    openLocations.remove(randomLocation);
                }
                if (hunter.getKnownMonsters().get(randomMonster).equals("Jyuratodus") &&
                        !containsMonster("Jyuratodus")) {
                    areaMap.getZone(openLocations.get(randomLocation) - 1).setMonster(
                            new Jyuratodus(openLocations.get(randomLocation), hunter, this));
                    currentMosters.add(areaMap.getZone(openLocations.get(randomLocation) - 1).getMonster());
                    openLocations.remove(randomLocation);
                }
                spawnTimer = 0;
            } else {
                spawnTimer++;
            }
        } else {
            spawnTimer = 0;
        }
    }

    /**
     * Checks if the hunter's weapon should be upgraded after victory.
     */
    private void checkUpgradeWeapon() {
        if (hunter.getWeaponPath().notFinalWeapon() && hunter.getKnownMonsters().contains(hunter.getWeaponPath()
                .getRequiredMonster())) {
            if (hunter.getWeaponPath().nextHasChoice()) {
                leftUpgrade.setVisible(true);
                leftUpgrade.setText(hunter.getWeaponPath().getCurrentWeapon().getLeftChild().getWeapon().getName());
                rightUpgrade.setVisible(true);
                rightUpgrade.setText(hunter.getWeaponPath().getCurrentWeapon().getRightChild().getWeapon().getName());
                middleUpgrade.setVisible(false);
            } else {
                middleUpgrade.setVisible(true);
                middleUpgrade.setText(hunter.getWeaponPath().getCurrentWeapon().getMiddleChild().getWeapon().getName());
                leftUpgrade.setVisible(false);
                rightUpgrade.setVisible(false);
            }
        } else {
            middleUpgrade.setVisible(false);
            leftUpgrade.setVisible(false);
            rightUpgrade.setVisible(false);
        }
    }

    /**
     * With this, only a basic area screen is needed. One does not need to make a whole 9 scenes independently.
     * It just requires some information that the selection of the map will initiate.
     */
    private void areaToScene() {
        optionViewer.getChildren().clear();
        currentBackground.setImage(areaMap.getBackgrounds().get(hunter.getCurrentLocation() - 1));
        areaTitle.setText("Area " + hunter.getCurrentLocation());
        updateLog(hunter.getName() + " entered Area " + hunter.getCurrentLocation() + ".");
        slot1View.setImage(areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot1().getIcon());
        slot2View.setImage(areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot2().getIcon());
        slot3View.setImage(areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot3().getIcon());
        optionViewer.getChildren().addAll(passiveVBox, inventoryVBox);
        if (areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot4() != null) {
            slot4View.setVisible(true);
            slot4View.setImage(areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot4().getIcon());
        } else {
            slot4View.setVisible(false);
        }
    }

    /**
     * A method to condense starting a game.
     */
    private void beginGame() {
        if (combatVBox.getChildren().contains(guard)) {
            combatVBox.getChildren().remove(guard);
        }
        if (combatVBox.getChildren().contains(escape)) {
            combatVBox.getChildren().remove(escape);
        }
        if (hunter.getWeapon().getGuard()) {
            combatVBox.getChildren().add(guard);
        }
        combatVBox.getChildren().add(escape);
        passageOne.setText(hunter.getName() + " started their quest to slay a " + questMonster.getName() + ".");
        hunter.setBurning(false);
        hunter.setPoisoned(false);
        hunter.setBleeding(false);
        staminaViewer.setText("Stamina: " + hunter.getStamina() + "/" + hunter.getMaxStamina());
        healthViewer.setText("Health: " + hunter.getHp() + "/" + hunter.getMaxHp());
        backgroundTitle.pause();
        backgroundPassive.setStartTime(Duration.ZERO);
        backgroundPassive.play();
    }

    /**
     * The GUI part of the program.
     *
     * @param primaryStage The prime stage for the game.
     */
    @Override
    public void start(Stage primaryStage) {

        this.stage = primaryStage; // Letting the stage be changed by outside methods.

        // Options menu
        StackPane optionPane = new StackPane();
        VBox optionVBox = new VBox();
        Text optionTitle = new Text("Options:");
        HBox mVHBox = new HBox();
        Text mVLabel = new Text("Master Volume: ");
        Slider mVSlider = new Slider();
        mVSlider.setMax(1);
        mVSlider.setMin(0);
        mVSlider.setValue(0.5);
        mVSlider.setShowTickLabels(true);
        mVSlider.setShowTickMarks(true);
        mVSlider.setMajorTickUnit(0.5);
        mVSlider.setMinorTickCount(5);
        mVSlider.setBlockIncrement(0.10);
        mVHBox.getChildren().addAll(mVLabel, mVSlider);
        Button optionsToMenu = new Button("Return to the Menu");
        standardButtonFormatting(optionsToMenu, optionPane);
        Button optionsToGame = new Button("Return to the Game");
        standardButtonFormatting(optionsToGame, optionPane);
        Text optionInfo = new Text("Press O during the game to open the Option menu again.");
        optionInfo.setFont(Font.font("Verdana", FontPosture.ITALIC, 25));
        optionVBox.getChildren().addAll(optionTitle, mVHBox, optionsToMenu, optionInfo);
        optionVBox.spacingProperty().bind(stage.heightProperty().divide(5));
        optionPane.getChildren().add(optionVBox);


        optionVBox.setAlignment(Pos.TOP_CENTER);
        mVHBox.setAlignment(Pos.CENTER);
        mVLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        optionTitle.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));

        backgroundTitle.volumeProperty().bind(mVSlider.valueProperty());
        backgroundPassive.volumeProperty().bind(mVSlider.valueProperty());
        backgroundCombat.volumeProperty().bind(mVSlider.valueProperty());
        backgroundDefeat.volumeProperty().bind(mVSlider.valueProperty());
        backgroundVictory.volumeProperty().bind(mVSlider.valueProperty());

        // Making all the music continuously loop.
        backgroundTitle.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundPassive.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundCombat.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundDefeat.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundVictory.setCycleCount(MediaPlayer.INDEFINITE);

        backgroundTitle.seek(Duration.ZERO);
        backgroundTitle.play();

        // Drop shadow for text that needs it.
        DropShadow ds = new DropShadow();
        ds.setOffsetY(10.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        // Creates the Title Screen.
        titlePane.setPrefSize(1200, 1000);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 90));
        title.setFill(Color.RED);
        title.setEffect(ds);
        title.setCache(true);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(2);
        VBox titleVBox = new VBox();
        VBox options = new VBox();
        titleButtonFormatting(play);
        titleButtonFormatting(help);
        titleButtonFormatting(option);
        titleButtonFormatting(quit);
        options.getChildren().addAll(play, help, option, quit);
        options.setSpacing(10);
        options.setAlignment(Pos.CENTER);
        titleVBox.getChildren().addAll(title, options);
        titleVBox.spacingProperty().bind(titlePane.heightProperty().divide(6));
        titleVBox.setAlignment(Pos.CENTER);
        titlePane.getChildren().add(titleVBox);
        bindBackground(titleScreenImageView, titlePane);
        currentScene = new Scene(titlePane, 1200, 1000);

        // Creates the help menu.
        StackPane helpPane = new StackPane();
        VBox helpOptions = new VBox();
        ScrollPane helpScroll = new ScrollPane();
        helpScroll.setContent(helpOptions);
        helpScroll.setMaxSize(310, 1000);
        gameButtonFormatting(craftingHelp, helpPane);
        gameButtonFormatting(gatheringHelp, helpPane);
        gameButtonFormatting(hunterHelp, helpPane);
        gameButtonFormatting(monsterHelp, helpPane);
        gameButtonFormatting(weaponHelp, helpPane);
        gameButtonFormatting(cancelHelp, helpPane);
        helpOptions.getChildren().addAll(craftingHelp, gatheringHelp, hunterHelp, monsterHelp, weaponHelp,
                cancelHelp);
        VBox helpInfo = new VBox();
        Text helpTitle = new Text();
        helpTitle.setFont(Font.font("Verdana", FontPosture.ITALIC, 40));
        Text helpDetails = new Text();
        helpDetails.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        helpDetails.wrappingWidthProperty().bind(helpPane.widthProperty().divide(1.5));
        helpInfo.getChildren().addAll(helpTitle, helpDetails);
        HBox helpDisplay = new HBox();
        helpDisplay.getChildren().addAll(helpScroll, helpInfo);
        helpPane.getChildren().add(helpDisplay);


        // Creates the character naming scene.
        StackPane namePane = new StackPane();
        VBox nameSelection = new VBox();
        enterName.setFont(Font.font("Verdana", FontPosture.ITALIC, 40));
        enterName.setFill(Color.RED);
        nameSelection.getChildren().addAll(enterName, hunterName);
        hunterName.prefWidthProperty().bind(namePane.widthProperty().divide(2));
        hunterName.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        hunterName.setAlignment(Pos.CENTER);
        nameSelection.setAlignment(Pos.CENTER);
        namePane.getChildren().add(nameSelection);

        // Creates the weapon selection screen.
        StackPane weaponPane = new StackPane();
        VBox weaponList = new VBox();
        weaponList.setSpacing(20);
        weaponList.setAlignment(Pos.CENTER);
        HBox hammerBox = new HBox();
        hammerDescription.wrappingWidthProperty().bind(weaponPane.widthProperty().divide(1.5));
        hammerDescription.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        standardButtonFormatting(hammerButton, weaponPane);
        hammerBox.getChildren().addAll(hammerButton, hammerDescription);
        hammerBox.setAlignment(Pos.CENTER);
        hammerBox.setSpacing(10);
        hammerBox.setPrefSize(1000, 100);
        HBox dualBladesBox = new HBox();
        dualBladesDescription.wrappingWidthProperty().bind(weaponPane.widthProperty().divide(1.5));
        dualBladesDescription.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        standardButtonFormatting(dualBladesButton, weaponPane);
        dualBladesBox.getChildren().addAll(dualBladesButton, dualBladesDescription);
        dualBladesBox.setAlignment(Pos.CENTER);
        dualBladesBox.setSpacing(10);
        dualBladesBox.setPrefSize(1000, 100);
        HBox swordAndShieldBox = new HBox();
        swordAndShieldDescription.wrappingWidthProperty().bind(weaponPane.widthProperty().divide(1.5));
        swordAndShieldDescription.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        standardButtonFormatting(swordAndShieldButton, weaponPane);
        swordAndShieldBox.getChildren().addAll(swordAndShieldButton, swordAndShieldDescription);
        swordAndShieldBox.setAlignment(Pos.CENTER);
        swordAndShieldBox.setSpacing(10);
        swordAndShieldBox.setPrefSize(1000, 100);
        weaponList.getChildren().addAll(hammerBox, dualBladesBox, swordAndShieldBox);
        VBox weaponSelector = new VBox();
        weaponSelector.getChildren().addAll(chooseWeapon, weaponList);
        weaponSelector.setAlignment(Pos.CENTER);
        weaponSelector.setSpacing(20);
        chooseWeapon.setFont(Font.font("Verdana", FontPosture.ITALIC, 40));
        chooseWeapon.setFill(Color.RED);
        weaponPane.getChildren().add(weaponSelector);

        // Creates the monster selection screen.
        StackPane monsterPane = new StackPane();
        VBox monsterList = new VBox();
        HBox greatJagrasBox = new HBox();
        greatJagrasDescription.wrappingWidthProperty().bind(monsterPane.widthProperty().divide(1.5));
        greatJagrasDescription.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        standardButtonFormatting(greatJagrasButton, monsterPane);
        greatJagrasBox.getChildren().addAll(greatJagrasButton, greatJagrasDescription);
        greatJagrasBox.setAlignment(Pos.CENTER);
        greatJagrasBox.setSpacing(10);
        HBox jyuratodusBox = new HBox();
        jyuratodusDescription.wrappingWidthProperty().bind(monsterPane.widthProperty().divide(1.5));
        jyuratodusDescription.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        standardButtonFormatting(jyuratodusButton, monsterPane);
        jyuratodusBox.getChildren().addAll(jyuratodusButton, jyuratodusDescription);
        jyuratodusBox.setAlignment(Pos.CENTER);
        jyuratodusBox.setSpacing(10);
        monsterList.getChildren().addAll(chooseMonster, greatJagrasBox, jyuratodusBox);
        chooseMonster.setFont(Font.font("Verdana", FontPosture.ITALIC, 40));
        chooseMonster.setFill(Color.RED);
        monsterList.setAlignment(Pos.CENTER);
        monsterList.setSpacing(20);
        monsterPane.getChildren().add(monsterList);

        // Creates the difficulty selection screen.
        StackPane difficultyPane = new StackPane();
        VBox difficultyList = new VBox();
        HBox easyBox = new HBox();
        easyDescription.wrappingWidthProperty().bind(difficultyPane.widthProperty().divide(1.5));
        easyDescription.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        standardButtonFormatting(easy, difficultyPane);
        easyBox.getChildren().addAll(easy, easyDescription);
        easyBox.setAlignment(Pos.CENTER);
        easyBox.setSpacing(10);
        HBox normalBox = new HBox();
        normalDescription.wrappingWidthProperty().bind(difficultyPane.widthProperty().divide(1.5));
        normalDescription.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        standardButtonFormatting(normal, difficultyPane);
        normalBox.getChildren().addAll(normal, normalDescription);
        normalBox.setAlignment(Pos.CENTER);
        normalBox.setSpacing(10);
        HBox hardBox = new HBox();
        hardDescription.wrappingWidthProperty().bind(difficultyPane.widthProperty().divide(1.5));
        hardDescription.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        standardButtonFormatting(hard, difficultyPane);
        hardBox.getChildren().addAll(hard, hardDescription);
        hardBox.setAlignment(Pos.CENTER);
        hardBox.setSpacing(10);
        HBox challengeBox = new HBox();
        challengeDescription.wrappingWidthProperty().bind(difficultyPane.widthProperty().divide(1.5));
        challengeDescription.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        standardButtonFormatting(challenge, difficultyPane);
        challengeBox.getChildren().addAll(challenge, challengeDescription);
        challengeBox.setAlignment(Pos.CENTER);
        challengeBox.setSpacing(10);
        difficultyList.getChildren().addAll(chooseDifficulty, easyBox, normalBox, hardBox, challengeBox);
        chooseDifficulty.setFont(Font.font("Verdana", FontPosture.ITALIC, 40));
        chooseDifficulty.setFill(Color.RED);
        difficultyList.setAlignment(Pos.CENTER);
        difficultyList.setSpacing(10);
        difficultyPane.getChildren().add(difficultyList);

        // Creates the map selection screen.
        StackPane mapSelectionPane = new StackPane();
        VBox mapList = new VBox();
        HBox tigerIslandBox = new HBox();
        tigerIslandDescription.wrappingWidthProperty().bind(mapSelectionPane.widthProperty().divide(1.5));
        tigerIslandDescription.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        standardButtonFormatting(tigerIslandButton, mapSelectionPane);
        tigerIslandBox.getChildren().addAll(tigerIslandButton, tigerIslandDescription);
        tigerIslandBox.setAlignment(Pos.CENTER);
        tigerIslandBox.setSpacing(10);
        mapList.getChildren().addAll(chooseMap, tigerIslandBox);
        chooseMap.setFont(Font.font("Verdana", FontPosture.ITALIC, 40));
        chooseMap.setFill(Color.RED);
        mapList.setAlignment(Pos.CENTER);
        mapList.setSpacing(10);
        mapSelectionPane.getChildren().add(mapList);

        // Creates the basics of the dynamic area screen.
        // 1. HUD displayed at the top of the screen. Includes weapon sharpness tracker, adventure logs, and
        // the area the hunter is currently in.
        // 2. Gathering nodes from the current area displayed.
        // 3. Options for combat and out-of-combat activities. Changes between combat and passive mode depending on
        // the monster's presence.
        // 4. The hunter status tracker. Shows the current and maximum stamina and health of the hunter.
        // 5. Background image.
        sharpHeading.setFont(Font.font("Verdana", FontPosture.ITALIC, 25));
        sharpnessLevel.setFont(Font.font("Verdana", FontPosture.ITALIC, 35));
        sharpnessLevel.wrappingWidthProperty().bind(areaPane.widthProperty().divide(8));
        sharpnessLevel.setFill(Color.GREEN);
        sharpHUD.getChildren().addAll(sharpHeading, sharpnessLevel);
        sharpHUD.prefWidthProperty().bind(areaPane.widthProperty().divide(24));
        passageOne.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        passageOne.wrappingWidthProperty().bind(areaPane.widthProperty().divide(1.5));
        passageTwo.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        passageTwo.wrappingWidthProperty().bind(areaPane.widthProperty().divide(1.5));
        passageThree.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        passageThree.wrappingWidthProperty().bind(areaPane.widthProperty().divide(1.5));
        logViewer.getChildren().addAll(passageThree, passageTwo, passageOne);
        logViewer.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: #000000;");
        logViewer.setSpacing(2);
        areaTitle.setFont(Font.font("Verdana", FontPosture.ITALIC, 45));
        areaTitle.setFill(Color.RED);
        areaTitle.setStroke(Color.BLACK);
        areaTitle.setStrokeWidth(2);
        hudViewer.getChildren().addAll(sharpHUD, logViewer, areaTitle);
        hudViewer.setSpacing(10);
        hudViewer.setAlignment(Pos.TOP_CENTER);
        hudViewer.prefWidthProperty().bind(areaPane.widthProperty());
        hudViewer.prefHeightProperty().bind(areaPane.heightProperty().divide(10));

        slot1View.fitWidthProperty().bind(areaPane.widthProperty().divide(4));
        slot1View.fitHeightProperty().bind(areaPane.heightProperty().divide(4));
        slot2View.fitWidthProperty().bind(areaPane.widthProperty().divide(4));
        slot2View.fitHeightProperty().bind(areaPane.heightProperty().divide(4));
        slot3View.fitWidthProperty().bind(areaPane.widthProperty().divide(4));
        slot3View.fitHeightProperty().bind(areaPane.heightProperty().divide(4));
        gatherViewer.getChildren().addAll(slot1View, slot2View, slot3View);
        gatherViewer.setSpacing(25);
        gatherViewer.setAlignment(Pos.CENTER);
        gatherViewer.prefWidthProperty().bind(areaPane.widthProperty());
        gatherViewer.prefHeightProperty().bind(areaPane.heightProperty().divide(5 / 3));

        // Passive Actions
        gameButtonFormatting(gather, areaPane);
        gameButtonFormatting(crafting, areaPane);
        gameButtonFormatting(map, areaPane);
        gameButtonFormatting(passiveSharp, areaPane);
        passiveVBox.getChildren().addAll(gather, crafting, map, passiveSharp);

        // Usable Items
        gameButtonFormatting(herb, areaPane);
        gameButtonFormatting(potion, areaPane);
        gameButtonFormatting(megaPotion, areaPane);
        gameButtonFormatting(antidote, areaPane);
        gameButtonFormatting(cookedMeat, areaPane);
        gameButtonFormatting(bandage, areaPane);
        inventoryVBox.getChildren().addAll(herb, potion, megaPotion, antidote, cookedMeat, bandage);

        // Combat Actions
        gameButtonFormatting(attack, areaPane);
        gameButtonFormatting(sharpen, areaPane);
        gameButtonFormatting(guard, areaPane);
        gameButtonFormatting(escape, areaPane);
        combatVBox.getChildren().addAll(attack, sharpen);
        optionViewer.getChildren().addAll(passiveVBox, inventoryVBox);
        optionViewer.setAlignment(Pos.BASELINE_CENTER);
        optionViewer.prefWidthProperty().bind(areaPane.widthProperty());
        optionViewer.prefHeightProperty().bind(areaPane.heightProperty().divide(5));

        staminaViewer.setFont(Font.font("Verdana", FontPosture.ITALIC, 40));
        healthViewer.setFont(Font.font("Verdana", FontPosture.ITALIC, 40));
        statusViewer.getChildren().addAll(staminaViewer, healthViewer);
        statusViewer.setAlignment(Pos.BOTTOM_RIGHT);
        statusViewer.setSpacing(50);
        statusViewer.prefWidthProperty().bind(areaPane.widthProperty());
        statusViewer.prefHeightProperty().bind(areaPane.heightProperty().divide(20));

        VBox areaViewer = new VBox();
        areaViewer.getChildren().addAll(hudViewer, gatherViewer, optionViewer, statusViewer);
        areaViewer.setSpacing(10);
        areaViewer.setAlignment(Pos.CENTER);
        areaViewer.prefWidthProperty().bind(areaPane.widthProperty().subtract(50));
        areaViewer.prefHeightProperty().bind(areaPane.heightProperty());
        areaPane.getChildren().add(areaViewer);
        bindBackground(currentBackground, areaPane);

        // Creates the monster image to be displayed when the monster is in the same location as the hunter.
        gameButtonFormatting(stepRight, areaPane);
        stepRight.prefWidthProperty().bind(areaPane.widthProperty().divide(8));
        gameButtonFormatting(dashRight, areaPane);
        dashRight.prefWidthProperty().bind(areaPane.widthProperty().divide(8));
        gameButtonFormatting(stepLeft, areaPane);
        stepLeft.prefWidthProperty().bind(areaPane.widthProperty().divide(8));
        gameButtonFormatting(dashLeft, areaPane);
        dashLeft.prefWidthProperty().bind(areaPane.widthProperty().divide(8));
        leftVBox.getChildren().addAll(stepLeft, dashLeft);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.prefWidthProperty().bind(areaPane.widthProperty().divide(8));
        leftVBox.prefHeightProperty().bind(areaPane.heightProperty().divide(2));
        rightVBox.getChildren().addAll(stepRight, dashRight);
        rightVBox.setAlignment(Pos.CENTER);
        rightVBox.prefWidthProperty().bind(areaPane.widthProperty().divide(8));
        rightVBox.prefHeightProperty().bind(areaPane.heightProperty().divide(2));
        monsterVBox.setAlignment(Pos.CENTER);
        monsterVBox.prefWidthProperty().bind(areaPane.widthProperty());
        monsterVBox.maxHeightProperty().bind(areaPane.heightProperty().divide(3));
        monsterView.fitWidthProperty().bind(areaPane.widthProperty().divide(2.4));
        monsterView.fitHeightProperty().bind(areaPane.heightProperty().divide(2.5));

        maneuverHBox.getChildren().addAll(leftVBox, monsterView, rightVBox);
        maneuverHBox.setAlignment(Pos.CENTER);
        monsterVBox.getChildren().add(maneuverHBox);

        // Creates a gathering option menu to replace the normal passive options when gathering.
        VBox gatherVBox = new VBox();
        gameButtonFormatting(gatherSlot1, areaPane);
        gameButtonFormatting(gatherSlot2, areaPane);
        gameButtonFormatting(gatherSlot3, areaPane);
        gameButtonFormatting(gatherSlot4, areaPane);
        gameButtonFormatting(exitGather, areaPane);
        gatherVBox.getChildren().addAll(gatherSlot1, gatherSlot2, gatherSlot3, gatherSlot4, exitGather);

        // Creates a crafting option menu to replace the normal passive options when crafting.
        VBox craftVBox = new VBox();
        gameButtonFormatting(craftPotion, areaPane);
        craftPotion.prefWidthProperty().bind(areaPane.widthProperty().divide(1.5));
        gameButtonFormatting(craftMegaPotion, areaPane);
        craftMegaPotion.prefWidthProperty().bind(areaPane.widthProperty().divide(1.5));
        gameButtonFormatting(craftAntidote, areaPane);
        craftAntidote.prefWidthProperty().bind(areaPane.widthProperty().divide(1.5));
        gameButtonFormatting(cookMeat, areaPane);
        cookMeat.prefWidthProperty().bind(areaPane.widthProperty().divide(1.5));
        gameButtonFormatting(craftBandage, areaPane);
        craftBandage.prefWidthProperty().bind(areaPane.widthProperty().divide(1.5));
        gameButtonFormatting(exitCrafting, areaPane);
        exitCrafting.prefWidthProperty().bind(areaPane.widthProperty().divide(1.5));
        craftVBox.getChildren().addAll(craftPotion, craftMegaPotion, craftAntidote, cookMeat, craftBandage, exitCrafting);

        // Creates a map screen to display the game map and neighboring areas to travel to.
        StackPane mapPane = new StackPane();
        VBox mapVBox = new VBox();
        locationTitle.setFont(Font.font("Verdana", FontPosture.ITALIC, 45));
        locationTitle.setFill(Color.RED);
        locationTitle.setStroke(Color.BLACK);
        locationTitle.setStrokeWidth(2);
        gameButtonFormatting(area1, mapPane);
        gameButtonFormatting(area2, mapPane);
        gameButtonFormatting(area3, mapPane);
        gameButtonFormatting(area4, mapPane);
        gameButtonFormatting(area5, mapPane);
        gameButtonFormatting(area6, mapPane);
        gameButtonFormatting(area7, mapPane);
        gameButtonFormatting(area8, mapPane);
        gameButtonFormatting(area9, mapPane);
        gameButtonFormatting(exitMap, mapPane);
        neighbors.getChildren().addAll(area1, area2, area3, area4, area5, area6, area7, area8, area9);
        mapView.fitWidthProperty().bind(mapPane.widthProperty().divide(2));
        mapView.fitHeightProperty().bind(mapPane.heightProperty().divide(20 / 9));
        mapVBox.getChildren().addAll(locationTitle, mapView, neighbors, exitMap);
        mapVBox.setSpacing(20);
        mapVBox.prefWidthProperty().bind(mapPane.widthProperty());
        mapVBox.prefHeightProperty().bind(mapPane.heightProperty().divide(4 / 3));
        mapVBox.setAlignment(Pos.CENTER);
        mapPane.getChildren().add(mapVBox);

        // Creates the basics of a victory or a defeat screen. Uses a method to morph appropriately.
        endTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 90));
        endTitle.setFill(Color.RED);
        endTitle.setEffect(ds);
        endTitle.setCache(true);
        endTitle.setStroke(Color.BLACK);
        endTitle.setStrokeWidth(2);
        turns.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        turns.wrappingWidthProperty().bind(endPane.widthProperty().divide(1.2));
        totalDamage.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        totalDamage.wrappingWidthProperty().bind(endPane.widthProperty().divide(1.2));
        endPassage.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        endPassage.wrappingWidthProperty().bind(endPane.widthProperty().divide(1.2));
        gameButtonFormatting(toMenu, endPane);
        gameButtonFormatting(huntAgain, endPane);
        HBox endOptions = new HBox();
        endOptions.getChildren().addAll(toMenu, huntAgain);
        endOptions.setAlignment(Pos.CENTER);
        endVBox.getChildren().addAll(endTitle, turns, totalDamage, endPassage, endOptions);
        endVBox.prefWidthProperty().bind(areaPane.widthProperty());
        endVBox.prefHeightProperty().bind(areaPane.heightProperty());
        endVBox.setAlignment(Pos.CENTER);
        endPane.getChildren().add(endVBox);
        bindBackground(endBackground, endPane);

        // Creates the smithy screen for upgrading weapons between quests.
        StackPane smithyPane = new StackPane();
        smithyTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 90));
        standardButtonFormatting(leftUpgrade, smithyPane);
        standardButtonFormatting(middleUpgrade, smithyPane);
        standardButtonFormatting(rightUpgrade, smithyPane);
        standardButtonFormatting(continueOn, smithyPane);
        upgradeOptions.getChildren().addAll(leftUpgrade, middleUpgrade, rightUpgrade);
        upgradeOptions.setAlignment(Pos.CENTER);
        VBox smithOrg = new VBox();
        smithOrg.getChildren().addAll(smithyTitle, upgradeOptions, continueOn);
        smithOrg.setAlignment(Pos.CENTER);
        smithyPane.getChildren().add(smithOrg);
        smithyPane.setAlignment(Pos.CENTER);
        bindBackground(forgingBackground, smithyPane);

        // ******** Button Activation ***********
        // Title Screen Buttons
        play.setOnAction(e -> {
            currentScene.setRoot(namePane);
            optionVBox.getChildren().remove(optionsToMenu);
            optionVBox.getChildren().remove(optionInfo);
            optionVBox.getChildren().add(optionsToGame);
        });

        help.setOnAction(e -> {
            currentScene.setRoot(helpPane);
        });

        option.setOnAction(e -> {
            currentScene.setRoot(optionPane);
        });

        quit.setOnAction(e -> {
            System.exit(0);
        });

        // Help buttons
        craftingHelp.setOnAction(e -> {
            helpTitle.setText(craftingHelp.getText());
            helpDetails.setText("Crafting items requires various ingredients found in the world. Crafting items " +
                    "passes time and can only be done when the monster is elsewhere. It is important to keep a " +
                    "good stock of items before you end up in the thick of battle as they can save your life. " +
                    "There are a number of life-saving items that can be crafted. Potions heal the hunter for a " +
                    "moderate amount. They are an improvement on simply applying herbs to your wounds. Mega Potions " +
                    "heal the hunter for a substantial amount. These are what can really save you in a pinch as " +
                    "they heal far more than a simple potion or herb. Antidotes cure the hunter of poison. Poison " +
                    "can rack up a large amount of damage if left unchecked for a long time. Poison will eventually " +
                    "wear off on its own, but it is ill advised to leave it alone for so long. (See " +
                    "'Gathering' for more information on gathering.)");
        });

        gatheringHelp.setOnAction(e -> {
            helpTitle.setText(gatheringHelp.getText());
            helpDetails.setText("Gathering can be done at any location in the world, but if there is a " +
                    "monster around, you won't be able to gather anything. What can be gathered is somewhat " +
                    "dependant on the location. For example, if you need to find some blue mushrooms, " +
                    "it would be a good idea to check a cave. Gathering is required in order to craft items." +
                    " There are a few different things that can be gathered. Antidote Flowers have a large " +
                    "amount of antitoxins that can be utilized by the hunter to create antidotes. Blue " +
                    "Mushrooms are used as a base for basic potions, like potions and antidotes. Herbs have " +
                    "natural healing properties and are used to craft the basic healing potion. Honey " +
                    "contains an enzyme which enhances the healing power of potions. (See 'Crafting' for " +
                    "more information on crafting.)");
        });

        hunterHelp.setOnAction(e -> {
            helpTitle.setText(hunterHelp.getText());
            helpDetails.setText("The hunter's life is filled with danger. They are the people called to hunt" +
                    " giant monsters. One misstep could lead to being crushed under the foot of one of these" +
                    " behemoths. Hunters need to be prepared and be quick on their feet in order to survive.");
        });

        monsterHelp.setOnAction(e -> {
            helpTitle.setText(monsterHelp.getText());
            helpDetails.setText("Giant monsters are both an ecological marvel and a dangerous threat. They tend " +
                    "to be very aggressive because it takes a lot of calories to keep them going, and humans " +
                    "seem like a tidy morsel. Once the humans fight back, it believes it has to kill in order to" +
                    " ensure its safety. Monsters of this size tend to be a bit slow compared to hunters. The " +
                    "hunters that fight them need to capitalize on this lest they find themselves on the wrong" +
                    " end of a hunter-sized, clawed hand.");
        });

        weaponHelp.setOnAction(e -> {
            helpTitle.setText(weaponHelp.getText());
            helpDetails.setText("The weapon of a hunter is their single greatest ally in the fight against " +
                    "a giant monster. Mere fists could not deal damage through thick hides and strong scales. Most" +
                    " weapons have a particular mechanic mose closely associated with them. A hammer can momentarily" +
                    " disorient a monster with its heavy hits. A sword and shield can allow the hunter to simply " +
                    "block the attack of the monster to prevent any chance of being grazed by an attack. The dual " +
                    "blades are light, so the hunter can swing them several times in the time it would take a " +
                    "hunter to swing a hammer. This does tend to make their edges dull much faster though.");
        });

        cancelHelp.setOnAction(e -> {
            currentScene.setRoot(titlePane);
        });

        // Options menu buttons
        optionsToMenu.setOnAction(e -> {
            currentScene.setRoot(titlePane);
        });

        optionsToGame.setOnAction(e -> {
            currentScene.setRoot(areaPane);
        });

        // Initial selection buttons
        hunterName.setOnAction(e -> {
            ArrayList<String> knownMonsters = new ArrayList<>();
            knownMonsters.add("Aptonoth");
            hunter = new Hunter(hunterName.getText(), new Inventory(), null, knownMonsters, this);
            currentScene.setRoot(weaponPane);
        });

        hammerButton.setOnAction(e -> {
            hunter.setWeaponTree(new HammerTree());
            hunter.updateWeapon();
            currentScene.setRoot(monsterPane);
        });
        dualBladesButton.setOnAction(e -> {
            hunter.setWeaponTree(new DualBladesTree());
            hunter.updateWeapon();
            currentScene.setRoot(monsterPane);
        });
        swordAndShieldButton.setOnAction(e -> {
            hunter.setWeaponTree(new SwordAndShieldTree());
            hunter.updateWeapon();
            currentScene.setRoot(monsterPane);
        });

        greatJagrasButton.setOnAction(e -> {
            questMonster = null;
            questMonster = new GreatJagras(9, hunter, this);
            monsterView.setImage(questMonster.getCurrentSide(hunter.getPosition()));
            currentScene.setRoot(difficultyPane);
        });

        jyuratodusButton.setOnAction(e -> {
            questMonster = null;
            questMonster = new Jyuratodus(7, hunter, this);
            monsterView.setImage(questMonster.getCurrentSide(hunter.getPosition()));
            currentScene.setRoot(difficultyPane);
        });

        easy.setOnAction(e -> {
            questMonster.easyMode();
            currentScene.setRoot(mapSelectionPane);
        });
        normal.setOnAction(e -> {
            currentScene.setRoot(mapSelectionPane);
        });
        hard.setOnAction(e -> {
            questMonster.hardMode();
            currentScene.setRoot(mapSelectionPane);
        });
        challenge.setOnAction(e -> {
            questMonster.challengeMode();
            currentScene.setRoot(mapSelectionPane);
        });

        tigerIslandButton.setOnAction(e -> {
            mapImage = new Image("/TigerIslandMapCompass.png");
            mapView = new ImageView(mapImage);
            areaMap = new TigerIsland();
            beginGame();
            targetMonster = null;
            startingLocation = 1;
            hunter.setCurrentLocation(startingLocation);
            currentMosters.clear();
            openLocations.clear();
            openLocations.addAll(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
            areaMap.getZone(questMonster.getCurrentLocation() - 1).setMonster(questMonster);
            currentMosters.add(questMonster);
            hunter.setHp(hunter.getMaxHp());
            checkMonster();
            updateStatus();
            areaToScene();
            currentScene.setRoot(areaPane);
        });

        // In-game buttons
        attack.setOnAction(e -> {
            hunter.getWeapon().attack(this);
            turn();
        });

        sharpen.setOnAction(e -> {
            hunter.getWeapon().sharpen();
            turn();
        });

        guard.setOnAction(e -> {
            hunter.setGuarding(true);
            turn();
        });

        escape.setOnAction(e -> {
            if (targetMonster.checkEscape()) {
                updateLog(hunter.getName() + " successfully escaped.");
                map.fire();
            } else {
                updateLog(hunter.getName() + " failed to escape.");
                turn();
            }
        });

        stepLeft.setOnAction(e -> {
            final String position = hunter.getPosition();
            switch (position) {
                case "Front": {
                    hunter.setPosition("Left");
                    break;
                }
                case "Left": {
                    hunter.setPosition("Back");
                    break;
                }
                case "Back": {
                    hunter.setPosition("Right");
                    break;
                }
                case "Right": {
                    hunter.setPosition("Front");
                    break;
                }
                default: {
                    System.out.println("Invalid Position: " + hunter.getPosition());
                }
            }
            updateLog(hunter.getName() + " stepped to the left.");
            hunter.checkBleeding();
            monsterView.setImage(targetMonster.getCurrentSide(hunter.getPosition()));
            turn();
        });

        dashLeft.setOnAction(e -> {
            if (!hunter.isMuddy()) {
                if (hunter.checkStaminaCost(25)) {
                    final String position = hunter.getPosition();
                    switch (position) {
                        case "Front": {
                            hunter.setPosition("Back");
                            break;
                        }
                        case "Left": {
                            hunter.setPosition("Right");
                            break;
                        }
                        case "Back": {
                            hunter.setPosition("Front");
                            break;
                        }
                        case "Right": {
                            hunter.setPosition("Left");
                            break;
                        }
                        default: {
                            System.out.println("Invalid Position: " + hunter.getPosition());
                        }
                    }
                    updateLog(hunter.getName() + " dashed to the left.");
                    hunter.setStamina(hunter.getStamina() - 25);
                    hunter.checkBleeding();
                    monsterView.setImage(targetMonster.getCurrentSide(hunter.getPosition()));
                    turn();
                } else {
                    updateLog(hunter.getName() + " did not have enough stamina to dash.");
                }
            } else {
                updateLog(hunter.getName() + " is covered in mud and can't move quickly!");
            }
        });

        stepRight.setOnAction(e -> {
            final String position = hunter.getPosition();
            switch (position) {
                case "Front": {
                    hunter.setPosition("Right");
                    break;
                }
                case "Right": {
                    hunter.setPosition("Back");
                    break;
                }
                case "Back": {
                    hunter.setPosition("Left");
                    break;
                }
                case "Left": {
                    hunter.setPosition("Front");
                    break;
                }
                default: {
                    System.out.println("Invalid Position: " + hunter.getPosition());
                }
            }
            updateLog(hunter.getName() + " stepped to the right.");
            hunter.checkBleeding();
            monsterView.setImage(targetMonster.getCurrentSide(hunter.getPosition()));
            turn();
        });

        dashRight.setOnAction(e -> {
            if (!hunter.isMuddy()) {
                if (hunter.checkStaminaCost(25)) {
                    final String position = hunter.getPosition();
                    switch (position) {
                        case "Front": {
                            hunter.setPosition("Back");
                            break;
                        }
                        case "Back": {
                            hunter.setPosition("Front");
                            break;
                        }
                        case "Left": {
                            hunter.setPosition("Right");
                            break;
                        }
                        case "Right": {
                            hunter.setPosition("Left");
                            break;
                        }
                        default: {
                            System.out.println("Invalid Position: " + hunter.getPosition());
                        }
                    }
                    updateLog(hunter.getName() + " dashed to the right.");
                    hunter.setStamina(hunter.getStamina() - 25);
                    hunter.checkBleeding();
                    monsterView.setImage(targetMonster.getCurrentSide(hunter.getPosition()));
                    turn();
                } else {
                    updateLog(hunter.getName() + " did not have enough stamina to dash.");
                }
            } else {
                updateLog(hunter.getName() + " is covered in mud and can't move quickly!");
            }
        });

        gather.setOnAction(e -> {
            optionViewer.getChildren().clear();
            optionViewer.getChildren().add(gatherVBox);
            optionViewer.setAlignment(Pos.BASELINE_CENTER);
            gatherSlot1.setText(areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot1().getItemName());
            gatherSlot2.setText(areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot2().getItemName());
            gatherSlot3.setText(areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot3().getItemName());
            if (areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot4() != null) {
                gatherSlot4.setText(areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot4().getItemName());
            } else {
                gatherSlot4.setText(" ");
            }
            hunter.setGathering(true);
        });

        gatherSlot1.setOnAction(e -> {
            areaMap.getZone(hunter.getCurrentLocation() - 1).getSlot1().harvest(this);
        });

        gatherSlot2.setOnAction(e -> {
            areaMap.getZone(hunter.getCurrentLocation() - 1).getSlot2().harvest(this);
        });

        gatherSlot3.setOnAction(e -> {
            areaMap.getZone(hunter.getCurrentLocation() - 1).getSlot3().harvest(this);
        });

        gatherSlot4.setOnAction(e -> {
            if (areaMap.getZones().get(hunter.getCurrentLocation() - 1).getSlot4() != null) {
                areaMap.getZone(hunter.getCurrentLocation() - 1).getSlot4().harvest(this);
            }
        });

        exitGather.setOnAction(e -> {
            optionViewer.getChildren().clear();
            optionViewer.getChildren().addAll(passiveVBox, inventoryVBox);
            optionViewer.setAlignment(Pos.BASELINE_CENTER);
            hunter.setGathering(false);
        });

        crafting.setOnAction(e -> {
            optionViewer.getChildren().clear();
            optionViewer.getChildren().add(craftVBox);
            optionViewer.setAlignment(Pos.BASELINE_CENTER);
            hunter.setCrafting(true);
        });

        craftPotion.setOnAction(e -> {
            hunter.getInventory().getItem("Potion").craftItem(this);
        });

        craftMegaPotion.setOnAction(e -> {
            hunter.getInventory().getItem("Mega Potion").craftItem(this);
        });

        craftAntidote.setOnAction(e -> {
            hunter.getInventory().getItem("Antidote").craftItem(this);
        });

        cookMeat.setOnAction(e -> {
            hunter.getInventory().getItem("Cooked Meat").craftItem(this);
        });

        craftBandage.setOnAction(e -> {
            hunter.getInventory().getItem("Bandage").craftItem(this);
        });

        exitCrafting.setOnAction(e -> {
            optionViewer.getChildren().clear();
            optionViewer.getChildren().addAll(passiveVBox, inventoryVBox);
            optionViewer.setAlignment(Pos.BASELINE_CENTER);
            hunter.setCrafting(false);
        });

        passiveSharp.setOnAction(e -> {
            hunter.getWeapon().sharpen();
            turn();
        });

        herb.setOnAction(e -> {
            hunter.getInventory().getItem("Herb").useItem(this);
            updateStatus();
        });

        potion.setOnAction(e -> {
            hunter.getInventory().getItem("Potion").useItem(this);
            updateStatus();
        });

        megaPotion.setOnAction(e -> {
            hunter.getInventory().getItem("Mega Potion").useItem(this);
            updateStatus();
        });

        antidote.setOnAction(e -> {
            hunter.getInventory().getItem("Antidote").useItem(this);
            updateStatus();
        });

        cookedMeat.setOnAction(e -> {
            hunter.getInventory().getItem("Cooked Meat").useItem(this);
            updateStatus();
        });

        bandage.setOnAction(e -> {
            hunter.getInventory().getItem("Bandage").useItem(this);
            updateStatus();
        });

        area1.setOnAction(e -> {
            hunter.setCurrentLocation(1);
            hunter.checkBleeding();
            areaToScene();
            checkMonster();
            currentScene.setRoot(areaPane);
            turn();
        });
        area2.setOnAction(e -> {
            hunter.setCurrentLocation(2);
            hunter.checkBleeding();
            areaToScene();
            checkMonster();
            currentScene.setRoot(areaPane);
            turn();
        });
        area3.setOnAction(e -> {
            hunter.setCurrentLocation(3);
            hunter.checkBleeding();
            areaToScene();
            checkMonster();
            currentScene.setRoot(areaPane);
            turn();
        });
        area4.setOnAction(e -> {
            hunter.setCurrentLocation(4);
            hunter.checkBleeding();
            areaToScene();
            checkMonster();
            currentScene.setRoot(areaPane);
            turn();
        });
        area5.setOnAction(e -> {
            hunter.setCurrentLocation(5);
            hunter.checkBleeding();
            areaToScene();
            checkMonster();
            currentScene.setRoot(areaPane);
            turn();
        });
        area6.setOnAction(e -> {
            hunter.setCurrentLocation(6);
            hunter.checkBleeding();
            areaToScene();
            checkMonster();
            currentScene.setRoot(areaPane);
            turn();
        });
        area7.setOnAction(e -> {
            hunter.setCurrentLocation(7);
            hunter.checkBleeding();
            areaToScene();
            checkMonster();
            currentScene.setRoot(areaPane);
            turn();
        });
        area8.setOnAction(e -> {
            hunter.setCurrentLocation(8);
            hunter.checkBleeding();
            areaToScene();
            checkMonster();
            currentScene.setRoot(areaPane);
            turn();
        });
        area9.setOnAction(e -> {
            hunter.setCurrentLocation(9);
            hunter.checkBleeding();
            areaToScene();
            checkMonster();
            currentScene.setRoot(areaPane);
            turn();
        });

        // Picks out the neighbors for the current area.
        map.setOnAction(e -> {
            neighbors.getChildren().clear();
            if (areaMap.getNeighbours(areaMap.getZone(hunter.getCurrentLocation() - 1)).contains(areaMap.getZone(0))) {
                neighbors.getChildren().add(area1);
            }
            if (areaMap.getNeighbours(areaMap.getZone(hunter.getCurrentLocation() - 1)).contains(areaMap.getZone(1))) {
                neighbors.getChildren().add(area2);
            }
            if (areaMap.getNeighbours(areaMap.getZone(hunter.getCurrentLocation() - 1)).contains(areaMap.getZone(2))) {
                neighbors.getChildren().add(area3);
            }
            if (areaMap.getNeighbours(areaMap.getZone(hunter.getCurrentLocation() - 1)).contains(areaMap.getZone(3))) {
                neighbors.getChildren().add(area4);
            }
            if (areaMap.getNeighbours(areaMap.getZone(hunter.getCurrentLocation() - 1)).contains(areaMap.getZone(4))) {
                neighbors.getChildren().add(area5);
            }
            if (areaMap.getNeighbours(areaMap.getZone(hunter.getCurrentLocation() - 1)).contains(areaMap.getZone(5))) {
                neighbors.getChildren().add(area6);
            }
            if (areaMap.getNeighbours(areaMap.getZone(hunter.getCurrentLocation() - 1)).contains(areaMap.getZone(6))) {
                neighbors.getChildren().add(area7);
            }
            if (areaMap.getNeighbours(areaMap.getZone(hunter.getCurrentLocation() - 1)).contains(areaMap.getZone(7))) {
                neighbors.getChildren().add(area8);
            }
            if (areaMap.getNeighbours(areaMap.getZone(hunter.getCurrentLocation() - 1)).contains(areaMap.getZone(8))) {
                neighbors.getChildren().add(area9);
            }
            currentScene.setRoot(mapPane);
        });

        exitMap.setOnAction(e -> {
            currentScene.setRoot(areaPane);
        });

        // End of game options
        toMenu.setOnAction(e -> {
            quit.fire();
        });

        huntAgain.setOnAction(e -> {
            checkUpgradeWeapon();
            currentScene.setRoot(smithyPane);
        });

        // Upgrade buttons
        leftUpgrade.setOnAction(e -> {
            hunter.getWeaponPath().setCurrentWeapon(hunter.getWeaponPath().getCurrentWeapon().getLeftChild());
            hunter.updateWeapon();
            continueOn.fire();
        });

        middleUpgrade.setOnAction(e -> {
            hunter.getWeaponPath().setCurrentWeapon(hunter.getWeaponPath().getCurrentWeapon().getMiddleChild());
            hunter.updateWeapon();
            continueOn.fire();
        });

        rightUpgrade.setOnAction(e -> {
            hunter.getWeaponPath().setCurrentWeapon(hunter.getWeaponPath().getCurrentWeapon().getRightChild());
            hunter.updateWeapon();
            continueOn.fire();
        });

        // Returns to the monster select screen.
        continueOn.setOnAction(e -> {
            stopAllSound();
            backgroundTitle.seek(Duration.ZERO);
            backgroundTitle.play();
            currentScene.setRoot(monsterPane);
        });

        currentScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.O && currentScene.getRoot().equals(areaPane)) {
                currentScene.setRoot(optionPane);
            }
        });

        primaryStage.setTitle("Monster Hunter: Quest");
        currentScene.setRoot(titlePane);
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }

    // All the stuff that happens when time passes. Update status merely tracks these changes.
    public void turn() {
        checkVictoryDefeat();
        checkMonster();
        if (targetMonster != null) {
            targetMonster.attack(this, hunter); // Checks for the monster at the hunter's location.
        }
        checkVictoryDefeat();
        for (int i = 0; i < areaMap.getZones().size(); i++) { // Iterates through all locations to affect all monsters.
            if (areaMap.getZone(i).getMonster() != null) {
                areaMap.getZone(i).getMonster().monsterStatus(); // Processes all turn effects on the monster.
                if (areaMap.getZone(i).getMonster() instanceof SmallMonster) {
                    // Processes belonging only to small monsters.
                    ((SmallMonster) areaMap.getZone(i).getMonster()).smallMonsterTurn();
                }
                // Processing the death of a monster.
                if (areaMap.getZone(i).getMonster() != questMonster && areaMap.getZone(i).getMonster().getHP() <= 0) {
                    areaMap.getZone(i).setMonster(null);
                    areaMap.getZone(i).setSlot4(new RawMeat());
                    openLocations.add(i + 1);
                }
                // Processes the death of a small monster (not the whole pack).
                else if (areaMap.getZone(i).getMonster() instanceof SmallMonster) {
                    if (((SmallMonster) areaMap.getZone(i).getMonster()).getMemberDied() &&
                            areaMap.getZone(i).getSlot4() != null) {
                        areaMap.getZone(i).getSlot4().addToHarvestNumber(2);
                    } else if (((SmallMonster) areaMap.getZone(i).getMonster()).getMemberDied()) {
                        areaMap.getZone(i).setSlot4(new RawMeat());
                    }
                    ((SmallMonster) areaMap.getZone(i).getMonster()).setMemberDied(false);
                }
            }
        }
        // Iterates through all monsters to allow them to move. (Avoids making the same monster move twice.)
        for (int i = 0; i < currentMosters.size(); i++) {
            if (currentMosters.get(i).equals(questMonster)) {
                if (currentMosters.get(i).move(this, areaMap)) {
                    questMonster.roar(this, areaMap.getDirections());
                }
            } else {
                currentMosters.get(i).move(this, areaMap);
            }
        }
        checkMonster();
        hunter.setGuarding(false);
        hunter.hunterTurn();
        checkSpawnTimer();
        checkInterruption();
        checkMonster();
        // Regenerates the plants of each area.
        for (int i = 0; i < areaMap.getZones().size(); i++) {
            areaMap.getZone(i).getSlot1().replenish();
            areaMap.getZone(i).getSlot2().replenish();
            areaMap.getZone(i).getSlot3().replenish();
            if (areaMap.getZone(i).getSlot4() != null && areaMap.getZone(i).getSlot4().getHarvestNumber() <= 0) {
                areaMap.getZone(i).setSlot4(null);
            }
        }
        updateStatus();
        turnsPassed++;
        checkLeftoverSound();
    }

}
