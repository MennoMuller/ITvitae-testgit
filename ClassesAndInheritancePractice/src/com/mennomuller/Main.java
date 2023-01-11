package com.mennomuller;

public class Main {

    public static void main(String[] args) {
	Grootouder opa = new Kind();
    Interface2 i2 = new Ouder1();
    Interface1 i1 = new Ouder2();
    Interface3 i3 = new Kind();
    opa.methode1();
    i3.methode3();
    i1.methode1();
    i2.methode2();

    }
}

abstract class Grootouder implements Interface1{}

class Ouder1 extends Grootouder implements Interface2{
    @Override
    public void methode1() {
        System.out.println("methode1,Ouder1");
    }

    @Override
    public void methode2() {
        System.out.println("methode2,Ouder1");
    }
}
class Ouder2 extends Grootouder{
    @Override
    public void methode1() {
        System.out.println("methode1,Ouder2");
    }
}

class Kind extends Ouder1 implements Interface3{
    @Override
    public void methode3() {
        System.out.println("methode3, Kind");
    }
}

interface Interface1{
    void methode1();
}

interface Interface2{
    void methode2();
}

interface Interface3{
    void methode3();
}