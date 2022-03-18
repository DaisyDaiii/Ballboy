package ballboy.model.entities.behaviour;

import ballboy.model.Level;
import ballboy.model.entities.DynamicEntity;
import ballboy.model.entities.utilities.Vector2D;

public class SquarecatBehaviourStrategy implements BehaviourStrategy {
    private final Level level;
    private static double offsetX = 0;
    private static double offsetY = 0;

    public SquarecatBehaviourStrategy(Level level) {
        this.level = level;
    }

    @Override
    public void behave(
            DynamicEntity cat,
            double frameDurationMilli) {

        double totalX = cat.getWidth() + level.getHeroWidth();
        double totalY = cat.getHeight() + level.getHeroHeight();

        if(offsetX < totalX && offsetY <= 0){
            offsetX += 1;
        }else if(offsetX >= totalX && offsetY < totalY){
            offsetY += 1;
        }else if(offsetX > 0 && offsetY >= totalY){
            offsetX -= 1;
        }else if(offsetX <= 0 && offsetY > 0){
            offsetY -= 1;
        }

        Vector2D vel = new Vector2D((level.getHeroX() - cat.getWidth()) + offsetX, (level.getHeroY() - cat.getHeight()) + offsetY);
        cat.setPosition(vel);
    }
}
