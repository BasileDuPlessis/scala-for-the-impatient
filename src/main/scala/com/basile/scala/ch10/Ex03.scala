package com.basile.scala.ch10

/**
 * Look at the BitSet class, and make a diagram of all its superclasses and traits.
 * Ignore the type parameters (everything inside the [...] ). Then give the linearization of the traits.
 */
/*
lin(BitSet) = BitSet >> Serializable >> java.io.Serializable >> collection.BitSet >>  BitSetLike >> Sorted >>
 collection.SortedSet >> SortedSetLike >> Set >> Setlike >> Subtractable >> GenSet >> GenericSetTemplate >>
  GenSetLike >> Iterable >> IterableLike >> Equals >> Traversable >> GenIterable >> GenTraversable >>
   GenericTraversableTemplate >> HasNewBuilder >> TraversableLike >> GenTraversableLike >> Parallelizable >>
    TraversableOnce >> GenTraversableOnce >> FilterMonadic >> Any >> (a) => Boolean >> AnyRef >> AbstractSet
*/

