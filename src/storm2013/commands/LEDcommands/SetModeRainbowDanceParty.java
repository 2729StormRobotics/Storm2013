package storm2013.commands.LEDcommands;

/** Flashes a bunch of colors very quickly. */
public class SetModeRainbowDanceParty extends Flash {
    
    private static final Color[] colors = {new Color(255,0,0), new Color(255,255,0), new Color(0,255,0),
                                           new Color(0,255,255), new Color(0,0,255), new Color(255,0,255)};
    
    public SetModeRainbowDanceParty(){
        super(colors,1.0/20);
    }
}
