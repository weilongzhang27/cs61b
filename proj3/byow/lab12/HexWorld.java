package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 40;
    private static final long SEED = 28;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Fills up a given world without anything ...
     */
    private static void fillNOTHING(TETile[][] world) {
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    private static void addHexagon(TETile[][] world, int xLowerLeft, int yLowerLeft, int s, TETile t){
        for(int nCall=0;nCall<s;nCall++) {
            int width=width(nCall,s);
            fillLine(world,xLowerLeft-(s-1)+nCall,yLowerLeft+(s-1)+1+nCall,width,t);
            fillLine(world,xLowerLeft-(s-1)+nCall,yLowerLeft+(s-1)-nCall,width,t);
        }


    }

    private static int width(int nCall,int s){
        return s+2*(s-1)-2*nCall;
    }

    private static void fillLine(TETile[][] world, int x, int y, int width, TETile t){
        for (int i = 0; i < width; i += 1) {
            world[x + i][y] = t;
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.GRASS;
            case 2: return Tileset.TREE;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.FLOWER;
            default: return Tileset.NOTHING;
        }
    }

    private static void randomAddHexagon(TETile[][] world, int xLowerLeft, int yLowerLeft,int s) {
        TETile tile=randomTile();
        addHexagon(world, xLowerLeft, yLowerLeft, s, tile);
    }

    private static void drawHexagonColumn(int col, TETile[][] world, int xLowerLeft, int yLowerLeft,int s) {
        for (int i = 0; i < col; i++) {
            TETile tile = randomTile();
            addHexagon(world, xLowerLeft, yLowerLeft+2*s*i, s, tile);
        }
    }

    private static void tessellate(TETile[][] world, int xLowerLeft, int yLowerLeft,int s){
        drawHexagonColumn(5, world, xLowerLeft, yLowerLeft,s);
        drawHexagonColumn(4, world, xLowerLeft-(2*s-1), yLowerLeft+s,s);
        drawHexagonColumn(4, world, xLowerLeft+(2*s-1), yLowerLeft+s,s);
        drawHexagonColumn(3, world, xLowerLeft-2*(2*s-1), yLowerLeft+2*s,s);
        drawHexagonColumn(3, world, xLowerLeft+2*(2*s-1), yLowerLeft+2*s,s);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexWorld = new TETile[WIDTH][HEIGHT];
        fillNOTHING(hexWorld);

        tessellate(hexWorld,50,0,3);

        ter.renderFrame(hexWorld);
    }

}
