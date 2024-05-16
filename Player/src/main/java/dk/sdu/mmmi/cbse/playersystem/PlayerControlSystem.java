package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import javafx.scene.layout.Pane;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;


public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        if (world.getEntities(Player.class).size() > 0) {
            for (Entity player : world.getEntities(Player.class)) {
                if (player.getHealth() <= 0 && !gameData.isGameOver()) {
                    List<Score> highScore = gameData.getHighScore();
                    Score score = new Score(Date.from(Instant.now()), gameData.getScore(), gameData.getPlayerName());
                    highScore.add(score);
                    System.out.println("High score for " + score.getDate() + " : " + score.getPlayerName() + " : " + score.getScore());
                    gameData.setHighScore(highScore);
                    player.setDead(true);
                    Pane menu = gameData.getMenu();
                    menu.setVisible(true);
                    menu.setDisable(false);
                    menu.toFront();
                    menu.setTranslateX((gameData.getDisplayWidth()/2)-(menu.getPrefWidth()/2));
                    menu.setTranslateY((gameData.getDisplayHeight()/2)-(menu.getPrefWidth()/2));
                    gameData.setGameOver(true);
                    gameData.setMenu(menu);
                    break;
                } else if (gameData.getMenu().isDisabled()){
                    if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                        player.setRotation(player.getRotation() - player.getRotationSpeed());
                    }
                    if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                        player.setRotation(player.getRotation() + player.getRotationSpeed());
                    }
                    if (gameData.getKeys().isDown(GameKeys.UP)) {
                        double changeX = Math.cos(Math.toRadians(player.getRotation()));
                        double changeY = Math.sin(Math.toRadians(player.getRotation()));
                        player.setX(player.getX() + changeX * player.getSpeed());
                        player.setY(player.getY() + changeY * player.getSpeed());
                    }
                    if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                        player.setDamage(player.getDamage()*((gameData.getScore()/1000)+1));
                        if(player.getShotTimer() >= player.getMaxShotTimer()) {
                            getBulletSPIs().stream().findFirst().ifPresent(
                                spi -> {
                                    world.addEntity(spi.createBullet(player, gameData));
                                }
                            );
                            player.setShotTimer(0);
                        }
                    }

                    if (player.getX() < 0) {
                        player.setX(gameData.getDisplayWidth());
                    }
                    if (player.getX() > gameData.getDisplayWidth()) {
                        player.setX(0);
                    }
                    if (player.getY() < 0) {
                        player.setY(gameData.getDisplayHeight());
                    }
                    if (player.getY() > gameData.getDisplayHeight()) {
                        player.setY(0);
                    }
                }
                if (player.getHealth() <= 0) player.setDead(true);
                if(player.getShotTimer() == gameData.getImmortalTime()) {
                    player.setImmortal(false);
                }
                else player.setShotTimer(player.getShotTimer()+1);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
