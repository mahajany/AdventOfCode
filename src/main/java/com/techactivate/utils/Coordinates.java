package com.techactivate.utils;

public class Coordinates {
	public int x;
	public int y;

	public boolean equals(Coordinates other) {
		if (this.x == other.x &&  this.y == other.y)
			return true;
		return false;
	}

	public int distance(Coordinates other) {
		return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
	}

	public static int distance(Coordinates a, Coordinates b) {
		return Math.abs((a.x - b.x + a.y - b.y));
	}

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinates() {
		this.x = 0;
		this.y = 0;
	}

	public void add(Coordinates other) {
		this.x += other.x;
		this.y += other.y;
	}

	public Coordinates addNReturn(Coordinates other) {
		return new Coordinates(this.x + other.x, this.y + other.y);
	}

	public String toString() {
		return "<" + this.x + " , " + this.y + ">";
	}
}
