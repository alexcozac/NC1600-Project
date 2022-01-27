import java.util.ArrayList;

public class cpuBrainz extends Controller {

	private static int vAxis;
	private static int hAxis;
	private static int vAxis_memory;
	private static int hAxis_memory;

	private static int direction;
	// up = 0; down = 1; left = 2; right = 3;

	private static final int up = 0;
	private static final int down = 1;
	private static final int left = 2;
	private static final int right = 3;

	// Path variables show possibility of direction and takes out already tried
	// paths during a state switch (zone in on ship hits)
	private static boolean pathUp = true;
	private static boolean pathDown = true;
	private static boolean pathLeft = true;
	private static boolean pathRight = true;

	private static int vUpperAnc;
	private static int vLowerAnc;
	private static int hLeftAnc;
	private static int hRightAnc;

	private static int state = 0;

	private static ArrayList<String> memory = new ArrayList<String>();
	private static ArrayList<String> state3_memory = new ArrayList<String>();

	private static int state3_vAxis;
	private static int state3_hAxis;
	private static boolean secondShipFound;

	private static int state4_vAxis;
	private static int state4_hAxis;

	/*
	 * s0 - random cluster (if miss, stay in state)
	 * 
	 * s1 - from hit point, random line shoot (if miss, stay in state)
	 * 
	 * s2 - keep line (if, miss change to s3 and log first anchor)
	 * 
	 * s3 - reverse direction starting from first hit, if already changed direction,
	 * cluster area, ship destroyed -> s0 --- if second miss happens (log second
	 * anchor, change to s4
	 * 
	 * 
	 */

	public static int initial_vCoordinates() {
		vAxis = rand.nextInt(10);
		return vAxis;
	}

	public static int initial_hCoordinates() {
		hAxis = rand.nextInt(10);
		return hAxis;
	}

	public static int vCoordinates() {
		return vAxis;
	}

	public static int hCoordinates() {
		return hAxis;
	}

	public static int getState() {
		return state;
	}

	public static void setState(int state) {
		cpuBrainz.state = state;
	}

	public static void stateSwitch(int state, int vAxis, int hAxis, char outcome) {

		// Make the cpu less dumb by filtering already hit coordinates
		// hide "you already hit coordinates" when cpu shoots...

		// Prototype run only
		cpuBrainz.vAxis = vAxis;
		cpuBrainz.hAxis = hAxis;

		boolean sent = false;

		switch (state) {
		// cpuMAINSTATE
		// -------------------------------------------------------------------------------------
		case 0:
			pathUp = true;
			pathDown = true;
			pathLeft = true;
			pathRight = true;
			// empty anchor log

			if (outcome == 'h') {
				direction = rand.nextInt(4);
				vAxis_memory = vAxis;
				hAxis_memory = hAxis;
				cpuBrainz.state = 1;

				switch (direction) {

				case 0:
					// Checks upper limit
					if (vAxis > 0) {
						cpuBrainz.vAxis -= 1;
					} else {
						cpuBrainz.vAxis += 1;
						direction = down;
					}
					break;

				case 1:
					// Checks lower limit
					if (vAxis < 9) {
						cpuBrainz.vAxis += 1;
					} else {
						cpuBrainz.vAxis -= 1;
						direction = up;
					}
					break;

				case 2:
					// Checks left hand-side limit
					if (hAxis > 0) {
						cpuBrainz.hAxis -= 1;
					} else {
						cpuBrainz.hAxis += 1;
						direction = right;
					}
					break;

				case 3:
					// Checks right hand-side limit
					if (hAxis < 9) {
						cpuBrainz.hAxis += 1;
					} else {
						cpuBrainz.hAxis -= 1;
						direction = left;
					}
					break;
				}
			} else {
				cpuBrainz.state = 0;
			}
			break;

		// cpuMAINSTATE
		// -------------------------------------------------------------------------------------
		case 1:
			switch (direction) {

			case 0:
				if (vAxis > 0) {
					if (outcome == 'h') {
						cpuBrainz.vAxis -= 1;
						cpuBrainz.state = 2;
						pathUp = true;
						pathDown = false;
						pathLeft = false;
						pathRight = false;
					} else {
						while (sent == false) {
							direction = rand.nextInt(4);
							if ((direction == up || direction == down) && pathDown == true && vAxis_memory < 9) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								direction = down;
								pathDown = false;
								pathUp = false;
								sent = true;
							}
							if (direction == left && pathLeft == true && hAxis_memory > 0) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathLeft = false;
								sent = true;
							}
							if (direction == right && pathRight == true && hAxis_memory < 9) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathRight = false;
								sent = true;
							}
						}
					}
				}

