import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Application extends StateBasedGame {

    public static final int WIDTH   = 1920;
    public static final int HEIGHT  = 1280;
    public static final int FPS     = 120;
    public static final double VERSION = 1.0;

    public Application(String appName) {
        super(appName);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new MainMenu());
        //this.addState(new Play());
        //this.addState(new Options());
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Application("Zombie escape" + VERSION));
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}