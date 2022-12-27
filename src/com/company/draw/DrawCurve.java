package com.company.draw;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.company.Methods;
import com.company.method.BSpline;
import com.company.method.Bezier;
import com.company.Point;

public class DrawCurve {

    private final Graphics2D g2;
    private final int centerX;
    private final int centerY;
    private final int step;
    private final List<Point> pointsCurve;
    private final List<Point> changedPoints = new ArrayList<>();
    private final Methods method;
    //задаю кривую
    public DrawCurve(Graphics2D g2, int centerX, int centerY, int step, List<Point> pointsCurve, Methods method) {
        this.g2 = g2;
        this.centerX = centerX;
        this.centerY = centerY;
        this.step = step;
        this.pointsCurve = pointsCurve;
        this.method = method;
    }
    //отрисовка кривой, в зависимости от выбранного метода
    public void paintCurve() {
        List<Point> pointsToDrawCurve = new ArrayList<>();
        changedPoints.clear();
        g2.setStroke(new BasicStroke(1));
        switch (method) {
            case BEZIER -> {
                if (pointsCurve.size() > 2) {
                    Bezier bezier = new Bezier(pointsCurve);
                    bezier.calculation();
                    pointsToDrawCurve = bezier.getAddPoints();
                }
            }
            case BSPLINE -> {
                if (pointsCurve.size() > 3) {
                    BSpline bSpline = new BSpline(pointsCurve);
                    bSpline.calculation();
                    pointsToDrawCurve = bSpline.getAddPoints();
                }
            }
        }
        g2.setColor(new Color(0, 0, 0));
        DrawPoints dp = new DrawPoints(g2, centerX, centerY, step, pointsToDrawCurve);
        dp.connectingPoints();
        drawPoints(pointsCurve);
    }
    //отрисовка точек
    private void drawPoints(List<Point> list) {
        for (Point value : list) {
            Point coordinates = new Point(0, 0);
            coordinates.setX(value.getX() * step + centerX);
            coordinates.setY(centerY - value.getY() * step);

            g2.setColor(new Color(213, 1, 35));
            g2.fillOval((int)Math.round(coordinates.getX()) - 2, (int)Math.round(coordinates.getY()) - 2, 4, 4);
            changedPoints.add(coordinates);
        }
    }

}