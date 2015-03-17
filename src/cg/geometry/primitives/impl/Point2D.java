/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cg.geometry.primitives.impl;

import cg.common.comparators.LexicographicComparator;
import cg.geometry.primitives.Point;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rrufai
 */
public class Point2D implements Point {

    private static Map<Point2D, String> map = new HashMap<>();
    private static int nextLetter = 0;
    private java.awt.geom.Point2D.Double point;
    private static double EPSILON = 1e-9;

    public Point2D(double x, double y) {
        point = new java.awt.geom.Point2D.Double(x, y);
    }

    @Override
    public double getX() {
        return point.getX();
    }

    @Override
    public double getY() {
        return point.getY();
    }

    @Override
    public void setLocation(double x, double y) {
        point.setLocation(x, y);
    }

    public double distanceSq(double px, double py) {
        return point.distanceSq(px, py);
    }

    public double distanceSq(Point pt) {
        return point.distanceSq(pt.getX(), pt.getY());
    }

    public double distance(double px, double py) {
        return point.distance(px, py);
    }

    @Override
    public double distance(Point pt) {
        return point.distance(pt.getX(), pt.getY());
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // should never happen
            throw new InternalError();
        }
    }

    @Override
    public int hashCode() {
        return getPoint().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point p2d = (Point) obj;
            return Math.abs(getX() - p2d.getX()) < EPSILON && Math.abs(getY() - p2d.getY()) < EPSILON;
        }
        return super.equals(obj);
    }

    /**
     * @return the point
     */
    @Override
    public java.awt.geom.Point2D getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(java.awt.geom.Point2D point) {
        this.point = new java.awt.geom.Point2D.Double(point.getX(), point.getY());
    }

    @Override
    public String toString() {
        return (getName() + "{" + point.x + ", " + point.y + "}");
    }

    @Override
    public int compareTo(Object obj) {
        return new LexicographicComparator<>().compare(this, (Point) obj);
    }

    @Override
    public String getName() {
        if (map.get(this) == null) {
            map.put(this, getNextLetter());

        }
        return map.get(this);
    }

 
    private static String  getNextLetter() {
        String name = "";
        for (int i = -1; i < nextLetter / 26; i++) {
            name += (char) ('A' + nextLetter % 26);
        }
        nextLetter++;
        return name;
    }
}
