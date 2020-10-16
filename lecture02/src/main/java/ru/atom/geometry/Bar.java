package ru.atom.geometry;

public class Bar implements Collider {

    private Point leftUpPoint;
    private Point rightBottomPoint;

    public Bar(Point leftUpPoint, Point rightBottomPoint) {
        this.leftUpPoint = leftUpPoint;
        this.rightBottomPoint = rightBottomPoint;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public boolean isColliding(Collider other) {
        if (other instanceof Point){
            Point p = (Point)other;
            if (p.x >= this.leftUpPoint.x && p.x <= this.rightBottomPoint.x && p.y >= this.leftUpPoint.y && p.y <= this.rightBottomPoint.y){
                return true;
            } else {
                return false;
            }
        }
        if (other instanceof Bar){
            Bar b = (Bar)other;
            int thisMinX, thisMaxX, bMaxX, bminX, thisMinY, thisMaxY, bMaxY, bminY;
            if (this.leftUpPoint.x < this.rightBottomPoint.x){
                thisMinX = this.leftUpPoint.x;
                thisMaxX = this.rightBottomPoint.x;
            }
            else {
                thisMinX = this.rightBottomPoint.x;
                thisMaxX = this.leftUpPoint.x;
            }
            if (b.leftUpPoint.x < b.rightBottomPoint.x){
                bminX = b.leftUpPoint.x;
                bMaxX = b.rightBottomPoint.x;
            }
            else {
                bminX = b.rightBottomPoint.x;
                bMaxX = b.leftUpPoint.x;
            }
            if (this.leftUpPoint.y < this.rightBottomPoint.y){
                thisMinY = this.leftUpPoint.y;
                thisMaxY = this.rightBottomPoint.y;
            }
            else {
                thisMinY = this.rightBottomPoint.x;
                thisMaxY = this.leftUpPoint.y;
            }
            if (b.leftUpPoint.y < b.rightBottomPoint.y){
                bminY = b.leftUpPoint.y;
                bMaxY = b.rightBottomPoint.y;
            }
            else {
                bminY = b.rightBottomPoint.y;
                bMaxY = b.leftUpPoint.y;
            }
            if (bMaxX < thisMinX || bMaxY < thisMinY || thisMaxX < bminX || thisMaxY < bminY){
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
