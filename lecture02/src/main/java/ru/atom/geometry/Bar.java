package ru.atom.geometry;

public class Bar implements Collider {

    private Point leftUpPoint;
    private Point rightBottomPoint;

    public Bar(Point leftUpPoint, Point rightBottomPoint) {
        this.leftUpPoint = leftUpPoint;
        this.rightBottomPoint = rightBottomPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public boolean isColliding(Collider other) {
        if (other instanceof Point) {
            Point point = (Point) other;
            if (point.x >= this.leftUpPoint.x && point.x <= this.rightBottomPoint.x && point.y
                    >= this.leftUpPoint.y && point.y <= this.rightBottomPoint.y) {
                return true;
            } else {
                return false;
            }
        }
        if (other instanceof Bar) {
            Bar bar = (Bar) other;
            int thisMinX;
            int thisMaxX;
            int barMaxX;
            int barMinX;
            int thisMinY;
            int thisMaxY;
            int barMaxY;
            int barMinY;
            if (this.leftUpPoint.x < this.rightBottomPoint.x) {
                thisMinX = this.leftUpPoint.x;
                thisMaxX = this.rightBottomPoint.x;
            } else {
                thisMinX = this.rightBottomPoint.x;
                thisMaxX = this.leftUpPoint.x;
            }
            if (bar.leftUpPoint.x < bar.rightBottomPoint.x) {
                barMinX = bar.leftUpPoint.x;
                barMaxX = bar.rightBottomPoint.x;
            } else {
                barMinX = bar.rightBottomPoint.x;
                barMaxX = bar.leftUpPoint.x;
            }
            if (this.leftUpPoint.y < this.rightBottomPoint.y) {
                thisMinY = this.leftUpPoint.y;
                thisMaxY = this.rightBottomPoint.y;
            } else {
                thisMinY = this.rightBottomPoint.x;
                thisMaxY = this.leftUpPoint.y;
            }
            if (bar.leftUpPoint.y < bar.rightBottomPoint.y) {
                barMinY = bar.leftUpPoint.y;
                barMaxY = bar.rightBottomPoint.y;
            } else {
                barMinY = bar.rightBottomPoint.y;
                barMaxY = bar.leftUpPoint.y;
            }
            if (barMaxX < thisMinX || barMaxY < thisMinY || thisMaxX < barMinX || thisMaxY < barMinY) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
