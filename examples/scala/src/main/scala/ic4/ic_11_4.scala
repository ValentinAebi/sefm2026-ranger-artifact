package ic4


def getThirdElement_correct(arr: Array[String]) = {    //> IC4::getThirdElement_1_correct loc=3
  arr(2)                                            //> IC4::getThirdElement_2_correct loc=3
}

def getThirdElement_buggy(arr: Array[String]) = {      //> IC4::getThirdElement_1_buggy loc=3
  arr(3)                                            //> IC4::getThirdElement_2_buggy loc=3
}
