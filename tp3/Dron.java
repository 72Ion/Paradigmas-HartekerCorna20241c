package dron;

import java.util.Objects;

public class Dron {
    private int speed = 0;
    private char heading = 'N';
    private String probe = "recovered";

    public int speed() {
        return speed;
    }

    public int heading() {
        return heading;
    }

    public String probe() {
        return probe;
    }

    public Dron process(char input) {

        if (input == 'd' && speed!=0) {
            probe = "deployed";
        }
        if (input == 'f') {
            probe = "recovered";
        }

        if (Objects.equals(probe, "deployed") && (input == 'i'||input == 's'||input == 'r'||input == 'l')) {
            throw new RuntimeException("Probe Destroyed");
        }
        if (Objects.equals(probe, "recovered")) {
            if (input == 'i') {
                speed++;
            }
            if (input == 's') {
                if (speed == 0) {
                    throw new RuntimeException("Too Slow");
                }
                speed--;
            }
            if (input == 'r') {
                if (heading == 'N') {
                    heading = 'E';
                } else if (heading == 'E') {
                    heading = 'S';
                } else if (heading == 'S') {
                    heading = 'W';
                } else if (heading == 'W') {
                    heading = 'N';
                }
            }

            if (input == 'l') {
                if (heading == 'N') {
                    heading = 'W';
                } else if (heading == 'W') {
                    heading = 'S';
                } else if (heading == 'S') {
                    heading = 'E';
                } else if (heading == 'E') {
                    heading = 'N';
                }
            }
        }
        return this;
    }
}
