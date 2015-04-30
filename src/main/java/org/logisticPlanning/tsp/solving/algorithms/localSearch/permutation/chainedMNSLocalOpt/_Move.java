package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.chainedMNSLocalOpt;

/**
 * <p>
 * The internal move class holds all information necessary for describing
 * and carrying out a search move.
 * </p>
 */
final class _Move {

  /** the reverse move type */
  private static final int TYPE_REVERSE = 0;
  /** the rotate move type */
  private static final int TYPE_ROT_LEFT = 1;
  /** the rotate right type */
  private static final int TYPE_ROT_RIGHT = 2;

  /** the swap move type */
  private static final int TYPE_SWAP = 3;

  /** the move's names */
  private static final String[] NAMES = new String[] {
      "reverse(", "rot_left(", "rot_right(", //$NON-NLS-1$//$NON-NLS-3$//$NON-NLS-2$
      "swap(" //$NON-NLS-1$
  };

  /** the move's type */
  int m_type;

  /** the first index */
  int m_i;

  /** the second index */
  int m_j;

  /** the delta */
  int m_delta;

  /** the first additional distance */
  private int m_a;

  /** the second additional distance */
  private int m_b;

  /** the third additional distance */
  private int m_c;

  /** the fourth additional distance */
  private int m_d;

  /** the next move in the list */
  _Move m_next;

  /** create the move */
  _Move() {
    super();
  }

  /**
   * Initialize this move as a &quot;rotate left&quot; move. You can find a
   * similar move described in
   * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Left}
   * .
   * 
   * @param i
   *          the i
   * @param j
   *          the j
   * @param delta
   *          the delta
   * @param a
   *          the distance between the current elements at index
   *          {@code i-1} and {@code i+1}
   * @param b
   *          the distance between the current elements at index {@code i}
   *          and {@code j}
   * @param c
   *          the distance between the current elements at index {@code i}
   *          and {@code j+1}
   */
  final void init_rotate_left(final int i, final int j, final int delta,
      final int a, final int b, final int c) {
    this.m_type = _Move.TYPE_ROT_LEFT;
    this.m_i = i;
    this.m_j = j;
    this.m_delta = delta;
    this.m_a = a;
    this.m_b = b;
    this.m_c = c;
  }

  /**
   * Initialize this move as a &quot;rotate right&quot; move. You can find
   * a similar move described in
   * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Rotate_Right}
   * .
   * 
   * @param i
   *          the i
   * @param j
   *          the j
   * @param delta
   *          the delta
   * @param a
   *          the distance between the current elements at index
   *          {@code i-1} and {@code i+1}
   * @param b
   *          the distance between the current elements at index {@code i}
   *          and {@code j}
   * @param c
   *          the distance between the current elements at index {@code i}
   *          and {@code j+1}
   */
  final void init_rotate_right(final int i, final int j, final int delta,
      final int a, final int b, final int c) {
    this.m_type = _Move.TYPE_ROT_RIGHT;
    this.m_i = i;
    this.m_j = j;
    this.m_delta = delta;
    this.m_a = a;
    this.m_b = b;
    this.m_c = c;
  }

  /**
   * Initialize this move as a &quot;swap&quot; move. You can find a
   * similar move described in
   * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Swap}
   * 
   * @param i
   *          the i
   * @param j
   *          the j
   * @param delta
   *          the delta
   * @param D_im1_j
   *          the distance between the current elements at index
   *          {@code i-1} and {@code j}
   * @param D_ip1_j
   *          the distance between the current elements at index
   *          {@code i+1} and {@code j}
   * @param D_i_jm1
   *          the distance between the current elements at index {@code i}
   *          and {@code j-1}
   * @param D_i_jp1
   *          the distance between the current elements at index {@code i}
   *          and {@code j+1}
   */
  final void init_swap(final int i, final int j, final int delta,
      final int D_im1_j, final int D_ip1_j, final int D_i_jm1,
      final int D_i_jp1) {
    this.m_type = _Move.TYPE_SWAP;
    this.m_i = i;
    this.m_j = j;
    this.m_delta = delta;
    this.m_a = D_i_jm1;
    this.m_b = D_i_jp1;
    this.m_c = D_im1_j;
    this.m_d = D_ip1_j;
  }

