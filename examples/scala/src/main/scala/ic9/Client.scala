package ic9


def clearIndex1(a: ArrayWrapper[? >: Null], i: Int): Unit = {   //> Client::clearIndex1 loc=3
  a.set(i, null)
}

def clearIndex2(a: ArrayWrapper[? >: Null], i: Int): Unit = {   //> Client::clearIndex2 loc=5
  if (0 <= i && i < a.size) {
    a.set(i, null)
  }
}
