
package com.spielwiese.enumeration;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;

public abstract class BaseEnum<K> {

  private final String name;

  public final String name() {
    return name;
  }

  private final int ordinal;

  public final int ordinal() {
    return ordinal;
  }

  protected BaseEnum(String name, int ordinal) {
    this.name = name;
    this.ordinal = ordinal;
  }

  public String toString() {
    return name;
  }

  public final boolean equals(Object other) {
    return this == other;
  }

  public final int hashCode() {
    return super.hashCode();
  }

  protected final Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }

  //  public final int compareTo(E o) {
  //    Enum other = (Enum) o;
  //    Enum self = this;
  //    if (self.getClass() != other.getClass() && // optimization
  //      self.getDeclaringClass() != other.getDeclaringClass())
  //      throw new ClassCastException();
  //    return self.ordinal - other.ordinal;
  //  }

  public final Class<K> getDeclaringClass() {
    Class clazz = getClass();
    Class zuper = clazz.getSuperclass();
    return (zuper == Enum.class) ? clazz : zuper;
  }

  //  public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
  //    T result = enumType.enumConstantDirectory().get(name);
  //    if (result != null)
  //      return result;
  //    if (name == null)
  //      throw new NullPointerException("Name is null");
  //    throw new IllegalArgumentException("No enum const " + enumType + "." + name);
  //  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    throw new InvalidObjectException("can't deserialize enum");
  }

  private void readObjectNoData() throws ObjectStreamException {
    throw new InvalidObjectException("can't deserialize enum");
  }

  protected final void finalize() {
  }
}
