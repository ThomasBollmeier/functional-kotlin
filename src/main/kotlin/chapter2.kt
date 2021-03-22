val <T> List<T>.tail : List<T>
    get() = drop(1)

val <T> List<T>.head : T
    get() = first()

tailrec fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean) : Boolean {

    return if (aa.size >= 2) {
        val fst = aa.head
        val snd = aa.tail.head
        if (!order(fst, snd))
            false
        else
            isSorted(aa.tail, order)
    } else {
        true
    }

}

fun <A, B, C> curry(f: (A, B) -> C) = { a:A -> { b:B -> f(a, b) } }

fun <A, B, C> uncurry(f: (A) -> (B) -> C) = {a: A, b: B -> f(a)(b) }

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B) = {a: A -> f(g(a))}


fun myAdd(a: Int, b: Int) = a + b
fun addOne(n: Int) = n + 1
fun multByTwo(n: Int) = 2 * n

fun main() {

    println(isSorted(listOf(4, 3, 2, 2, 1)) {a, b -> a >= b})

    val myCurriedAdd = curry(::myAdd)
    val f1 = compose(::multByTwo, ::addOne)
    val f2 = compose(::addOne, ::multByTwo)

    println(myAdd(1, 41))
    println(myCurriedAdd(1)(41))
    println(uncurry(myCurriedAdd)(1, 41))

    println(f1(1)) // 2*(n+1) = 2*(1+1) = 4
    println(f2(1)) // (2*n) + 1 = 2*1 + 1 = 3

}
