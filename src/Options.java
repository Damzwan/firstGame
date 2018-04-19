import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Options extends BasicGameState{
    private String on, off;
    private TrueTypeFont font;
    private boolean isFullScreen, showFps, isVSync, isMusic;
    private String status1, status2, status3, status4;
    private Image map;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
         font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 50), false);
         on = "ON";
         off = "OFF";
         isMusic = true;
         isFullScreen = true;
         showFps = true;

         status1 = on;
         status2 = on;
         status3 = off;
         status4 = on;

         map = new Image("images/mainMenu.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.draw();

        g.setFont(font);
        String fullScreen = "Full Screen(1)";
        g.drawString(fullScreen, Application.WIDTH/2 - (font.getWidth(fullScreen)/2) - 200, Application.HEIGHT/2 - (font.getHeight(fullScreen) + 100));
        g.drawString(status1, Application.WIDTH/2 - (font.getWidth(status1)/2) + 200, Application.HEIGHT/2 - (font.getHeight(status1) + 100));
        String fps = "Show FPS(2)";
        g.drawString(fps, Application.WIDTH/2 - (font.getWidth(fps)/2) - 200, Application.HEIGHT/2 - (font.getHeight(fps)));
        g.drawString(status2, Application.WIDTH/2 - (font.getWidth(status2)/2) + 200, Application.HEIGHT/2 - (font.getHeight(status2)));
        String vSync = "Vsync(3)";
        g.drawString(vSync, Application.WIDTH/2 - (font.getWidth(vSync)/2) - 200, Application.HEIGHT/2 - (font.getHeight(vSync) - 100));
        g.drawString(status3, Application.WIDTH/2 - (font.getWidth(status3)/2) + 200, Application.HEIGHT/2 - (font.getHeight(status3) - 100));
        String musico = "Music(4)";
        g.drawString(musico, Application.WIDTH/2 - (font.getWidth(musico)/2) - 200, Application.HEIGHT/2 - (font.getHeight(musico) - 200));
        g.drawString(status4, Application.WIDTH/2 - (font.getWidth(status4)/2) + 200, Application.HEIGHT/2 - (font.getHeight(status4) - 200));
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int _delta) throws SlickException {

        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());

        if (gc.getInput().isKeyPressed(Input.KEY_1)){
            if (!isFullScreen){
                isFullScreen = true;
                status1 = on;
            }
            else{
                isFullScreen = false;
                status1 = off;
            }
            Application.setFullScreen(isFullScreen);
        }

        if (gc.getInput().isKeyPressed(Input.KEY_3)){
            if (!isVSync){
                isVSync = true;
                status3 = on;
            }
            else{
                isVSync = false;
                status3 = off;
            }
            Application.setVSync(isVSync);
        }

        if (gc.getInput().isKeyPressed(Input.KEY_2)){
            if (!showFps){
                showFps = true;
                status2 = on;
            }
            else{
                showFps = false;
                status2 = off;
            }
            Application.setShowFPS(showFps);
        }

        if (gc.getInput().isKeyPressed(Input.KEY_4)){
            if (!isMusic){
                isMusic = true;
                status4 = on;
                MainMenu.backgroundMusic.resume();
            }
            else{
                isMusic = false;
                status4 = off;
                MainMenu.backgroundMusic.pause();
            }
        }
    }
}
