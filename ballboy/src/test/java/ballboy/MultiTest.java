package ballboy;

import ballboy.model.*;
import ballboy.model.Memento.CreateMemento;
import ballboy.model.entities.ControllableDynamicEntity;
import ballboy.model.entities.DynamicEntity;
import ballboy.model.entities.StaticEntity;
import ballboy.model.factories.*;
import ballboy.model.levels.LevelImpl;
import ballboy.model.levels.PhysicsEngine;
import ballboy.model.levels.PhysicsEngineImpl;
import ballboy.model.observer.*;
import ballboy.view.EntityViewImpl;
import ballboy.view.GameWindow;
import ballboy.view.Labels.AddLabel;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;
import java.util.concurrent.Semaphore;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class MultiTest {
    private AddLabel add = new AddLabel();

    @BeforeAll
    public static void setupJavaFx() throws InterruptedException {
        Semaphore available = new Semaphore(0, true);
        Platform.startup(available::release);
        available.acquire();
    }

    @Test
    public void ObserverTest(){
        RedObserver red = new RedObserver(add.getLabel("red"));
        Subject subject = new Subject();
        subject.attach(red);
        assertEquals(red.getScore(), 0);
        subject.notifyObserver(3);
        assertEquals(red.getScore(), 3);
        subject.notifyObserver(3);
        assertEquals(red.getScore(), 6);

        subject.resetObserver(5);
        assertEquals(red.getScore(), 5);

        BlueObserver blue  = new BlueObserver(add.getLabel("blue"));
        assertEquals(blue.getScore(), 0);
        blue.update(3);
        assertEquals(blue.getScore(), 3);
        blue.update(1);
        assertEquals(blue.getScore(), 4);
        blue.reset(10);
        assertEquals(blue.getScore(), 10);

        GreenObserver green  = new GreenObserver(add.getLabel("green"));
        assertEquals(green.getScore(), 0);
        green.update(3);
        assertEquals(green.getScore(), 3);
        green.update(1);
        assertEquals(green.getScore(), 4);
        green.reset(10);
        assertEquals(green.getScore(), 10);

        LevelObserevr curr  = new LevelObserevr(add.getLabel("level"));
        assertEquals(curr.getScore(), 0);
        curr.update(3);
        assertEquals(curr.getScore(), 3);
        curr.update(1);
        assertEquals(curr.getScore(), 4);
        curr.reset(10);
        assertEquals(curr.getScore(), 10);

        PreviousObserver prev  = new PreviousObserver(add.getLabel("pre"));
        assertEquals(prev.getScore(), 0);
        prev.update(3);
        assertEquals(prev.getScore(), 3);
        prev.update(1);
        assertEquals(prev.getScore(), 4);
        prev.reset(10);
        assertEquals(prev.getScore(), 10);
    }

    @Test
    public void LevelTest(){
        EntityFactoryRegistry entityFactoryRegistry = new EntityFactoryRegistry();
        entityFactoryRegistry.registerFactory("cloud", new CloudFactory());
        entityFactoryRegistry.registerFactory("enemy", new EnemyFactory());
        entityFactoryRegistry.registerFactory("background", new StaticEntityFactory(Entity.Layer.BACKGROUND));
        entityFactoryRegistry.registerFactory("static", new StaticEntityFactory(Entity.Layer.FOREGROUND));
        entityFactoryRegistry.registerFactory("finish", new FinishFactory());
        entityFactoryRegistry.registerFactory("hero", new BallboyFactory());
        entityFactoryRegistry.registerFactory("squarecat", new SquarecatFactory());

        PhysicsEngine engine = new PhysicsEngineImpl(17);

        ConfigurationParser configuration = new ConfigurationParser();
        JSONObject parsedConfiguration = null;
        try {
            parsedConfiguration = configuration.parseConfig("config.json");
        } catch (ConfigurationParseException e) {
            System.out.println(e);
        }
        JSONArray levelConfigs = (JSONArray) parsedConfiguration.get("levels");
        JSONObject levelConfig = (JSONObject) levelConfigs.get(0);

        Level level = new LevelImpl(levelConfig, engine, entityFactoryRegistry, 17);
        GameEngine gameEngine = new GameEngineImpl(level);

        assertTrue(level.getLevelHeight() == 620.0);
        assertTrue(level.getLevelWidth() == 2000.0);
        assertTrue(level.getHeroX() == 150.0);
        assertTrue(level.getHeroY() == 300.0);
        assertTrue(level.getHeroHeight() == 50.0);
        assertFalse(level.getHeroWidth() == 29);
        assertTrue(level.getGravity() == 700.0);
        assertTrue(level.getFloorHeight()== 600.0 );

        level.boostHeight(); // test up/down/left/right
        level.update();
        assertFalse(level.getHeroY() == 300.0);
        level.dropHeight();
        level.update();
        assertFalse(level.getHeroY() == 300.0);
        level.moveLeft();
        level.update();
        assertTrue(level.getHeroX() == 150.0);
        level.moveRight();
        level.update();
        assertFalse(level.getHeroX() == 150.0);

        level.resetHero(); // test reset hero
        level.update();
        assertTrue(level.getHeroX() == 150.0);
        assertTrue(level.getHeroY() == 300.0);

        ChangeLevel cl = new ChangeLevel(levelConfigs, 0); // test finish and change to next level
        ChangeScore cs = new ChangeScore(add);
        level.setScore(cs);
        level.setChangeLevel(cl);
        level.finish();
        assertTrue(level.getChangeLevel().getCurr() == 1);

        CreateMemento mem = new CreateMemento(); // test memento
        double x = level.getHeroX();
        mem.create(gameEngine);
        level.moveRight();
        level.update();
        level.moveRight();
        level.update();
        assertFalse(x == level.getHeroX());
        mem.getMem(gameEngine);
        assertTrue(x == level.getHeroX());

        List<Entity> entities = level.getEntities();
        ControllableDynamicEntity hero = (ControllableDynamicEntity<DynamicEntity>) entities.get(entities.size()-3);
        Entity cat = entities.get(entities.size()-1);
        Entity finish = entities.get(entities.size()-2);
        Entity enemy = entities.get(8);
        assertFalse(hero.hashCode()== 1671236868);
        assertTrue(hero.getLayer() ==  Entity.Layer.FOREGROUND);
        assertFalse(hero.collidesWith(finish));


        StaticEntity stat = (StaticEntity) entities.get(4);
        assertTrue(stat.getPosition().getX() == 400);
        assertTrue(stat.getPosition().getY() == 565);
        assertTrue(stat.getHeight() == 40);
        assertTrue(stat.getWidth() == 40*new Image("boulder.png").getWidth()/new Image("boulder.png").getHeight());

        assertTrue(hero.getLayer() ==  Entity.Layer.FOREGROUND);

        EntityViewImpl view = new EntityViewImpl(cat);
        assertTrue(view.matchesEntity(cat));
        view.markForDelete();
        assertTrue(view.isMarkedForDelete());

        GameWindow window = new GameWindow(gameEngine, 640, 400, 17);
        window.run();
    }

    @AfterAll
    public static void cleanupJavaFx() {
        Platform.exit();
    }

}
