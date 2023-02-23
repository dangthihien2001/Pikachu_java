/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pikachu;

import java.util.ArrayList;
import java.util.Random;

import java.lang.Exception;
import java.util.Collections;

/**
 *
 * @author HienNhu
 */
public class Controller {

	private int row;
	private int col;
	private int[][] matrix;
//    PikachuPanel panel;
	PikachuFrame frame;

	public Controller(PikachuFrame frame, int row, int col) {
		this.frame = frame;
		this.row = row;
		this.col = col;
		System.out.println("row:" + row + "," + "col: " + col);
		CreateMatrix();
		this.ShowMatrix();
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	// createMatrix
	private void CreateMatrix() {
		matrix = new int[row][col];
		Random random = new Random();
		int img = 25;
		int max = 20;
		int array[] = new int[img + 1];

		ArrayList<Point> l_point = new ArrayList();

		// Matrix Wrap =0
		for (int i = 0; i < col; i++) {
			matrix[0][i] = matrix[row - 1][i] = 0;
		}
		for (int i = 0; i < row; i++) {
			matrix[i][0] = matrix[i][col - 1] = 0;
		}

		// add Points to l_point with the corresponding position of the Matrix
		// l_point has the same number of elements as the Matrix
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < col - 1; j++) {
				l_point.add(new Point(i, j));
			}
		}
		int i = 0;
		do {
			int imgIndex = random.nextInt(img) + 1;
			if (array[imgIndex] < max) {
				array[imgIndex] += 2;
				for (int j = 0; j < 2; j++) {
					try {
						int size = l_point.size();
						int p_Index = random.nextInt(size);
						matrix[l_point.get(p_Index).getX()][l_point.get(p_Index).getY()] = imgIndex;
						l_point.remove(p_Index);
					} catch (Exception e) {
					}
				}
				i++;
			}

		} while (i < row * col / 2);
	}

	// show matrix
	public void ShowMatrix() {
		for (int i = 1; i < row - 1; i++) {
			for (int j = 1; j < col - 1; j++) {
				System.out.printf("%3d", matrix[i][j]);
			}
			System.out.println("");
		}
	}

	// Alogrithm
	private boolean CheckLineX(int y1, int y2, int x) {
		System.out.println("Check Line X");
		int maxY = Math.max(y1, y2);
		int minY = Math.min(y1, y2);
		for (int i = minY + 1; i < maxY; i++) {
			if (matrix[x][i] != 0) {
				return false;
			}
		}
		return true;
	}

	private boolean CheckLineY(int x1, int x2, int y) {
		System.out.println("Check Line Y");
		int maxX = Math.max(x1, x2);
		int minX = Math.min(x1, x2);
		for (int i = minX + 1; i < maxX; i++) {
			if (matrix[i][y] != 0) {
				return false;
			}
		}
		return true;
	}

	private boolean CheckRectX(Point p1, Point p2) {
		// find point have p min and p max
		System.out.println("Check rect x ");
		Point pMinY = p1;
		Point pMaxY = p2;
		if (p1.getY() > p2.getY()) {
			pMinY = p2;
			pMaxY = p1;
		}
		for (int y = pMinY.getY(); y <= pMaxY.getY(); y++) {
			if (y > pMinY.getY() && matrix[pMinY.getX()][y] != 0) {
				return false;
			}
			// check two line
			if ((matrix[pMaxY.getX()][y] == 0 || y == pMaxY.getY()) && CheckLineY(pMinY.getX(), pMaxY.getX(), y)
					&& CheckLineX(y, pMaxY.getY(), pMaxY.getX())) {
				System.out.println("Rect X");
				System.out.println("(" + pMinY.getX() + "," + pMinY.getY() + ") -> (" + pMinY.getX() + "," + y
						+ ") -> (" + pMaxY.getX() + "," + y + ") -> (" + pMaxY.getX() + "," + pMaxY.getY() + ")");
				return true;
			}
		}
		return false;
	}

	private boolean CheckRectY(Point p1, Point p2) {
		System.out.println("Check Rect X");
		Point pMinX = p1;
		Point pMaxX = p2;
		if (p1.getX() > p2.getX()) {
			pMinX = p2;
			pMaxX = p1;
		}

		for (int x = pMinX.getX(); x <= pMaxX.getX(); x++) {
			if (x > pMinX.getX() && matrix[x][pMinX.getY()] != 0) {
				return false;
			}
			if ((matrix[x][pMaxX.getY()] == 0 || x == pMaxX.getX()) && CheckLineX(pMinX.getY(), pMaxX.getY(), x)
					&& CheckLineY(x, pMaxX.getX(), pMaxX.getY())) {
				System.out.println("Rect x");
				System.out.println("(" + pMinX.getX() + "," + pMinX.getY() + "-> (" + x + "," + pMinX.getY() + ") -> ("
						+ x + "," + pMaxX.getY() + ")" + pMaxX.getX() + "," + pMaxX.getY() + ")");
				return true;
			}
		}
		return false;
	}

	private boolean CheckMoreLineX(Point p1, Point p2, int type) {
		System.out.println("check More Line X");
		// find point have p min
		Point pMinY = p1;
		Point pMaxY = p2;
		if (p1.getY() > p2.getY()) {
			pMinY = p2;
			pMaxY = p1;
		}
		// find line and y begin
		int y = pMaxY.getY() + type;
		int row = pMinY.getX();
		int ColFinish = pMaxY.getY();
		if (type == -1) {
			ColFinish = pMinY.getY();
			y = pMinY.getY() + type;
			row = pMaxY.getX();
			System.out.println("colFinish = " + ColFinish);
		}
		// find column finish of line
		// check more
		if ((matrix[row][ColFinish] == 0 || pMinY.getY() == pMaxY.getY())
				&& CheckLineX(pMinY.getY(), pMaxY.getY(), row)) {
			while (matrix[pMinY.getX()][y] == 0 && matrix[pMaxY.getX()][y] == 0) {
				if (CheckLineY(pMinY.getX(), pMaxY.getX(), y)) {

					System.out.println("TH X " + type);
					System.out.println("(" + pMinY.getX() + "," + pMinY.getY() + ") -> (" + pMinY.getX() + "," + y
							+ ") -> (" + pMaxY.getX() + "," + y + ") -> (" + pMaxY.getX() + "," + pMaxY.getY() + ")");
					return true;
				}
				y += type;
			}
		}

		return false;
	}

	private boolean CheckMoreLineY(Point p1, Point p2, int type) {
		System.out.println("check More Line y");
		// find point have p min
		Point pMinX = p1;
		Point pMaxX = p2;
		if (p1.getX() > p2.getX()) {
			pMinX = p2;
			pMaxX = p1;
		}
		// find line and x begin
		int x = pMaxX.getX() + type;
		int Col = pMinX.getY();
		int RowFinish = pMaxX.getX();
		if (type == -1) {
			RowFinish = pMinX.getX();
			x = pMinX.getX() + type;
			Col = pMaxX.getY();
		}
		if ((matrix[RowFinish][Col] == 0 || pMinX.getX() == pMaxX.getX())
				&& CheckLineY(pMinX.getX(), pMaxX.getX(), Col)) {
			while (matrix[x][pMinX.getY()] == 0 && matrix[x][pMaxX.getY()] == 0) {
				if (CheckLineX(pMinX.getY(), pMaxX.getY(), x)) {
					System.out.println("TH Y " + type);
					System.out.println("(" + pMinX.getX() + "," + pMinX.getY() + ") -> (" + x + "," + pMinX.getY()
							+ ") -> (" + x + "," + pMaxX.getY() + ") -> (" + pMaxX.getX() + "," + pMaxX.getY() + ")");
					return true;
				}
				x += type;
			}
		}
		return false;
	}

	public MyLine CheckTwoPoint(Point p1, Point p2) {
		if (matrix[p1.getX()][p1.getY()] == matrix[p2.getX()][p2.getY()]) {
			if ((p1.getX() != (p2.getX())) || (p1.getY() != p2.getY())) {
				// check line with x
				if (p1.getX() == p2.getX()) {
					System.out.println("line X");
					if (CheckLineX(p1.getY(), p2.getY(), p1.getX())) {
						return new MyLine(p1, p2);
					}
				}
				// check line with y
				if (p1.getY() == p2.getY()) {
					System.out.println("line y");
					if (CheckLineY(p1.getX(), p2.getX(), p1.getY())) {
						return new MyLine(p1, p2);
					}
				}
				// check in rectangle with x
				if (CheckRectX(p1, p2)) {
					System.out.println("Rect X");
					return new MyLine(p1, p2);
				}
				// check in rectangle with x
				if (CheckRectY(p1, p2)) {
					System.out.println("Rect Y");
					return new MyLine(p1, p2);
				}
				// check more right
				if (CheckMoreLineX(p1, p2, 1)) {
					System.out.println("more right");
					return new MyLine(p1, p2);
				}
				// check more left
				if (CheckMoreLineX(p1, p2, -1)) {
					System.out.println("more left");
					return new MyLine(p1, p2);
				}
				// check more down
				if (CheckMoreLineY(p1, p2, 1)) {
					System.out.println("more down");
					return new MyLine(p1, p2);
				}
				// check more up
				if (CheckMoreLineY(p1, p2, -1)) {
					System.out.println("more Up");
					return new MyLine(p1, p2);
				}
			}
		}
		return null;
	}
}
