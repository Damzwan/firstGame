import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Application extends StateBasedGame {

    public static final int WIDTH   = 1920;
    public static final int HEIGHT  = 1080;
    private static final int FPS     = 120;
    private static final double VERSION = 1.0;
    private static AppGameContainer app;


    public Application(String appName) {
        super(appName);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new MainMenu());
        this.addState(new Tutorial());
        this.addState(new Options());
        this.addState(new WorldMenu());
    }

    public static void main(String[] args) {
        try {
            app = new AppGameContainer(new Application("Zombie escape" + VERSION));
            app.setDisplayMode(WIDTH, HEIGHT, true);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static void setFullScreen(boolean isFullScreen) throws SlickException {
        app.setDisplayMode(WIDTH, HEIGHT, isFullScreen);
    }

    public static void setShowFPS(boolean isFps){
        app.setShowFPS(isFps);
    }

    public static void setVSync(boolean isVSync){
        app.setVSync(isVSync);
    }


}