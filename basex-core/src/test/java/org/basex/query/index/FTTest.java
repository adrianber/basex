package org.basex.query.index;

import org.basex.core.*;
import org.basex.core.cmd.*;
import org.basex.util.options.*;
import org.junit.Test;

/**
 * Full-text test queries.
 *
 * @author BaseX Team 2005-16, BSD License
 * @author Christian Gruen
 */
public final class FTTest extends FTData {
  /** Test all flag. */
  private static final boolean ALL = true;

  static { create(DOC); }
  static { queries = QUERIES; }

  @Test
  @Override
  public void test() {
    if(ALL) {
      // test with and without index
      for(int a = 0; a < 2; ++a) {
        set(MainOptions.FTINDEX, a == 0);
        super.test();
      }
    } else {
      // single test
      set(MainOptions.FTINDEX, true);
      set(MainOptions.STEMMING, true);
      set(MainOptions.DIACRITICS, true);
      set(MainOptions.CASESENS, true);
      super.test();
    }
  }

  @Override
  protected String details() {
    final MainOptions opts = context.options;
    final StringBuilder sb = new StringBuilder();
    sb.append(toString(opts, MainOptions.FTINDEX)).append(';');
    sb.append(toString(opts, MainOptions.STEMMING)).append(';');
    sb.append(toString(opts, MainOptions.DIACRITICS)).append(';');
    sb.append(toString(opts, MainOptions.CASESENS));
    return sb.toString();
  }

  /**
   * Returns a flag string.
   * @param opts options
   * @param option option
   * @return string
   */
  private static String toString(final Options opts, final BooleanOption option) {
    return new Set(option, opts.get(option)).toString();
  }
}