				else {
					if (outcome == 'h') {
						cpuBrainz.vAxis += 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = true;
						pathLeft = false;
						pathRight = false;
						direction = down;
					} else {
						while (sent == false) {
							direction = rand.nextInt(2) + 2;
							if (direction == left && pathLeft == true && hAxis_memory > 0) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathLeft = false;
								sent = true;

							}
							if (direction == right && pathRight == true && hAxis_memory < 9) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathRight = false;
								sent = true;
							}
						}
					}
				}
				break;

			case 1:
				if (vAxis < 9) {
					if (outcome == 'h') {
						cpuBrainz.vAxis += 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = true;
						pathLeft = false;
						pathRight = false;
					} else {
						while (sent == false) {
							direction = rand.nextInt(4);
							if ((direction == down || direction == up) && pathUp == true && vAxis_memory > 0) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								direction = up;
								pathUp = false;
								pathDown = false;
								sent = true;
							}
							if (direction == left && pathLeft == true && hAxis_memory > 0) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathLeft = false;
								sent = true;
							}
							if (direction == right && pathRight == true && hAxis_memory < 9) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathRight = false;
								sent = true;
							}
						}
					}
				}

				else {
					if (outcome == 'h') {
						cpuBrainz.vAxis -= 1;
						cpuBrainz.state = 2;
						pathUp = true;
						pathDown = false;
						pathLeft = false;
						pathRight = false;
						direction = up;
					} else {
						while (sent == false) {
							direction = rand.nextInt(2) + 2;
							if (direction == left && pathLeft == true && hAxis_memory > 0) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathLeft = false;
								sent = true;
							}
							if (direction == right && pathRight == true && hAxis_memory < 9) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								pathRight = false;
								sent = true;
							}
						}
					}
				}
				break;

			case 2:
				if (hAxis > 0) {
					if (outcome == 'h') {
						cpuBrainz.hAxis -= 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = false;
						pathLeft = true;
						pathRight = false;
					} else {
						while (sent == false) {
							direction = rand.nextInt(4);
							if ((direction == left || direction == right) && pathRight == true && hAxis_memory < 9) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								direction = right;
								pathLeft = false;
								pathRight = false;
								sent = true;
							}
							if (direction == up && pathUp == true && vAxis_memory > 0) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathUp = false;
								sent = true;
							}
							if (direction == down && pathDown == true && vAxis_memory < 9) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathDown = false;
								sent = true;
							}
						}
					}
				} else {
					if (outcome == 'h') {
						cpuBrainz.hAxis += 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = false;
						pathLeft = false;
						pathRight = true;
						direction = right;
					} else {
						while (sent == false) {
							direction = rand.nextInt(2);
							if (direction == up && pathUp == true && vAxis_memory > 0) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathUp = false;
								sent = true;
							}
							if (direction == down && pathDown == true && vAxis_memory < 9) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathDown = false;
								sent = true;
							}
						}
					}
				}
				break;

			case 3:
				if (hAxis < 9) {
					if (outcome == 'h') {
						cpuBrainz.hAxis += 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = false;
						pathLeft = false;
						pathRight = true;
					} else {
						while (sent == false) {
							direction = rand.nextInt(4);
							if ((direction == right || direction == left) && pathLeft == true && hAxis_memory > 0) {
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
								direction = left;
								pathRight = false;
								pathLeft = false;
								sent = true;
							}
							if (direction == up && pathUp == true && vAxis_memory > 0) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathUp = false;
								sent = true;
							}
							if (direction == down && pathDown == true && vAxis_memory < 9) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathDown = false;
								sent = true;
							}
						}
					}
				} else {
					if (outcome == 'h') {
						cpuBrainz.hAxis -= 1;
						cpuBrainz.state = 2;
						pathUp = false;
						pathDown = false;
						pathLeft = true;
						pathRight = false;
						direction = left;
					} else {
						while (sent == false) {
							direction = rand.nextInt(2);
							if (direction == up && pathUp == true & vAxis_memory > 0) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathUp = false;
								sent = true;
							}
							if (direction == down && pathDown == true && vAxis_memory < 9) {
								cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
								cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
								pathDown = false;
								sent = true;
							}
						}
					}

				}
				break;
			}
			break;

		// cpuMAINSTATE
		// -------------------------------------------------------------------------------------
		case 2:

			switch (direction) {

			case 0:
				if (vAxis > 0) {
					if (outcome == 'h') {
						cpuBrainz.vAxis -= 1;
						pathUp = true;
					} else {
						if (vAxis_memory < 9) {
							vUpperAnc = vAxis;
							cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
							cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
							pathUp = false;
							pathDown = true;
							direction = down;
							cpuBrainz.state = 3;
							// add first anchor point
						}
					}
				} else {
					if (vAxis_memory < 9) {
						vUpperAnc = vAxis;
						cpuBrainz.vAxis = cpuBrainz.vAxis_memory + 1;
						cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
						pathUp = false;
						pathDown = true;
						direction = down;
						cpuBrainz.state = 3;
					}
				}
				break;

			case 1:
				if (vAxis < 9) {
					if (outcome == 'h') {
						cpuBrainz.vAxis += 1;
						pathDown = true;
					} else {
						if (vAxis_memory > 0) {
							vLowerAnc = vAxis;
							cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
							cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
							pathUp = true;
							pathDown = false;
							direction = up;
							cpuBrainz.state = 3;
						}
					}
				} else {
					if (vAxis_memory > 0) {
						vLowerAnc = vAxis;
						cpuBrainz.vAxis = cpuBrainz.vAxis_memory - 1;
						cpuBrainz.hAxis = cpuBrainz.hAxis_memory;
						pathUp = true;
						pathDown = false;
						direction = up;
						cpuBrainz.state = 3;
					}
				}
				break;

			case 2:
				if (hAxis > 0) {
					if (outcome == 'h') {
						cpuBrainz.hAxis -= 1;
						pathLeft = true;
					} else {
						if (hAxis_memory < 9) {
							hLeftAnc = hAxis;
							cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
							cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
							pathLeft = false;
							pathRight = true;
							direction = right;
							cpuBrainz.state = 3;
						}
					}
				} else {
					if (hAxis_memory < 9) {
						hLeftAnc = hAxis;
						cpuBrainz.hAxis = cpuBrainz.hAxis_memory + 1;
						cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
						pathLeft = false;
						pathRight = true;
						direction = right;
						cpuBrainz.state = 3;
					}
				}
				break;

			case 3:
				if (hAxis < 9) {
					if (outcome == 'h') {
						cpuBrainz.hAxis += 1;
						pathRight = true;
					} else {
						if (hAxis_memory > 0) {
							hRightAnc = hAxis;
							cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
							cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
							pathLeft = true;
							pathRight = false;
							direction = left;
							cpuBrainz.state = 3;
						}
					}
				} else {
					if (hAxis_memory > 0) {
						hRightAnc = hAxis;
						cpuBrainz.hAxis = cpuBrainz.hAxis_memory - 1;
						cpuBrainz.vAxis = cpuBrainz.vAxis_memory;
						pathLeft = true;
						pathRight = false;
						direction = left;
						cpuBrainz.state = 3;
					}
				}
				break;
			}
			break;

		// Until here, 50 possibilities

		// cpuMAINSTATE
		// -------------------------------------------------------------------------------------
		case 3:

			int p;

			switch (direction) {

			case 0:

				if (vAxis > 0 && outcome == 'h') {
					cpuBrainz.vAxis -= 1;
				} else if (vAxis >= 0) {
					vUpperAnc = vAxis;
					state3_hAxis = hAxis;
					p = vLowerAnc - vUpperAnc;
					for (int i = 1; i < p; i++) {
						// already hit line
						vAxis = vLowerAnc - i;
						for (int j = -1; j < 3;) {
							hAxis = state3_hAxis + j;
							state3_memory.add(Integer.toString(vAxis) + Integer.toString(hAxis));
							j += 2;
						}
					}
					if (vUpperAnc == 0 && outcome == 'h') {
						state3_memory.add(Integer.toString(0) + Integer.toString(state3_hAxis - 1));
						state3_memory.add(Integer.toString(0) + Integer.toString(state3_hAxis + 1));
					}
					int randIndex = rand.nextInt(state3_memory.size());
					vAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(0));
					hAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(1));
					state3_memory.remove(randIndex);
					if (hAxis < state3_hAxis && hAxis >= 0) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = left;
					}
					if (hAxis > state3_hAxis && hAxis <= 9) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = right;
					}
					if (hAxis == -1) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis + 2;
						cpuBrainz.state = 4;
						direction = right;
					}
					if (hAxis == 11) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis - 2;
						cpuBrainz.state = 4;
						direction = left;
					}

				}

				break;

			case 1:

				if (vAxis < 9 && outcome == 'h') {
					cpuBrainz.vAxis += 1;
				} else if (vAxis <= 9) {
					vLowerAnc = vAxis;
					state3_hAxis = hAxis;
					p = vLowerAnc - vUpperAnc;
					for (int i = 1; i < p; i++) {
						// already hit line
						vAxis = vLowerAnc - i;
						for (int j = -1; j < 3;) {
							hAxis = state3_hAxis + j;
							state3_memory.add(Integer.toString(vAxis) + Integer.toString(hAxis));
							j += 2;
						}
					}
					if (vLowerAnc == 9 && outcome == 'h') {
						state3_memory.add(Integer.toString(9) + Integer.toString(state3_hAxis - 1));
						state3_memory.add(Integer.toString(9) + Integer.toString(state3_hAxis + 1));
					}
					int randIndex = rand.nextInt(state3_memory.size());
					vAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(0));
					hAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(1));
					state3_memory.remove(randIndex);
					if (hAxis < state3_hAxis && hAxis >= 0) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = left;
					}
					if (hAxis > state3_hAxis && hAxis <= 9) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = right;
					}
					if (hAxis == -1) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis + 2;
						cpuBrainz.state = 4;
						direction = right;
					}
					if (hAxis == 11) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis - 2;
						cpuBrainz.state = 4;
						direction = left;
					}
				}

				break;

			case 2:

				if (hAxis > 0 && outcome == 'h') {
					cpuBrainz.hAxis -= 1;
				} else if (hAxis >= 0) {
					hLeftAnc = hAxis;
					state3_vAxis = vAxis;
					p = hRightAnc - hLeftAnc;
					for (int i = 1; i < p; i++) {
						// already hit line
						hAxis = hRightAnc - i;
						for (int j = -1; j < 3;) {
							vAxis = state3_vAxis + j;
							state3_memory.add(Integer.toString(vAxis) + Integer.toString(hAxis));
							j += 2;
						}
					}
					if (hLeftAnc == 0 && outcome == 'h') {
						state3_memory.add(Integer.toString(state3_vAxis - 1) + Integer.toString(0));
						state3_memory.add(Integer.toString(state3_vAxis + 1) + Integer.toString(0));
					}
					int randIndex = rand.nextInt(state3_memory.size());
					vAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(0));
					hAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(1));
					state3_memory.remove(randIndex);
					if (vAxis < state3_vAxis && vAxis >= 0) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = up;
					}
					if (vAxis > state3_vAxis && vAxis <= 9) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = down;
					}
					if (vAxis == -1) {
						cpuBrainz.vAxis = vAxis + 2;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = down;
					}
					if (vAxis == 11) {
						cpuBrainz.vAxis = vAxis - 2;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = up;
					}

				}
				break;

			case 3:

				if (hAxis < 9 && outcome == 'h') {
					cpuBrainz.hAxis += 1;
				} else if (hAxis <= 9) {
					hRightAnc = hAxis;
					state3_vAxis = vAxis;
					p = hRightAnc - hLeftAnc;
					for (int i = 1; i < p; i++) {
						// already hit line
						hAxis = hRightAnc - i;
						for (int j = -1; j < 3;) {
							vAxis = state3_vAxis + j;
							state3_memory.add(Integer.toString(vAxis) + Integer.toString(hAxis));
							j += 2;
						}
					}
					if (hRightAnc == 9 && outcome == 'h') {
						state3_memory.add(Integer.toString(state3_vAxis - 1) + Integer.toString(0));
						state3_memory.add(Integer.toString(state3_vAxis + 1) + Integer.toString(0));
					}
					int randIndex = rand.nextInt(state3_memory.size());
					vAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(0));
					hAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(1));
					state3_memory.remove(randIndex);
					if (vAxis < state3_vAxis && vAxis >= 0) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = up;
					}
					if (vAxis > state3_vAxis && vAxis <= 9) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = down;
					}
					if (vAxis == -1) {
						cpuBrainz.vAxis = vAxis + 2;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = down;
					}
					if (vAxis == 11) {
						cpuBrainz.vAxis = vAxis - 2;
						cpuBrainz.hAxis = hAxis;
						cpuBrainz.state = 4;
						direction = up;
					}

				}
				break;
			}

			break;

		// cpuMAINSTATE
		// -------------------------------------------------------------------------------------
		case 4:
			// add condition in s2 with ship found
			switch (direction) {

			case 0:

				if (vAxis > 0 && outcome == 'h') {
					cpuBrainz.vAxis -= 1;
					secondShipFound = true;
					direction = up;
					cpuBrainz.state = 2;
				} else if (vAxis >= 0) {
					int randIndex = rand.nextInt(state3_memory.size());
					vAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(0));
					hAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(1));
					state3_memory.remove(randIndex);
					if (vAxis < state3_vAxis && vAxis >= 0) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						direction = up;
					}
					if (vAxis > state3_vAxis && vAxis <= 9) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						direction = down;
					}
					if (vAxis == -1) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis + 2;
						direction = down;
					}
					if (vAxis == 11) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis - 2;
						direction = up;
					}
				}

				break;

			case 1:

				if (vAxis < 9 && outcome == 'h') {
					cpuBrainz.vAxis += 1;
					secondShipFound = true;
					direction = down;
					cpuBrainz.state = 2;
				} else if (vAxis <= 9) {
					int randIndex = rand.nextInt(state3_memory.size());
					vAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(0));
					hAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(1));
					state3_memory.remove(randIndex);
					if (vAxis < state3_vAxis && vAxis >= 0) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						direction = up;
					}
					if (vAxis > state3_vAxis && vAxis <= 9) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						direction = down;
					}
					if (vAxis == -1) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis + 2;
						direction = down;
					}
					if (vAxis == 11) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis - 2;
						direction = up;
					}
				}

				break;

			case 2:

				if (hAxis > 0 && outcome == 'h') {
					cpuBrainz.hAxis -= 1;
					secondShipFound = true;
					direction = left;
					cpuBrainz.state = 2;
				} else if (hAxis >= 0) {
					int randIndex = rand.nextInt(state3_memory.size());
					vAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(0));
					hAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(1));
					state3_memory.remove(randIndex);
					if (hAxis < state3_hAxis && hAxis >= 0) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						direction = left;
					}
					if (hAxis > state3_hAxis && hAxis <= 9) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						direction = right;
					}
					if (hAxis == -1) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis + 2;
						direction = right;
					}
					if (hAxis == 11) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis - 2;
						direction = left;
					}
				}

				break;

			case 3:

				if (hAxis > 9 && outcome == 'h') {

					cpuBrainz.hAxis += 1;
					secondShipFound = true;
					direction = right;
					cpuBrainz.state = 2;
				} else if (hAxis >= 9) {
					int randIndex = rand.nextInt(state3_memory.size());
					vAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(0));
					hAxis = Character.getNumericValue(state3_memory.get(randIndex).charAt(1));
					state3_memory.remove(randIndex);
					if (hAxis < state3_hAxis && hAxis >= 0) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						direction = left;
					}
					if (hAxis > state3_hAxis && hAxis <= 9) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis;
						direction = right;
					}
					if (hAxis == -1) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis + 2;
						direction = right;
					}
					if (hAxis == 11) {
						cpuBrainz.vAxis = vAxis;
						cpuBrainz.hAxis = hAxis - 2;
						direction = left;
					}
				}

				break;
			}

			break;
		}

	}

}
