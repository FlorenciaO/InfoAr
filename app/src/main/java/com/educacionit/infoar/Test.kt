package com.educacionit.infoar

open class Test {
    private var variable1 = ""
    private var variable2 = ""

    protected fun metodo1() {

    }
    public fun metodo2() {

    }
    private fun metodo3() {
        metodo1()
        metodo2()
        variable1
        variable2
    }
}

class Test2: Test() {
    private fun metodo4() {
        metodo1()
        metodo2()
        //metodo3()
    }
}

class Test3 {
    private fun metodo5() {
        val instancia = Test()
        instancia.metodo2()
        val instancia2 = Test2()
        instancia2.metodo2()
    }
}