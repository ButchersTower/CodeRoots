package defaultStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DefaultStuff {

	// This works with any type of array.
	String[] appendStringAR(String[] st, String appendage) {
		String[] temp = new String[st.length + 1];
		for (int a = 0; a < st.length; a++) {
			temp[a] = st[a];
		}
		temp[temp.length - 1] = appendage;
		return temp;
	}

	// I don't think this is useful unless I can change my variable to (Object)s
	Object[] appendAR(Object[] st, Object appendage) {
		Object[] temp = new Object[st.length + 1];
		for (int a = 0; a < st.length; a++) {
			temp[a] = st[a];
		}
		temp[temp.length - 1] = appendage;
		return temp;
	}

	// This removed the [numToRemove] variable from an AR and compresses
	int[] shortenAR(int[] in, int numToRemove) {
		int[] temp = new int[in.length - 1];
		boolean reachedYet = false;
		for (int a = 0; a < in.length; a++) {
			System.out.println("a: " + a);
			if (a == numToRemove) {
				reachedYet = true;
				a++;
				System.out.println("newA: " + a);
			}
			if (a < in.length) {
				if (reachedYet) {
					temp[a - 1] = in[a];
				} else {
					temp[a] = in[a];
				}
			}
		}
		return temp;
	}

	void testForLoop() {
		// the limiter in a for loop can be updated mid loop and it updates fine
		// (a, d): (0, 6) d after: 6
		// (a, d): (1, 6) d after: 6
		// (a, d): (2, 6) d after: 5
		// (a, d): (3, 5) d after: 5
		// (a, d): (4, 5) d after: 5
		int d = 6;
		for (int a = 0; a < d; a++) {
			System.out.print("(a, d): (" + a + ", " + d + ")");
			if (a == 2) {
				d--;
			}
			System.out.println("\td after: " + d);
		}
	}

	public void txtBox(Graphics g, int wi, int hi, int font, int xl, int yl,
			ArrayList<String> st, boolean drwBkg) {
		int[] lastLetterLoc = new int[2];
		int SRSBUISNESS = 0;
		if (drwBkg) {
			g.fillRect(xl, yl, wi, hi);
		} else {
		}
		int lineDrawnOn = 0;
		for (int stl = 0; stl < st.size(); stl++) {
			String[] words = st.get(stl).split("[ ]");

			int twi = 0, thi = 0;
			if (font == 0) {
				twi = 12;
				thi = 16;
			}
			if (font == 1) {
				twi = 6;
				thi = 8;
			}

			int lettersPerRow = (wi - (wi % twi)) / twi;

			int numLines = 0;
			int ghe = 0;
			while (ghe < words.length) {
				ghe = repeat(ghe, 0, words, lettersPerRow);
				numLines++;
			}
			// System.out.println("(" + a + ", " + b + ", " + c + ", " + d +
			// ")");
			int[] figures = new int[numLines];
			for (int i = 0; i < figures.length; i++) {
				if (i == 0) {
					figures[0] = repeat(0, 0, words, lettersPerRow);
				} else {
					figures[i] = repeat(figures[i - 1], 0, words, lettersPerRow);
				}
			}
			for (int i = 0; i < figures.length; i++) {
				for (int ii = 0; ii < i; ii++) {
					figures[i] -= figures[ii];
				}
			}
			int lettersDrawnOfThisTextBox = 0;
			int drawPlace = xl;
			int drawnWords = 0;
			// If figures.length = 1 and if figures[ig] == null then save the
			// letLoc as the left side of the text box.
			if (figures.length == 1) {
				// System.out.println("figures[0]: " + figures[0]);
			}
			// System.out.println("figures.l: " + figures.length);
			// this goes through all of the lines
			for (int ig = 0; ig < figures.length; ig++) {
				// System.out.println("figures[" + ig + "]: " + figures[ig]);
				// this ques up the words of a line
				for (int ih = 0; ih < figures[ig]; ih++) {
					// this draws individual words
					if (words[drawnWords].length() == 0) {
						// add letLoc.
						// System.out.println("ZERO LINE: ig[" + ig + "]   ih["
						// + ih + "]");
					}
					int[] j = converter(words[drawnWords]);
					for (int i = 0; i < j.length; i++) {
						// System.out.println("(" + a + ", " + b + ", " + c +
						// ", " + 0 + ")" + "lettersDrawn: " +
						// lettersDrawnOfThisTextBox);
						g.drawImage(txtAr[j[i]], drawPlace, yl + (ig * thi)
								+ (lineDrawnOn * thi), null);
						lettersDrawnOfThisTextBox++;
						SRSBUISNESS++;
						drawPlace += twi;
					}
					int countLetter = 0;
					for (int w = 0; w < words.length; w++) {
						countLetter += words[w].length() + 1;
					}
					countLetter--;
					if (SRSBUISNESS < countLetter) {
						SRSBUISNESS++;
						lettersDrawnOfThisTextBox++;
					}
					lastLetterLoc[0] = drawPlace;
					lastLetterLoc[1] = (yl + (ig * thi) + (lineDrawnOn * thi));
					drawPlace += twi;
					drawnWords++;
				}
				drawPlace = xl;
			}
			lineDrawnOn += figures.length;
			String removeSpaces = st.get(st.size() - 1);
			String opposite = new StringBuilder(removeSpaces).reverse()
					.toString();
			int endSpaces = 0;
			aLoop: for (char cee : opposite.toCharArray()) {
				if (cee == ' ') {
					endSpaces++;
				} else {
					break aLoop;
				}
			}
		}
	}

	int repeat(int a, int b, String[] words, int lettersPerRow) {
		if (a >= words.length) {
			return a;
		}
		if (b + words[a].length() <= lettersPerRow) {
			if (b + words[a].length() + 1 <= lettersPerRow) {
				b += words[a].length() + 1;
			} else {
				b += words[a].length();
			}
			a++;
			return repeat(a, b, words, lettersPerRow);
		} else {
			return a;
		}

	}

	public static int[] converter(String st) {
		int a = st.length();
		int[] nw = new int[a];

		for (int b = 0; b < a; b++) {
			if (st.charAt(b) == 'a') {
				nw[b] = 26;
			} else if (st.charAt(b) == 'A') {
				nw[b] = 0;
			} else if (st.charAt(b) == 'b') {
				nw[b] = 27;
			} else if (st.charAt(b) == 'B') {
				nw[b] = 1;
			} else if (st.charAt(b) == 'c') {
				nw[b] = 28;
			} else if (st.charAt(b) == 'C') {
				nw[b] = 2;
			} else if (st.charAt(b) == 'd') {
				nw[b] = 29;
			} else if (st.charAt(b) == 'D') {
				nw[b] = 3;
			} else if (st.charAt(b) == 'e') {
				nw[b] = 30;
			} else if (st.charAt(b) == 'E') {
				nw[b] = 4;
			} else if (st.charAt(b) == 'f') {
				nw[b] = 31;
			} else if (st.charAt(b) == 'F') {
				nw[b] = 5;
			} else if (st.charAt(b) == 'g') {
				nw[b] = 32;
			} else if (st.charAt(b) == 'G') {
				nw[b] = 6;
			} else if (st.charAt(b) == 'h') {
				nw[b] = 33;
			} else if (st.charAt(b) == 'H') {
				nw[b] = 7;
			} else if (st.charAt(b) == 'i') {
				nw[b] = 34;
			} else if (st.charAt(b) == 'I') {
				nw[b] = 8;
			} else if (st.charAt(b) == 'j') {
				nw[b] = 35;
			} else if (st.charAt(b) == 'J') {
				nw[b] = 9;
			} else if (st.charAt(b) == 'k') {
				nw[b] = 36;
			} else if (st.charAt(b) == 'K') {
				nw[b] = 10;
			} else if (st.charAt(b) == 'l') {
				nw[b] = 37;
			} else if (st.charAt(b) == 'L') {
				nw[b] = 11;
			} else if (st.charAt(b) == 'm') {
				nw[b] = 38;
			} else if (st.charAt(b) == 'M') {
				nw[b] = 12;
			} else if (st.charAt(b) == 'n') {
				nw[b] = 39;
			} else if (st.charAt(b) == 'N') {
				nw[b] = 13;
			} else if (st.charAt(b) == 'o') {
				nw[b] = 40;
			} else if (st.charAt(b) == 'O') {
				nw[b] = 14;
			} else if (st.charAt(b) == 'p') {
				nw[b] = 41;
			} else if (st.charAt(b) == 'P') {
				nw[b] = 15;
			} else if (st.charAt(b) == 'q') {
				nw[b] = 42;
			} else if (st.charAt(b) == 'Q') {
				nw[b] = 16;
			} else if (st.charAt(b) == 'r') {
				nw[b] = 43;
			} else if (st.charAt(b) == 'R') {
				nw[b] = 17;
			} else if (st.charAt(b) == 's') {
				nw[b] = 44;
			} else if (st.charAt(b) == 'S') {
				nw[b] = 18;
			} else if (st.charAt(b) == 't') {
				nw[b] = 45;
			} else if (st.charAt(b) == 'T') {
				nw[b] = 19;
			} else if (st.charAt(b) == 'u') {
				nw[b] = 46;
			} else if (st.charAt(b) == 'U') {
				nw[b] = 20;
			} else if (st.charAt(b) == 'v') {
				nw[b] = 47;
			} else if (st.charAt(b) == 'V') {
				nw[b] = 21;
			} else if (st.charAt(b) == 'w') {
				nw[b] = 48;
			} else if (st.charAt(b) == 'W') {
				nw[b] = 22;
			} else if (st.charAt(b) == 'x') {
				nw[b] = 49;
			} else if (st.charAt(b) == 'X') {
				nw[b] = 23;
			} else if (st.charAt(b) == 'y') {
				nw[b] = 50;
			} else if (st.charAt(b) == 'Y') {
				nw[b] = 24;
			} else if (st.charAt(b) == 'z') {
				nw[b] = 51;
			} else if (st.charAt(b) == 'Z') {
				nw[b] = 25;
			} else if (st.charAt(b) == ' ') {
				nw[b] = 52;
			} else if (st.charAt(b) == '0') {
				nw[b] = 53;
			} else if (st.charAt(b) == '1') {
				nw[b] = 54;
			} else if (st.charAt(b) == '2') {
				nw[b] = 55;
			} else if (st.charAt(b) == '3') {
				nw[b] = 56;
			} else if (st.charAt(b) == '4') {
				nw[b] = 57;
			} else if (st.charAt(b) == '5') {
				nw[b] = 58;
			} else if (st.charAt(b) == '6') {
				nw[b] = 59;
			} else if (st.charAt(b) == '7') {
				nw[b] = 60;
			} else if (st.charAt(b) == '8') {
				nw[b] = 61;
			} else if (st.charAt(b) == '9') {
				nw[b] = 62;
			} else if (st.charAt(b) == '/') {
				nw[b] = 63;
			} else if (st.charAt(b) == '?') {
				nw[b] = 64;
			} else if (st.charAt(b) == '¿') {
				nw[b] = 65;
			} else if (st.charAt(b) == '(') {
				nw[b] = 66;
			} else if (st.charAt(b) == ')') {
				nw[b] = 67;
			} else if (st.charAt(b) == 'é') {
				nw[b] = 4;
			} else if (st.charAt(b) == 'á') {
				nw[b] = 0;
			} else if (st.charAt(b) == 'ó') {
				nw[b] = 14;
			} else if (st.charAt(b) == 'í') {
				nw[b] = 8;
			} else if (st.charAt(b) == '.') {
				nw[b] = 68;
			} else if (st.charAt(b) == ',') {
				nw[b] = 69;
			} else if (st.charAt(b) == '\'') {
				nw[b] = 70;
			}

		}
		return nw;
	}

	// if you declare s.cTM as a double you wont have to access it twice,
	// but it will have a higher probability to lag since it has to draw
	// between setting the double and the beginning of next scroll wheel.
	@Override
	public void mouseWheelMoved(MouseWheelEvent me) {
		// Scrolling gains speed in which if the dT is under 3 for 20 ticks it
		// ignores more draws having less lag making it "feel" like it goes
		// faster.
		int notches = me.getWheelRotation();
		if (System.currentTimeMillis() - lastDraw > 20) {
			drawAll();
			drwGm();
			lastDraw = System.currentTimeMillis();
		}
		if (notches < 0) {
			if (scrollY <= -3) {
				scrollY += 3;
				// drawAll();
				// drwGm();
			}
		} else {
			if (canScrollDown) {
				scrollY -= 3;
			}
			// drawAll();
			// drwGm();
		}
		lastScroll = System.currentTimeMillis();
	}

	// Method that

	float dotShapeThea(float[] t, float[] w) {
		float dotTW = (t[0] * w[0]) + (t[1] * w[1]);
		float ta = (float) Math.hypot(t[0], t[1]);
		float wa = (float) Math.hypot(w[0], w[1]);
		float thea = (float) Math.acos(dotTW / (ta * wa));
		return thea;
	}

	float[] getA1(float[] a, float[] b) {
		// |b|
		float ba = (float) Math.hypot(b[0], b[1]);
		float[] bhat = { b[0] / ba, b[1] / ba };
		float ascalar = ((a[0] * b[0]) + (a[1] * b[1])) / ba;
		float[] a1 = { (ascalar * bhat[0]), (ascalar * bhat[1]) };
		return a1;
	}

	float[] getA2(float[] a, float[] b) {
		// |b|
		float ba = (float) Math.hypot(b[0], b[1]);
		float[] bhat = { b[0] / ba, b[1] / ba };
		float ascalar = ((a[0] * b[0]) + (a[1] * b[1])) / ba;
		float[] a1 = { (ascalar * bhat[0]), (ascalar * bhat[1]) };
		float[] a2 = { a[0] - a1[0], a[1] - a1[1] };
		return a2;
	}

	float dotProduct(float[] a, float[] b) {
		float dp = (a[0] * b[0]) + (a[1] * b[1]);
		return dp;
	}

	float scalarA(float[] a, float[] b) {
		float ba = (float) Math.hypot(b[0], b[1]);
		float ascalar = ((a[0] * b[0]) + (a[1] * b[1])) / ba;
		return ascalar;
	}

}
