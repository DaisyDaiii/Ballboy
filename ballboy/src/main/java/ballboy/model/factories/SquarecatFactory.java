package ballboy.model.factories;

import ballboy.ConfigurationParseException;
import ballboy.model.Entity;
import ballboy.model.Level;
import ballboy.model.entities.DynamicEntityImpl;
import ballboy.model.entities.behaviour.AggressiveEnemyBehaviourStrategy;
import ballboy.model.entities.behaviour.FloatingCloudBehaviourStrategy;
import ballboy.model.entities.behaviour.PassiveEntityBehaviourStrategy;
import ballboy.model.entities.behaviour.SquarecatBehaviourStrategy;
import ballboy.model.entities.collision.PassiveCollisionStrategy;
import ballboy.model.entities.utilities.*;
import javafx.scene.image.Image;
import org.json.simple.JSONObject;

public class SquarecatFactory implements EntityFactory {
    @Override
    public Entity createEntity(Level level, JSONObject config) {
        try{
            String imageName = (String) config.getOrDefault("image", "squarecat.png");

            Image image = new Image(imageName);
            double width = image.getWidth() / 30;
            double height = image.getHeight() / 30;

            double startX = level.getHeroX() - width;
            double startY = level.getHeroY() - height;

            Vector2D startingPosition = new Vector2D(startX, startY);

            KinematicState kinematicState = new KinematicStateImpl.KinematicStateBuilder()
                    .setPosition(startingPosition)
                    .build();

            AxisAlignedBoundingBox volume = new AxisAlignedBoundingBoxImpl(
                    startingPosition,
                    height,
                    width
            );

            return new DynamicEntityImpl(
                    kinematicState,
                    volume,
                    Entity.Layer.BACKGROUND,
                    new Image(imageName),
                    new PassiveCollisionStrategy(),
                    new SquarecatBehaviourStrategy(level)
            );
        }catch (Exception e) {
            throw new ConfigurationParseException(
                    String.format("Invalid square cat entity configuration | %s | %s", config, e));
        }

    }
}