  /**
   * Initialize this move as a &quot;reverse&quot; move. You can find a
   * similar move described in
   * {@link org.logisticPlanning.tsp.solving.operators.permutation.update.PermutationUpdate_Reverse}
   * 
   * @param i
   *          the i
   * @param j
   *          the j
   * @param delta
   *          the delta
   * @param D_im1_j
   *          the distance between the current elements at index
   *          {@code i-1} and {@code j}
   * @param D_i_jp1
   *          the distance between the current elements at index {@code i}
   *          and {@code j+1}
   */
  final void init_reverse(final int i, final int j, final int delta,
      final int D_im1_j, final int D_i_jp1) {
    this.m_type = _Move.TYPE_REVERSE;
    this.m_i = i;
    this.m_j = j;
    this.m_delta = delta;
    this.m_b = D_im1_j;
    this.m_a = D_i_jp1;
  }

  /**
   * <p>
   * This method has one &quot;external&quot; and one &quot;internal&quot;
   * purpose. For the outside, it applies this move and updates the
   * candidate solution and distance list.
   * </p>
   * <p>
   * Internally, it changes the meaning of the member variables
   * {@link #m_a} to {@link #m_d}. Until before this method, they hold
   * additional distance information that we use to update the distance
   * list. Now we change them to hold the indices that would cause
   * collisions with this move.
   * </p>
   * <p>
   * After this method was called, the queue of moves must be updated by
   * calling the {@link #checkDeleteMove(_Move, int)} methods of all
   * enqeued moves.
   * </p>
   * 
   * @param n
   *          the number of nodes in the tsp instance
   * @param sol
   *          the solution array
   * @param dist
   *          the distance array
   */
  final void apply(final int n, final int[] sol, final int[] dist) {
    final int i, j, im1;
    int t, m, ii, jj;

    // perform the move

    i = this.m_i;
    j = this.m_j;
    im1 = (((i + n) - 1) % n);

    // Depending on the type of our move, we have different things to do
    switch (this.m_type) {

      case TYPE_REVERSE: {
        // In a reverse move, we turn around the tour segment in between
        // (and
        // including) indices i and j.
        for (ii = i, jj = j; ii < jj; ii++, jj--) {
          t = sol[ii];
          sol[ii] = sol[jj];
          sol[jj] = t;
        }

        for (ii = i, jj = (j - 1); ii < jj; ii++, jj--) {
          t = dist[ii];
          dist[ii] = dist[jj];
          dist[jj] = t;
        }

        dist[im1] = this.m_b;
        dist[j] = this.m_a;

        break;
      }

      case TYPE_ROT_LEFT: {
        // In a rotate left move, we rotate the segment in between (and
        // including) i and j one step to the left. The element at index i
        // would be shifted outside of the range, but since we do a
        // _rotate_
        // it
        // re-appears on index j. The element at index j has moved to j-1.
        t = sol[i];
        m = (j - i);
        System.arraycopy(sol, i + 1, sol, i, m);
        sol[j] = t;

        System.arraycopy(dist, i + 1, dist, i, m - 1);

        dist[im1] = this.m_a;
        dist[j - 1] = this.m_b;
        dist[j] = this.m_c;

        this.m_c = (i + 1); // The rotate left move has one more forbidden
        // coordinate: i+1
        break;
      }

      case TYPE_ROT_RIGHT: {
        // In a rotate right move, we rotate the segment in between (and
        // including) i and j one step to the right. The element at index j
        // would be shifted outside of the range, but since we do a
        // _rotate_
        // it
        // re-appears on index i. The element at index i has moved to i+1.
        t = sol[j];
        m = (j - i);
        System.arraycopy(sol, i, sol, i + 1, m);
        sol[i] = t;

        System.arraycopy(dist, i, dist, i + 1, m - 1);

        dist[im1] = this.m_c;
        dist[i] = this.m_b;
        dist[j] = this.m_a;

        this.m_c = (j - 1);// The rotate left move has one more forbidden
        // coordinate: j-1
        break;
      }

      default: {// TYPE_SWAP
        // In a swap move, we swap the elements at indices i and j.

        t = sol[i];
        sol[i] = sol[j];
        sol[j] = t;

        dist[im1] = this.m_c;
        dist[i] = this.m_d;

        t = (j - 1);
        dist[t] = this.m_a;
        dist[j] = this.m_b;

        this.m_c = t; // the swap move does not allow touching the
        this.m_d = (i + 1); // coordinates j-1 and i+1
      }
    }

    this.m_a = im1; // any move forbids touching the coordinates i-1
    this.m_b = ((j + 1) % n); // and j+1
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return (((((((_Move.NAMES[this.m_type]) + //
    this.m_i) + ',') + this.m_j) + ')') + '=') + this.m_delta);
  }

