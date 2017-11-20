package snakeTests;

import java.awt.Point;
import org.junit.*;

import snakeCore.Dir;
import snakeCore.FoodFactory;
import snakeCore.GameState;
import snakeCore.HedgFactory;
import snakeCore.IObject;
import snakeCore.PillowFactory;
import snakeCore.StateParser;
import snakeCore.TeleportFactory;

public class CoreTests extends Assert {
    @Test
    public void test1D1Dying() throws Exception {
        GameState game = StateParser.makeGame("tests\\T1.txt");
        if (game.makeTick()) {
            throw new Exception();
        } ;
    }

    @Test
    public void test1D2Moving() throws Exception {
        GameState game = StateParser.makeGame("tests\\T1.txt");
        game.turnSnake1(Dir.Left);
        if (!game.makeTick() && game.getMap()[2][0] == '@') {
            throw new Exception();
        } ;
    }


    @Test
    public void test2SelfKilling() throws Exception {
        GameState game = StateParser.makeGame("tests\\T2.txt");
        if (game.makeTick()) {
            throw new Exception();
        } ;
    }

    @Test
    public void test3Tesseract() throws Exception {
        GameState game = StateParser.makeGame("tests\\T3.txt");
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 0 && game.getHead(game.getSnake()).y == 2)) {
            throw new Exception();
        } ;
    }

    @Test
    public void test4Growing() throws Exception {
        GameState game = StateParser.makeGame("tests\\T4.txt");
        IObject[] f = new FoodFactory().create(game, new Point[] {new Point(0, 1)});
        game.setObjs(f);
        if (!(game.makeTick() && game.getMap()[0][0] == '@')) {
            throw new Exception();
        } ;
    }

    @Test
    public void test5D1Teleport() throws Exception {
        GameState game = StateParser.makeGame("tests\\T5.txt");
        IObject[] t = (new TeleportFactory()).create(game,
                new Point[] {new Point(0, 1), new Point(1, 0)});
        game.setObjs(t);
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 1 && game.getHead(game.getSnake()).y == 1)) {
            throw new Exception();
        } ;
    }

    @Test
    public void test5D2TeleportTesseract() throws Exception {
        GameState game = StateParser.makeGame("tests\\T5.txt");
        IObject[] t = (new TeleportFactory()).create(game,
                new Point[] {new Point(0, 1), new Point(1, 0)});
        game.setObjs(t);
        game.turnSnake1(Dir.Right);
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 1 && game.getHead(game.getSnake()).y == 1)) {
            throw new Exception();
        } ;
    }


    @Test
    public void test6Hedg() throws Exception {
        for (int i = 0; i < 10; i++) {
            GameState game = StateParser.makeGame("tests\\T6.txt");
            IObject[] t = (new HedgFactory()).create(game,
                    new Point[] {new Point(0, 1), new Point(1, 0)});
            game.setObjs(t);
            if (!(t[0].getIcon() == 'W' && (game.makeTick() && game.getHead(game.getSnake()).x == 0 && game.getHead(game.getSnake()).y == 0))) {
                throw new Exception();
            } ;
        }
    }

    @Test
    public void test7Pillow() throws Exception {
        GameState game = StateParser.makeGame("tests\\T7.txt");
        IObject[] t = (new PillowFactory()).create(game, new Point[] {new Point(0, 1)});
        game.setObjs(t);
        game.makeTick();
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 0 && game.getHead(game.getSnake()).y == 0)) {
            throw new Exception();
        } ;
    }

    @Test
    public void test8SoftWall() throws Exception {
        GameState game = StateParser.makeGame("tests\\T8.txt");
        game.makeTick();
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 0 && game.getHead(game.getSnake()).y == 0)) {
            throw new Exception();
        } ;
        game.turnSnake1(Dir.Right);
        if (!(game.makeTick() && game.getHead(game.getSnake()).x == 1 && game.getHead(game.getSnake()).y == 0)) {
            throw new Exception();
        } ;
    }
}
