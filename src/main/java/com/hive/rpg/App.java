package com.hive.rpg;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        boolean isRunning;
        int framesPerSecond = 60;
        int timePerLoop = 1000000000 / framesPerSecond;
        Screen screen;
        screen = new Screen(500,500);
        isRunning = true;

        while(isRunning) {
            long startTime = System.nanoTime();

            screen.inputHandler.processInput(); //60 fps input processing (actual game can be a manual game loop)

            long endTime = System.nanoTime();

            long sleepTime = timePerLoop - (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000);
                } catch (InterruptedException e) {
                    isRunning = false;
                }
            }
        }
    }
}