  /**
   * <p>
   * Check whether a given move should be deleted if {@code move} was
   * performed, or whether it can still be used. In the latter case, this
   * function will also perform any necessary amendments.
   * </p>
   * <p>
   * Any move that collides with a performed move, i.e., whose proposed
   * tour change has become impossible since [@code move} was performed,
   * will return {@code true} here, which indicates that it should be
   * deleted. Moves that do not intersect or may otherwise be adapted to
   * still be valid will instead return {@code false}, which indicates that
   * they should remain in the move queue and can still be used.
   * </p>
   * <p>
   * Notice that this method requires that
   * {@link #apply(int, int[], int[])} was called first for {@code move},
   * as it accesses the internal variables of {@code move} holding
   * collision information.
   * </p>
   * 
   * @param move
   *          the move
   * @param n
   *          the number of nodes
   * @return {@code true} if the move must be deleted
   */
  final boolean checkDeleteMove(final _Move move, final int n) {
    final int i, j, im1, jp1, type, moveType;
    int t;

    // First, we check if there are any collisions.
    // Each move leads to changes of the distances between a few, certain
    // nodes.
    // Thus, each move defines a set of nodes for which at least one of the
    // two
    // distances related to them change.
    // If the two sets of such nodes for two moves overlap, a collision
    // occurs.
    // If this move has a collision with another move, it cannot be
    // performed.
    // So here we check this.

    i = this.m_i;
    if (_Move.checkCollision(i, move)) {
      // Our i coordinate must not hit any forbidden coordinate of the
      // performed move, since the element at this coordinate would
      // definitely
      // change if we do our move.
      return true;
    }

    im1 = (((i - 1) + n) % n);
    if (_Move.checkCollision(im1, move)) {
      // Since our move will always change the element at its i
      // coordinate, the
      // distance between this node and the (definitely unchanged) node
      // before
      // will change, too
      return true;
    }

    j = this.m_j;
    if (_Move.checkCollision(j, move)) {
      // Our j coordinate must not hit any forbidden coordinate of the
      // performed move, since the element at this coordinate would
      // definitely
      // change if we do our move.
      return true;
    }

    jp1 = ((j + 1) % n);
    if (_Move.checkCollision(jp1, move)) {
      // Since our move will always change the element at its j
      // coordinate, the
      // distance between this node and the (definitely unchanged) node
      // thereafter will change, too
      return true;
    }

    type = this.m_type;

    if ((type == _Move.TYPE_ROT_LEFT) || (type == _Move.TYPE_SWAP)) {
      // For left rotation or a swap move, also block coordinate i+1
      if (_Move.checkCollision((i + 1), move)) {
        return true;
      }
    }

    if ((type == _Move.TYPE_ROT_RIGHT) || (type == _Move.TYPE_SWAP)) {
      // For right rotation or a swap move, also block coordinate k-1
      if (_Move.checkCollision((j - 1), move)) {
        return true;
      }
    }

    // OK, there was no collision. Now we need to check whether we can
    // transform the current move if it can be transformed such that it can
    // still be carried out.

    moveType = move.m_type;
    if (moveType == _Move.TYPE_SWAP) {
      // If the move was a swap move and there was no collision, then
      // everything is OK and we still can carry it out.
      return false;
    }

    if ((i >= move.m_i) && (i <= move.m_j)) {
      // Our i coordinate lies within the window of the performed move.

      // Depending on the type of the performed move, we need to take
      // different
      // actions.
      switch (moveType) {

        case TYPE_REVERSE: {
          // The performed move was a reversal
          if (type == _Move.TYPE_SWAP) {
            // If we are a swap move and our i coordinate lies in the
            // range of
            // the performed reversal, we need to reverse it as well and
            // swap
            // its two corresponding distance changes
            t = this.m_c;
            this.m_c = this.m_d;
            this.m_d = t;

            this.m_i = (move.m_j - (i - move.m_i));// reverse coordinate
            break;
          }

          // Our move is not swap, so it is either reverse, rol, or rot
          if (j >= move.m_j) {
            // This means that only fully contained intersecting moves
            // are
            // allowed, i.e., if our i coordinate is in the move range,
            // our j
            // coordinate must be so too. Otherwise, we cannot perform
            // our move
            // as the nodes next to its i-end have changed.
            return true;
          }

          // Our move is fully contained in the performed reversal, so we
          // reverse its coordinates: Notice reversed i is larger than
          // reversed
          // j and vice versa, so the two switch places.
          this.m_j = (move.m_j - (i - move.m_i));
          this.m_i = (move.m_j - (j - move.m_i));

          // Perform actions depending on the type of our move
          switch (type) {
            case TYPE_REVERSE: {
              // For reverse moves, the distances at the ends change
              t = this.m_a;
              this.m_a = this.m_b;
              this.m_b = t;
              return false;// We are done here, the move can be preserved.
            }
            case TYPE_ROT_LEFT: {
              // rol moves become ror moves
              this.m_type = _Move.TYPE_ROT_RIGHT;
              return false;// We are done here, the move can be preserved.
            }
            default /* case TYPE_ROT_RIGHT */: {
              // and ror moves become rol moves
              this.m_type = _Move.TYPE_ROT_LEFT;
              return false; // We are done here, the move can be
              // preserved.
            }
          }
        }

        case TYPE_ROT_LEFT: {
          // Our i coordinate intersects with the i coordinate of the
          // performed
          // rotate left move, but does not produce a collision. We
          // therefore
          // simply shift it one step to the left too, regardless what
          // move we
          // are planning to do.
          this.m_i = im1;
          break;
        }
        default /* case TYPE_ROT_RIGHT */: {
          // Our i coordinate intersects with the i coordinate of the
          // performed
          // rotate right move, but does not produce a collision. We
          // therefore
          // simply shift it one step to the right too, regardless what
          // move we
          // are planning to do.
          this.m_i = (i + 1);
          break;
        }
      }
    }
    // We are finished checking our move's i coordinate versus the range of
    // the
    // performed move. We now need to check our move's j coordinate.

    if ((j >= move.m_i) && (j <= move.m_j)) {
      // Our move's j coordinate intersects with the move's range, but
      // does not
      // produce a collision.
      switch (moveType) {

        case TYPE_REVERSE: {
          // The performed move was a reversal.

          if (type == _Move.TYPE_SWAP) {
            // If our move is a swap move, we have to reverse its j
            // coordinate
            // and swap the associated distances.
            t = this.m_a;
            this.m_a = this.m_b;
            this.m_b = t;
            this.m_j = (move.m_j - (j - move.m_i));
            break;
          }

          // We have an overlap of either reverse, rol, ror with a
          // performed
          // reverse -- this is not allowed. When checking our i
          // coordinate, we
          // already have signaled "delete" (true) for any non-swap move
          // intersecting with the performed reversal "from the left" AND
          // signaled false / performed the necessary changes for any
          // fully
          // included non-swap move. Thus, coming here means that our move
          // intersects with the performed reversal "from the right",
          // i.e., its
          // j coordinate is in the reversed range but its i coordinate is
          // not.
          // This is not allowed, as the nodes neighboring the j
          // coordinate
          // have changed. Thus we signal "delete".
          return true;
        }

        case TYPE_ROT_LEFT: {
          // If the performed move was a rotate-left, we, too, shift the j
          // coordinate one step to the left
          this.m_j = (j - 1);
          break;
        }

        default/* case TYPE_ROT_RIGHT */: {
          // If the performed move was a rotate-right, we, too, shift the
          // j
          // coordinate one step to the right.
          this.m_j = jp1;
          break;
        }
      }
    }

    // If we come here, the j coordinate did not intersect with the
    // performed
    // move or any necessary amendments of the i and j coordinates have
    // been
    // performed successful. We can signal "keep" (false) - if the move is
    // not
    // redundant. However, we are lucky: Because of the forbidden nodes of
    // each
    // move, the two indices cannot come too close to each other by
    // augmenting
    // a move. Thus, a move that was not redundant cannot become redundant.

    return false;
  }

  /**
   * Check for a collision: this method checks whether a given value
   * {@code v} occurs in the forbidden values stored in the record
   * {@code move} (according to {@link #apply(int, int[], int[])}). It will
   * return {@code true} if such a collision was found and {@code false}
   * otherwise.
   * 
   * @param v
   *          the value
   * @param move
   *          the move
   * @return {@code true} on collision
   */
  @SuppressWarnings({ "incomplete-switch", "fallthrough" })
  private static final boolean checkCollision(final int v, final _Move move) {
    switch (move.m_type) {
      case TYPE_SWAP: {
        // swap moves use all four variables
        if (v == move.m_d) {
          return true;
        }
        // fall through to rotate
      }
      case TYPE_ROT_LEFT:
      case TYPE_ROT_RIGHT: {
        // rotate moves three variables
        if (v == move.m_c) {
          return true;
        }
        // fall through
      }
    }

    // all moves use at least four variables
    return ((v == move.m_i) || (v == move.m_j) || (v == move.m_a) || (v == move.m_b));
  }
}
