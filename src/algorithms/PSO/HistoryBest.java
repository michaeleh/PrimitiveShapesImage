package algorithms.PSO;

import java.awt.image.BufferedImage;

class HistoryBest {
    private double fitness;
    private BufferedImage image;

    void setIfBest(double score, BufferedImage image) {
        if (fitness < score) {
            fitness = score;
            this.image = image;
            System.out.println("New Best: " + fitness);
        }
    }

    BufferedImage getImage() {
        return image;
    }
}
