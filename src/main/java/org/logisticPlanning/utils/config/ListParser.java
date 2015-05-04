package org.logisticPlanning.utils.config;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.logisticPlanning.utils.collections.lists.ArrayListView;
import org.logisticPlanning.utils.text.TextUtils;

/**
 * A parser takes an object and translates it to a list. If the list is
 * encoded as one big string, then the string is split at occurrences of
 * {@link java.io.File#pathSeparatorChar} (';' characters in Windows, ':'
 * in Linux) and the elements are then parsed by the sub-parser.
 *
 * @param <T>
 *          the item type
 */
public final class ListParser<T> extends Parser<List<T>> {

  /** a list parser for strings */
  public static final ListParser<String> STRING_LIST_PARSER = new ListParser<>(
      StringParser.INSTANCE);

  /** a list parser for files */
  public static final ListParser<File> FILE_LIST_PARSER = new ListParser<>(
      FileParser.INSTANCE);

  /** the list item separator */
  public static final char LIST_ITEM_SEPARATOR = File.pathSeparatorChar;

  /** the item parser */
  private final Parser<T> m_items;

  /**
   * create
   *
   * @param items
   *          the item parser
   */
  public ListParser(final Parser<T> items) {
    super();

    if (items == null) {
      throw new IllegalArgumentException(//
          "Item parser must not be null."); //$NON-NLS-1$
    }
    if (Collection.class.isAssignableFrom(items.objectClass())) {
      throw new IllegalArgumentException(//
          "No collections are supported as list items, but item class was " //$NON-NLS-1$
          + items.objectClass());
    }

    this.m_items = items;
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final List<T> parse(final Object value) {
    final List<Object> objLst;
    final int size;
    final Class<T> clazz;
    final Parser<T> parser;
    final ArrayList<T> list;
    final String s;
    String temp;
    int i, j;
    Object obj;
    T t;
    boolean quit;

    // process existing lists: check if they have the correct data types
    // inside
    if (value instanceof List) {
      objLst = ((List<Object>) value);
      size = objLst.size();

      if (size <= 0) {
        return Collections.EMPTY_LIST;
      }

      clazz = this.m_items.objectClass();

      for (i = size; (--i) >= 0;) {
        obj = objLst.get(i);
        if (!(clazz.isInstance(obj))) {
          throw new _ClassError(List.class, obj.getClass(), List.class,
              clazz);
        }
      }

      if (objLst instanceof ArrayListView) {
        return ((ArrayListView<T>) objLst);
      }

      return ArrayListView.makeArrayListView(objLst.toArray((T[]) (Array
          .newInstance(clazz, size))));
    }

    // ok, we have a single string: split it
    if (value instanceof String) {
      s = ((String) value);
      list = new ArrayList<>();
      i = 0;
      parser = this.m_items;
      clazz = parser.objectClass();

      looper: for (;;) {
        j = s.indexOf(ListParser.LIST_ITEM_SEPARATOR, i);

        quit = (j < i);
        if (quit) {
          temp = TextUtils.prepare(s.substring(i));
        } else {
          temp = TextUtils.prepare(s.substring(i, j));
        }

        if (temp != null) {
          t = parser.parse(temp);
          if (t != null) {
            list.add(t);
          }
        }

        if (quit) {
          break looper;
        }
        i = (j + 1);
      }

      return ArrayListView
          .makeArrayListView(((size = list.size()) > 0) ? list
              .toArray((T[]) (Array.newInstance(clazz, size))) : null);

    }

    throw new _ClassError(value.getClass(), List.class);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final Class<List<T>> objectClass() {
    return ((Class<List<T>>) ((Object) (List.class)));
  }
}
