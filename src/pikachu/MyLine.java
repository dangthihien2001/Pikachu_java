/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pikachu;

/**
 *
 * @author HienNhu
 */
public class MyLine {

    /*
    Attributes
    */

    private Point p1;
    private Point p2;

    /*
    Contrustor
     */
    public MyLine(Point p1, Point p2) {
        super();
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;

    }

    @Override
    public String toString() {
        return p1 +"and"+ p2;
    }
}
