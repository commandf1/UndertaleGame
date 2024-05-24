package listener;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    public static boolean isCollided(BarAttack barAttack, Rectangle boxAttackHitBox) {
        return Intersector.overlaps(barAttack.getHitBox(), boxAttackHitBox);
    }


    public static boolean isCollided(Heart heart, Label sans) {
        Rectangle rectangle = new Rectangle(sans.getX(), sans.getY(), sans.getWidth(), sans.getHeight());
        return Intersector.overlaps(heart.getHitBox(), rectangle);
    }
}
