package storm2013.commands.LEDcommands;

import storm2013.Robot;

/**
 * Flashes the LEDs, getting faster as the wheel approaches the target speed.
 */
public class SetModeSpinningUp extends FlashOnAndOff {
    public SetModeSpinningUp(){
        super(Color.SPINNING_UP);
    }
    
    private static final double PERIOD_SCALAR = 1.0/2500;

    public void execute() {
        setPeriod(Math.abs(Robot.shooter.getPIDController().getError())*PERIOD_SCALAR);
        super.execute();
    }
}
