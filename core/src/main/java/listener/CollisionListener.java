package listener;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.game.*;


public class CollisionListener {

    public static boolean isCollided(Heart heart, FightOp fightOp) {
        return Intersector.overlaps(heart.getHitBox(), fightOp.getHitBox());
    }

    public static boolean isCollided(Heart heart, ActOp actOp) {
        return Intersector.overlaps(heart.getHitBox(), actOp.getHitBox());
    }

    public static boolean isCollided(Heart heart, ItemOp itemOp) {
        return Intersector.overlaps(heart.getHitBox(), itemOp.getHitBox());
    }

    public static boolean isCollided(Heart heart, MercyOp mercyOp) {
        return Intersector.overlaps(heart.getHitBox(), mercyOp.getHitBox());
    }

    public static boolean isCollided(Heart heart, ObjetsItems objectItem) {
        return Intersector.overlaps(heart.getHitBox(), objectItem.getHitBox());
    }

    public static boolean isCollided(Heart heart, Rectangle rashioLaser) {
        return Intersector.overlaps(heart.getHitBox(), rashioLaser);
    }






}
