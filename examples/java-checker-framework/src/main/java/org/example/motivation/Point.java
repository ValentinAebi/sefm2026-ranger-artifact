package org.example.motivation;

public record Point<I extends Integer, J extends Integer>(I x, J y) {}      //> Point::constructor p=(2,0,0/0) r=none loc=1
