package algorithms.PSO;

import java.awt.image.BufferedImage;

public class HistoryBest {
    private int fitness;
    private BufferedImage image;

    public void setIfBest(int score, BufferedImage image) {
        if (fitness < score) {
            fitness = score;
            this.image = image;
            System.out.println("New Best: " + fitness);
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
