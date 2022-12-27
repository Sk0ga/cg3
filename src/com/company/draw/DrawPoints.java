package com.company.draw;

import com.company.Point;

import java.awt.*;
import java.util.List;

public class DrawPoints {

    private final Graphics2D g2;
    private final int centerX;
    private final int centerY;
    private final int step;
    private final List<Point> listPoints;

    public DrawPoints(Graphics2D g2, int centerX, int centerY, int step, List<Point> listPoints) {
        this.g2 = g2;
        this.centerX = centerX;
        this.centerY = centerY;
        this.step = step;
        this.listPoints = listPoints;
    }

    private void plot(int x, int y, int pointSize) {
        int shift = pointSize / 2;
        g2.fillOval(x - shift, y - shift, pointSize, pointSize);
    }

    void connectingPoints() {
        g2.setStroke(new BasicStroke(3));
        for (int i = 0; i < listPoints.size() - 1; i++) {
            plot((int) Math.round(centerX + listPoints.get(i).getX() * step), (int) Math.round(centerY - listPoints.get(i).getY() * step), 4);
        }
        g2.setColor(Color.black);
    }
}