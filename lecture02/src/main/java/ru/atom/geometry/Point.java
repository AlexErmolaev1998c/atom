package ru.atom.geometry;

/**
 * Template class for
 */
public class Point implements Collider /* super class and interfaces here if necessary */ {
    // fields
    // and methods
    protected int x, y;

    /**
     * @param o - other object to check equality with
     * @return true if two points are equal and not null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // cast from Object to Point
        //Point point = (Point) o;

        // your code here
        return true;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    @Override
    public boolean isColliding(Collider other) {
        if (other instanceof Point) {
            Point p = (Point) other;
            if (this.x == p.x && this.y == p.y) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
